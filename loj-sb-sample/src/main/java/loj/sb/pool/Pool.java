
package loj.sb.pool;

import java.net.MalformedURLException;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.star.comp.helper.BootstrapException;

import loj.tempuserdir.TempUserDirConnector;
import loj.tempuserdir.TempUserDirServer;
import loj.util.LoUtil;

@Configuration
public class Pool {

    private static final Logger logger =
            LoggerFactory.getLogger(Pool.class);

    @Bean(destroyMethod = "close")
    public GenericObjectPool<TempUserDirConnector> connectorPool() {

        BasePooledObjectFactory<TempUserDirConnector> factory =
                new BasePooledObjectFactory<>() {

            @Override
            public TempUserDirConnector create()
                    throws MalformedURLException,
                           BootstrapException {

                TempUserDirConnector connector =
                        new TempUserDirConnector(
                                new TempUserDirServer(
                                        LoUtil.getExecFolder()));

                logger.info("create connector: {}", connector.key);

                return connector;
            }

            @Override
            public PooledObject<TempUserDirConnector> wrap(
                    TempUserDirConnector connector) {

                return new DefaultPooledObject<>(connector);
            }

            @Override
            public void destroyObject(
                    PooledObject<TempUserDirConnector> p)
                    throws Exception {

                TempUserDirConnector connector = p.getObject();

                logger.info("destroyObject: {}", connector.key);

                if (connector.key != null) {
                    try {
                        connector.disconnect();
                    } finally {
                        connector.key = null;
                    }
                }
            }
        };

        GenericObjectPoolConfig<TempUserDirConnector> config =
                new GenericObjectPoolConfig<>();

        config.setMaxTotal(10);
        config.setMaxIdle(2);
        config.setMinIdle(0);
        config.setBlockWhenExhausted(true);

        return new GenericObjectPool<>(factory, config);
    }
    
    @FunctionalInterface
    public interface ConnectorAction {
        void execute(TempUserDirConnector connector) throws java.lang.Exception;
    }

    public static void withConnector(
            GenericObjectPool<TempUserDirConnector> pool,
            ConnectorAction action) throws java.lang.Exception {

        TempUserDirConnector connector = pool.borrowObject();
        boolean invalidate = false;

        try {
            action.execute(connector);
        } catch (java.lang.Exception e) {
            Throwable cause = e;

            while (cause != null) {
                if (cause instanceof com.sun.star.lang.DisposedException
                        || cause instanceof com.sun.star.connection.NoConnectException
                        || cause instanceof com.sun.star.connection.ConnectionSetupException) {
                    invalidate = true;
                    break;
                }
                cause = cause.getCause();
            }

            throw e;
        } finally {
            if (invalidate) {
                logger.warn("invalidate connector: {}", connector.key);
                pool.invalidateObject(connector);
            } else {
                pool.returnObject(connector);
            }
        }
    }
}
