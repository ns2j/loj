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
                    System.err.println("Interrupted while deleting temporary user directory: " + f);
                    break;
                }
            }
        }

        if (f.exists()) {
            System.err.println("Failed to delete temporary user directory after "
                    + DELETE_RETRIES + " pass(es): " + f);
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
                    System.err.println("Unable to list directory for deletion: " + f
                            + " (check permissions and whether the directory is still accessible)");
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
                System.err.println("Unable to delete entry: " + f + " ("
                        + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
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
            System.err.println("  Remaining file: " + f
                    + " (size=" + f.length() + " bytes"
                    + ", lastModified=" + f.lastModified() + ")");
            return;
        }

        File[] children = f.listFiles();
        if (children == null) {
            System.err.println("  Unable to list remaining entries in: " + f);
            return;
        }
        if (children.length == 0) {
            System.err.println("  Directory is empty now; it may have changed during deletion: " + f);
            return;
        }

        System.err.println("  Remaining entries in " + f + ":");
        for (File child : children) {
            if (child.isDirectory()) {
                System.err.println("    directory: " + child);
                logRemainingEntries(child);
            } else {
                System.err.println("    file: " + child + " (size=" + child.length()
                        + " bytes, lastModified=" + child.lastModified() + ")");
            }
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
