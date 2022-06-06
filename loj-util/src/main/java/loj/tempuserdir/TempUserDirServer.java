package loj.tempuserdir;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import loj.connector.LoServer;

public class TempUserDirServer extends LoServer{
    public String TEMP_APP_DIR = "/uno/";
    public String key = null;

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

    public File getTempUserDir() {
        File tmpDir = new File(System.getProperty("java.io.tmpdir"));
        File tmpAppDir = new File(tmpDir, TEMP_APP_DIR);
        //File tmpUserDir = new File(tmpAppDir,  System.getProperty("user.name"));
        //return tmpUserDir;
        return new File(tmpAppDir, key);
    }

    protected void deleteDir(File f) {
        if (!f.exists()) return;
        if (f.isFile()) f.delete();
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for(int i = 0; i < files.length; i++)
                deleteDir( files[i] );
            f.delete();
        }
    }
    @Override
    public void kill() {
        System.out.println("kill key: " + key);
        super.kill();
        deleteDir(getTempUserDir());
    }
}