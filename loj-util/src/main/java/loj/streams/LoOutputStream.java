package loj.streams;

import java.io.ByteArrayOutputStream;

import com.sun.star.io.BufferSizeExceededException;
import com.sun.star.io.NotConnectedException;
import com.sun.star.io.XOutputStream;

/**
 * An in-memory output stream bridging Java's {@link ByteArrayOutputStream} with LibreOffice's
 * UNO {@link XOutputStream} interface.
 */
public class LoOutputStream extends ByteArrayOutputStream implements XOutputStream {

    /**
     * Constructs a {@code LoOutputStream} with an initial buffer size of 32 KB.
     */
    public LoOutputStream() {
        super(32768);
    }

    // Implement XOutputStream

    /**
     * Writes the given array of bytes to the stream.
     *
     * @param values the byte values to write
     */
    @Override
    public void writeBytes(byte[] values) {
        try {
            this.write(values);
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flushes and closes the output stream.
     *
     * @throws NotConnectedException if the stream is not connected
     * @throws BufferSizeExceededException if buffer capacity is exceeded
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
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

    /**
     * Flushes any buffered bytes to the stream.
     */
    @Override
    public void flush() {
        try {
            super.flush();
        }
        catch (java.io.IOException e) {
        }
    }
}
