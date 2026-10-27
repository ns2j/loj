package loj.tempuserdir;

import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
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

    private static final Logger LOGGER =
            System.getLogger(TempUserDirServer.class.getName());

    private static final int DELETE_RETRIES = 5;
    private static final long DELETE_RETRY_DELAY_MILLIS = 200L;

    /** Subdirectory under the temporary directory used for UNO profiles. */
    private static final String TEMP_APP_DIR = "/uno/";

    /** Unique identifier for this server instance. */
    private final String key;

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
        LOGGER.log(Level.DEBUG, "UserInstallation URL: {0}", url);
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
     * Returns the unique identifier for this server instance.
     *
     * @return the unique key
     */
    public String getKey() {
        return key;
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
     * Deletes a file or directory tree, retrying the whole tree in case entries
     * remain or appear while a previous deletion pass is in progress.
     *
     * @param f the file or directory to delete
     */
    protected void deleteDir(File f) {
        for (int attempt = 1; attempt <= DELETE_RETRIES; attempt++) {
            deleteTree(f, attempt == DELETE_RETRIES);
            if (!f.exists()) {
                return;
            }
            if (attempt < DELETE_RETRIES) {
                try {
                    Thread.sleep(DELETE_RETRY_DELAY_MILLIS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOGGER.log(Level.WARNING, "Interrupted while deleting temporary user directory: {0}", f);
                    break;
                }
            }
        }

        if (f.exists()) {
            LOGGER.log(Level.WARNING,
                    "Failed to delete temporary user directory after {0} pass(es): {1}",
                    DELETE_RETRIES, f);
            logRemainingEntries(f);
        }
    }

    /**
     * Performs one recursive deletion pass.
     *
     * @param f the file or directory to delete
     * @param logErrors whether to log individual deletion errors
     */
    private void deleteTree(File f, boolean logErrors) {
        if (!f.exists()) {
            return;
        }

        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files == null) {
                if (logErrors) {
                    LOGGER.log(Level.WARNING,
                            "Unable to list directory for deletion: {0} (check permissions and whether the directory is still accessible)",
                            f);
                }
                return;
            }
            for (File child : files) {
                deleteTree(child, logErrors);
            }
        }

        try {
            Files.deleteIfExists(f.toPath());
        } catch (IOException e) {
            if (logErrors) {
                LOGGER.log(Level.DEBUG, "Unable to delete entry: {0} ({1}: {2})",
                        f, e.getClass().getSimpleName(), e.getMessage());
            }
        }
    }

    /**
     * Logs all entries still present when deletion fails.
     *
     * @param f the file or directory that could not be deleted
     */
    private void logRemainingEntries(File f) {
        if (!f.exists()) {
            return;
        }
        if (!f.isDirectory()) {
            LOGGER.log(Level.WARNING, "  Remaining file: {0} (size={1} bytes, lastModified={2})",
                    f, f.length(), f.lastModified());
            return;
        }

        File[] children = f.listFiles();
        if (children == null) {
            LOGGER.log(Level.WARNING, "  Unable to list remaining entries in: {0}", f);
            return;
        }
        if (children.length == 0) {
            LOGGER.log(Level.WARNING, "  Directory is empty now; it may have changed during deletion: {0}", f);
            return;
        }

        LOGGER.log(Level.WARNING, "  Remaining entries in {0}:", f);
        for (File child : children) {
            if (child.isDirectory()) {
                LOGGER.log(Level.WARNING, "    directory: {0}", child);
                logRemainingEntries(child);
            } else {
                LOGGER.log(Level.WARNING, "    file: {0} (size={1} bytes, lastModified={2})",
                        child, child.length(), child.lastModified());
            }
        }
    }

    /**
     * Kills the LibreOffice process and deletes its associated temporary user directory.
     */
    @Override
    public void kill() {
        LOGGER.log(Level.INFO, "kill key: {0}", key);
        super.kill();
        deleteDir(getTempUserDir());
    }
}
