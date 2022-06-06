package loj.proxy.creator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sun.star.uno.XInterface;

public class ProxyMethods extends HashMap<KeyClass, List<Method>>{
    Function<Class<?>, String> classNameFunc;
    
    public ProxyMethods(Function<Class<?>, String> classNameFunc) {
        this.classNameFunc = classNameFunc;
    }
    
    boolean containsXInterface(Class<?> c) {
        if (c == XInterface.class) return true;
        if (c.getInterfaces().length > 0)
            for (Class<?> cc: c.getInterfaces())
                if (containsXInterface(cc))
                    return true;
        return false;
    }

    String getClassName(Class<?> c) {
        return classNameFunc.apply(c);
    }
    
    boolean methodEquals(Method m1, Method m2) {
        if (m1.equals(m2)) return true;
        if (!m1.getName().equals(m2.getName())) return false;
        if (m1.getReturnType() != m2.getReturnType()) return false;
        Class<?> cs1[] = m1.getParameterTypes();
        Class<?> cs2[] = m2.getParameterTypes();
        if (cs1.length != cs2.length) return false;
        for (int i = 0; i < cs1.length ; i++)
            if (cs1[i] != cs2[i]) return false;
        return true;
    }
    
    List<String> getMethodEachStr(KeyClass kc, List<Method> ms, Set<Method> doneMethods) {
        List<String> methodStrs = new ArrayList<>();
        ms.forEach(m -> {
            if (doneMethods.contains(m)) return;
            for (Method m2: doneMethods)
                if (methodEquals(m, m2))
                    return;
            doneMethods.add(m);

            Class<?> cs[] = m.getParameterTypes();
            Parameter[] ps = m.getParameters();
            String paramDeclareStr = "";
            String paramCallerStr = "";
            for (int i = 0; i < cs.length ; i++) {
                Class<?> pc  = cs[i];
                if (i != 0) {
                    paramDeclareStr += ", ";
                    paramCallerStr += ", ";
                }
                paramDeclareStr +=  (ps[i].isVarArgs() ? getClassName(pc) + "..." : getClassName(pc)) +
                        " arg" + i;
                if (containsXInterface(pc)) {
                    paramCallerStr += ("\n            __ARG__ instanceof Proxiable ? " +
                            "qi(__CLASSNAME__.class, ((Proxiable)__ARG__).getProxy()) : __ARG__\n            ")
                            .replaceAll("__ARG__", "arg" + i)
                            .replaceAll("__CLASSNAME__", getClassName(pc));
                } else {
                    paramCallerStr += "arg" + i;
                }
            }
            String exception = "";
            Class<?>[] es = m.getExceptionTypes();
            for (int i = 0; i < es.length; i++) {
                Class<?> e = es[i];
                if (i != 0) exception += ", ";
                exception += getClassName(e);
            }
            if (es.length != 0)
                exception = "throws " + exception + " "; 

            String returnStr = m.getReturnType() == void.class ? "": "return ";
            String execStr = String.format("qi(%s.class, proxy).%s(%s);", 
                    getClassName(kc.clazz), m.getName(), paramCallerStr);

            methodStrs.add(String.format("    @Override public %s %s (%s) %s {%s%s}",
                    getClassName(m.getReturnType()), m.getName(), paramDeclareStr, exception, returnStr, execStr));

        });
        return methodStrs;
    }
    public String getMethodsStr() {
        Set<Method> doneMethods = new HashSet<>();
        List<String> methodStrs = new ArrayList<>();
        forEach((kc, ms) -> methodStrs.addAll(getMethodEachStr(kc, ms, doneMethods)));
        return methodStrs.stream().collect(Collectors.joining("\n"));
    }

    public String getImplsStr() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (KeyClass kc: keySet()) {
            if (kc.getPackageName().equals("java.lang")) break; 
            sb.append(getClassName(kc.clazz));
            if (size() != 1 && i != size() - 1)
                sb.append(", ");
            i++;
        }
        return "" + sb;
    }


}
