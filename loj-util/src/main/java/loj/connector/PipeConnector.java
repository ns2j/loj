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
    public PipeConnector(LoServer server) {
        super(server);
    }

    public XComponentContext connect() throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        String pipeName = "uno" + Long.toString((new Random()).nextLong() & 0x7fffffffffffffffL);
        return connect(pipeName);
    }
    
    public XComponentContext connect(String pipeName) throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        String oooAcceptOption = "--accept=pipe,name=" + pipeName + ";urp;";

        String unoConnectString = "uno:pipe,name=" + pipeName + ";urp;StarOffice.ComponentContext";

        return connect(oooAcceptOption, unoConnectString);
    }

}