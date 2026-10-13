
package loj.pool;

import java.net.MalformedURLException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import loj.tempuserdir.TempUserDirConnector;
import loj.tempuserdir.TempUserDirServer;

/**
 * A pooled object factory for managing TempUserDirConnector instances.
 */
public class ConnectorFactory
        extends BasePooledObjectFactory<TempUserDirConnector> {

    private final String execFolder;

    public ConnectorFactory(String execFolder) {
        this.execFolder = execFolder;
    }

    @Override
    public TempUserDirConnector create() throws MalformedURLException {
        TempUserDirServer server = new TempUserDirServer(execFolder);
        return new TempUserDirConnector(server);
    }

    @Override
    public PooledObject<TempUserDirConnector> wrap(
            TempUserDirConnector connector) {
        return new DefaultPooledObject<>(connector);
    }

    @Override
    public void destroyObject(
            PooledObject<TempUserDirConnector> pooled) throws Exception {
        TempUserDirConnector connector = pooled.getObject();

        try {
            connector.disconnect();
        } finally {
            connector.key = null;
        }
    }
}
