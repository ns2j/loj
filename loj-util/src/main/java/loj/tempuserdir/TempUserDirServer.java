package loj.tempuserdir;

import java.io.File;
import java.net.MalformedURLException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import loj.connector.LoServer;

/**
 * A LibreOffice server instance that uses an isolated, temporary user installation directory.
 * <p>
 * This prevents conflicts when running multiple concurrent LibreOffice processes
 * by isolating their user configuration profiles.
 */
public class TempUserDirServer extends LoServer {

    private static final int DELETE_RETRIES = 5;
    private static final long DELETE_RETRY_DELAY_MILLIS = 200L;

    /** Subdirectory under the temporary directory used for UNO profiles. */
    public String TEMP_APP_DIR = "/uno/";

    /** Unique identifier for this server instance. */
    public String key = null;

    /**
     * Constructs a {@code TempUserDirServer} instance with a unique temporary user profile directory.
     * Configures the server with headless and non-interactive startup options.
     *
     * @param oooExecFolder the directory containing the LibreOffice executable
     * @throws MalformedURLException if the URL of the temporary profile directory cannot be created
     */
    public TempUserDirServer(String oooExecFolder) throws MalformedURLException {
        super(oooExecFolder);
        key = UUID.randomUUID().toString();
        //String url = getTempUserDir().toURI().toURL().toString();
        String url = getTempUserDir().toURI().toURL().toString();
        System.out.println(url);
        List<String> o = new ArrayList<>();
        o.add("--nologo");
        o.add("--nodefault");
        o.add("--nocrashreport");
        o.add("--nolockcheck");
        o.add("--nofirststartwizard");
        o.add("-env:UserInstallation=" + url);
        //if (isHeadless) {
        o.add("--norestore");
        o.add("--minimized");
        o.add("--invisible");
        o.add("--headless");
        //}
        setOptions(o);    
    }

    /**
     * Returns the temporary user directory associated with this server instance.
     *
     * @return the {@link File} object representing the temporary directory
     */
    public File getTempUserDir() {
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File tmpAppDir = new File(tmpDir, TEMP_APP_DIR);
        //File tmpUserDir = new File(tmpAppDir,  System.getProperty("user.name"));
        //return tmpUserDir;
        return new File(tmpAppDir, key);
    }

    /**
     * Recursively deletes a file or directory.
     *
     * @param f the file or directory to delete
     */
    protected void deleteDir(File f) {
        if (!f.exists()) {
            return;
        }

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files == null) {
                System.err.println("Unable to list directory for deletion: " + f
                        + " (check permissions and whether the directory is still accessible)");
                return;
            }
            for (File child : files) {
                deleteDir(child);
            }
        }

        deleteWithRetry(f);
    }

    /**
     * Retries deletion briefly because LibreOffice may still be finishing profile cleanup.
     * Reports the underlying I/O error so a failed cleanup can be diagnosed.
     *
     * @param f the file or directory to delete
     */
    private void deleteWithRetry(File f) {
        IOException lastError = null;
        int attempts = 0;

        for (int attempt = 1; attempt <= DELETE_RETRIES; attempt++) {
            attempts = attempt;
            try {
                Files.deleteIfExists(f.toPath());
                return;
            } catch (IOException e) {
                lastError = e;
                if (attempt == DELETE_RETRIES) {
                    break;
                }
                try {
                    Thread.sleep(DELETE_RETRY_DELAY_MILLIS);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    lastError = new IOException(
                            "Interrupted while retrying deletion", interrupted);
                    break;
                }
            }
        }

        if (lastError != null && f.exists()) {
            System.err.println("Failed to delete after " + attempts + " attempt(s): "
                    + f + " (" + lastError.getClass().getSimpleName()
                    + ": " + lastError.getMessage() + ")");
        }
    }

    /**
     * Kills the LibreOffice process and deletes its associated temporary user directory.
     */
    @Override
    public void kill() {
        System.out.println("kill key: " + key);
        super.kill();
        deleteDir(getTempUserDir());
    }
}
