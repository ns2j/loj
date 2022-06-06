package loproxy.chart;

import com.sun.star.awt.Size;
import com.sun.star.awt.XRequestCallback;
import com.sun.star.beans.PropertyState;
import com.sun.star.beans.PropertyValue;
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
import com.sun.star.chart.XChartData;
import com.sun.star.chart.XChartDocument;
import com.sun.star.chart.XDiagram;
import com.sun.star.chart2.XChartTypeManager;
import com.sun.star.chart2.XTitle;
import com.sun.star.chart2.XTitled;
import com.sun.star.chart2.data.XDataProvider;
import com.sun.star.chart2.data.XDataReceiver;
import com.sun.star.chart2.data.XDataSource;
import com.sun.star.chart2.data.XLabeledDataSequence;
import com.sun.star.chart2.data.XRangeHighlighter;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XChild;
import com.sun.star.datatransfer.DataFlavor;
import com.sun.star.datatransfer.UnsupportedFlavorException;
import com.sun.star.datatransfer.XTransferable;
import com.sun.star.document.XDocumentProperties;
import com.sun.star.document.XDocumentPropertiesSupplier;
import com.sun.star.document.XStorageBasedDocument;
import com.sun.star.document.XStorageChangeListener;
import com.sun.star.document.XUndoManager;
import com.sun.star.document.XUndoManagerSupplier;
import com.sun.star.drawing.XDrawPage;
import com.sun.star.drawing.XDrawPageSupplier;
import com.sun.star.drawing.XShape;
import com.sun.star.embed.VisualRepresentation;
import com.sun.star.embed.WrongStateException;
import com.sun.star.embed.XStorage;
import com.sun.star.embed.XVisualObject;
import com.sun.star.frame.DoubleInitializationException;
import com.sun.star.frame.XController;
import com.sun.star.frame.XLoadable;
import com.sun.star.frame.XStorable2;
import com.sun.star.io.IOException;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.NoSupportException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.lang.XUnoTunnel;
import com.sun.star.qa.XDumper;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XAggregation;
import com.sun.star.uno.XWeak;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.XCloneable;
import com.sun.star.util.XCloseListener;
import com.sun.star.util.XCloseable;
import com.sun.star.util.XModifiable;
import com.sun.star.util.XModifyListener;
import com.sun.star.util.XNumberFormats;
import com.sun.star.util.XNumberFormatsSupplier;
import com.sun.star.util.XUpdatable;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Chart implements Proxiable, XLoadable, XCloneable, XTitled, XChild, XVisualObject, XPropertySet, XDataSource, XUndoManagerSupplier, XUnoTunnel, XPropertyState, XDataReceiver, XDocumentPropertiesSupplier, XInitialization, XModifyListener, XWeak, XCloseable, XMultiPropertyStates, com.sun.star.chart2.XChartDocument, XChartDocument, XAggregation, XMultiServiceFactory, XServiceInfo, XMultiPropertySet, XDumper, XModifiable, XUpdatable, XNumberFormatsSupplier, XTypeProvider, XTransferable, XStorageBasedDocument, XDrawPageSupplier, XStorable2 {
    protected Object proxy;

    public P_Chart(Object object) {this.proxy = object;}

    public P_Chart qi(Object object) {
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

    public P_Chart prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public void load (PropertyValue[] arg0) throws DoubleInitializationException, IOException, Exception  {qi(XLoadable.class, proxy).load(arg0);}
    @Override public void initNew () throws DoubleInitializationException, IOException, Exception  {qi(XLoadable.class, proxy).initNew();}
    @Override public XCloneable createClone ()  {return qi(XCloneable.class, proxy).createClone();}
    @Override public XTitle getTitleObject ()  {return qi(XTitled.class, proxy).getTitleObject();}
    @Override public void setTitleObject (XTitle arg0)  {qi(XTitled.class, proxy).setTitleObject(
            arg0 instanceof Proxiable ? qi(XTitle.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getParent ()  {return qi(XChild.class, proxy).getParent();}
    @Override public void setParent (Object arg0) throws NoSupportException  {qi(XChild.class, proxy).setParent(arg0);}
    @Override public VisualRepresentation getPreferredVisualRepresentation (long arg0) throws IllegalArgumentException, WrongStateException, Exception  {return qi(XVisualObject.class, proxy).getPreferredVisualRepresentation(arg0);}
    @Override public void setVisualAreaSize (long arg0, Size arg1) throws IllegalArgumentException, WrongStateException, Exception  {qi(XVisualObject.class, proxy).setVisualAreaSize(arg0, arg1);}
    @Override public Size getVisualAreaSize (long arg0) throws IllegalArgumentException, WrongStateException, Exception  {return qi(XVisualObject.class, proxy).getVisualAreaSize(arg0);}
    @Override public int getMapUnit (long arg0) throws Exception  {return qi(XVisualObject.class, proxy).getMapUnit(arg0);}
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
    @Override public XLabeledDataSequence[] getDataSequences ()  {return qi(XDataSource.class, proxy).getDataSequences();}
    @Override public XUndoManager getUndoManager ()  {return qi(XUndoManagerSupplier.class, proxy).getUndoManager();}
    @Override public long getSomething (byte[] arg0)  {return qi(XUnoTunnel.class, proxy).getSomething(arg0);}
    @Override public PropertyState getPropertyState (String arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyState(arg0);}
    @Override public PropertyState[] getPropertyStates (String[] arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyStates(arg0);}
    @Override public void setPropertyToDefault (String arg0) throws UnknownPropertyException  {qi(XPropertyState.class, proxy).setPropertyToDefault(arg0);}
    @Override public Object getPropertyDefault (String arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XPropertyState.class, proxy).getPropertyDefault(arg0);}
    @Override public void attachNumberFormatsSupplier (XNumberFormatsSupplier arg0)  {qi(XDataReceiver.class, proxy).attachNumberFormatsSupplier(
            arg0 instanceof Proxiable ? qi(XNumberFormatsSupplier.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XRangeHighlighter getRangeHighlighter ()  {return qi(XDataReceiver.class, proxy).getRangeHighlighter();}
    @Override public String[] getUsedRangeRepresentations ()  {return qi(XDataReceiver.class, proxy).getUsedRangeRepresentations();}
    @Override public void attachDataProvider (XDataProvider arg0)  {qi(XDataReceiver.class, proxy).attachDataProvider(
            arg0 instanceof Proxiable ? qi(XDataProvider.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XDataSource getUsedData ()  {return qi(XDataReceiver.class, proxy).getUsedData();}
    @Override public XRequestCallback getPopupRequest ()  {return qi(XDataReceiver.class, proxy).getPopupRequest();}
    @Override public void setArguments (PropertyValue[] arg0) throws IllegalArgumentException  {qi(XDataReceiver.class, proxy).setArguments(arg0);}
    @Override public XDocumentProperties getDocumentProperties ()  {return qi(XDocumentPropertiesSupplier.class, proxy).getDocumentProperties();}
    @Override public void initialize (Object[] arg0) throws Exception  {qi(XInitialization.class, proxy).initialize(arg0);}
    @Override public void modified (EventObject arg0)  {qi(XModifyListener.class, proxy).modified(arg0);}
    @Override public void disposing (EventObject arg0)  {qi(XModifyListener.class, proxy).disposing(arg0);}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
    @Override public void close (boolean arg0) throws CloseVetoException  {qi(XCloseable.class, proxy).close(arg0);}
    @Override public void removeCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).removeCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).addCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setAllPropertiesToDefault ()  {qi(XMultiPropertyStates.class, proxy).setAllPropertiesToDefault();}
    @Override public void setPropertiesToDefault (String[] arg0) throws UnknownPropertyException  {qi(XMultiPropertyStates.class, proxy).setPropertiesToDefault(arg0);}
    @Override public Object[] getPropertyDefaults (String[] arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XMultiPropertyStates.class, proxy).getPropertyDefaults(arg0);}
    @Override public com.sun.star.chart2.XDiagram getFirstDiagram ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getFirstDiagram();}
    @Override public void setFirstDiagram (com.sun.star.chart2.XDiagram arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).setFirstDiagram(
            arg0 instanceof Proxiable ? qi(com.sun.star.chart2.XDiagram.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void createInternalDataProvider (boolean arg0) throws CloseVetoException  {qi(com.sun.star.chart2.XChartDocument.class, proxy).createInternalDataProvider(arg0);}
    @Override public boolean hasInternalDataProvider ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).hasInternalDataProvider();}
    @Override public XDataProvider getDataProvider ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getDataProvider();}
    @Override public XPropertySet getPageBackground ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getPageBackground();}
    @Override public void setChartTypeManager (XChartTypeManager arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).setChartTypeManager(
            arg0 instanceof Proxiable ? qi(XChartTypeManager.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XChartTypeManager getChartTypeManager ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getChartTypeManager();}
    @Override public void createDefaultChart ()  {qi(com.sun.star.chart2.XChartDocument.class, proxy).createDefaultChart();}
    @Override public String getURL ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getURL();}
    @Override public void setCurrentController (XController arg0) throws NoSuchElementException  {qi(com.sun.star.chart2.XChartDocument.class, proxy).setCurrentController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getCurrentSelection ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getCurrentSelection();}
    @Override public boolean attachResource (String arg0, PropertyValue[] arg1)  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).attachResource(arg0, arg1);}
    @Override public void connectController (XController arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).connectController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void disconnectController (XController arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).disconnectController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void lockControllers ()  {qi(com.sun.star.chart2.XChartDocument.class, proxy).lockControllers();}
    @Override public void unlockControllers ()  {qi(com.sun.star.chart2.XChartDocument.class, proxy).unlockControllers();}
    @Override public boolean hasControllersLocked ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).hasControllersLocked();}
    @Override public XController getCurrentController ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getCurrentController();}
    @Override public PropertyValue[] getArgs ()  {return qi(com.sun.star.chart2.XChartDocument.class, proxy).getArgs();}
    @Override public void dispose ()  {qi(com.sun.star.chart2.XChartDocument.class, proxy).dispose();}
    @Override public void addEventListener (XEventListener arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(com.sun.star.chart2.XChartDocument.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XShape getTitle ()  {return qi(XChartDocument.class, proxy).getTitle();}
    @Override public XShape getSubTitle ()  {return qi(XChartDocument.class, proxy).getSubTitle();}
    @Override public void setDiagram (XDiagram arg0)  {qi(XChartDocument.class, proxy).setDiagram(
            arg0 instanceof Proxiable ? qi(XDiagram.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XShape getLegend ()  {return qi(XChartDocument.class, proxy).getLegend();}
    @Override public XPropertySet getArea ()  {return qi(XChartDocument.class, proxy).getArea();}
    @Override public XDiagram getDiagram ()  {return qi(XChartDocument.class, proxy).getDiagram();}
    @Override public void attachData (XChartData arg0)  {qi(XChartDocument.class, proxy).attachData(
            arg0 instanceof Proxiable ? qi(XChartData.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XChartData getData ()  {return qi(XChartDocument.class, proxy).getData();}
    @Override public void setDelegator (Object arg0)  {qi(XAggregation.class, proxy).setDelegator(arg0);}
    @Override public Object queryAggregation (Type arg0)  {return qi(XAggregation.class, proxy).queryAggregation(arg0);}
    @Override public Object createInstance (String arg0) throws Exception  {return qi(XMultiServiceFactory.class, proxy).createInstance(arg0);}
    @Override public String[] getAvailableServiceNames ()  {return qi(XMultiServiceFactory.class, proxy).getAvailableServiceNames();}
    @Override public Object createInstanceWithArguments (String arg0, Object[] arg1) throws Exception  {return qi(XMultiServiceFactory.class, proxy).createInstanceWithArguments(arg0, arg1);}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
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
    @Override public String dump ()  {return qi(XDumper.class, proxy).dump();}
    @Override public boolean isModified ()  {return qi(XModifiable.class, proxy).isModified();}
    @Override public void setModified (boolean arg0) throws PropertyVetoException  {qi(XModifiable.class, proxy).setModified(arg0);}
    @Override public void removeModifyListener (XModifyListener arg0)  {qi(XModifiable.class, proxy).removeModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addModifyListener (XModifyListener arg0)  {qi(XModifiable.class, proxy).addModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void update ()  {qi(XUpdatable.class, proxy).update();}
    @Override public XPropertySet getNumberFormatSettings ()  {return qi(XNumberFormatsSupplier.class, proxy).getNumberFormatSettings();}
    @Override public XNumberFormats getNumberFormats ()  {return qi(XNumberFormatsSupplier.class, proxy).getNumberFormats();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public boolean isDataFlavorSupported (DataFlavor arg0)  {return qi(XTransferable.class, proxy).isDataFlavorSupported(arg0);}
    @Override public Object getTransferData (DataFlavor arg0) throws UnsupportedFlavorException, IOException  {return qi(XTransferable.class, proxy).getTransferData(arg0);}
    @Override public DataFlavor[] getTransferDataFlavors ()  {return qi(XTransferable.class, proxy).getTransferDataFlavors();}
    @Override public void loadFromStorage (XStorage arg0, PropertyValue[] arg1) throws IllegalArgumentException, DoubleInitializationException, IOException, Exception  {qi(XStorageBasedDocument.class, proxy).loadFromStorage(
            arg0 instanceof Proxiable ? qi(XStorage.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1);}
    @Override public void storeToStorage (XStorage arg0, PropertyValue[] arg1) throws IllegalArgumentException, IOException, Exception  {qi(XStorageBasedDocument.class, proxy).storeToStorage(
            arg0 instanceof Proxiable ? qi(XStorage.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1);}
    @Override public void switchToStorage (XStorage arg0) throws IllegalArgumentException, IOException, Exception  {qi(XStorageBasedDocument.class, proxy).switchToStorage(
            arg0 instanceof Proxiable ? qi(XStorage.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XStorage getDocumentStorage () throws IOException, Exception  {return qi(XStorageBasedDocument.class, proxy).getDocumentStorage();}
    @Override public void addStorageChangeListener (XStorageChangeListener arg0)  {qi(XStorageBasedDocument.class, proxy).addStorageChangeListener(
            arg0 instanceof Proxiable ? qi(XStorageChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeStorageChangeListener (XStorageChangeListener arg0)  {qi(XStorageBasedDocument.class, proxy).removeStorageChangeListener(
            arg0 instanceof Proxiable ? qi(XStorageChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XDrawPage getDrawPage ()  {return qi(XDrawPageSupplier.class, proxy).getDrawPage();}
    @Override public void storeSelf (PropertyValue[] arg0) throws IllegalArgumentException, IOException  {qi(XStorable2.class, proxy).storeSelf(arg0);}
    @Override public String getLocation ()  {return qi(XStorable2.class, proxy).getLocation();}
    @Override public void store () throws IOException  {qi(XStorable2.class, proxy).store();}
    @Override public boolean isReadonly ()  {return qi(XStorable2.class, proxy).isReadonly();}
    @Override public void storeAsURL (String arg0, PropertyValue[] arg1) throws IOException  {qi(XStorable2.class, proxy).storeAsURL(arg0, arg1);}
    @Override public void storeToURL (String arg0, PropertyValue[] arg1) throws IOException  {qi(XStorable2.class, proxy).storeToURL(arg0, arg1);}
    @Override public boolean hasLocation ()  {return qi(XStorable2.class, proxy).hasLocation();}
}
    
