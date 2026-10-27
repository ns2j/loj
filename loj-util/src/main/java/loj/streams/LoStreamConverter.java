package loj.streams;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XCloseable;

/**
 * Converts documents entirely in memory using streams via the LibreOffice UNO API.
 */
public class LoStreamConverter {

    private XComponentContext xComponentContext;

    /**
     * Constructs a {@code LoStreamConverter} using the provided component context.
     *
     * @param xComponentContext the remote or local component context
     */
    public LoStreamConverter(XComponentContext xComponentContext) {
        this.xComponentContext = xComponentContext;
    }

    /**
     * Converts a document from the input stream to the output stream using the specified filter.
     *
     * @param input the input stream containing document data
     * @param output the output stream to receive converted data
     * @param filterName the LibreOffice export filter name (e.g., "writer_pdf_Export", "calc_pdf_Export")
     * @throws Exception if a LibreOffice UNO error occurs during conversion
     */
    public void convert(LoInputStream input, LoOutputStream output, String filterName) throws Exception {
        XMultiComponentFactory xMultiComponentFactory = xComponentContext.getServiceManager();
        Object desktopService = xMultiComponentFactory.createInstanceWithContext("com.sun.star.frame.Desktop", xComponentContext);
        XComponentLoader xComponentLoader = UnoRuntime.queryInterface(XComponentLoader.class, desktopService);

        PropertyValue[] conversionProperties = new PropertyValue[2];
        conversionProperties[0] = new PropertyValue();
        conversionProperties[1] = new PropertyValue();

        conversionProperties[0].Name = "InputStream";
        conversionProperties[0].Value = input;
        conversionProperties[1].Name = "Hidden";
        conversionProperties[1].Value = Boolean.TRUE;

        XComponent document = xComponentLoader.loadComponentFromURL("private:stream", "_blank", 0, conversionProperties);
        if (document == null) {
            throw new Exception("Failed to load document from stream");
        }

        Throwable primaryFailure = null;
        try {
            conversionProperties[0].Name = "OutputStream";
            conversionProperties[0].Value = output;
            conversionProperties[1].Name = "FilterName";
            conversionProperties[1].Value = filterName;

            XStorable xstorable = UnoRuntime.queryInterface(XStorable.class, document);
            if (xstorable == null) {
                throw new Exception("Document does not support XStorable");
            }
            xstorable.storeToURL("private:stream", conversionProperties);
        } catch (Exception | RuntimeException | Error e) {
            primaryFailure = e;
            throw e;
        } finally {
            try {
                XCloseable xclosable = UnoRuntime.queryInterface(XCloseable.class, document);
                if (xclosable != null) {
                    xclosable.close(true);
                } else {
                    document.dispose();
                }
            } catch (Exception | RuntimeException | Error closeFailure) {
                if (primaryFailure != null) {
                    primaryFailure.addSuppressed(closeFailure);
                } else {
                    throw closeFailure;
                }
            }
        }
    }
}
