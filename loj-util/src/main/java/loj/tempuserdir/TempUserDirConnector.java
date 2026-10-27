package loj.tempuserdir;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.XComponentContext;

import loj.connector.PipeConnector;

/**
 * A connector that connects to a {@link TempUserDirServer} instance via a named pipe.
 * <p>
 * The pipe name is derived from the unique identifier (key) of the server.
 */
public class TempUserDirConnector extends PipeConnector {

    private static final Logger LOGGER =
            System.getLogger(TempUserDirConnector.class.getName());

    /** Unique key identifying the server instance and pipe. */
    private final String key;

    /**
     * Constructs a {@code TempUserDirConnector} for the given {@link TempUserDirServer}.
     *
     * @param server the server instance with an isolated user directory
     */
    public TempUserDirConnector(TempUserDirServer server) {
        super(server);
        key = server.getKey();
    }

    /**
     * Returns the unique key identifying the server instance and pipe.
     *
     * @return the unique key
     */
    public String getKey() {
        return key;
    }

    /**
     * Connects to the server through a named pipe determined by the server's unique key.
     *
     * @return the remote {@link XComponentContext}
     * @throws IllegalArgumentException if the connection arguments are invalid
     * @throws MalformedURLException if the connection URL cannot be resolved
     * @throws ConnectionSetupException if setting up the connection fails
     * @throws BootstrapException if bootstrapping the LibreOffice instance fails
     * @throws IOException if an I/O error occurs during connection
     */
    @Override
    public XComponentContext connect() throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        LOGGER.log(Level.DEBUG, "connect with key: {0}", key);
        return connect("uno" + key);
    }
}