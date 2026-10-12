package loj.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sun.star.comp.helper.BootstrapException;

import loj.tempuserdir.TempUserDirConnector;
import loj.util.LoUtil;

/**
 * Singleton-like manager providing a shared {@link GenericObjectPool} of {@link TempUserDirConnector} instances.
 */
public class PoolManager {

    /** The singleton pool instance. */
    protected static GenericObjectPool<TempUserDirConnector> pool = null;

    /**
     * Protected constructor to prevent direct instantiation.
     */
    protected PoolManager() {
    }

    /**
     * Returns the singleton instance of the connector object pool.
     * Initializes the pool with default configuration if not already created.
     *
     * @return the {@link GenericObjectPool} of {@link TempUserDirConnector} instances
     * @throws BootstrapException if resolving the LibreOffice executable folder fails
     */
    public static GenericObjectPool<TempUserDirConnector> getPool() throws BootstrapException {
        synchronized(PoolManager.class) {
            if (pool == null) {
                GenericObjectPoolConfig<TempUserDirConnector> gopc = new GenericObjectPoolConfig<>();
                gopc.setMaxIdle(2);
                dumpConfig(gopc);
                pool = new GenericObjectPool<>(new ConnectorFactory(LoUtil.getExecFolder()));
                pool.setConfig(gopc);
            }
        }
        return pool;
    }

    /**
     * Dumps configuration properties of the given {@link GenericObjectPoolConfig} to stdout.
     *
     * @param gopc the pool configuration to dump
     */
    static public void dumpConfig(GenericObjectPoolConfig<TempUserDirConnector> gopc) {
        System.out.println(gopc.getEvictionPolicyClassName());
        System.out.println(gopc.getMinEvictableIdleTimeMillis());
        System.out.println(gopc.getMaxIdle());
        System.out.println(gopc.getMinIdle());
    }
}
