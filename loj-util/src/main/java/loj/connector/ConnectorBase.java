package loj.connector;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sun.star.bridge.UnoUrlResolver;
import com.sun.star.bridge.XUnoUrlResolver;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.ConnectionSetupException;
import com.sun.star.connection.NoConnectException;
import com.sun.star.frame.XDesktop;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;

/**
 * A bootstrap connector which establishes a connection to an lo server.
 * 
 * Most of the source code in this class has been taken from the Java class
 * "Bootstrap.java" (Revision: 1.15) from the UDK projekt (Uno Software Develop-
 * ment Kit) from OpenOffice.org (http://udk.openoffice.org/). The source code
 * is available for example through a browser based online version control
 * access at http://udk.openoffice.org/source/browse/udk/. The Java class
 * "Bootstrap.java" is there available at
 * http://udk.openoffice.org/source/browse/udk/javaunohelper/com/sun/star/comp/helper/Bootstrap.java?view=markup
 * 
 * The idea to develop this BootstrapConnector comes from the blog "Getting
 * started with the OpenOffice.org API part III : starting OpenOffice.org with
 * jars not in the lo install dir by Wouter van Reeven"
 * (http://technology.amis.nl/blog/?p=1284) and from various posts in the
 * "(Unofficial) OpenOffice.org Forum" at http://www.loforum.org/ and the
 * "OpenOffice.org Community Forum" at http://user.services.openoffice.org/
 * complaining about "no office executable found!".
 */
public class ConnectorBase {

    /** The lo server. */
    protected LoServer server;
    
    /** The connection string which has ben used to establish the connection. */
    protected String connectionString;

    /**
     * Constructs a bootstrap connector which uses the folder of the lo
     * installation containing the soffice executable.
     * 
     * @param   loExecFoder   The folder of the lo installation containing the soffice executable
     */
    public ConnectorBase(String loExecFolder) {
        
        this.server = new LoServer(loExecFolder);
        this.connectionString = null;
    }

    /**
     * Constructs a bootstrap connector which connects to the specified
     * lo server.
     * 
     * @param   server   The lo server
     */
    public ConnectorBase(LoServer server) {

        this.server = server;
        this.connectionString = null;
    }

    /**
     * Connects to an lo server using the specified accept option and
     * connection string and returns a component context for using the
     * connection to the lo server.
     * 
     * The accept option and the connection string should match to get a
     * connection. lo provides to different types of connections:
     * 1) The socket connection
     * 2) The named pipe connection
     * 
     * To create a socket connection a host and port must be provided.
     * For example using the host "localhost" and the port "8100" the
     * accept option and connection string looks like this:
     * - accept option    : -accept=socket,host=localhost,port=8100;urp;
     * - connection string: uno:socket,host=localhost,port=8100;urp;StarOffice.ComponentContext
     * 
     * To create a named pipe a pipe name must be provided. For example using
     * the pipe name "loPipe" the accept option and connection string looks
     * like this:
     * - accept option    : -accept=pipe,name=loPipe;urp;
     * - connection string: uno:pipe,name=loPipe;urp;StarOffice.ComponentContext
     * 
     * @param   acceptOption       The accept option
     * @param   connectionString   The connection string
     * @return                        The component context
     * @throws BootstrapException 
     * @throws IOException 
     * @throws MalformedURLException 
     * @throws ConnectionSetupException 
     * @throws IllegalArgumentException 
     */
    public XComponentContext connect(String acceptOption, String connectionString) throws BootstrapException, MalformedURLException, IOException, IllegalArgumentException, ConnectionSetupException {

        this.connectionString = connectionString;

        XComponentContext xContext = null;
            // get local context
            XComponentContext xLocalContext = getLocalContext();

            server.start(acceptOption);

            // initial service manager
            XMultiComponentFactory xLocalServiceManager = xLocalContext.getServiceManager();
            if ( xLocalServiceManager == null )
                throw new BootstrapException("no initial service manager!");

            // create a URL resolver
            XUnoUrlResolver xUrlResolver = UnoUrlResolver.create(xLocalContext);

            // wait until office is started
            for (int i = 0;; ++i) {
                try {
                    xContext = getRemoteContext(xUrlResolver);
                    break;
                } catch ( com.sun.star.connection.NoConnectException ex ) {
                    // Wait 500 ms, then try to connect again, but do not wait
                    // longer than 5 min (= 600 * 500 ms) total:
                    if (i == 600) {
                        throw new BootstrapException(ex.toString());
                    }
                    try {
			Thread.sleep(500);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
                }
            }
        return xContext;
    }

    /**
     * Disconnects from an lo server using the connection string from the
     * previous connect.
     * 
     * If there has been no previous connect, the disconnects does nothing.
     * 
     * If there has been a previous connect, disconnect tries to terminate
     * the lo server and kills the lo server process the connect started.
     */
    public void disconnect() {
        if (connectionString == null)
            return;

        // call office to terminate itself
        try {
            // get local context
            XComponentContext xLocalContext = getLocalContext();

            // create a URL resolver
            XUnoUrlResolver xUrlResolver = UnoUrlResolver.create(xLocalContext);

            // get remote context
            XComponentContext xRemoteContext = getRemoteContext(xUrlResolver);

            // get desktop to terminate office
            Object desktop = xRemoteContext.getServiceManager().createInstanceWithContext("com.sun.star.frame.Desktop",xRemoteContext);
            XDesktop xDesktop = UnoRuntime.queryInterface(XDesktop.class, desktop);
            xDesktop.terminate();
        }
        catch (Exception e) {
            // Bad luck, unable to terminate office
        }

        server.kill();
        connectionString = null;
    }

    /**
     * Create default local component context.
     * 
     * @return      The default local component context
     */
    private XComponentContext getLocalContext() throws BootstrapException {
	XComponentContext xLocalContext;
	try {
	    xLocalContext = Bootstrap.createInitialComponentContext(null);
	    if (xLocalContext == null) {
		throw new BootstrapException("no local component context!");
	    }
	} catch (Exception e) {
	    throw new BootstrapException(e);
	}
        return xLocalContext;
    }

    /**
     * Try to connect to office.
     * 
     * @return      The remote component context
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws java.lang.IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    private XComponentContext getRemoteContext(XUnoUrlResolver xUrlResolver) throws BootstrapException, ConnectionSetupException, IllegalArgumentException, NoConnectException  {
        Object context = xUrlResolver.resolve(connectionString);
        XComponentContext xContext = UnoRuntime.queryInterface(XComponentContext.class, context);
        if (xContext == null) {
            throw new BootstrapException("no component context!");
        }
        return xContext;
    }
}