package loj.pool;

import java.net.MalformedURLException;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import loj.tempuserdir.TempUserDirServer;
import loj.tempuserdir.TempUserDirConnector;

public class ConnectorFactory extends BasePooledObjectFactory<TempUserDirConnector>{
    protected String execFolder;
    
    public ConnectorFactory(String ExecFolder) {
        super();
        this.execFolder = ExecFolder;    
    }
    
    @Override
    public TempUserDirConnector create() throws MalformedURLException {
            TempUserDirServer server = new TempUserDirServer(execFolder);
            System.out.println(server.key);    
            return new TempUserDirConnector(server);
    }
    @Override
    public PooledObject<TempUserDirConnector> wrap(TempUserDirConnector connector) {
        return new DefaultPooledObject<TempUserDirConnector>(connector);
    }

    @Override
    public void destroyObject(PooledObject<TempUserDirConnector> c) throws Exception {
        c.getObject().disconnect();
    }

}
