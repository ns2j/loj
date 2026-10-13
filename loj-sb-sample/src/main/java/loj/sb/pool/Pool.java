package loj.sb.pool;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sun.star.comp.helper.BootstrapException;

import loj.pool.PoolManager;
import loj.tempuserdir.TempUserDirConnector;

@Configuration
public class Pool {

    /**
     * Creates the connector pool managed by Spring.
     *
     * PoolManager provides the common pool creation logic.
     * Spring closes this pool when the application context shuts down.
     *
     * @return the connector pool
     * @throws BootstrapException if resolving the LibreOffice executable
     *         folder fails
     */
    @Bean(destroyMethod = "close")
    public GenericObjectPool<TempUserDirConnector> connectorPool()
            throws BootstrapException {

        GenericObjectPoolConfig<TempUserDirConnector> config =
                new GenericObjectPoolConfig<>();

        config.setMaxTotal(10);
        config.setMaxIdle(2);
        config.setMinIdle(0);
        config.setMaxWait(Duration.ofSeconds(30));
        config.setBlockWhenExhausted(true);

        return PoolManager.createPool(config);
    }
}
