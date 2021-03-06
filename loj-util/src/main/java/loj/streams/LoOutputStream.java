package loj.streams;

import java.io.ByteArrayOutputStream;

import com.sun.star.io.BufferSizeExceededException;
import com.sun.star.io.NotConnectedException;
import com.sun.star.io.XOutputStream;

/**
 * <a href="http://www.oooforum.org/forum/viewtopic.phtml?t=13205">OOInputStream from the thread <b>OOo-Java: Using XInputStream...</b></a>
 */
public class LoOutputStream extends ByteArrayOutputStream implements XOutputStream {

    public LoOutputStream() {
	super(32768);
    }

    // Implement XOutputStream

    @Override
    //public void writeBytes(byte[] values) throws com.sun.star.io.IOException {
    public void writeBytes(byte[] values) {
	try {
	    this.write(values);
	}
	catch (java.io.IOException e) {
	    e.printStackTrace();
	//    throw(new com.sun.star.io.IOException(e.getMessage()));
	}
    }

    @Override
    public void closeOutput() throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
	try {
	    super.flush();
	    super.close();
	}
	catch (java.io.IOException e) {
	    throw(new com.sun.star.io.IOException(e.getMessage()));
	}
    }

    @Override
    public void flush() {
	try {
	    super.flush();
	}
	catch (java.io.IOException e) {
	}
    }
}
