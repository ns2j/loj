package loj.my;

import com.sun.star.beans.Property;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;

import loproxy.beans.P_PropertySet;

/**
 * Wrapper for a UNO property set.
 */
public class Prop extends P_PropertySet {
    /**
     * Constructs a Prop from a UNO object.
     *
     * @param object the UNO property set object
     */
    public Prop(Object object) {
        super(object);
    }
    
    /**
     * Factory method to create a Prop instance.
     *
     * @param object the UNO property set object
     * @return a new Prop instance
     */
    public static Prop of(Object object) {
    //    Util.writeType("proxy.properties", object, "property set");
        return new Prop(object);
    }
    
    /**
     * Copies properties from this Prop to another property set object.
     *
     * @param toProp the destination property set object
     */
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
    
    /**
     * Gets a property value by name.
     *
     * @param name the property name
     * @return the property value
     * @throws UnknownPropertyException if the property is unknown
     * @throws WrappedTargetException if an error occurs while retrieving
     */
    public Object get(String name) throws UnknownPropertyException, WrappedTargetException {
        return getPropertyValue(name);
    }
    
    /**
     * Sets a property value by name.
     *
     * @param name the property name
     * @param value the property value
     * @return this Prop
     * @throws IllegalArgumentException if the argument is illegal
     * @throws UnknownPropertyException if the property is unknown
     * @throws PropertyVetoException if setting is vetoed
     * @throws WrappedTargetException if an error occurs while setting
     */
    public Prop set(String name, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(name, value);
        return this;
    }
}
