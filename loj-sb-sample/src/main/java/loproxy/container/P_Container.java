package loproxy.container;

import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.ElementExistException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XIndexAccess;
import com.sun.star.container.XNameContainer;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Container implements Proxiable, XPropertySet, XNameContainer, XIndexAccess, XTypeProvider, XServiceInfo, XWeak {
    protected Object proxy;

    public P_Container(Object object) {this.proxy = object;}

    public P_Container qi(Object object) {
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

    public P_Container prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public XPropertySetInfo getPropertySetInfo ()  {return qi(XPropertySet.class, proxy).getPropertySetInfo();}
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
    @Override public void removeByName (String arg0) throws NoSuchElementException, WrappedTargetException  {qi(XNameContainer.class, proxy).removeByName(arg0);}
    @Override public void insertByName (String arg0, Object arg1) throws IllegalArgumentException, ElementExistException, WrappedTargetException  {qi(XNameContainer.class, proxy).insertByName(arg0, arg1);}
    @Override public void replaceByName (String arg0, Object arg1) throws IllegalArgumentException, NoSuchElementException, WrappedTargetException  {qi(XNameContainer.class, proxy).replaceByName(arg0, arg1);}
    @Override public Object getByName (String arg0) throws NoSuchElementException, WrappedTargetException  {return qi(XNameContainer.class, proxy).getByName(arg0);}
    @Override public String[] getElementNames ()  {return qi(XNameContainer.class, proxy).getElementNames();}
    @Override public boolean hasByName (String arg0)  {return qi(XNameContainer.class, proxy).hasByName(arg0);}
    @Override public Type getElementType ()  {return qi(XNameContainer.class, proxy).getElementType();}
    @Override public boolean hasElements ()  {return qi(XNameContainer.class, proxy).hasElements();}
    @Override public int getCount ()  {return qi(XIndexAccess.class, proxy).getCount();}
    @Override public Object getByIndex (int arg0) throws IndexOutOfBoundsException, WrappedTargetException  {return qi(XIndexAccess.class, proxy).getByIndex(arg0);}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
