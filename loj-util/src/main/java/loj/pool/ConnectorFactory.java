package loj.pool;

import java.net.MalformedURLException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import loj.tempuserdir.TempUserDirServer;
import loj.tempuserdir.TempUserDirConnector;

/**
 * A pooled object factory for managing {@link TempUserDirConnector} instances using Apache Commons Pool.
 */
public class ConnectorFactory extends BasePooledObjectFactory<TempUserDirConnector> {

    /** The directory containing the LibreOffice executable. */
    protected String execFolder;

    /**
     * Constructs a factory configured with the given LibreOffice executable directory.
     *
     * @param ExecFolder the directory containing the LibreOffice executable
     */
    public ConnectorFactory(String ExecFolder) {
        super();
        this.execFolder = ExecFolder;    
    }

    /**
     * Creates a new {@link TempUserDirConnector} instance with a unique temporary user profile.
     *
     * @return a new connector instance
     * @throws MalformedURLException if the temporary directory URL cannot be formed
     */
    @Override
    public TempUserDirConnector create() throws MalformedURLException {
        TempUserDirServer server = new TempUserDirServer(execFolder);
        System.out.println(server.key);    
        return new TempUserDirConnector(server);
    }

    /**
     * Wraps a connector instance into a {@link PooledObject}.
     *
     * @param connector the connector to wrap
     * @return the wrapped pooled object
     */
    @Override
    public PooledObject<TempUserDirConnector> wrap(TempUserDirConnector connector) {
        return new DefaultPooledObject<TempUserDirConnector>(connector);
    }

    /**
     * Destroys an object in the pool, disconnecting its LibreOffice connection.
     *
     * @param c the pooled object to destroy
     * @throws Exception if an error occurs while disconnecting
     */
    @Override
    public void destroyObject(PooledObject<TempUserDirConnector> c) throws Exception {
        c.getObject().disconnect();
    }
}
