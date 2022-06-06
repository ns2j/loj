package loj.proxy.creator;

public class KeyClass implements Comparable<KeyClass> {
    Class<?> clazz;

    public KeyClass(Class<?> clazz) {
        this.clazz = getArrayComponentType(clazz);
    }

    Class<?> getArrayComponentType(Class<?> c) {
        Class<?> cc;
        for (cc = c; cc.isArray(); cc = cc.getComponentType()) {
        };
        return cc;
    }

    public String getPackageName() {
        return clazz.getPackageName();
    }
    public String getCanonicalName() {
        return clazz.getCanonicalName();
    }
    public String getSimpleName() {
        return clazz.getSimpleName();
    }
    
    @Override public int hashCode() {return clazz.hashCode();}
    @Override public boolean equals(Object o) {
	if (o == null) return false;
	KeyClass cf = (KeyClass)o;
	return clazz.equals(cf.clazz);
    }
    @Override public int compareTo(KeyClass cf) {
        if (clazz.getCanonicalName() == null) return 1;
        if (cf == null) return 1;
        if (cf.clazz.getCanonicalName() == null) return 1;
        return clazz.getCanonicalName().compareTo(cf.clazz.getCanonicalName());
    }
}
