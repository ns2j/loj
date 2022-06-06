package loj.streams;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.sun.star.beans.PropertyValue;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.XCloseable;

//import ooo.connector.BootstrapSocketConnector;

public class LoStreamConverter {
    private XComponentContext xComponentContext;

    public LoStreamConverter(XComponentContext xComponentContext) {
	this.xComponentContext = xComponentContext;
    }

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

	conversionProperties[0].Name = "OutputStream";
	conversionProperties[0].Value = output;
	conversionProperties[1].Name = "FilterName";
	conversionProperties[1].Value = filterName;

	XStorable xstorable = UnoRuntime.queryInterface(XStorable.class,document);
	xstorable.storeToURL("private:stream", conversionProperties);

	XCloseable xclosable = UnoRuntime.queryInterface(XCloseable.class,document);
	xclosable.close(true);
    }

    public static void main(String[] args) {
	//String oooExecutableFolder = "c:/program files/openoffice.org 2.3/program/";
	//String inputFilename = "c:/temp/text.odt";
	//String outputFilename = "c:/temp/text.pdf";
	String inputFilename = "loprac.xls";
	String outputFilename = "loprac.pdf";

	try {
	    // Connect to OOo server
	    //XComponentContext xComponentContext = BootstrapSocketConnector.bootstrap(oooExecutableFolder);
	    XComponentContext xComponentContext = Bootstrap.bootstrap();
	    LoStreamConverter converter = new LoStreamConverter(xComponentContext);

	    // Create OOoInputStream
	    InputStream inputFile = new BufferedInputStream(new FileInputStream(inputFilename));
	    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    byte[] byteBuffer = new byte[4096];
	    int byteBufferLength = 0;
	    while ((byteBufferLength = inputFile.read(byteBuffer)) > 0) {
		bytes.write(byteBuffer,0,byteBufferLength);
	    }
	    inputFile.close();
	    LoInputStream inputStream = new LoInputStream(bytes.toByteArray());

	    // Create OOoOutputStream
	    LoOutputStream outputStream = new LoOutputStream();

	    // Convert document to PDF
	    converter.convert(inputStream, outputStream, "writer_pdf_Export");

	    // Save OOoOutputStream
	    FileOutputStream outputFile = new FileOutputStream(outputFilename);
	    outputFile.write(outputStream.toByteArray());
	    outputFile.close();
	} catch (BootstrapException e) {
	    System.err.println("Connection not available");
	    e.printStackTrace();
	} catch (IOException e) {
	    System.err.println("File error");
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.exit(0);
    }
}
