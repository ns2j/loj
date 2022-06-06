package loproxy.frame;

import com.sun.star.awt.Rectangle;
import com.sun.star.awt.XControl;
import com.sun.star.awt.XControlModel;
import com.sun.star.awt.XEnhancedMouseClickHandler;
import com.sun.star.awt.XKeyHandler;
import com.sun.star.awt.XMouseClickHandler;
import com.sun.star.awt.XUserInputInterception;
import com.sun.star.awt.XWindow;
import com.sun.star.beans.PropertyValue;
import com.sun.star.beans.PropertyVetoException;
import com.sun.star.beans.StringPair;
import com.sun.star.beans.UnknownPropertyException;
import com.sun.star.beans.XPropertyChangeListener;
import com.sun.star.beans.XPropertySet;
import com.sun.star.beans.XPropertySetInfo;
import com.sun.star.beans.XVetoableChangeListener;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.datatransfer.UnsupportedFlavorException;
import com.sun.star.datatransfer.XTransferable;
import com.sun.star.datatransfer.XTransferableSupplier;
import com.sun.star.form.XForm;
import com.sun.star.form.runtime.XFormController;
import com.sun.star.frame.BorderWidths;
import com.sun.star.frame.DispatchDescriptor;
import com.sun.star.frame.DispatchInformation;
import com.sun.star.frame.XBorderResizeListener;
import com.sun.star.frame.XController2;
import com.sun.star.frame.XControllerBorder;
import com.sun.star.frame.XDispatch;
import com.sun.star.frame.XDispatchInformationProvider;
import com.sun.star.frame.XDispatchProvider;
import com.sun.star.frame.XFrame;
import com.sun.star.frame.XInfobarProvider;
import com.sun.star.frame.XModel;
import com.sun.star.frame.XTitle;
import com.sun.star.frame.XTitleChangeBroadcaster;
import com.sun.star.frame.XTitleChangeListener;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XEventListener;
import com.sun.star.lang.XInitialization;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.lang.XUnoTunnel;
import com.sun.star.sheet.XActivationBroadcaster;
import com.sun.star.sheet.XActivationEventListener;
import com.sun.star.sheet.XCellRangeReferrer;
import com.sun.star.sheet.XEnhancedMouseClickBroadcaster;
import com.sun.star.sheet.XRangeSelection;
import com.sun.star.sheet.XRangeSelectionChangeListener;
import com.sun.star.sheet.XRangeSelectionListener;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetView;
import com.sun.star.sheet.XViewFreezable;
import com.sun.star.sheet.XViewPane;
import com.sun.star.sheet.XViewSplitable;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XCellRange;
import com.sun.star.task.XStatusIndicator;
import com.sun.star.task.XStatusIndicatorSupplier;
import com.sun.star.ui.XContextMenuInterception;
import com.sun.star.ui.XContextMenuInterceptor;
import com.sun.star.ui.XSidebarProvider;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.util.URL;
import com.sun.star.view.XFormLayerAccess;
import com.sun.star.view.XSelectionChangeListener;
import com.sun.star.view.XSelectionSupplier;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Controller implements Proxiable, XDispatchProvider, XCellRangeReferrer, XStatusIndicatorSupplier, XTitle, XUserInputInterception, XTitleChangeBroadcaster, XPropertySet, XUnoTunnel, XRangeSelection, XController2, XViewSplitable, XContextMenuInterception, XInitialization, XControllerBorder, XActivationBroadcaster, XWeak, XInfobarProvider, XViewFreezable, XEnumerationAccess, XServiceInfo, XDispatchInformationProvider, XEnhancedMouseClickBroadcaster, XViewPane, XFormLayerAccess, XIndexAccess, XSpreadsheetView, XTypeProvider, XTransferableSupplier, XSelectionSupplier {
    protected Object proxy;

    public P_Controller(Object object) {this.proxy = object;}

    public P_Controller qi(Object object) {
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

    public P_Controller prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public XDispatch queryDispatch (URL arg0, String arg1, int arg2)  {return qi(XDispatchProvider.class, proxy).queryDispatch(arg0, arg1, arg2);}
    @Override public XDispatch[] queryDispatches (DispatchDescriptor[] arg0)  {return qi(XDispatchProvider.class, proxy).queryDispatches(arg0);}
    @Override public XCellRange getReferredCells ()  {return qi(XCellRangeReferrer.class, proxy).getReferredCells();}
    @Override public XStatusIndicator getStatusIndicator ()  {return qi(XStatusIndicatorSupplier.class, proxy).getStatusIndicator();}
    @Override public void setTitle (String arg0)  {qi(XTitle.class, proxy).setTitle(arg0);}
    @Override public String getTitle ()  {return qi(XTitle.class, proxy).getTitle();}
    @Override public void addKeyHandler (XKeyHandler arg0)  {qi(XUserInputInterception.class, proxy).addKeyHandler(
            arg0 instanceof Proxiable ? qi(XKeyHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeKeyHandler (XKeyHandler arg0)  {qi(XUserInputInterception.class, proxy).removeKeyHandler(
            arg0 instanceof Proxiable ? qi(XKeyHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeMouseClickHandler (XMouseClickHandler arg0)  {qi(XUserInputInterception.class, proxy).removeMouseClickHandler(
            arg0 instanceof Proxiable ? qi(XMouseClickHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addMouseClickHandler (XMouseClickHandler arg0)  {qi(XUserInputInterception.class, proxy).addMouseClickHandler(
            arg0 instanceof Proxiable ? qi(XMouseClickHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
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
    @Override public long getSomething (byte[] arg0)  {return qi(XUnoTunnel.class, proxy).getSomething(arg0);}
    @Override public void removeRangeSelectionChangeListener (XRangeSelectionChangeListener arg0)  {qi(XRangeSelection.class, proxy).removeRangeSelectionChangeListener(
            arg0 instanceof Proxiable ? qi(XRangeSelectionChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void startRangeSelection (PropertyValue[] arg0)  {qi(XRangeSelection.class, proxy).startRangeSelection(arg0);}
    @Override public void abortRangeSelection ()  {qi(XRangeSelection.class, proxy).abortRangeSelection();}
    @Override public void addRangeSelectionListener (XRangeSelectionListener arg0)  {qi(XRangeSelection.class, proxy).addRangeSelectionListener(
            arg0 instanceof Proxiable ? qi(XRangeSelectionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeRangeSelectionListener (XRangeSelectionListener arg0)  {qi(XRangeSelection.class, proxy).removeRangeSelectionListener(
            arg0 instanceof Proxiable ? qi(XRangeSelectionListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addRangeSelectionChangeListener (XRangeSelectionChangeListener arg0)  {qi(XRangeSelection.class, proxy).addRangeSelectionChangeListener(
            arg0 instanceof Proxiable ? qi(XRangeSelectionChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XSidebarProvider getSidebar ()  {return qi(XController2.class, proxy).getSidebar();}
    @Override public String getViewControllerName ()  {return qi(XController2.class, proxy).getViewControllerName();}
    @Override public PropertyValue[] getCreationArguments ()  {return qi(XController2.class, proxy).getCreationArguments();}
    @Override public XWindow getComponentWindow ()  {return qi(XController2.class, proxy).getComponentWindow();}
    @Override public boolean suspend (boolean arg0)  {return qi(XController2.class, proxy).suspend(arg0);}
    @Override public void attachFrame (XFrame arg0)  {qi(XController2.class, proxy).attachFrame(
            arg0 instanceof Proxiable ? qi(XFrame.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean attachModel (XModel arg0)  {return qi(XController2.class, proxy).attachModel(
            arg0 instanceof Proxiable ? qi(XModel.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getViewData ()  {return qi(XController2.class, proxy).getViewData();}
    @Override public void restoreViewData (Object arg0)  {qi(XController2.class, proxy).restoreViewData(arg0);}
    @Override public XFrame getFrame ()  {return qi(XController2.class, proxy).getFrame();}
    @Override public XModel getModel ()  {return qi(XController2.class, proxy).getModel();}
    @Override public void dispose ()  {qi(XController2.class, proxy).dispose();}
    @Override public void addEventListener (XEventListener arg0)  {qi(XController2.class, proxy).addEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEventListener (XEventListener arg0)  {qi(XController2.class, proxy).removeEventListener(
            arg0 instanceof Proxiable ? qi(XEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean getIsWindowSplit ()  {return qi(XViewSplitable.class, proxy).getIsWindowSplit();}
    @Override public int getSplitHorizontal ()  {return qi(XViewSplitable.class, proxy).getSplitHorizontal();}
    @Override public int getSplitVertical ()  {return qi(XViewSplitable.class, proxy).getSplitVertical();}
    @Override public int getSplitColumn ()  {return qi(XViewSplitable.class, proxy).getSplitColumn();}
    @Override public int getSplitRow ()  {return qi(XViewSplitable.class, proxy).getSplitRow();}
    @Override public void splitAtPosition (int arg0, int arg1)  {qi(XViewSplitable.class, proxy).splitAtPosition(arg0, arg1);}
    @Override public void registerContextMenuInterceptor (XContextMenuInterceptor arg0)  {qi(XContextMenuInterception.class, proxy).registerContextMenuInterceptor(
            arg0 instanceof Proxiable ? qi(XContextMenuInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void releaseContextMenuInterceptor (XContextMenuInterceptor arg0)  {qi(XContextMenuInterception.class, proxy).releaseContextMenuInterceptor(
            arg0 instanceof Proxiable ? qi(XContextMenuInterceptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void initialize (Object[] arg0) throws Exception  {qi(XInitialization.class, proxy).initialize(arg0);}
    @Override public BorderWidths getBorder ()  {return qi(XControllerBorder.class, proxy).getBorder();}
    @Override public void addBorderResizeListener (XBorderResizeListener arg0)  {qi(XControllerBorder.class, proxy).addBorderResizeListener(
            arg0 instanceof Proxiable ? qi(XBorderResizeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeBorderResizeListener (XBorderResizeListener arg0)  {qi(XControllerBorder.class, proxy).removeBorderResizeListener(
            arg0 instanceof Proxiable ? qi(XBorderResizeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Rectangle queryBorderedArea (Rectangle arg0)  {return qi(XControllerBorder.class, proxy).queryBorderedArea(arg0);}
    @Override public void removeActivationEventListener (XActivationEventListener arg0)  {qi(XActivationBroadcaster.class, proxy).removeActivationEventListener(
            arg0 instanceof Proxiable ? qi(XActivationEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addActivationEventListener (XActivationEventListener arg0)  {qi(XActivationBroadcaster.class, proxy).addActivationEventListener(
            arg0 instanceof Proxiable ? qi(XActivationEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
    @Override public void appendInfobar (String arg0, String arg1, String arg2, int arg3, StringPair[] arg4, boolean arg5) throws IllegalArgumentException  {qi(XInfobarProvider.class, proxy).appendInfobar(arg0, arg1, arg2, arg3, arg4, arg5);}
    @Override public void updateInfobar (String arg0, String arg1, String arg2, int arg3) throws NoSuchElementException  {qi(XInfobarProvider.class, proxy).updateInfobar(arg0, arg1, arg2, arg3);}
    @Override public void removeInfobar (String arg0) throws NoSuchElementException  {qi(XInfobarProvider.class, proxy).removeInfobar(arg0);}
    @Override public boolean hasInfobar (String arg0)  {return qi(XInfobarProvider.class, proxy).hasInfobar(arg0);}
    @Override public boolean hasFrozenPanes ()  {return qi(XViewFreezable.class, proxy).hasFrozenPanes();}
    @Override public void freezeAtPosition (int arg0, int arg1)  {qi(XViewFreezable.class, proxy).freezeAtPosition(arg0, arg1);}
    @Override public XEnumeration createEnumeration ()  {return qi(XEnumerationAccess.class, proxy).createEnumeration();}
    @Override public Type getElementType ()  {return qi(XEnumerationAccess.class, proxy).getElementType();}
    @Override public boolean hasElements ()  {return qi(XEnumerationAccess.class, proxy).hasElements();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public short[] getSupportedCommandGroups ()  {return qi(XDispatchInformationProvider.class, proxy).getSupportedCommandGroups();}
    @Override public DispatchInformation[] getConfigurableDispatchInformation (short arg0)  {return qi(XDispatchInformationProvider.class, proxy).getConfigurableDispatchInformation(arg0);}
    @Override public void addEnhancedMouseClickHandler (XEnhancedMouseClickHandler arg0)  {qi(XEnhancedMouseClickBroadcaster.class, proxy).addEnhancedMouseClickHandler(
            arg0 instanceof Proxiable ? qi(XEnhancedMouseClickHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeEnhancedMouseClickHandler (XEnhancedMouseClickHandler arg0)  {qi(XEnhancedMouseClickBroadcaster.class, proxy).removeEnhancedMouseClickHandler(
            arg0 instanceof Proxiable ? qi(XEnhancedMouseClickHandler.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setFirstVisibleRow (int arg0)  {qi(XViewPane.class, proxy).setFirstVisibleRow(arg0);}
    @Override public CellRangeAddress getVisibleRange ()  {return qi(XViewPane.class, proxy).getVisibleRange();}
    @Override public int getFirstVisibleColumn ()  {return qi(XViewPane.class, proxy).getFirstVisibleColumn();}
    @Override public void setFirstVisibleColumn (int arg0)  {qi(XViewPane.class, proxy).setFirstVisibleColumn(arg0);}
    @Override public int getFirstVisibleRow ()  {return qi(XViewPane.class, proxy).getFirstVisibleRow();}
    @Override public boolean isFormDesignMode ()  {return qi(XFormLayerAccess.class, proxy).isFormDesignMode();}
    @Override public XFormController getFormController (XForm arg0)  {return qi(XFormLayerAccess.class, proxy).getFormController(
            arg0 instanceof Proxiable ? qi(XForm.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void setFormDesignMode (boolean arg0)  {qi(XFormLayerAccess.class, proxy).setFormDesignMode(arg0);}
    @Override public XControl getControl (XControlModel arg0) throws NoSuchElementException  {return qi(XFormLayerAccess.class, proxy).getControl(
            arg0 instanceof Proxiable ? qi(XControlModel.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public int getCount ()  {return qi(XIndexAccess.class, proxy).getCount();}
    @Override public Object getByIndex (int arg0) throws IndexOutOfBoundsException, WrappedTargetException  {return qi(XIndexAccess.class, proxy).getByIndex(arg0);}
    @Override public XSpreadsheet getActiveSheet ()  {return qi(XSpreadsheetView.class, proxy).getActiveSheet();}
    @Override public void setActiveSheet (XSpreadsheet arg0)  {qi(XSpreadsheetView.class, proxy).setActiveSheet(
            arg0 instanceof Proxiable ? qi(XSpreadsheet.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public XTransferable getTransferable ()  {return qi(XTransferableSupplier.class, proxy).getTransferable();}
    @Override public void insertTransferable (XTransferable arg0) throws UnsupportedFlavorException  {qi(XTransferableSupplier.class, proxy).insertTransferable(
            arg0 instanceof Proxiable ? qi(XTransferable.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object getSelection ()  {return qi(XSelectionSupplier.class, proxy).getSelection();}
    @Override public void addSelectionChangeListener (XSelectionChangeListener arg0)  {qi(XSelectionSupplier.class, proxy).addSelectionChangeListener(
            arg0 instanceof Proxiable ? qi(XSelectionChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeSelectionChangeListener (XSelectionChangeListener arg0)  {qi(XSelectionSupplier.class, proxy).removeSelectionChangeListener(
            arg0 instanceof Proxiable ? qi(XSelectionChangeListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean select (Object arg0) throws IllegalArgumentException  {return qi(XSelectionSupplier.class, proxy).select(arg0);}
}
    
