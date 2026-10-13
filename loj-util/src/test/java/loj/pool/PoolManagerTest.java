
package loj.pool;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.star.lang.DisposedException;

import loj.tempuserdir.TempUserDirConnector;

class PoolManagerTest {

    private GenericObjectPool<TempUserDirConnector> pool;
    private TempUserDirConnector connector;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() throws Exception {
        pool = mock(GenericObjectPool.class);
        connector = mock(TempUserDirConnector.class);

        when(pool.borrowObject()).thenReturn(connector);
    }

    @Test
    void successReturnsConnector() throws Exception {
        PoolManager.withConnector(pool, c -> {
            assertSame(connector, c);
        });

        verify(pool).returnObject(connector);
        verify(pool, never()).invalidateObject(connector);
    }

    @Test
    void ordinaryExceptionReturnsConnectorAndPreservesException()
            throws Exception {
        RuntimeException expected =
                new RuntimeException("application error");

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> PoolManager.withConnector(pool, c -> {
                    throw expected;
                }));

        assertSame(expected, actual);
        verify(pool).returnObject(connector);
        verify(pool, never()).invalidateObject(connector);
    }

    @Test
    void disposedExceptionInvalidatesConnector() throws Exception {
        DisposedException expected =
                new DisposedException("disposed", null);

        DisposedException actual = assertThrows(
                DisposedException.class,
                () -> PoolManager.withConnector(pool, c -> {
                    throw expected;
                }));

        assertSame(expected, actual);
        verify(pool).invalidateObject(connector);
        verify(pool, never()).returnObject(connector);
    }

    @Test
    void errorInvalidatesConnector() throws Exception {
        AssertionError expected = new AssertionError("unexpected error");

        AssertionError actual = assertThrows(
                AssertionError.class,
                () -> PoolManager.withConnector(pool, c -> {
                    throw expected;
                }));

        assertSame(expected, actual);
        verify(pool).invalidateObject(connector);
        verify(pool, never()).returnObject(connector);
    }

    @Test
    void cleanupFailureIsSuppressedWhenActionFails() throws Exception {
        RuntimeException actionFailure =
                new RuntimeException("action failed");
        RuntimeException cleanupFailure =
                new RuntimeException("return failed");

        doThrow(cleanupFailure).when(pool).returnObject(connector);

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> PoolManager.withConnector(pool, c -> {
                    throw actionFailure;
                }));

        assertSame(actionFailure, actual);
        assertEquals(1, actual.getSuppressed().length);
        assertSame(cleanupFailure, actual.getSuppressed()[0]);
    }

    @Test
    void cleanupFailurePropagatesWhenActionSucceeds() throws Exception {
        RuntimeException cleanupFailure =
                new RuntimeException("return failed");

        doThrow(cleanupFailure).when(pool).returnObject(connector);

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> PoolManager.withConnector(pool, c -> {
                    // Normal completion
                }));

        assertSame(cleanupFailure, actual);
    }

    @Test
    void borrowFailureDoesNotReturnOrInvalidateConnector()
            throws Exception {
        RuntimeException expected =
                new RuntimeException("borrow failed");

        when(pool.borrowObject()).thenThrow(expected);

        RuntimeException actual = assertThrows(
                RuntimeException.class,
                () -> PoolManager.withConnector(pool, c -> {
                    fail("Action must not be executed");
                }));

        assertSame(expected, actual);
        verify(pool, never()).returnObject(any());
        verify(pool, never()).invalidateObject(any());
    }
}
