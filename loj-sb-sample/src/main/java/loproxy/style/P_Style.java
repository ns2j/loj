package loproxy.style;

import com.sun.star.beans.PropertyState;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XMultiPropertySet;
import com.sun.star.beans.XMultiPropertyStates;
import com.sun.star.beans.XPropertiesChangeListener;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XPropertyState;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.lang.XUnoTunnel;
import com.sun.star.style.XStyle;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Style implements Proxiable, XMultiPropertySet, XMultiPropertyStates, XPropertySet, XUnoTunnel, XStyle, XPropertyState, XTypeProvider, XServiceInfo, XWeak {
    protected Object proxy;

    public P_Style(Object object) {this.proxy = object;}

    public P_Style qi(Object object) {
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

    public P_Style prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
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
    @Override public PropertyState[] getPropertyStates (String[] arg0) throws UnknownPropertyException  {return qi(XMultiPropertyStates.class, proxy).getPropertyStates(arg0);}
    @Override public void setAllPropertiesToDefault ()  {qi(XMultiPropertyStates.class, proxy).setAllPropertiesToDefault();}
    @Override public void setPropertiesToDefault (String[] arg0) throws UnknownPropertyException  {qi(XMultiPropertyStates.class, proxy).setPropertiesToDefault(arg0);}
    @Override public Object[] getPropertyDefaults (String[] arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XMultiPropertyStates.class, proxy).getPropertyDefaults(arg0);}
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
    @Override public long getSomething (byte[] arg0)  {return qi(XUnoTunnel.class, proxy).getSomething(arg0);}
    @Override public boolean isUserDefined ()  {return qi(XStyle.class, proxy).isUserDefined();}
    @Override public boolean isInUse ()  {return qi(XStyle.class, proxy).isInUse();}
    @Override public String getParentStyle ()  {return qi(XStyle.class, proxy).getParentStyle();}
    @Override public void setParentStyle (String arg0) throws NoSuchElementException  {qi(XStyle.class, proxy).setParentStyle(arg0);}
    @Override public String getName ()  {return qi(XStyle.class, proxy).getName();}
    @Override public void setName (String arg0)  {qi(XStyle.class, proxy).setName(arg0);}
    @Override public PropertyState getPropertyState (String arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyState(arg0);}
    @Override public void setPropertyToDefault (String arg0) throws UnknownPropertyException  {qi(XPropertyState.class, proxy).setPropertyToDefault(arg0);}
    @Override public Object getPropertyDefault (String arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XPropertyState.class, proxy).getPropertyDefault(arg0);}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
