package loj.streams;

import java.io.ByteArrayInputStream;

import com.sun.star.io.BufferSizeExceededException;
import com.sun.star.io.NotConnectedException;
import com.sun.star.io.XInputStream;
import com.sun.star.io.XSeekable;

/**
 * An in-memory input stream bridging Java's {@link ByteArrayInputStream} with LibreOffice's
 * UNO {@link XInputStream} and {@link XSeekable} interfaces.
 */
public class LoInputStream extends ByteArrayInputStream implements XInputStream, XSeekable {

    /**
     * Creates a new {@code LoInputStream} from the specified byte array.
     *
     * @param buf the input buffer of bytes
     */
    public LoInputStream(byte[] buf) {
        super(buf);
    }

    // Implement XInputStream

    /**
     * Reads up to {@code bufferSize} bytes from the stream into the provided buffer.
     *
     * @param buffer an array containing the destination byte array at index 0
     * @param bufferSize the maximum number of bytes to read
     * @return the number of bytes actually read
     * @throws NotConnectedException if the stream is not connected
     * @throws BufferSizeExceededException if the requested buffer size exceeds allowable limit
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public int readBytes(byte[][] buffer, int bufferSize) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
        int numberOfReadBytes;
        try {
            byte[] bytes = new byte[bufferSize];
            numberOfReadBytes = super.read(bytes);
            if(numberOfReadBytes > 0) {
                if(numberOfReadBytes < bufferSize) {
                    byte[] smallerBuffer = new byte[numberOfReadBytes];
                    System.arraycopy(bytes, 0, smallerBuffer, 0, numberOfReadBytes);
                    bytes = smallerBuffer;
                }
            }
            else {
                bytes = new byte[0];
                numberOfReadBytes = 0;
            }

            buffer[0]=bytes;
            return numberOfReadBytes;
        }
        catch (java.io.IOException e) {
            throw new com.sun.star.io.IOException(e.getMessage(),this);
        }
    }

    /**
     * Reads available bytes from the stream. Equivalent to {@link #readBytes(byte[][], int)}.
     *
     * @param buffer an array containing the destination byte array at index 0
     * @param bufferSize the maximum number of bytes to read
     * @return the number of bytes read
     * @throws NotConnectedException if the stream is not connected
     * @throws BufferSizeExceededException if the buffer size is exceeded
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public int readSomeBytes(byte[][] buffer, int bufferSize) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
        return readBytes(buffer, bufferSize);
    }

    /**
     * Skips the specified number of bytes from the input stream.
     *
     * @param skipLength the number of bytes to skip
     * @throws NotConnectedException if the stream is not connected
     * @throws BufferSizeExceededException if the skip buffer size is exceeded
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public void skipBytes(int skipLength) throws NotConnectedException, BufferSizeExceededException, com.sun.star.io.IOException {
        skip(skipLength);
    }

    /**
     * Closes the input stream and releases associated resources.
     *
     * @throws NotConnectedException if the stream is not connected
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public void closeInput() throws NotConnectedException, com.sun.star.io.IOException {
        try {
            close();
        }
        catch (java.io.IOException e) {
            throw new com.sun.star.io.IOException(e.getMessage(), this);
        }
    }


    //
    // Implement XSeekable
    //

    /**
     * Returns the total length of the stream in bytes.
     *
     * @return the total length of the stream
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public long getLength() throws com.sun.star.io.IOException {
        return count;
    }

    /**
     * Returns the current read position in the stream.
     *
     * @return the current position in bytes
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public long getPosition() throws com.sun.star.io.IOException {
        return pos;
    }

    /**
     * Sets the read position in the stream to the specified byte offset.
     *
     * @param position the new position in bytes
     * @throws IllegalArgumentException if the position is invalid
     * @throws com.sun.star.io.IOException if an I/O error occurs
     */
    @Override
    public void seek(long position) throws IllegalArgumentException, com.sun.star.io.IOException {
        pos = (int) position;
    }
}
