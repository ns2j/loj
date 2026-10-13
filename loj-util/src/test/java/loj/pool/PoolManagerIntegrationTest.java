package loj.pool;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import com.sun.star.lang.DisposedException;
import com.sun.star.uno.XComponentContext;

import loj.tempuserdir.TempUserDirConnector;

/**
 * Integration tests that start real LibreOffice processes.
 *
 * Run explicitly with:
 * mvn -pl loj-util -Dloj.integrationTests=true
 *     -Dlo.execFolder=/path/to/libreoffice/program
 *     -Dtest=PoolManagerIntegrationTest test
 *
 * These tests are opt-in because they require a local LibreOffice installation.
 */
class PoolManagerIntegrationTest {

    @Test
    @EnabledIfSystemProperty(named = "loj.integrationTests", matches = "true")
    void connectorConnectsIsReusedAndIsRecreatedAfterInvalidation()
            throws Exception {

        Assumptions.assumeTrue(
                System.getenv("LO_EXEC_PATH") != null
                        || System.getProperty("lo.execFolder") != null,
                "Set LO_EXEC_PATH or -Dlo.execFolder to the LibreOffice program directory");

        GenericObjectPoolConfig<TempUserDirConnector> config =
                new GenericObjectPoolConfig<>();
        config.setMaxTotal(1);
        config.setMaxIdle(1);
        config.setMinIdle(0);
        config.setMaxWaitMillis(30_000L);
        config.setBlockWhenExhausted(true);

        GenericObjectPool<TempUserDirConnector> pool =
                PoolManager.createPool(config);

        AtomicReference<TempUserDirConnector> originalConnector =
                new AtomicReference<>();
        AtomicReference<XComponentContext> originalContext =
                new AtomicReference<>();

        try {
            // Start a real LibreOffice process and verify UNO service access.
            PoolManager.withConnector(pool, connector -> {
                originalConnector.set(connector);

                XComponentContext context = connector.connect();
                assertNotNull(context, "UNO component context should be available");
                assertNotNull(context.getServiceManager(),
                        "UNO service manager should be available");

                Object desktop = context.getServiceManager()
                        .createInstanceWithContext(
                                "com.sun.star.frame.Desktop", context);
                assertNotNull(desktop, "LibreOffice Desktop service should be created");

                originalContext.set(context);
            });

            // Returning the connector to the pool should make it reusable.
            PoolManager.withConnector(pool, connector -> {
                assertSame(originalConnector.get(), connector,
                        "The idle connector should be reused");

                Object desktop = originalContext.get().getServiceManager()
                        .createInstanceWithContext(
                                "com.sun.star.frame.Desktop",
                                originalContext.get());
                assertNotNull(desktop,
                        "The reused connection should still provide UNO services");
            });

            // A disposed-connection exception should destroy the old connector.
            assertThrows(DisposedException.class, () ->
                    PoolManager.withConnector(pool, connector -> {
                        assertSame(originalConnector.get(), connector);
                        throw new DisposedException(
                                "Simulated disposed UNO connection", null);
                    }));

            // The next borrow should create a new connector and a new process.
            PoolManager.withConnector(pool, connector -> {
                assertNotSame(originalConnector.get(), connector,
                        "An invalidated connector must not be reused");

                XComponentContext context = connector.connect();
                assertNotNull(context,
                        "A replacement connector should establish a new connection");

                Object desktop = context.getServiceManager()
                        .createInstanceWithContext(
                                "com.sun.star.frame.Desktop", context);
                assertNotNull(desktop,
                        "The replacement LibreOffice process should provide UNO services");
            });
        } finally {
            // Closing the pool destroys idle connectors and their processes.
            pool.close();
        }
    }
}
