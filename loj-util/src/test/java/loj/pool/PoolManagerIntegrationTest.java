package loj.pool;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import com.sun.star.bridge.UnoUrlResolver;
import com.sun.star.comp.helper.Bootstrap;
import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.connection.NoConnectException;
import com.sun.star.lang.DisposedException;
import com.sun.star.uno.XComponentContext;

import loj.tempuserdir.TempUserDirConnector;
import loj.tempuserdir.TempUserDirServer;
import loj.util.LoUtil;

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

    @Test
    @EnabledIfSystemProperty(named = "loj.integrationTests", matches = "true")
    void connectorIsReusedAcrossRepeatedBorrowAndReturnCycles()
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
        AtomicReference<TempUserDirConnector> firstConnector =
                new AtomicReference<>();

        try {
            // Repeatedly borrow and return the same live connector.
            for (int i = 0; i < 10; i++) {
                final int iteration = i;
                PoolManager.withConnector(pool, connector -> {
                    if (iteration == 0) {
                        firstConnector.set(connector);
                    } else {
                        assertSame(firstConnector.get(), connector,
                                "The pool should reuse the connector on cycle " + iteration);
                    }

                    XComponentContext context = connector.connect();
                    assertTrue(connector.getServer().isAlive(),
                            "Original server process must remain alive on cycle " + iteration);
                    assertNotNull(context,
                            "UNO context should remain available on cycle " + iteration);
                    Object desktop = context.getServiceManager()
                            .createInstanceWithContext(
                                    "com.sun.star.frame.Desktop", context);
                    assertNotNull(desktop,
                            "Desktop service should remain available on cycle " + iteration);
                });

                assertEquals(0, pool.getNumActive(),
                        "No connector should remain borrowed after cycle " + iteration);
                assertEquals(1, pool.getNumIdle(),
                        "One connector should be idle after cycle " + iteration);
            }
        } finally {
            pool.close();
        }
    }

    @Test
    @EnabledIfSystemProperty(named = "loj.integrationTests", matches = "true")
    void serverKillTerminatesOriginalProcessAfterRepeatedConnectCalls()
            throws Exception {

        Assumptions.assumeTrue(
                System.getenv("LO_EXEC_PATH") != null
                        || System.getProperty("lo.execFolder") != null,
                "Set LO_EXEC_PATH or -Dlo.execFolder to the LibreOffice program directory");

        TempUserDirServer server = new TempUserDirServer(LoUtil.getExecFolder());
        TempUserDirConnector connector = new TempUserDirConnector(server);
        String unoUrl = "uno:pipe,name=uno" + connector.getKey() + ";urp;StarOffice.ComponentContext";

        try {
            connector.connect();
            assertTrue(server.isAlive(), "Process should be alive after first connect()");

            connector.connect();
            assertTrue(server.isAlive(), "Original process reference must remain alive after second connect()");

            // Simulate fallback cleanup when UNO terminate is not called.
            server.kill();
            assertFalse(server.isAlive(), "Process should be terminated after server.kill()");

            XComponentContext localContext = Bootstrap.createInitialComponentContext(null);
            assertThrows(NoConnectException.class, () ->
                    UnoUrlResolver.create(localContext).resolve(unoUrl),
                    "Original LibreOffice process must no longer be listening after server.kill()");
        } finally {
            connector.disconnect();
        }
    }

    @Test
    @EnabledIfSystemProperty(named = "loj.integrationTests", matches = "true")
    void connectFailsQuicklyWhenLibreOfficeProcessExitsImmediately()
            throws Exception {

        Assumptions.assumeTrue(
                System.getenv("LO_EXEC_PATH") != null
                        || System.getProperty("lo.execFolder") != null,
                "Set LO_EXEC_PATH or -Dlo.execFolder to the LibreOffice program directory");

        TempUserDirServer server = new TempUserDirServer(LoUtil.getExecFolder());
        // "--version" prints the version and exits immediately without opening the UNO pipe.
        server.setOptions(List.of("--version"));
        TempUserDirConnector connector = new TempUserDirConnector(server);

        try {
            assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
                assertThrows(BootstrapException.class, connector::connect);
            });
        } finally {
            connector.disconnect();
        }
    }

}
