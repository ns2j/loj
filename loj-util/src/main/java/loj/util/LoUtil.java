package loj.util;

import com.sun.star.comp.helper.BootstrapException;

public class LoUtil {
    public static String getExecFolder() throws BootstrapException {
        String execFolder = System.getenv("LO_EXEC_PATH");
        if (execFolder == null)
            execFolder = System.getProperty("lo.execFolder");

        if (execFolder == null)
            throw new BootstrapException("execFolder is null. set env LO_EXEC_PATH or set prop lo.exceFolder");
        return execFolder;
    }
}
