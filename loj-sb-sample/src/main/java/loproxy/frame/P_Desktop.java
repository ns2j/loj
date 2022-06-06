package loproxy.frame;

import com.sun.star.awt.XWindow;
import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XFastPropertySet;
import com.sun.star.beans.XMultiPropertySet;
import com.sun.star.beans.XPropertiesChangeListener;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.frame.DispatchDescriptor;
import com.sun.star.frame.DispatchResultEvent;
import com.sun.star.frame.XController;
import com.sun.star.frame.XDesktop2;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XDispatchProviderInterceptor;
import com.sun.star.frame.XDispatchResultListener;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XFrameActionListener;
import com.sun.star.frame.XFrames;
import com.sun.star.frame.XFramesSupplier;
import com.sun.star.frame.XTask;
import com.sun.star.frame.XTasksSupplier;
import com.sun.star.frame.XTerminateListener;
import com.sun.star.frame.XUntitledNumbers;
import com.sun.star.io.IOException;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.task.XInteractionHandler;
import com.sun.star.task.XInteractionRequest;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.util.URL;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Desktop implements Proxiable, XMultiPropertySet, XPropertySet, XFastPropertySet, XUntitledNumbers, XTasksSupplier, XTypeProvider, XInteractionHandler, XDesktop2, XServiceInfo, XDispatchResultListener, XWeak {
    protected Object proxy;

    public P_Desktop(Object object) {this.proxy = object;}

    public P_Desktop qi(Object object) {
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

    public P_Desktop prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
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
    @Override public void setFastPropertyValue (int arg0, Object arg1) throws UnknownPropertyException, PropertyVetoException, IllegalArgumentException, WrappedTargetException  {qi(XFastPropertySet.class, proxy).setFastPropertyValue(arg0, arg1);}
    @Override public Object getFastPropertyValue (int arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XFastPropertySet.class, proxy).getFastPropertyValue(arg0);}
    @Override public String getUntitledPrefix ()  {return qi(XUntitledNumbers.class, proxy).getUntitledPrefix();}
    @Override public int leaseNumber (Object arg0) throws IllegalArgumentException  {return qi(XUntitledNumbers.class, proxy).leaseNumber(arg0);}
    @Override public void releaseNumber (int arg0) throws IllegalArgumentException  {qi(XUntitledNumbers.class, proxy).releaseNumber(arg0);}
    @Override public void releaseNumberForComponent (Object arg0) throws IllegalArgumentException  {qi(XUntitledNumbers.class, proxy).releaseNumberForComponent(arg0);}
    @Override public XTask getActiveTask ()  {return qi(XTasksSupplier.class, proxy).getActiveTask();}
    @Override public XEnumerationAccess getTasks ()  {return qi(XTasksSupplier.class, proxy).getTasks();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public void handle (XInteractionRequest arg0)  {qi(XInteractionHandler.class, proxy).handle(
            arg0 instanceof Proxiable ? qi(XInteractionRequest.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XDispatch queryDispatch (URL arg0, String arg1, int arg2)  {return qi(XDesktop2.class, proxy).queryDispatch(arg0, arg1, arg2);}
    @Override public XDispatch[] queryDispatches (DispatchDescriptor[] arg0)  {return qi(XDesktop2.class, proxy).queryDispatches(arg0);}
    @Override public void registerDispatchProviderInterceptor (XDispatchProviderInterceptor arg0)  {qi(XDesktop2.class, proxy).registerDispatchProviderInterceptor(
            arg0 instanceof Proxiable ? qi(XDispatchProviderInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void releaseDispatchProviderInterceptor (XDispatchProviderInterceptor arg0)  {qi(XDesktop2.class, proxy).releaseDispatchProviderInterceptor(
            arg0 instanceof Proxiable ? qi(XDispatchProviderInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XFrames getFrames ()  {return qi(XDesktop2.class, proxy).getFrames();}
    @Override public XFrame getActiveFrame ()  {return qi(XDesktop2.class, proxy).getActiveFrame();}
    @Override public void setActiveFrame (XFrame arg0)  {qi(XDesktop2.class, proxy).setActiveFrame(
            arg0 instanceof Proxiable ? qi(XFrame.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public String getName ()  {return qi(XDesktop2.class, proxy).getName();}
    @Override public void initialize (XWindow arg0)  {qi(XDesktop2.class, proxy).initialize(
            arg0 instanceof Proxiable ? qi(XWindow.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setName (String arg0)  {qi(XDesktop2.class, proxy).setName(arg0);}
    @Override public boolean isActive ()  {return qi(XDesktop2.class, proxy).isActive();}
    @Override public XFramesSupplier getCreator ()  {return qi(XDesktop2.class, proxy).getCreator();}
    @Override public XWindow getContainerWindow ()  {return qi(XDesktop2.class, proxy).getContainerWindow();}
    @Override public void setCreator (XFramesSupplier arg0)  {qi(XDesktop2.class, proxy).setCreator(
            arg0 instanceof Proxiable ? qi(XFramesSupplier.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XFrame findFrame (String arg0, int arg1)  {return qi(XDesktop2.class, proxy).findFrame(arg0, arg1);}
    @Override public boolean isTop ()  {return qi(XDesktop2.class, proxy).isTop();}
    @Override public void deactivate ()  {qi(XDesktop2.class, proxy).deactivate();}
    @Override public boolean setComponent (XWindow arg0, XController arg1)  {return qi(XDesktop2.class, proxy).setComponent(
            arg0 instanceof Proxiable ? qi(XWindow.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public XWindow getComponentWindow ()  {return qi(XDesktop2.class, proxy).getComponentWindow();}
    @Override public XController getController ()  {return qi(XDesktop2.class, proxy).getController();}
    @Override public void contextChanged ()  {qi(XDesktop2.class, proxy).contextChanged();}
    @Override public void addFrameActionListener (XFrameActionListener arg0)  {qi(XDesktop2.class, proxy).addFrameActionListener(
            arg0 instanceof Proxiable ? qi(XFrameActionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeFrameActionListener (XFrameActionListener arg0)  {qi(XDesktop2.class, proxy).removeFrameActionListener(
            arg0 instanceof Proxiable ? qi(XFrameActionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void activate ()  {qi(XDesktop2.class, proxy).activate();}
    @Override public void dispose ()  {qi(XDesktop2.class, proxy).dispose();}
    @Override public void addEventListener (XEventListener arg0)  {qi(XDesktop2.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(XDesktop2.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addTerminateListener (XTerminateListener arg0)  {qi(XDesktop2.class, proxy).addTerminateListener(
            arg0 instanceof Proxiable ? qi(XTerminateListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean terminate ()  {return qi(XDesktop2.class, proxy).terminate();}
    @Override public void removeTerminateListener (XTerminateListener arg0)  {qi(XDesktop2.class, proxy).removeTerminateListener(
            arg0 instanceof Proxiable ? qi(XTerminateListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XComponent getCurrentComponent ()  {return qi(XDesktop2.class, proxy).getCurrentComponent();}
    @Override public XFrame getCurrentFrame ()  {return qi(XDesktop2.class, proxy).getCurrentFrame();}
    @Override public XEnumerationAccess getComponents ()  {return qi(XDesktop2.class, proxy).getComponents();}
    @Override public XComponent loadComponentFromURL (String arg0, String arg1, int arg2, PropertyValue[] arg3) throws IOException, IllegalArgumentException  {return qi(XDesktop2.class, proxy).loadComponentFromURL(arg0, arg1, arg2, arg3);}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public void dispatchFinished (DispatchResultEvent arg0)  {qi(XDispatchResultListener.class, proxy).dispatchFinished(arg0);}
    @Override public void disposing (EventObject arg0)  {qi(XDispatchResultListener.class, proxy).disposing(arg0);}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
