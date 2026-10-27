package loj.util;

import com.sun.star.comp.helper.BootstrapException;

/**
 * Utility class providing helper methods for LibreOffice executable path resolution.
 */
public class LoUtil {

    /**
     * Resolves the execution directory of the LibreOffice installation.
     * Looks for the environment variable {@code LO_EXEC_PATH} first, followed by
     * the system property {@code lo.execFolder}.
     *
     * @return the directory path where the LibreOffice executable resides
     * @throws BootstrapException if neither the environment variable nor the system property is set
     */
    public static String getExecFolder() throws BootstrapException {
        String execFolder = System.getenv("LO_EXEC_PATH");
        if (execFolder == null)
            execFolder = System.getProperty("lo.execFolder");

        if (execFolder == null)
            throw new BootstrapException("execFolder is null. set env LO_EXEC_PATH or set prop lo.execFolder");
        return execFolder;
    }
}
