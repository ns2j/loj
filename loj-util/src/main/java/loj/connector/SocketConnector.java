package loj.connector;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.XComponentContext;

/**
 * A Bootstrap Connector which uses a socket to connect to an OOo server.
 */
public class SocketConnector extends ConnectorBase {

    /**
     * Constructs a bootstrap socket connector which uses the folder of the OOo installation containing the soffice executable.
     * 
     * @param   oooExecFolder   The folder of the OOo installation containing the soffice executable
     */
    public SocketConnector(String oooExecFolder) {

        super(oooExecFolder);
    }

    /**
     * Constructs a bootstrap socket connector which connects to the specified
     * OOo server.
     * 
     * @param   server   The OOo server
     */
    public SocketConnector(LoServer server) {

        super(server);
    }

    /**
     * Connects to an OOo server using a default socket and returns a
     * component context for using the connection to the OOo server.
     * 
     * @return             The component context
     * @throws IOException 
     * @throws ConnectionSetupException 
     * @throws MalformedURLException 
     * @throws IllegalArgumentException 
     */
    public XComponentContext connect() throws BootstrapException, IllegalArgumentException, MalformedURLException, ConnectionSetupException, IOException {

        // create random pipe name
        String host = "localhost";
        int    port = 8100;

        return connect(host,port);
    }

    /**
     * Connects to an OOo server using the specified host and port for the
     * socket and returns a component context for using the connection to the
     * OOo server.
     * 
     * @param   host   The host
     * @param   port   The port
     * @return         The component context
     * @throws IOException 
     * @throws ConnectionSetupException 
     * @throws MalformedURLException 
     * @throws IllegalArgumentException 
     */
    public XComponentContext connect(String host, int port) throws BootstrapException, IllegalArgumentException, MalformedURLException, ConnectionSetupException, IOException {

        // host and port
        String hostAndPort = "host="+host+",port="+port;

        // accept option
        String oooAcceptOption = "--accept=socket,"+hostAndPort+";urp;";

        // connection string
        String unoConnectString = "uno:socket,"+hostAndPort+";urp;StarOffice.ComponentContext";

        return connect(oooAcceptOption, unoConnectString);
    }
}