package loj.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.sun.star.comp.helper.BootstrapException;

import loj.tempuserdir.TempUserDirConnector;
import loj.util.LoUtil;

public class PoolManager {
    protected static GenericObjectPool<TempUserDirConnector> pool = null;
    
    protected PoolManager() {
    }

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

    static public void dumpConfig(GenericObjectPoolConfig<TempUserDirConnector> gopc) {
        System.out.println(gopc.getEvictionPolicyClassName());
        System.out.println(gopc.getMinEvictableIdleTimeMillis());
        System.out.println(gopc.getMaxIdle());
        System.out.println(gopc.getMinIdle());
    }
}
