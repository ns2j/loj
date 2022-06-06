package loproxy.beans;

import com.sun.star.beans.GetDirectPropertyTolerantResult;
import com.sun.star.beans.GetPropertyTolerantResult;
import com.sun.star.beans.PropertyState;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.SetPropertyTolerantFailed;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XMultiPropertySet;
import com.sun.star.beans.XPropertiesChangeListener;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XPropertyState;
import com.sun.star.beans.XTolerantMultiPropertySet;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_PropertySet implements Proxiable, XMultiPropertySet, XPropertySet, XPropertyState, XTolerantMultiPropertySet {
    protected Object proxy;

    public P_PropertySet(Object object) {this.proxy = object;}

    public P_PropertySet qi(Object object) {
        return UnoRuntime.queryInterface(getClass(), object);
    }

    public <S> S qi(Class<S> zInterface) {
        return UnoRuntime.queryInterface(zInterface, proxy);
    }
    public <S> S qi(Class<S> zInterface, Object object) {
        if (object instanceof Proxiable)
            return UnoRuntime.queryInterface(zInterface, ((Proxiable)object).getProxy());
        return UnoRuntime.queryInterface(zInterface, object);
    }

    @Override public Object getProxy() {return proxy;}

    public P_PropertySet prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public XPropertySetInfo getPropertySetInfo ()  {return qi(XMultiPropertySet.class, proxy).getPropertySetInfo();}
    @Override public void setPropertyValues (String[] arg0, Object[] arg1) throws PropertyVetoException, IllegalArgumentException, WrappedTargetException  {qi(XMultiPropertySet.class, proxy).setPropertyValues(arg0, arg1);}
    @Override public Object[] getPropertyValues (String[] arg0)  {return qi(XMultiPropertySet.class, proxy).getPropertyValues(arg0);}
    @Override public void addPropertiesChangeListener (String[] arg0, XPropertiesChangeListener arg1)  {qi(XMultiPropertySet.class, proxy).addPropertiesChangeListener(arg0, 
            arg1 instanceof Proxiable ? qi(XPropertiesChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void removePropertiesChangeListener (XPropertiesChangeListener arg0)  {qi(XMultiPropertySet.class, proxy).removePropertiesChangeListener(
            arg0 instanceof Proxiable ? qi(XPropertiesChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void firePropertiesChangeEvent (String[] arg0, XPropertiesChangeListener arg1)  {qi(XMultiPropertySet.class, proxy).firePropertiesChangeEvent(arg0, 
            arg1 instanceof Proxiable ? qi(XPropertiesChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void setPropertyValue (String arg0, Object arg1) throws UnknownPropertyException, PropertyVetoException, IllegalArgumentException, WrappedTargetException  {qi(XPropertySet.class, proxy).setPropertyValue(arg0, arg1);}
    @Override public void addPropertyChangeListener (String arg0, XPropertyChangeListener arg1) throws UnknownPropertyException, WrappedTargetException  {qi(XPropertySet.class, proxy).addPropertyChangeListener(arg0, 
            arg1 instanceof Proxiable ? qi(XPropertyChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void removePropertyChangeListener (String arg0, XPropertyChangeListener arg1) throws UnknownPropertyException, WrappedTargetException  {qi(XPropertySet.class, proxy).removePropertyChangeListener(arg0, 
            arg1 instanceof Proxiable ? qi(XPropertyChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void addVetoableChangeListener (String arg0, XVetoableChangeListener arg1) throws UnknownPropertyException, WrappedTargetException  {qi(XPropertySet.class, proxy).addVetoableChangeListener(arg0, 
            arg1 instanceof Proxiable ? qi(XVetoableChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void removeVetoableChangeListener (String arg0, XVetoableChangeListener arg1) throws UnknownPropertyException, WrappedTargetException  {qi(XPropertySet.class, proxy).removeVetoableChangeListener(arg0, 
            arg1 instanceof Proxiable ? qi(XVetoableChangeListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public Object getPropertyValue (String arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XPropertySet.class, proxy).getPropertyValue(arg0);}
    @Override public PropertyState getPropertyState (String arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyState(arg0);}
    @Override public PropertyState[] getPropertyStates (String[] arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyStates(arg0);}
    @Override public void setPropertyToDefault (String arg0) throws UnknownPropertyException  {qi(XPropertyState.class, proxy).setPropertyToDefault(arg0);}
    @Override public Object getPropertyDefault (String arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XPropertyState.class, proxy).getPropertyDefault(arg0);}
    @Override public SetPropertyTolerantFailed[] setPropertyValuesTolerant (String[] arg0, Object[] arg1) throws IllegalArgumentException  {return qi(XTolerantMultiPropertySet.class, proxy).setPropertyValuesTolerant(arg0, arg1);}
    @Override public GetPropertyTolerantResult[] getPropertyValuesTolerant (String[] arg0)  {return qi(XTolerantMultiPropertySet.class, proxy).getPropertyValuesTolerant(arg0);}
    @Override public GetDirectPropertyTolerantResult[] getDirectPropertyValuesTolerant (String[] arg0)  {return qi(XTolerantMultiPropertySet.class, proxy).getDirectPropertyValuesTolerant(arg0);}
}
    
