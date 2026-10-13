package loj.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sun.star.comp.helper.BootstrapException;

import loj.tempuserdir.TempUserDirConnector;
import loj.util.LoUtil;

/**
 * Singleton-like manager providing a shared pool of
 * {@link TempUserDirConnector} instances.
 */
public class PoolManager {

    /** The singleton pool instance. */
    protected static GenericObjectPool<TempUserDirConnector> pool = null;

    /**
     * Prevents direct instantiation.
     */
    protected PoolManager() {
    }

    /**
     * Action to execute with a borrowed connector.
     */
    @FunctionalInterface
    public interface ConnectorAction {
        void execute(TempUserDirConnector connector) throws Exception;
    }

    /**
     * Returns the shared connector pool.
     *
     * @return the shared connector pool
     * @throws BootstrapException if resolving the LibreOffice executable
     *         folder fails
     */
    public static GenericObjectPool<TempUserDirConnector> getPool()
            throws BootstrapException {

        synchronized (PoolManager.class) {
            if (pool == null) {
                GenericObjectPoolConfig<TempUserDirConnector> config =
                        new GenericObjectPoolConfig<>();

                config.setMaxIdle(2);
                dumpConfig(config);

                pool = createPool(config);
            }
        }

        return pool;
    }

    /**
     * Creates a connector pool with the specified configuration.
     * The caller is responsible for closing the returned pool.
     *
     * @param config pool configuration
     * @return a new connector pool
     * @throws BootstrapException if resolving the LibreOffice executable
     *         folder fails
     */
    public static GenericObjectPool<TempUserDirConnector> createPool(
            GenericObjectPoolConfig<TempUserDirConnector> config)
            throws BootstrapException {

        return new GenericObjectPool<>(
                new ConnectorFactory(LoUtil.getExecFolder()),
                config);
    }

    /**
     * Executes an action using the shared connector pool.
     *
     * @param action action to execute
     * @throws Exception if borrowing the connector or executing the action fails
     */
    public static void withConnector(ConnectorAction action)
            throws Exception {

        withConnector(getPool(), action);
    }

    /**
     * Executes an action using the specified connector pool.
     *
     * @param targetPool pool from which to borrow a connector
     * @param action action to execute
     * @throws Exception if borrowing the connector or executing the action fails
     */
    public static void withConnector(
            GenericObjectPool<TempUserDirConnector> targetPool,
            ConnectorAction action) throws Exception {

        TempUserDirConnector connector = targetPool.borrowObject();
        boolean invalidate = false;

        try {
            action.execute(connector);
        } catch (Exception e) {
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
                targetPool.invalidateObject(connector);
            } else {
                targetPool.returnObject(connector);
            }
        }
    }

    /**
     * Dumps selected pool configuration values to standard output.
     *
     * @param config pool configuration
     */
    public static void dumpConfig(
            GenericObjectPoolConfig<TempUserDirConnector> config) {

        System.out.println(config.getEvictionPolicyClassName());
        System.out.println(config.getMinEvictableIdleTimeMillis());
        System.out.println(config.getMaxIdle());
        System.out.println(config.getMinIdle());
    }
}
