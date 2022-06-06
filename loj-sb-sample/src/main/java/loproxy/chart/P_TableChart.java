package loproxy.chart;

import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XFastPropertySet;
import com.sun.star.beans.XMultiPropertySet;
import com.sun.star.beans.XPropertiesChangeListener;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.XNamed;
import com.sun.star.document.XEmbeddedObjectSupplier;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XTableChart;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_TableChart implements Proxiable, XTableChart, XMultiPropertySet, XPropertySet, XComponent, XFastPropertySet, XNamed, XTypeProvider, XEmbeddedObjectSupplier, XServiceInfo, XWeak {
    protected Object proxy;

    public P_TableChart(Object object) {this.proxy = object;}

    public P_TableChart qi(Object object) {
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

    public P_TableChart prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public boolean getHasRowHeaders ()  {return qi(XTableChart.class, proxy).getHasRowHeaders();}
    @Override public boolean getHasColumnHeaders ()  {return qi(XTableChart.class, proxy).getHasColumnHeaders();}
    @Override public void setHasRowHeaders (boolean arg0)  {qi(XTableChart.class, proxy).setHasRowHeaders(arg0);}
    @Override public void setRanges (CellRangeAddress[] arg0)  {qi(XTableChart.class, proxy).setRanges(arg0);}
    @Override public void setHasColumnHeaders (boolean arg0)  {qi(XTableChart.class, proxy).setHasColumnHeaders(arg0);}
    @Override public CellRangeAddress[] getRanges ()  {return qi(XTableChart.class, proxy).getRanges();}
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
    @Override public void dispose ()  {qi(XComponent.class, proxy).dispose();}
    @Override public void addEventListener (XEventListener arg0)  {qi(XComponent.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(XComponent.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setFastPropertyValue (int arg0, Object arg1) throws UnknownPropertyException, PropertyVetoException, IllegalArgumentException, WrappedTargetException  {qi(XFastPropertySet.class, proxy).setFastPropertyValue(arg0, arg1);}
    @Override public Object getFastPropertyValue (int arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XFastPropertySet.class, proxy).getFastPropertyValue(arg0);}
    @Override public String getName ()  {return qi(XNamed.class, proxy).getName();}
    @Override public void setName (String arg0)  {qi(XNamed.class, proxy).setName(arg0);}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public XComponent getEmbeddedObject ()  {return qi(XEmbeddedObjectSupplier.class, proxy).getEmbeddedObject();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
