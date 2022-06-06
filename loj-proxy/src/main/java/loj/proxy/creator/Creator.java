package loj.proxy.creator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Creator extends HashMap<KeyClass, List<Method>>{
    Set<KeyClass> allClassies = new TreeSet<>();
    Set<KeyClass> simpleNameSet = new TreeSet<>();
    Set<KeyClass> canonicalNameSet = new TreeSet<>();

    List<KeyClass> importings = new ArrayList<>();
    ProxyMethods proxyMethods;

    boolean propertySet;
    
    public Creator() {
    }

    public void putRecursive(Class<?> c, KeyClass keyClass) {
        if (!c.isInterface() || !Modifier.isPublic(c.getModifiers())) return;

        if (c == com.sun.star.beans.XPropertySet.class)
            propertySet = true;
        
        if (keyClass == null) {
            keyClass = new KeyClass(c);
            put(keyClass, new ArrayList<>());
        }
        
        for (Method m: c.getDeclaredMethods()) {
            get(keyClass).add(m);
        }

        for (Class<?> ifc: c.getInterfaces())
            putRecursive(ifc, keyClass);
    }


    String getClassName(Class<?> c) {
        if (c.isPrimitive()) return c.getSimpleName();
        return simpleNameSet.contains(new KeyClass(c)) ? c.getSimpleName() : c.getCanonicalName();
    }

    void addClass(Class<?> c) {
        if (!c.isPrimitive())
            allClassies.add(new KeyClass(c));
    }
    void setToAllClassies() {
        forEach((kc, ms) -> {
            allClassies.add(kc);
            ms.forEach(m -> {
                Class<?> rc = m.getReturnType();
                addClass(rc);

                Class<?> cs[] = m.getParameterTypes();
                for (int i = 0; i < cs.length ; i++) {
                    Class<?> pc  = cs[i];
                    addClass(pc);
                }
                Class<?>[] es = m.getExceptionTypes();
                for (int i = 0; i < es.length; i++) {
                        Class<?> e = es[i];
                        addClass(e);
                }
            });                    
        });
    }
    
    boolean containsAll(KeyClass kc1, List<Method> ms1) {
        for (KeyClass kc2: keySet()) {
            List<Method> ms2 = get(kc2);
            if (kc1.equals(kc2)) continue;
            if (ms2.containsAll(ms1)) return true;
        }
        return false;
    }
    void setToImpls() {
        proxyMethods = new ProxyMethods(this::getClassName);
        forEach((kc1, ms1) -> {
            if (!containsAll(kc1, ms1))
                proxyMethods.put(kc1, ms1);
        });
    }

    void divideJavaLangClass() {
        allClassies.stream()
        .filter(kc -> !simpleNameSet.contains(kc) && !canonicalNameSet.contains(kc) &&
                kc.getPackageName().equals("java.lang"))
        .forEach(kc -> simpleNameSet.add(kc));
    }
    void divideClass() {
        allClassies.stream()
        .filter(kc -> !simpleNameSet.contains(kc) && !canonicalNameSet.contains(kc))
        .forEach(kc -> {
            simpleNameSet.add(kc);
            allClassies.stream()
            .filter(kc2 -> !kc.equals(kc2) && kc.getSimpleName().equals(kc2.getSimpleName()))
            .forEach(kc2 -> canonicalNameSet.add(kc2));
        });
    }
    void addToImportings() {
        simpleNameSet.stream()
        .filter(kc -> !kc.getPackageName().equals("java.lang"))
        .forEach(importings::add);
    }
    void divide() {
        divideJavaLangClass();
        divideClass();
        addToImportings();
    }

    String getProp(String className) {
        if (!propertySet) return "";
        return "\n    public " + className + " prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {\n" +
        "        setPropertyValue(key, value);\n" +
        "        return this;\n" +
        "    }\n";
    }
    
    public Result create(String packageName, String className) {
        Result r = new Result();

        setToAllClassies();
        if (size() == 0)
            return r;

        setToImpls();
        divide();

        r.packageName =  "package " + packageName + ";";
        r.importings = importings.stream().map(kc -> "import " + kc.getCanonicalName() + ";").collect(Collectors.joining("\n"));
        r.className = className;
        r.impls = proxyMethods.getImplsStr();
        r.methods = proxyMethods.getMethodsStr();
        r.prop = getProp(className);

        return r;
    }
}
