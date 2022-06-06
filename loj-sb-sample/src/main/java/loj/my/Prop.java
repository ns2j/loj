package loj.my;

import com.sun.star.beans.Property;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;

import loproxy.beans.P_PropertySet;

public class Prop extends P_PropertySet {
    public Prop(Object object) {
        super(object);
    }
    
    public static Prop of(Object object) {
    //    Util.writeType("proxy.properties", object, "property set");
        return new Prop(object);
    }
    
    public void copy(Object toProp) {
        for (Property p: getPropertySetInfo().getProperties()) {
            Object val = null;
            try {
                val = get(p.Name);
            } catch (UnknownPropertyException | WrappedTargetException e) {
                e.printStackTrace();
                continue;
            }
            try {
                Prop.of(toProp).set(p.Name, val);
            } catch (IllegalArgumentException | UnknownPropertyException
                    | PropertyVetoException | WrappedTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Object get(String name) throws UnknownPropertyException, WrappedTargetException {
        return getPropertyValue(name);
    }
    
    public Prop set(String name, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(name, value);
        return this;
    }
}
