package loj.proxy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;

import loj.proxy.creator.Creator;
import loj.proxy.creator.Result;

public class ProxyCreator {

    static Creator getCreator(String[] canonicalNames) {
        Creator creator = new Creator();
        for (int i = 0; i < canonicalNames.length; i++) {
            String cn = canonicalNames[i];
            Class<?> c = null;
            try {
                c = Class.forName(cn);
                creator.putRecursive(c, null);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        return creator; 
    }
    static void write(File file, Result r) throws IOException {
        String str = getTemplateStr()
                .replaceAll("__PACKAGE__", r.packageName)
                .replaceAll("__IMPORTS__", r.importings)
                .replaceAll("__CLASS__", r.className)
                .replaceAll("__IMPLEMENTS__", r.impls)
                .replaceAll("__PROPERTYSET__", r.prop)
                .replaceAll("__METHODS__", r.methods);
                
        try (PrintStream ps = new PrintStream(new FileOutputStream(file));){
            ps.print(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
    
    public static String getTemplateStr() throws IOException {
        String templName = "template.txt";
        StringBuilder sb = new StringBuilder();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(templName);
                InputStreamReader isr =  new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);){
            String l;
            while ((l = br.readLine()) != null) {
                sb.append(l);
                sb.append("\n");
            }
        }
        return "" + sb;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("cmd outDir propFile");
            System.exit(1);
        }

        Properties ps = new Properties();
        try {
            ps.load(new FileInputStream(new File(args[1])));
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(2);
        }
        
        ps.forEach((k, v) -> {
            var cns  = ((String)v).split(" ");
            try {
                var creator = getCreator(cns);

                String outCanonicalName = "" + k;
                int ind = outCanonicalName.lastIndexOf('.');
                String pn = outCanonicalName.substring(0, ind);
                String cn = outCanonicalName.substring(ind + 1, outCanonicalName.length());

                var r = creator.create(pn, cn);
                File dir = new File(args[0] + "/" + pn.replace(".", "/"));
                dir.mkdirs();
                File file = new File(dir, "/" + cn + ".java");
                write(file, r);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(3);
            }
        });
    }
}