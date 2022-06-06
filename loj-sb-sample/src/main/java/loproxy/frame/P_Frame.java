package loproxy.frame;

import com.sun.star.awt.FocusEvent;
import com.sun.star.awt.WindowEvent;
import com.sun.star.awt.XFocusListener;
import com.sun.star.awt.XTopWindowListener;
import com.sun.star.awt.XWindow;
import com.sun.star.awt.XWindowListener;
import com.sun.star.beans.Property;
import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.XNameContainer;
import com.sun.star.document.XActionLockable;
import com.sun.star.frame.DispatchDescriptor;
import com.sun.star.frame.DispatchInformation;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.frame.XController;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XDispatchProviderInterceptor;
import com.sun.star.frame.XDispatchRecorderSupplier;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XFrame2;
import com.sun.star.frame.XFrameActionListener;
import com.sun.star.frame.XFrames;
import com.sun.star.frame.XFramesSupplier;
import com.sun.star.frame.XTitle;
import com.sun.star.frame.XTitleChangeBroadcaster;
import com.sun.star.frame.XTitleChangeListener;
import com.sun.star.io.IOException;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XComponent;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.task.XStatusIndicator;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.URL;
import com.sun.star.util.XCloseListener;
import com.sun.star.util.XCloseable;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Frame implements Proxiable, XWindowListener, XActionLockable, XTopWindowListener, XTitle, XServiceInfo, XTitleChangeBroadcaster, XPropertySet, XComponentLoader, XFrame2, XTypeProvider, XFocusListener, XPropertySetInfo, XWeak, XCloseable {
    protected Object proxy;

    public P_Frame(Object object) {this.proxy = object;}

    public P_Frame qi(Object object) {
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

    public P_Frame prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public void windowResized (WindowEvent arg0)  {qi(XWindowListener.class, proxy).windowResized(arg0);}
    @Override public void windowMoved (WindowEvent arg0)  {qi(XWindowListener.class, proxy).windowMoved(arg0);}
    @Override public void windowShown (EventObject arg0)  {qi(XWindowListener.class, proxy).windowShown(arg0);}
    @Override public void windowHidden (EventObject arg0)  {qi(XWindowListener.class, proxy).windowHidden(arg0);}
    @Override public void disposing (EventObject arg0)  {qi(XWindowListener.class, proxy).disposing(arg0);}
    @Override public void setActionLocks (short arg0)  {qi(XActionLockable.class, proxy).setActionLocks(arg0);}
    @Override public short resetActionLocks ()  {return qi(XActionLockable.class, proxy).resetActionLocks();}
    @Override public boolean isActionLocked ()  {return qi(XActionLockable.class, proxy).isActionLocked();}
    @Override public void addActionLock ()  {qi(XActionLockable.class, proxy).addActionLock();}
    @Override public void removeActionLock ()  {qi(XActionLockable.class, proxy).removeActionLock();}
    @Override public void windowClosing (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowClosing(arg0);}
    @Override public void windowClosed (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowClosed(arg0);}
    @Override public void windowMinimized (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowMinimized(arg0);}
    @Override public void windowNormalized (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowNormalized(arg0);}
    @Override public void windowActivated (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowActivated(arg0);}
    @Override public void windowDeactivated (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowDeactivated(arg0);}
    @Override public void windowOpened (EventObject arg0)  {qi(XTopWindowListener.class, proxy).windowOpened(arg0);}
    @Override public void setTitle (String arg0)  {qi(XTitle.class, proxy).setTitle(arg0);}
    @Override public String getTitle ()  {return qi(XTitle.class, proxy).getTitle();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public void removeTitleChangeListener (XTitleChangeListener arg0)  {qi(XTitleChangeBroadcaster.class, proxy).removeTitleChangeListener(
            arg0 instanceof Proxiable ? qi(XTitleChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addTitleChangeListener (XTitleChangeListener arg0)  {qi(XTitleChangeBroadcaster.class, proxy).addTitleChangeListener(
            arg0 instanceof Proxiable ? qi(XTitleChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
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
    @Override public XComponent loadComponentFromURL (String arg0, String arg1, int arg2, PropertyValue[] arg3) throws IOException, IllegalArgumentException  {return qi(XComponentLoader.class, proxy).loadComponentFromURL(arg0, arg1, arg2, arg3);}
    @Override public XDispatchRecorderSupplier getDispatchRecorderSupplier ()  {return qi(XFrame2.class, proxy).getDispatchRecorderSupplier();}
    @Override public void setDispatchRecorderSupplier (XDispatchRecorderSupplier arg0)  {qi(XFrame2.class, proxy).setDispatchRecorderSupplier(
            arg0 instanceof Proxiable ? qi(XDispatchRecorderSupplier.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getLayoutManager ()  {return qi(XFrame2.class, proxy).getLayoutManager();}
    @Override public void setLayoutManager (Object arg0)  {qi(XFrame2.class, proxy).setLayoutManager(arg0);}
    @Override public XNameContainer getUserDefinedAttributes ()  {return qi(XFrame2.class, proxy).getUserDefinedAttributes();}
    @Override public XDispatch queryDispatch (URL arg0, String arg1, int arg2)  {return qi(XFrame2.class, proxy).queryDispatch(arg0, arg1, arg2);}
    @Override public XDispatch[] queryDispatches (DispatchDescriptor[] arg0)  {return qi(XFrame2.class, proxy).queryDispatches(arg0);}
    @Override public short[] getSupportedCommandGroups ()  {return qi(XFrame2.class, proxy).getSupportedCommandGroups();}
    @Override public DispatchInformation[] getConfigurableDispatchInformation (short arg0)  {return qi(XFrame2.class, proxy).getConfigurableDispatchInformation(arg0);}
    @Override public void registerDispatchProviderInterceptor (XDispatchProviderInterceptor arg0)  {qi(XFrame2.class, proxy).registerDispatchProviderInterceptor(
            arg0 instanceof Proxiable ? qi(XDispatchProviderInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void releaseDispatchProviderInterceptor (XDispatchProviderInterceptor arg0)  {qi(XFrame2.class, proxy).releaseDispatchProviderInterceptor(
            arg0 instanceof Proxiable ? qi(XDispatchProviderInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XFrames getFrames ()  {return qi(XFrame2.class, proxy).getFrames();}
    @Override public XFrame getActiveFrame ()  {return qi(XFrame2.class, proxy).getActiveFrame();}
    @Override public void setActiveFrame (XFrame arg0)  {qi(XFrame2.class, proxy).setActiveFrame(
            arg0 instanceof Proxiable ? qi(XFrame.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public String getName ()  {return qi(XFrame2.class, proxy).getName();}
    @Override public void initialize (XWindow arg0)  {qi(XFrame2.class, proxy).initialize(
            arg0 instanceof Proxiable ? qi(XWindow.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setName (String arg0)  {qi(XFrame2.class, proxy).setName(arg0);}
    @Override public boolean isActive ()  {return qi(XFrame2.class, proxy).isActive();}
    @Override public XFramesSupplier getCreator ()  {return qi(XFrame2.class, proxy).getCreator();}
    @Override public XWindow getContainerWindow ()  {return qi(XFrame2.class, proxy).getContainerWindow();}
    @Override public void setCreator (XFramesSupplier arg0)  {qi(XFrame2.class, proxy).setCreator(
            arg0 instanceof Proxiable ? qi(XFramesSupplier.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XFrame findFrame (String arg0, int arg1)  {return qi(XFrame2.class, proxy).findFrame(arg0, arg1);}
    @Override public boolean isTop ()  {return qi(XFrame2.class, proxy).isTop();}
    @Override public void deactivate ()  {qi(XFrame2.class, proxy).deactivate();}
    @Override public boolean setComponent (XWindow arg0, XController arg1)  {return qi(XFrame2.class, proxy).setComponent(
            arg0 instanceof Proxiable ? qi(XWindow.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public XWindow getComponentWindow ()  {return qi(XFrame2.class, proxy).getComponentWindow();}
    @Override public XController getController ()  {return qi(XFrame2.class, proxy).getController();}
    @Override public void contextChanged ()  {qi(XFrame2.class, proxy).contextChanged();}
    @Override public void addFrameActionListener (XFrameActionListener arg0)  {qi(XFrame2.class, proxy).addFrameActionListener(
            arg0 instanceof Proxiable ? qi(XFrameActionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeFrameActionListener (XFrameActionListener arg0)  {qi(XFrame2.class, proxy).removeFrameActionListener(
            arg0 instanceof Proxiable ? qi(XFrameActionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void activate ()  {qi(XFrame2.class, proxy).activate();}
    @Override public void dispose ()  {qi(XFrame2.class, proxy).dispose();}
    @Override public void addEventListener (XEventListener arg0)  {qi(XFrame2.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(XFrame2.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XStatusIndicator createStatusIndicator ()  {return qi(XFrame2.class, proxy).createStatusIndicator();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public void focusGained (FocusEvent arg0)  {qi(XFocusListener.class, proxy).focusGained(arg0);}
    @Override public void focusLost (FocusEvent arg0)  {qi(XFocusListener.class, proxy).focusLost(arg0);}
    @Override public Property[] getProperties ()  {return qi(XPropertySetInfo.class, proxy).getProperties();}
    @Override public Property getPropertyByName (String arg0) throws UnknownPropertyException  {return qi(XPropertySetInfo.class, proxy).getPropertyByName(arg0);}
    @Override public boolean hasPropertyByName (String arg0)  {return qi(XPropertySetInfo.class, proxy).hasPropertyByName(arg0);}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
    @Override public void close (boolean arg0) throws CloseVetoException  {qi(XCloseable.class, proxy).close(arg0);}
    @Override public void removeCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).removeCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).addCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
}
    
