package loj.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import com.sun.star.comp.helper.BootstrapException;
import com.sun.star.lib.util.NativeLibraryLoader;

/**
 * Starts and stops an lo server.
 * 
 * Most of the source code in this class has been taken from the Java class
 * "Bootstrap.java" (Revision: 1.15) from the UDK projekt (Uno Software Develop-
 * ment Kit) from OpenOffice.org (http://udk.openoffice.org/). The source code
 * is available for example through a browser based online version control
 * access at http://udk.openoffice.org/source/browse/udk/. The Java class
 * "Bootstrap.java" is there available at
 * http://udk.openoffice.org/source/browse/udk/javaunohelper/com/sun/star/comp/helper/Bootstrap.java?view=markup
 */
public class LoServer {

    /** The lo server process. */
    private Process loProcess;

    /** The folder of the lo installation containing the soffice executable. */
    private String  loExecFolder;

    /** The options for starting the lo server. */
    private List<String>    loOptions;

    /**
     * Constructs an lo server which uses the folder of the lo installation
     * containing the soffice executable and a list of default options to start
     * lo.
     * 
     * @param   loExecFolder   The folder of the lo installation containing the soffice executable
     */
    public LoServer(String loExecFolder) {

        this.loProcess = null;
        this.loExecFolder = loExecFolder;
        this.loOptions = getDefaultOptions();
    }

    /**
     * Constructs an lo server which uses the folder of the lo installation
     * containing the soffice executable and a given list of options to start
     * lo.
     * 
     * @param   loExecFolder   The folder of the lo installation containing the soffice executable
     * @param   loOptions         The list of options
     */
    public LoServer(String loExecFolder, List<String> loOptions) {

        this.loProcess = null;
        this.loExecFolder = loExecFolder;
        this.loOptions = loOptions;
    }
    
    public void setOptions(List<String> options) {
        loOptions = options;
    }

    /**
     * Starts an lo server which uses the specified accept option.
     * 
     * The accept option can be used for two different types of connections:
     * 1) The socket connection
     * 2) The named pipe connection
     * 
     * To create a socket connection a host and port must be provided.
     * For example using the host "localhost" and the port "8100" the
     * accept option looks like this:
     * - accept option    : -accept=socket,host=localhost,port=8100;urp;
     * 
     * To create a named pipe a pipe name must be provided. For example using
     * the pipe name "loPipe" the accept option looks like this:
     * - accept option    : -accept=pipe,name=loPipe;urp;
     * 
     * @param   loAcceptOption      The accept option
     */
    public void start(String loAcceptOption) throws BootstrapException, IOException, MalformedURLException {

        // find office executable relative to this class's class loader
        String sOffice = System.getProperty("os.name").startsWith("Windows")? "soffice.exe": "soffice";            

        URL[] loExecFolderURL = new URL[] {new File(loExecFolder).toURI().toURL()};
        URLClassLoader loader = new URLClassLoader(loExecFolderURL);
        File fOffice = NativeLibraryLoader.getResource(loader, sOffice);
        if (fOffice == null)
            throw new BootstrapException("no office executable found! loExecFolder: " + loExecFolder + ", sOffice: " + sOffice);

        // create call with arguments
        int arguments = (loOptions != null)? loOptions.size()+1: 1;
        if (loAcceptOption != null)
            arguments++;

        String[] loCommand = new String[arguments];
        loCommand[0] = fOffice.getPath();

        for (int i = 0; i < loOptions.size(); i++) {
            loCommand[i+1] = loOptions.get(i);
        }

        if (loAcceptOption != null)
            loCommand[arguments-1] = loAcceptOption;

        // start office process
        loProcess = Runtime.getRuntime().exec(loCommand);

        pipe(loProcess.getInputStream(), System.out, "CO> ");
        pipe(loProcess.getErrorStream(), System.err, "CE> ");
    }

    /**
     * Kills the lo server process from the previous start.
     * 
     * If there has been no previous start of the lo server, the kill does
     * nothing.
     * 
     * If there has been a previous start, kill destroys the process.
     */
    public void kill() {
        if (loProcess != null) {
            loProcess.destroy();
            loProcess = null;
        }
    }

    private static void pipe(final InputStream in, final PrintStream out, final String prefix ) {
       new Thread( "Pipe: " + prefix) {
            @Override
            public void run() {
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                try {
                    for ( ; ; ) {
                        String s = r.readLine();
                        if (s == null) {
                            break;
                        }
                        out.println(prefix + s);
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }.start();
    }

    /**
     * Returns the list of default options.
     * 
     * @return     The list of default options
     */
    public static List<String> getDefaultOptions() {

        List<String> options = new ArrayList<>();
        options.add("--nologo");
        options.add("--nodefault");
        options.add("--norestore");
        options.add("--nocrashreport");
        options.add("--nolockcheck");
        return options;
    }
}