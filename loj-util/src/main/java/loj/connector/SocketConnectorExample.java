package loj.connector;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.List;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.NoConnectException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * Example demonstrating how to use {@link SocketConnector} to connect to LibreOffice
 * and convert a document to PDF.
 */
public class SocketConnectorExample {

    private static final Logger LOGGER =
            System.getLogger(SocketConnectorExample.class.getName());
    
    //private static final String OOO_EXEC_FOLDER = "C:/Program Files/LibreOffice/program/";
    private static final String OOO_EXEC_FOLDER = "/usr/lib64/libreoffice/program/";

    //private static final String TEMPLATE_FOLDER = "C:/Temp/";
    private static final String TEMPLATE_FOLDER = "/tmp/";
    private static final String TEXT_DOCUMENT_NAME = "Text";

    private static final String FILE_URL_PREFIX = "file:///";
    private static final String TEXT_DOCUMENT_EXTENSION = ".odt";
    private static final String PDF_DOCUMENT_EXTENSION = ".pdf";

    /**
     * Converts an OOo text document (.odt) to a PDF file using a
     * BootstrapConnector.
     * 
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        
        String textDocumentName = (args.length > 0)? args[0]: TEXT_DOCUMENT_NAME;

        String loadUrl=FILE_URL_PREFIX+TEMPLATE_FOLDER+textDocumentName+TEXT_DOCUMENT_EXTENSION;
        String storeUrl;

        try {
            storeUrl=FILE_URL_PREFIX+TEMPLATE_FOLDER+textDocumentName+"SC"+PDF_DOCUMENT_EXTENSION;
            convertWithStaticConnector(loadUrl, storeUrl);

            storeUrl=FILE_URL_PREFIX+TEMPLATE_FOLDER+textDocumentName+"C"+PDF_DOCUMENT_EXTENSION;
            convertWithConnector(loadUrl, storeUrl);
        }
        catch (NoConnectException e) {
            LOGGER.log(Level.ERROR, "OOo is not responding", e);
        }
        catch (Exception e) {
            LOGGER.log(Level.ERROR, "Error during document conversion", e);
        }
        finally {
            System.exit(0);
        }
    }

    /**
     * Converts a document using a default {@link SocketConnector}.
     *
     * @param loadUrl the source document URL
     * @param storeUrl the destination document URL
     * @throws Exception if conversion fails
     */
    private static void convertWithStaticConnector(String loadUrl, String storeUrl) throws Exception, IllegalArgumentException, IOException, BootstrapException {
        LoServer server = new LoServer(OOO_EXEC_FOLDER);
        SocketConnector socketConnector = new SocketConnector(server);
        // Connect to OOo
        XComponentContext remoteContext = socketConnector.connect();

        // Convert text document to PDF
        convert(loadUrl, storeUrl, remoteContext);
    }

    /**
     * Converts a document using a customized {@link SocketConnector} with extra options.
     *
     * @param loadUrl the source document URL
     * @param storeUrl the destination document URL
     * @throws Exception if conversion fails
     */
    private static void convertWithConnector(String loadUrl, String storeUrl) throws Exception, IllegalArgumentException, IOException, BootstrapException {

        // Create OOo server with additional -nofirststartwizard option
        List oooOptions = LoServer.getDefaultOptions();
        oooOptions.add("--nofirststartwizard");
        LoServer oooServer = new LoServer(OOO_EXEC_FOLDER, oooOptions);

        // Connect to OOo
        SocketConnector socketConnector = new SocketConnector(oooServer);
        XComponentContext remoteContext = socketConnector.connect();

        // Convert text document to PDF
        convert(loadUrl, storeUrl, remoteContext);

        // Disconnect and terminate OOo server
        socketConnector.disconnect();
    }

    /**
     * Loads a document from loadUrl and stores it to storeUrl using writer_pdf_Export filter.
     *
     * @param loadUrl the source document URL
     * @param storeUrl the destination document URL
     * @param remoteContext the remote component context
     * @throws IllegalArgumentException if arguments are invalid
     * @throws IOException if an I/O error occurs
     * @throws Exception if UNO conversion fails
     */
    protected static void convert(String loadUrl, String storeUrl, XComponentContext remoteContext) throws IllegalArgumentException, IOException, Exception {

        XComponentLoader xcomponentloader = getComponentLoader(remoteContext);

        Object objectDocumentToStore = xcomponentloader.loadComponentFromURL(loadUrl, "_blank", 0, new PropertyValue[0]);

        PropertyValue[] conversionProperties = new PropertyValue[1];
        conversionProperties[0] = new PropertyValue();
        conversionProperties[0].Name = "FilterName";
        conversionProperties[0].Value = "writer_pdf_Export";

        XStorable xstorable = UnoRuntime.queryInterface(XStorable.class,objectDocumentToStore);
        xstorable.storeToURL(storeUrl, conversionProperties);
    }

    /**
     * Gets the component loader from the remote context.
     *
     * @param remoteContext the component context
     * @return the {@link XComponentLoader}
     * @throws Exception if desktop instance cannot be instantiated
     */
    private static XComponentLoader getComponentLoader(XComponentContext remoteContext) throws Exception {

        XMultiComponentFactory remoteServiceManager = remoteContext.getServiceManager();
        Object desktop = remoteServiceManager.createInstanceWithContext("com.sun.star.frame.Desktop", remoteContext);
        XComponentLoader xcomponentloader = UnoRuntime.queryInterface(XComponentLoader.class,desktop);

        return xcomponentloader;
    }
}
