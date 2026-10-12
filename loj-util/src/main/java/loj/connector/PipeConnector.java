package loj.connector;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.XComponentContext;

/**
 * A bootstrap connector which uses a named pipe to connect to an OOo server.
 * 
 * Very helpful in getting the named pipe connection working has been the posts
 * of mnasato in the thread "Correct FilterName to open RTF from bytestream?" at
 * http://www.oooforum.org/forum/viewtopic.phtml?t=40263&highlight=named+pipe.
 */
public class PipeConnector extends ConnectorBase {

    /**
     * Constructs a {@code PipeConnector} targeting the given {@link LoServer}.
     *
     * @param server the server instance to connect to
     */
    public PipeConnector(LoServer server) {
        super(server);
    }

    /**
     * Connects to the server using a randomly generated named pipe.
     *
     * @return the remote {@link XComponentContext}
     * @throws IllegalArgumentException if arguments are invalid
     * @throws MalformedURLException if URL cannot be formed
     * @throws ConnectionSetupException if setting up connection fails
     * @throws BootstrapException if bootstrapping fails
     * @throws IOException if an I/O error occurs
     */
    public XComponentContext connect() throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        String pipeName = "uno" + Long.toString((new Random()).nextLong() & 0x7fffffffffffffffL);
        return connect(pipeName);
    }
    
    /**
     * Connects to the server using the specified pipe name.
     *
     * @param pipeName the name of the pipe to connect with
     * @return the remote {@link XComponentContext}
     * @throws IllegalArgumentException if arguments are invalid
     * @throws MalformedURLException if URL cannot be formed
     * @throws ConnectionSetupException if setting up connection fails
     * @throws BootstrapException if bootstrapping fails
     * @throws IOException if an I/O error occurs
     */
    public XComponentContext connect(String pipeName) throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        String oooAcceptOption = "--accept=pipe,name=" + pipeName + ";urp;";

        String unoConnectString = "uno:pipe,name=" + pipeName + ";urp;StarOffice.ComponentContext";

        return connect(oooAcceptOption, unoConnectString);
    }

}