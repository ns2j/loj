package loj.my;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sun.star.beans.Property;
import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertySet;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.uno.Type;
import com.sun.star.uno.UnoRuntime;

import loj.proxy.Proxiable;

public class Util {
    public static <T> T qi(Class<T> inteface, Object object) {
        return UnoRuntime.queryInterface(inteface, object);
    }
    
    public static PropertyValue[] getPropertyValueArray(Map<String, Object> map) {
        return map.keySet().stream().map(k -> {
            PropertyValue pv = new PropertyValue();
            pv.Name = k;
            pv.Value = map.get(k);
            return pv;
        }).toArray(size -> new PropertyValue[size]);
    }

    public static int getColor(String cs) {
        return Optional.ofNullable(cs)
        .filter(s -> s.length() > 6 && s.length() < 10)
        .filter(s -> s.charAt(0) == '#')
        .map(s -> s.toUpperCase().substring(1))
        .map(s -> Integer.valueOf(s, 16))
        .orElseGet(() -> 0);
    }
    
    public static String getTypeProviderStr(Object o) {
        XTypeProvider tp;
        if (o instanceof Proxiable)
            tp = qi(XTypeProvider.class, ((Proxiable)o).getProxy());
        else
            tp = qi(XTypeProvider.class, o);
        if (tp == null) return "target object does not have XTypeProvider ingerface";

        List<String> list = new ArrayList<>();
        for (Type t: tp.getTypes())
            list.add(t.getTypeName());
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        StringBuilder line = new StringBuilder();
        line.append("=");
        for (String s: list) {
            line.append(s);
            if (line.length() > 85) {
                sb.append(line + " \\\n");
                line = new StringBuilder();
            } else {
                line.append(" ");
            }
        }
        sb.append(line + "\n");   
        return "" + sb;
    }
    
    public static void writeType(String filename, Object o, String comment) {
        String str = "#" + comment + "\n" + getTypeProviderStr(o);
        File f = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            fos.write(str.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getServiceInfoStr(Object o) {
        XServiceInfo si;
        if (o instanceof Proxiable)
            si = qi(XServiceInfo.class, ((Proxiable)o).getProxy());
        else
            si = qi(XServiceInfo.class, o);
        if (si == null) return "target object does not have XServiceInfo ingerface";

        StringBuilder sb = new StringBuilder();
        sb.append(si.getImplementationName() + "\n");
        List<String> list = new ArrayList<>();
        for (var ssn: si.getSupportedServiceNames())
            list.add(ssn);
        Collections.sort(list);
        list.forEach(s -> sb.append(s + "\n"));
        return "" + sb;
        
    }
        
    public static String getPropsStr(Object o) throws UnknownPropertyException, WrappedTargetException {
        XPropertySet ps;
        if (o instanceof Proxiable)
            ps = qi(XPropertySet.class, ((Proxiable)o).getProxy());
        else
            ps = qi(XPropertySet.class, o);
        if (ps == null) return "target object does not have XPropertySet ingerface";

        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (Property p: ps.getPropertySetInfo().getProperties()) {
            try {
                list.add(p.Name + ": " + ps.getPropertyValue(p.Name));
            } catch (UnknownPropertyException e) {
                System.err.println("UnknownPropertyException: prop name is " + p.Name);
                int i = 0;
                for (StackTraceElement ste: e.getStackTrace()) {
                    if (i > 15) break;
                    System.err.println("" + ste);
                    i++;
                }
            }
        }
        Collections.sort(list);
        list.forEach(s ->  {
             sb.append(s);
             sb.append("\n");
        });
        return "" + sb;
    }
}
