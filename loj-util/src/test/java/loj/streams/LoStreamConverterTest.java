package loj.streams;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XStorable;
import com.sun.star.io.IOException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Exception;
import com.sun.star.uno.XComponentContext;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloseable;

class LoStreamConverterTest {

    private interface TestDocument extends XComponent, XStorable, XCloseable {
    }

    private interface StorableDocumentWithoutCloseable extends XComponent, XStorable {
    }

    private XComponentContext context;
    private XMultiComponentFactory serviceManager;
    private XComponentLoader componentLoader;
    private LoStreamConverter converter;

    @BeforeEach
    void setUp() throws Exception {
        context = mock(XComponentContext.class);
        serviceManager = mock(XMultiComponentFactory.class);
        componentLoader = mock(XComponentLoader.class);

        when(context.getServiceManager()).thenReturn(serviceManager);
        when(serviceManager.createInstanceWithContext(
                eq("com.sun.star.frame.Desktop"), eq(context)))
                .thenReturn(componentLoader);

        converter = new LoStreamConverter(context);
    }

    @Test
    void convertClosesDocumentOnSuccess() throws Exception {
        TestDocument document = mock(TestDocument.class);
        when(componentLoader.loadComponentFromURL(
                eq("private:stream"), eq("_blank"), eq(0), any(PropertyValue[].class)))
                .thenReturn(document);

        LoInputStream input = new LoInputStream(new byte[] {1, 2, 3});
        LoOutputStream output = new LoOutputStream();

        converter.convert(input, output, "writer_pdf_Export");

        verify(document).storeToURL(eq("private:stream"), any(PropertyValue[].class));
        verify(document).close(true);
    }

    @Test
    void convertThrowsExceptionInsteadOfNpeWhenLoadReturnsNull() throws Exception {
        when(componentLoader.loadComponentFromURL(
                eq("private:stream"), eq("_blank"), eq(0), any(PropertyValue[].class)))
                .thenReturn(null);

        LoInputStream input = new LoInputStream(new byte[0]);
        LoOutputStream output = new LoOutputStream();

        Exception actual = assertThrows(
                Exception.class,
                () -> converter.convert(input, output, "writer_pdf_Export"));
        assertNotNull(actual.getMessage());
    }

    @Test
    void convertClosesDocumentWhenStoreToUrlThrowsException() throws Exception {
        TestDocument document = mock(TestDocument.class);
        when(componentLoader.loadComponentFromURL(
                eq("private:stream"), eq("_blank"), eq(0), any(PropertyValue[].class)))
                .thenReturn(document);

        IOException storeFailure = new IOException("export failed");
        doThrow(storeFailure)
                .when(document)
                .storeToURL(eq("private:stream"), any(PropertyValue[].class));

        LoInputStream input = new LoInputStream(new byte[] {1, 2, 3});
        LoOutputStream output = new LoOutputStream();

        IOException actual = assertThrows(
                IOException.class,
                () -> converter.convert(input, output, "invalid_filter"));

        assertSame(storeFailure, actual);
        verify(document).close(true);
    }

    @Test
    void convertSuppressesCloseFailureWhenStoreToUrlThrowsException() throws Exception {
        TestDocument document = mock(TestDocument.class);
        when(componentLoader.loadComponentFromURL(
                eq("private:stream"), eq("_blank"), eq(0), any(PropertyValue[].class)))
                .thenReturn(document);

        IOException storeFailure = new IOException("export failed");
        CloseVetoException closeFailure = new CloseVetoException("close vetoed");
        doThrow(storeFailure)
                .when(document)
                .storeToURL(eq("private:stream"), any(PropertyValue[].class));
        doThrow(closeFailure)
                .when(document)
                .close(true);

        LoInputStream input = new LoInputStream(new byte[] {1, 2, 3});
        LoOutputStream output = new LoOutputStream();

        IOException actual = assertThrows(
                IOException.class,
                () -> converter.convert(input, output, "invalid_filter"));

        assertSame(storeFailure, actual);
        assertEquals(1, actual.getSuppressed().length);
        assertSame(closeFailure, actual.getSuppressed()[0]);
    }

    @Test
    void convertDisposesDocumentWhenXCloseableIsNotSupported() throws Exception {
        StorableDocumentWithoutCloseable document =
                mock(StorableDocumentWithoutCloseable.class);
        when(componentLoader.loadComponentFromURL(
                eq("private:stream"), eq("_blank"), eq(0), any(PropertyValue[].class)))
                .thenReturn(document);

        IOException storeFailure = new IOException("export failed");
        doThrow(storeFailure)
                .when(document)
                .storeToURL(eq("private:stream"), any(PropertyValue[].class));

        LoInputStream input = new LoInputStream(new byte[] {1, 2, 3});
        LoOutputStream output = new LoOutputStream();

        IOException actual = assertThrows(
                IOException.class,
                () -> converter.convert(input, output, "invalid_filter"));

        assertSame(storeFailure, actual);
        verify(document).dispose();
    }
}
