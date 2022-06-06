package loj.tempuserdir;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.uno.XComponentContext;

import loj.connector.PipeConnector;

/**
 * A bootstrap connector which uses a named pipe to connect to an OOo server.
 * 
 * Very helpful in getting the named pipe connection working has been the posts
 * of mnasato in the thread "Correct FilterName to open RTF from bytestream?" at
 * http://www.oooforum.org/forum/viewtopic.phtml?t=40263&highlight=named+pipe.
 */
public class TempUserDirConnector extends PipeConnector {
    public String key;
    public TempUserDirConnector(TempUserDirServer server) {
        super(server);
        key = server.key;
    }

    @Override
    public XComponentContext connect() throws IllegalArgumentException, MalformedURLException, ConnectionSetupException, BootstrapException, IOException {
        System.out.println("key: " + key);
        return connect("uno" + key);
    }

}