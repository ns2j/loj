package loproxy.table;

import com.sun.star.awt.Size;
import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.StringPair;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.ElementExistException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XChild;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XIndexAccess;
import com.sun.star.container.XNameAccess;
import com.sun.star.container.XNameContainer;
import com.sun.star.container.XNameReplace;
import com.sun.star.datatransfer.DataFlavor;
import com.sun.star.datatransfer.UnsupportedFlavorException;
import com.sun.star.datatransfer.XTransferable;
import com.sun.star.document.CmisProperty;
import com.sun.star.document.CmisVersion;
import com.sun.star.document.XActionLockable;
import com.sun.star.document.XCmisDocument;
import com.sun.star.document.XDocumentEventBroadcaster;
import com.sun.star.document.XDocumentEventListener;
import com.sun.star.document.XDocumentProperties;
import com.sun.star.document.XDocumentPropertiesSupplier;
import com.sun.star.document.XDocumentRecovery;
import com.sun.star.document.XDocumentSubStorageSupplier;
import com.sun.star.document.XEmbeddedScripts;
import com.sun.star.document.XEventListener;
import com.sun.star.document.XEventsSupplier;
import com.sun.star.document.XLinkTargetSupplier;
import com.sun.star.document.XScriptInvocationContext;
import com.sun.star.document.XShapeEventBroadcaster;
import com.sun.star.document.XShapeEventListener;
import com.sun.star.document.XStorageBasedDocument;
import com.sun.star.document.XStorageChangeListener;
import com.sun.star.document.XUndoManager;
import com.sun.star.document.XUndoManagerSupplier;
import com.sun.star.document.XViewDataSupplier;
import com.sun.star.drawing.XDrawPages;
import com.sun.star.drawing.XDrawPagesSupplier;
import com.sun.star.drawing.XShape;
import com.sun.star.embed.VisualRepresentation;
import com.sun.star.embed.WrongStateException;
import com.sun.star.embed.XStorage;
import com.sun.star.embed.XVisualObject;
import com.sun.star.frame.DoubleInitializationException;
import com.sun.star.frame.XController;
import com.sun.star.frame.XController2;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XLoadable;
import com.sun.star.frame.XModel2;
import com.sun.star.frame.XModule;
import com.sun.star.frame.XStorable2;
import com.sun.star.frame.XTitle;
import com.sun.star.frame.XTitleChangeBroadcaster;
import com.sun.star.frame.XTitleChangeListener;
import com.sun.star.frame.XUntitledNumbers;
import com.sun.star.io.IOException;
import com.sun.star.io.XInputStream;
import com.sun.star.lang.EventObject;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.NoSupportException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.lang.XUnoTunnel;
import com.sun.star.rdf.ParseException;
import com.sun.star.rdf.XDocumentMetadataAccess;
import com.sun.star.rdf.XMetadatable;
import com.sun.star.rdf.XRepository;
import com.sun.star.rdf.XURI;
import com.sun.star.script.XStarBasicAccess;
import com.sun.star.script.XStorageBasedLibraryContainer;
import com.sun.star.script.provider.XScriptProvider;
import com.sun.star.script.provider.XScriptProviderSupplier;
import com.sun.star.sheet.GoalResult;
import com.sun.star.sheet.XCalculatable;
import com.sun.star.sheet.XConsolidatable;
import com.sun.star.sheet.XConsolidationDescriptor;
import com.sun.star.sheet.XDocumentAuditing;
import com.sun.star.sheet.XGoalSeek;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.sheet.opencl.OpenCLPlatform;
import com.sun.star.sheet.opencl.XOpenCLSelection;
import com.sun.star.style.XStyleFamiliesSupplier;
import com.sun.star.table.CellAddress;
import com.sun.star.task.XInteractionHandler;
import com.sun.star.ui.XUIConfigurationManager;
import com.sun.star.ui.XUIConfigurationManagerSupplier;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XAggregation;
import com.sun.star.uno.XWeak;
import com.sun.star.util.CloseVetoException;
import com.sun.star.util.InvalidStateException;
import com.sun.star.util.XChangesListener;
import com.sun.star.util.XChangesNotifier;
import com.sun.star.util.XCloseListener;
import com.sun.star.util.XCloseable;
import com.sun.star.util.XModifiable2;
import com.sun.star.util.XModifyListener;
import com.sun.star.util.XNumberFormats;
import com.sun.star.util.XNumberFormatsSupplier;
import com.sun.star.util.XProtectable;
import com.sun.star.view.XPrintJobBroadcaster;
import com.sun.star.view.XPrintJobListener;
import com.sun.star.view.XPrintable;
import com.sun.star.view.XRenderable;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Doc implements Proxiable, XShapeEventBroadcaster, XProtectable, XDocumentAuditing, XStyleFamiliesSupplier, XChangesNotifier, XScriptProviderSupplier, XEmbeddedScripts, XVisualObject, XTitleChangeBroadcaster, XPropertySet, XModel2, XUntitledNumbers, XDocumentPropertiesSupplier, XViewDataSupplier, XCloseable, XDocumentRecovery, XAggregation, XMultiServiceFactory, XServiceInfo, com.sun.star.lang.XEventListener, XRenderable, XEventsSupplier, XTypeProvider, XTransferable, XStorageBasedDocument, XLoadable, XPrintJobBroadcaster, XChild, XLinkTargetSupplier, XTitle, XStarBasicAccess, XPrintable, XUndoManagerSupplier, XUnoTunnel, XSpreadsheetDocument, XUIConfigurationManagerSupplier, XCmisDocument, XModifiable2, XModule, XScriptInvocationContext, XWeak, XCalculatable, XActionLockable, XGoalSeek, XDocumentEventBroadcaster, XDocumentSubStorageSupplier, XDrawPagesSupplier, XOpenCLSelection, XNumberFormatsSupplier, XStorable2, XConsolidatable, XDocumentMetadataAccess {
    protected Object proxy;

    public P_Doc(Object object) {this.proxy = object;}

    public P_Doc qi(Object object) {
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

    public P_Doc prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public void removeShapeEventListener (XShape arg0, XShapeEventListener arg1)  {qi(XShapeEventBroadcaster.class, proxy).removeShapeEventListener(
            arg0 instanceof Proxiable ? qi(XShape.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XShapeEventListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void addShapeEventListener (XShape arg0, XShapeEventListener arg1)  {qi(XShapeEventBroadcaster.class, proxy).addShapeEventListener(
            arg0 instanceof Proxiable ? qi(XShape.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XShapeEventListener.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public void addEventListener (XEventListener arg0)  {qi(XShapeEventBroadcaster.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(XShapeEventBroadcaster.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean isProtected ()  {return qi(XProtectable.class, proxy).isProtected();}
    @Override public void protect (String arg0)  {qi(XProtectable.class, proxy).protect(arg0);}
    @Override public void unprotect (String arg0) throws IllegalArgumentException  {qi(XProtectable.class, proxy).unprotect(arg0);}
    @Override public void refreshArrows ()  {qi(XDocumentAuditing.class, proxy).refreshArrows();}
    @Override public XNameAccess getStyleFamilies ()  {return qi(XStyleFamiliesSupplier.class, proxy).getStyleFamilies();}
    @Override public void addChangesListener (XChangesListener arg0)  {qi(XChangesNotifier.class, proxy).addChangesListener(
            arg0 instanceof Proxiable ? qi(XChangesListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeChangesListener (XChangesListener arg0)  {qi(XChangesNotifier.class, proxy).removeChangesListener(
            arg0 instanceof Proxiable ? qi(XChangesListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XScriptProvider getScriptProvider ()  {return qi(XScriptProviderSupplier.class, proxy).getScriptProvider();}
    @Override public XStorageBasedLibraryContainer getBasicLibraries ()  {return qi(XEmbeddedScripts.class, proxy).getBasicLibraries();}
    @Override public XStorageBasedLibraryContainer getDialogLibraries ()  {return qi(XEmbeddedScripts.class, proxy).getDialogLibraries();}
    @Override public boolean getAllowMacroExecution ()  {return qi(XEmbeddedScripts.class, proxy).getAllowMacroExecution();}
    @Override public VisualRepresentation getPreferredVisualRepresentation (long arg0) throws IllegalArgumentException, WrongStateException, Exception  {return qi(XVisualObject.class, proxy).getPreferredVisualRepresentation(arg0);}
    @Override public void setVisualAreaSize (long arg0, Size arg1) throws IllegalArgumentException, WrongStateException, Exception  {qi(XVisualObject.class, proxy).setVisualAreaSize(arg0, arg1);}
    @Override public Size getVisualAreaSize (long arg0) throws IllegalArgumentException, WrongStateException, Exception  {return qi(XVisualObject.class, proxy).getVisualAreaSize(arg0);}
    @Override public int getMapUnit (long arg0) throws Exception  {return qi(XVisualObject.class, proxy).getMapUnit(arg0);}
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
    @Override public XEnumeration getControllers ()  {return qi(XModel2.class, proxy).getControllers();}
    @Override public XController2 createDefaultViewController (XFrame arg0) throws IllegalArgumentException, Exception  {return qi(XModel2.class, proxy).createDefaultViewController(
            arg0 instanceof Proxiable ? qi(XFrame.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XController2 createViewController (String arg0, PropertyValue[] arg1, XFrame arg2) throws IllegalArgumentException, Exception  {return qi(XModel2.class, proxy).createViewController(arg0, arg1, 
            arg2 instanceof Proxiable ? qi(XFrame.class, ((Proxiable)arg2).getProxy()) : arg2
            );}
    @Override public String[] getAvailableViewControllerNames ()  {return qi(XModel2.class, proxy).getAvailableViewControllerNames();}
    @Override public void setArgs (PropertyValue[] arg0) throws IllegalArgumentException, InvalidStateException  {qi(XModel2.class, proxy).setArgs(arg0);}
    @Override public String getURL ()  {return qi(XModel2.class, proxy).getURL();}
    @Override public void setCurrentController (XController arg0) throws NoSuchElementException  {qi(XModel2.class, proxy).setCurrentController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getCurrentSelection ()  {return qi(XModel2.class, proxy).getCurrentSelection();}
    @Override public boolean attachResource (String arg0, PropertyValue[] arg1)  {return qi(XModel2.class, proxy).attachResource(arg0, arg1);}
    @Override public void connectController (XController arg0)  {qi(XModel2.class, proxy).connectController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void disconnectController (XController arg0)  {qi(XModel2.class, proxy).disconnectController(
            arg0 instanceof Proxiable ? qi(XController.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void lockControllers ()  {qi(XModel2.class, proxy).lockControllers();}
    @Override public void unlockControllers ()  {qi(XModel2.class, proxy).unlockControllers();}
    @Override public boolean hasControllersLocked ()  {return qi(XModel2.class, proxy).hasControllersLocked();}
    @Override public XController getCurrentController ()  {return qi(XModel2.class, proxy).getCurrentController();}
    @Override public PropertyValue[] getArgs ()  {return qi(XModel2.class, proxy).getArgs();}
    @Override public void dispose ()  {qi(XModel2.class, proxy).dispose();}
    @Override public void addEventListener (com.sun.star.lang.XEventListener arg0)  {qi(XModel2.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(com.sun.star.lang.XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (com.sun.star.lang.XEventListener arg0)  {qi(XModel2.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(com.sun.star.lang.XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public String getUntitledPrefix ()  {return qi(XUntitledNumbers.class, proxy).getUntitledPrefix();}
    @Override public int leaseNumber (Object arg0) throws IllegalArgumentException  {return qi(XUntitledNumbers.class, proxy).leaseNumber(arg0);}
    @Override public void releaseNumber (int arg0) throws IllegalArgumentException  {qi(XUntitledNumbers.class, proxy).releaseNumber(arg0);}
    @Override public void releaseNumberForComponent (Object arg0) throws IllegalArgumentException  {qi(XUntitledNumbers.class, proxy).releaseNumberForComponent(arg0);}
    @Override public XDocumentProperties getDocumentProperties ()  {return qi(XDocumentPropertiesSupplier.class, proxy).getDocumentProperties();}
    @Override public XIndexAccess getViewData ()  {return qi(XViewDataSupplier.class, proxy).getViewData();}
    @Override public void setViewData (XIndexAccess arg0)  {qi(XViewDataSupplier.class, proxy).setViewData(
            arg0 instanceof Proxiable ? qi(XIndexAccess.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void close (boolean arg0) throws CloseVetoException  {qi(XCloseable.class, proxy).close(arg0);}
    @Override public void removeCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).removeCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addCloseListener (XCloseListener arg0)  {qi(XCloseable.class, proxy).addCloseListener(
            arg0 instanceof Proxiable ? qi(XCloseListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void recoverFromFile (String arg0, String arg1, PropertyValue[] arg2) throws IOException, WrappedTargetException  {qi(XDocumentRecovery.class, proxy).recoverFromFile(arg0, arg1, arg2);}
    @Override public boolean wasModifiedSinceLastSave ()  {return qi(XDocumentRecovery.class, proxy).wasModifiedSinceLastSave();}
    @Override public void storeToRecoveryFile (String arg0, PropertyValue[] arg1) throws IOException, WrappedTargetException  {qi(XDocumentRecovery.class, proxy).storeToRecoveryFile(arg0, arg1);}
    @Override public void setDelegator (Object arg0)  {qi(XAggregation.class, proxy).setDelegator(arg0);}
    @Override public Object queryAggregation (Type arg0)  {return qi(XAggregation.class, proxy).queryAggregation(arg0);}
    @Override public Object createInstance (String arg0) throws Exception  {return qi(XMultiServiceFactory.class, proxy).createInstance(arg0);}
    @Override public String[] getAvailableServiceNames ()  {return qi(XMultiServiceFactory.class, proxy).getAvailableServiceNames();}
    @Override public Object createInstanceWithArguments (String arg0, Object[] arg1) throws Exception  {return qi(XMultiServiceFactory.class, proxy).createInstanceWithArguments(arg0, arg1);}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public void disposing (EventObject arg0)  {qi(com.sun.star.lang.XEventListener.class, proxy).disposing(arg0);}
    @Override public int getRendererCount (Object arg0, PropertyValue[] arg1) throws IllegalArgumentException  {return qi(XRenderable.class, proxy).getRendererCount(arg0, arg1);}
    @Override public PropertyValue[] getRenderer (int arg0, Object arg1, PropertyValue[] arg2) throws IllegalArgumentException  {return qi(XRenderable.class, proxy).getRenderer(arg0, arg1, arg2);}
    @Override public void render (int arg0, Object arg1, PropertyValue[] arg2) throws IllegalArgumentException  {qi(XRenderable.class, proxy).render(arg0, arg1, arg2);}
    @Override public XNameReplace getEvents ()  {return qi(XEventsSupplier.class, proxy).getEvents();}
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
    @Override public void load (PropertyValue[] arg0) throws DoubleInitializationException, IOException, Exception  {qi(XLoadable.class, proxy).load(arg0);}
    @Override public void initNew () throws DoubleInitializationException, IOException, Exception  {qi(XLoadable.class, proxy).initNew();}
    @Override public void removePrintJobListener (XPrintJobListener arg0)  {qi(XPrintJobBroadcaster.class, proxy).removePrintJobListener(
            arg0 instanceof Proxiable ? qi(XPrintJobListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addPrintJobListener (XPrintJobListener arg0)  {qi(XPrintJobBroadcaster.class, proxy).addPrintJobListener(
            arg0 instanceof Proxiable ? qi(XPrintJobListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getParent ()  {return qi(XChild.class, proxy).getParent();}
    @Override public void setParent (Object arg0) throws NoSupportException  {qi(XChild.class, proxy).setParent(arg0);}
    @Override public XNameAccess getLinks ()  {return qi(XLinkTargetSupplier.class, proxy).getLinks();}
    @Override public void setTitle (String arg0)  {qi(XTitle.class, proxy).setTitle(arg0);}
    @Override public String getTitle ()  {return qi(XTitle.class, proxy).getTitle();}
    @Override public void createLibrary (String arg0, String arg1, String arg2, String arg3) throws ElementExistException  {qi(XStarBasicAccess.class, proxy).createLibrary(arg0, arg1, arg2, arg3);}
    @Override public void addDialog (String arg0, String arg1, byte[] arg2) throws NoSuchElementException  {qi(XStarBasicAccess.class, proxy).addDialog(arg0, arg1, arg2);}
    @Override public XNameContainer getLibraryContainer ()  {return qi(XStarBasicAccess.class, proxy).getLibraryContainer();}
    @Override public void addModule (String arg0, String arg1, String arg2, String arg3) throws NoSuchElementException  {qi(XStarBasicAccess.class, proxy).addModule(arg0, arg1, arg2, arg3);}
    @Override public void print (PropertyValue[] arg0) throws IllegalArgumentException  {qi(XPrintable.class, proxy).print(arg0);}
    @Override public PropertyValue[] getPrinter ()  {return qi(XPrintable.class, proxy).getPrinter();}
    @Override public void setPrinter (PropertyValue[] arg0) throws IllegalArgumentException  {qi(XPrintable.class, proxy).setPrinter(arg0);}
    @Override public XUndoManager getUndoManager ()  {return qi(XUndoManagerSupplier.class, proxy).getUndoManager();}
    @Override public long getSomething (byte[] arg0)  {return qi(XUnoTunnel.class, proxy).getSomething(arg0);}
    @Override public XSpreadsheets getSheets ()  {return qi(XSpreadsheetDocument.class, proxy).getSheets();}
    @Override public XUIConfigurationManager getUIConfigurationManager ()  {return qi(XUIConfigurationManagerSupplier.class, proxy).getUIConfigurationManager();}
    @Override public void updateCmisProperties (CmisProperty[] arg0)  {qi(XCmisDocument.class, proxy).updateCmisProperties(arg0);}
    @Override public CmisProperty[] getCmisProperties ()  {return qi(XCmisDocument.class, proxy).getCmisProperties();}
    @Override public void setCmisProperties (CmisProperty[] arg0)  {qi(XCmisDocument.class, proxy).setCmisProperties(arg0);}
    @Override public void checkOut ()  {qi(XCmisDocument.class, proxy).checkOut();}
    @Override public void cancelCheckOut ()  {qi(XCmisDocument.class, proxy).cancelCheckOut();}
    @Override public void checkIn (boolean arg0, String arg1)  {qi(XCmisDocument.class, proxy).checkIn(arg0, arg1);}
    @Override public boolean isVersionable ()  {return qi(XCmisDocument.class, proxy).isVersionable();}
    @Override public boolean canCheckOut ()  {return qi(XCmisDocument.class, proxy).canCheckOut();}
    @Override public boolean canCancelCheckOut ()  {return qi(XCmisDocument.class, proxy).canCancelCheckOut();}
    @Override public boolean canCheckIn ()  {return qi(XCmisDocument.class, proxy).canCheckIn();}
    @Override public CmisVersion[] getAllVersions ()  {return qi(XCmisDocument.class, proxy).getAllVersions();}
    @Override public boolean disableSetModified ()  {return qi(XModifiable2.class, proxy).disableSetModified();}
    @Override public boolean isSetModifiedEnabled ()  {return qi(XModifiable2.class, proxy).isSetModifiedEnabled();}
    @Override public boolean enableSetModified ()  {return qi(XModifiable2.class, proxy).enableSetModified();}
    @Override public boolean isModified ()  {return qi(XModifiable2.class, proxy).isModified();}
    @Override public void setModified (boolean arg0) throws PropertyVetoException  {qi(XModifiable2.class, proxy).setModified(arg0);}
    @Override public void removeModifyListener (XModifyListener arg0)  {qi(XModifiable2.class, proxy).removeModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addModifyListener (XModifyListener arg0)  {qi(XModifiable2.class, proxy).addModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setIdentifier (String arg0)  {qi(XModule.class, proxy).setIdentifier(arg0);}
    @Override public String getIdentifier ()  {return qi(XModule.class, proxy).getIdentifier();}
    @Override public XEmbeddedScripts getScriptContainer ()  {return qi(XScriptInvocationContext.class, proxy).getScriptContainer();}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
    @Override public void calculateAll ()  {qi(XCalculatable.class, proxy).calculateAll();}
    @Override public boolean isAutomaticCalculationEnabled ()  {return qi(XCalculatable.class, proxy).isAutomaticCalculationEnabled();}
    @Override public void enableAutomaticCalculation (boolean arg0)  {qi(XCalculatable.class, proxy).enableAutomaticCalculation(arg0);}
    @Override public void calculate ()  {qi(XCalculatable.class, proxy).calculate();}
    @Override public void setActionLocks (short arg0)  {qi(XActionLockable.class, proxy).setActionLocks(arg0);}
    @Override public short resetActionLocks ()  {return qi(XActionLockable.class, proxy).resetActionLocks();}
    @Override public boolean isActionLocked ()  {return qi(XActionLockable.class, proxy).isActionLocked();}
    @Override public void addActionLock ()  {qi(XActionLockable.class, proxy).addActionLock();}
    @Override public void removeActionLock ()  {qi(XActionLockable.class, proxy).removeActionLock();}
    @Override public GoalResult seekGoal (CellAddress arg0, CellAddress arg1, String arg2)  {return qi(XGoalSeek.class, proxy).seekGoal(arg0, arg1, arg2);}
    @Override public void addDocumentEventListener (XDocumentEventListener arg0)  {qi(XDocumentEventBroadcaster.class, proxy).addDocumentEventListener(
            arg0 instanceof Proxiable ? qi(XDocumentEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeDocumentEventListener (XDocumentEventListener arg0)  {qi(XDocumentEventBroadcaster.class, proxy).removeDocumentEventListener(
            arg0 instanceof Proxiable ? qi(XDocumentEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void notifyDocumentEvent (String arg0, XController2 arg1, Object arg2) throws IllegalArgumentException, NoSupportException  {qi(XDocumentEventBroadcaster.class, proxy).notifyDocumentEvent(arg0, 
            arg1 instanceof Proxiable ? qi(XController2.class, ((Proxiable)arg1).getProxy()) : arg1
            , arg2);}
    @Override public String[] getDocumentSubStoragesNames () throws IOException  {return qi(XDocumentSubStorageSupplier.class, proxy).getDocumentSubStoragesNames();}
    @Override public XStorage getDocumentSubStorage (String arg0, int arg1)  {return qi(XDocumentSubStorageSupplier.class, proxy).getDocumentSubStorage(arg0, arg1);}
    @Override public XDrawPages getDrawPages ()  {return qi(XDrawPagesSupplier.class, proxy).getDrawPages();}
    @Override public int getDeviceID ()  {return qi(XOpenCLSelection.class, proxy).getDeviceID();}
    @Override public OpenCLPlatform[] getOpenCLPlatforms ()  {return qi(XOpenCLSelection.class, proxy).getOpenCLPlatforms();}
    @Override public void enableOpcodeSubsetTest ()  {qi(XOpenCLSelection.class, proxy).enableOpcodeSubsetTest();}
    @Override public void disableOpcodeSubsetTest ()  {qi(XOpenCLSelection.class, proxy).disableOpcodeSubsetTest();}
    @Override public boolean isOpcodeSubsetTested ()  {return qi(XOpenCLSelection.class, proxy).isOpcodeSubsetTested();}
    @Override public void setFormulaCellNumberLimit (int arg0)  {qi(XOpenCLSelection.class, proxy).setFormulaCellNumberLimit(arg0);}
    @Override public int getFormulaCellNumberLimit ()  {return qi(XOpenCLSelection.class, proxy).getFormulaCellNumberLimit();}
    @Override public void disableAutomaticDeviceSelection ()  {qi(XOpenCLSelection.class, proxy).disableAutomaticDeviceSelection();}
    @Override public void enableAutomaticDeviceSelection (boolean arg0)  {qi(XOpenCLSelection.class, proxy).enableAutomaticDeviceSelection(arg0);}
    @Override public boolean isOpenCLEnabled ()  {return qi(XOpenCLSelection.class, proxy).isOpenCLEnabled();}
    @Override public void enableOpenCL (boolean arg0)  {qi(XOpenCLSelection.class, proxy).enableOpenCL(arg0);}
    @Override public void selectOpenCLDevice (int arg0, int arg1)  {qi(XOpenCLSelection.class, proxy).selectOpenCLDevice(arg0, arg1);}
    @Override public int getPlatformID ()  {return qi(XOpenCLSelection.class, proxy).getPlatformID();}
    @Override public XPropertySet getNumberFormatSettings ()  {return qi(XNumberFormatsSupplier.class, proxy).getNumberFormatSettings();}
    @Override public XNumberFormats getNumberFormats ()  {return qi(XNumberFormatsSupplier.class, proxy).getNumberFormats();}
    @Override public void storeSelf (PropertyValue[] arg0) throws IllegalArgumentException, IOException  {qi(XStorable2.class, proxy).storeSelf(arg0);}
    @Override public String getLocation ()  {return qi(XStorable2.class, proxy).getLocation();}
    @Override public void store () throws IOException  {qi(XStorable2.class, proxy).store();}
    @Override public boolean isReadonly ()  {return qi(XStorable2.class, proxy).isReadonly();}
    @Override public void storeAsURL (String arg0, PropertyValue[] arg1) throws IOException  {qi(XStorable2.class, proxy).storeAsURL(arg0, arg1);}
    @Override public void storeToURL (String arg0, PropertyValue[] arg1) throws IOException  {qi(XStorable2.class, proxy).storeToURL(arg0, arg1);}
    @Override public boolean hasLocation ()  {return qi(XStorable2.class, proxy).hasLocation();}
    @Override public XConsolidationDescriptor createConsolidationDescriptor (boolean arg0)  {return qi(XConsolidatable.class, proxy).createConsolidationDescriptor(arg0);}
    @Override public void consolidate (XConsolidationDescriptor arg0)  {qi(XConsolidatable.class, proxy).consolidate(
            arg0 instanceof Proxiable ? qi(XConsolidationDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XURI[] getMetadataGraphsWithType (XURI arg0) throws IllegalArgumentException  {return qi(XDocumentMetadataAccess.class, proxy).getMetadataGraphsWithType(
            arg0 instanceof Proxiable ? qi(XURI.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XMetadatable getElementByMetadataReference (StringPair arg0)  {return qi(XDocumentMetadataAccess.class, proxy).getElementByMetadataReference(arg0);}
    @Override public XMetadatable getElementByURI (XURI arg0) throws IllegalArgumentException  {return qi(XDocumentMetadataAccess.class, proxy).getElementByURI(
            arg0 instanceof Proxiable ? qi(XURI.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XURI addMetadataFile (String arg0, XURI[] arg1) throws IllegalArgumentException, ElementExistException  {return qi(XDocumentMetadataAccess.class, proxy).addMetadataFile(arg0, arg1);}
    @Override public XURI importMetadataFile (short arg0, XInputStream arg1, String arg2, XURI arg3, XURI[] arg4) throws IllegalArgumentException, UnsupportedFlavorException, ElementExistException, ParseException, IOException  {return qi(XDocumentMetadataAccess.class, proxy).importMetadataFile(arg0, 
            arg1 instanceof Proxiable ? qi(XInputStream.class, ((Proxiable)arg1).getProxy()) : arg1
            , arg2, 
            arg3 instanceof Proxiable ? qi(XURI.class, ((Proxiable)arg3).getProxy()) : arg3
            , arg4);}
    @Override public void removeMetadataFile (XURI arg0) throws IllegalArgumentException, NoSuchElementException  {qi(XDocumentMetadataAccess.class, proxy).removeMetadataFile(
            arg0 instanceof Proxiable ? qi(XURI.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addContentOrStylesFile (String arg0) throws IllegalArgumentException, ElementExistException  {qi(XDocumentMetadataAccess.class, proxy).addContentOrStylesFile(arg0);}
    @Override public void removeContentOrStylesFile (String arg0) throws IllegalArgumentException, NoSuchElementException  {qi(XDocumentMetadataAccess.class, proxy).removeContentOrStylesFile(arg0);}
    @Override public void loadMetadataFromStorage (XStorage arg0, XURI arg1, XInteractionHandler arg2) throws IllegalArgumentException, WrappedTargetException  {qi(XDocumentMetadataAccess.class, proxy).loadMetadataFromStorage(
            arg0 instanceof Proxiable ? qi(XStorage.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XURI.class, ((Proxiable)arg1).getProxy()) : arg1
            , 
            arg2 instanceof Proxiable ? qi(XInteractionHandler.class, ((Proxiable)arg2).getProxy()) : arg2
            );}
    @Override public void storeMetadataToStorage (XStorage arg0) throws IllegalArgumentException, WrappedTargetException  {qi(XDocumentMetadataAccess.class, proxy).storeMetadataToStorage(
            arg0 instanceof Proxiable ? qi(XStorage.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void loadMetadataFromMedium (PropertyValue[] arg0) throws IllegalArgumentException, WrappedTargetException  {qi(XDocumentMetadataAccess.class, proxy).loadMetadataFromMedium(arg0);}
    @Override public void storeMetadataToMedium (PropertyValue[] arg0) throws IllegalArgumentException, WrappedTargetException  {qi(XDocumentMetadataAccess.class, proxy).storeMetadataToMedium(arg0);}
    @Override public String getLocalName ()  {return qi(XDocumentMetadataAccess.class, proxy).getLocalName();}
    @Override public String getNamespace ()  {return qi(XDocumentMetadataAccess.class, proxy).getNamespace();}
    @Override public String getStringValue ()  {return qi(XDocumentMetadataAccess.class, proxy).getStringValue();}
    @Override public XRepository getRDFRepository ()  {return qi(XDocumentMetadataAccess.class, proxy).getRDFRepository();}
}
    
