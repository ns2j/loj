package loj.my;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.star.beans.PropertyValue;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.XComponentContext;

import loj.streams.LoInputStream;
import loproxy.frame.P_Desktop;

/**
 * Wrapper for the LibreOffice Desktop service.
 */
public class Desktop extends P_Desktop {
    final private static Logger logger = LoggerFactory.getLogger(Desktop.class);
    
    /**
     * Constructs a Desktop instance from an XComponentContext.
     *
     * @param context the component context
     */
    public Desktop(XComponentContext context) {
        super(com.sun.star.frame.theDesktop.get(context));        
    }

    /**
     * Loads a document from a resource file.
     *
     * @param filename the resource filename
     * @return the loaded document
     * @throws IllegalArgumentException if arguments are illegal
     * @throws com.sun.star.io.IOException if a UNO IO error occurs
     * @throws IOException if a Java IO error occurs
     */
    public Doc getDocFromResource(String filename) throws IllegalArgumentException, com.sun.star.io.IOException, IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        byte[] byteBuffer = new byte[4096];
        int byteBufferLength = 0;
        while ((byteBufferLength = is.read(byteBuffer)) > 0) {
            bytes.write(byteBuffer, 0, byteBufferLength);
        }
        is.close();

        LoInputStream loIs= new LoInputStream(bytes.toByteArray());
        var pvs = Util.getPropertyValueArray(Map.of(
                "InputStream", loIs,
                "Hidden", Boolean.TRUE));
        return new Doc(loadComponentFromURL("private:stream", "_blank", 0, pvs));
       // var pvs = Util.getPropertyValueArray(Map.of(
        //        "Hidden", Boolean.TRUE));
        //return new Doc(loadComponentFromURL("" + new File("test.ods").toURI(), "_blank", 0, pvs));
    }
    
    /**
     * Creates a new spreadsheet document.
     *
     * @return the new document
     * @throws IllegalArgumentException if arguments are illegal
     * @throws IOException if a Java IO error occurs
     * @throws com.sun.star.io.IOException if a UNO IO error occurs
     */
    public Doc getNewDoc() throws IllegalArgumentException, IOException, com.sun.star.io.IOException  {
        return new Doc(loadComponentFromURL("private:factory/scalc", "_blank", 0, new PropertyValue[0]));
    }
}
