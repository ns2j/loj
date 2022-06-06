package loproxy.table;

import com.sun.star.beans.GetDirectPropertyTolerantResult;
import com.sun.star.beans.GetPropertyTolerantResult;
import com.sun.star.beans.PropertyState;
import com.sun.star.beans.PropertyValue;
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
import com.sun.star.chart.XChartDataArray;
import com.sun.star.chart.XChartDataChangeEventListener;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.container.XNameAccess;
import com.sun.star.document.XActionLockable;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.lang.XUnoTunnel;
import com.sun.star.sheet.FillDateMode;
import com.sun.star.sheet.FillDirection;
import com.sun.star.sheet.FillMode;
import com.sun.star.sheet.FormulaToken;
import com.sun.star.sheet.GeneralFunction;
import com.sun.star.sheet.TableOperationMode;
import com.sun.star.sheet.XArrayFormulaRange;
import com.sun.star.sheet.XArrayFormulaTokens;
import com.sun.star.sheet.XCellAddressable;
import com.sun.star.sheet.XCellFormatRangesSupplier;
import com.sun.star.sheet.XCellRangeAddressable;
import com.sun.star.sheet.XCellRangeData;
import com.sun.star.sheet.XCellRangeFormula;
import com.sun.star.sheet.XCellRangesQuery;
import com.sun.star.sheet.XCellSeries;
import com.sun.star.sheet.XFormulaQuery;
import com.sun.star.sheet.XFormulaTokens;
import com.sun.star.sheet.XMultipleOperation;
import com.sun.star.sheet.XSheetAnnotation;
import com.sun.star.sheet.XSheetAnnotationAnchor;
import com.sun.star.sheet.XSheetCellRange;
import com.sun.star.sheet.XSheetCellRanges;
import com.sun.star.sheet.XSheetFilterDescriptor;
import com.sun.star.sheet.XSheetFilterable;
import com.sun.star.sheet.XSheetFilterableEx;
import com.sun.star.sheet.XSheetOperation;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSubTotalCalculatable;
import com.sun.star.sheet.XSubTotalDescriptor;
import com.sun.star.sheet.XUniqueCellFormatRangesSupplier;
import com.sun.star.table.CellAddress;
import com.sun.star.table.CellContentType;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XAutoFormattable;
import com.sun.star.table.XCell;
import com.sun.star.table.XCell2;
import com.sun.star.table.XCellRange;
import com.sun.star.table.XColumnRowRange;
import com.sun.star.table.XTableColumns;
import com.sun.star.table.XTableRows;
import com.sun.star.text.XText;
import com.sun.star.text.XTextContent;
import com.sun.star.text.XTextCursor;
import com.sun.star.text.XTextFieldsSupplier;
import com.sun.star.text.XTextRange;
import com.sun.star.uno.Exception;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.util.XImportable;
import com.sun.star.util.XIndent;
import com.sun.star.util.XMergeable;
import com.sun.star.util.XModifyBroadcaster;
import com.sun.star.util.XModifyListener;
import com.sun.star.util.XReplaceDescriptor;
import com.sun.star.util.XReplaceable;
import com.sun.star.util.XSearchDescriptor;
import com.sun.star.util.XSortable;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Cell implements Proxiable, XMergeable, XText, XArrayFormulaRange, XCellFormatRangesSupplier, XUniqueCellFormatRangesSupplier, XTextFieldsSupplier, XCellRangeAddressable, XFormulaTokens, XCellRangesQuery, XSubTotalCalculatable, XPropertySet, XUnoTunnel, XAutoFormattable, XSheetCellRange, XPropertyState, XCellRangeFormula, XIndent, XArrayFormulaTokens, XFormulaQuery, XWeak, XReplaceable, XSheetFilterableEx, XCellAddressable, XCellRangeData, XMultipleOperation, XModifyBroadcaster, XChartDataArray, XEnumerationAccess, XActionLockable, XCell2, XServiceInfo, XImportable, XMultiPropertySet, XSheetOperation, XTolerantMultiPropertySet, XSortable, XTypeProvider, XColumnRowRange, XSheetAnnotationAnchor, XCellSeries {
    protected Object proxy;

    public P_Cell(Object object) {this.proxy = object;}

    public P_Cell qi(Object object) {
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

    public P_Cell prop(String key, Object value) throws IllegalArgumentException, UnknownPropertyException, PropertyVetoException, WrappedTargetException {
        setPropertyValue(key, value);
        return this;
    }

    @Override public void merge (boolean arg0)  {qi(XMergeable.class, proxy).merge(arg0);}
    @Override public boolean getIsMerged ()  {return qi(XMergeable.class, proxy).getIsMerged();}
    @Override public void insertTextContent (XTextRange arg0, XTextContent arg1, boolean arg2) throws IllegalArgumentException  {qi(XText.class, proxy).insertTextContent(
            arg0 instanceof Proxiable ? qi(XTextRange.class, ((Proxiable)arg0).getProxy()) : arg0
            , 
            arg1 instanceof Proxiable ? qi(XTextContent.class, ((Proxiable)arg1).getProxy()) : arg1
            , arg2);}
    @Override public void removeTextContent (XTextContent arg0) throws NoSuchElementException  {qi(XText.class, proxy).removeTextContent(
            arg0 instanceof Proxiable ? qi(XTextContent.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XTextCursor createTextCursor ()  {return qi(XText.class, proxy).createTextCursor();}
    @Override public void insertString (XTextRange arg0, String arg1, boolean arg2)  {qi(XText.class, proxy).insertString(
            arg0 instanceof Proxiable ? qi(XTextRange.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1, arg2);}
    @Override public void insertControlCharacter (XTextRange arg0, short arg1, boolean arg2) throws IllegalArgumentException  {qi(XText.class, proxy).insertControlCharacter(
            arg0 instanceof Proxiable ? qi(XTextRange.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1, arg2);}
    @Override public XTextCursor createTextCursorByRange (XTextRange arg0)  {return qi(XText.class, proxy).createTextCursorByRange(
            arg0 instanceof Proxiable ? qi(XTextRange.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public String getString ()  {return qi(XText.class, proxy).getString();}
    @Override public XText getText ()  {return qi(XText.class, proxy).getText();}
    @Override public XTextRange getStart ()  {return qi(XText.class, proxy).getStart();}
    @Override public XTextRange getEnd ()  {return qi(XText.class, proxy).getEnd();}
    @Override public void setString (String arg0)  {qi(XText.class, proxy).setString(arg0);}
    @Override public String getArrayFormula ()  {return qi(XArrayFormulaRange.class, proxy).getArrayFormula();}
    @Override public void setArrayFormula (String arg0)  {qi(XArrayFormulaRange.class, proxy).setArrayFormula(arg0);}
    @Override public XIndexAccess getCellFormatRanges ()  {return qi(XCellFormatRangesSupplier.class, proxy).getCellFormatRanges();}
    @Override public XIndexAccess getUniqueCellFormatRanges ()  {return qi(XUniqueCellFormatRangesSupplier.class, proxy).getUniqueCellFormatRanges();}
    @Override public XEnumerationAccess getTextFields ()  {return qi(XTextFieldsSupplier.class, proxy).getTextFields();}
    @Override public XNameAccess getTextFieldMasters ()  {return qi(XTextFieldsSupplier.class, proxy).getTextFieldMasters();}
    @Override public CellRangeAddress getRangeAddress ()  {return qi(XCellRangeAddressable.class, proxy).getRangeAddress();}
    @Override public FormulaToken[] getTokens ()  {return qi(XFormulaTokens.class, proxy).getTokens();}
    @Override public void setTokens (FormulaToken[] arg0)  {qi(XFormulaTokens.class, proxy).setTokens(arg0);}
    @Override public XSheetCellRanges queryVisibleCells ()  {return qi(XCellRangesQuery.class, proxy).queryVisibleCells();}
    @Override public XSheetCellRanges queryEmptyCells ()  {return qi(XCellRangesQuery.class, proxy).queryEmptyCells();}
    @Override public XSheetCellRanges queryContentCells (short arg0)  {return qi(XCellRangesQuery.class, proxy).queryContentCells(arg0);}
    @Override public XSheetCellRanges queryFormulaCells (int arg0)  {return qi(XCellRangesQuery.class, proxy).queryFormulaCells(arg0);}
    @Override public XSheetCellRanges queryColumnDifferences (CellAddress arg0)  {return qi(XCellRangesQuery.class, proxy).queryColumnDifferences(arg0);}
    @Override public XSheetCellRanges queryRowDifferences (CellAddress arg0)  {return qi(XCellRangesQuery.class, proxy).queryRowDifferences(arg0);}
    @Override public XSheetCellRanges queryIntersection (CellRangeAddress arg0)  {return qi(XCellRangesQuery.class, proxy).queryIntersection(arg0);}
    @Override public void removeSubTotals ()  {qi(XSubTotalCalculatable.class, proxy).removeSubTotals();}
    @Override public XSubTotalDescriptor createSubTotalDescriptor (boolean arg0)  {return qi(XSubTotalCalculatable.class, proxy).createSubTotalDescriptor(arg0);}
    @Override public void applySubTotals (XSubTotalDescriptor arg0, boolean arg1)  {qi(XSubTotalCalculatable.class, proxy).applySubTotals(
            arg0 instanceof Proxiable ? qi(XSubTotalDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1);}
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
    @Override public void autoFormat (String arg0) throws IllegalArgumentException  {qi(XAutoFormattable.class, proxy).autoFormat(arg0);}
    @Override public XSpreadsheet getSpreadsheet ()  {return qi(XSheetCellRange.class, proxy).getSpreadsheet();}
    @Override public XCell getCellByPosition (int arg0, int arg1) throws IndexOutOfBoundsException  {return qi(XSheetCellRange.class, proxy).getCellByPosition(arg0, arg1);}
    @Override public XCellRange getCellRangeByName (String arg0)  {return qi(XSheetCellRange.class, proxy).getCellRangeByName(arg0);}
    @Override public XCellRange getCellRangeByPosition (int arg0, int arg1, int arg2, int arg3) throws IndexOutOfBoundsException  {return qi(XSheetCellRange.class, proxy).getCellRangeByPosition(arg0, arg1, arg2, arg3);}
    @Override public PropertyState getPropertyState (String arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyState(arg0);}
    @Override public PropertyState[] getPropertyStates (String[] arg0) throws UnknownPropertyException  {return qi(XPropertyState.class, proxy).getPropertyStates(arg0);}
    @Override public void setPropertyToDefault (String arg0) throws UnknownPropertyException  {qi(XPropertyState.class, proxy).setPropertyToDefault(arg0);}
    @Override public Object getPropertyDefault (String arg0) throws UnknownPropertyException, WrappedTargetException  {return qi(XPropertyState.class, proxy).getPropertyDefault(arg0);}
    @Override public String[][] getFormulaArray ()  {return qi(XCellRangeFormula.class, proxy).getFormulaArray();}
    @Override public void setFormulaArray (String[][] arg0)  {qi(XCellRangeFormula.class, proxy).setFormulaArray(arg0);}
    @Override public void decrementIndent ()  {qi(XIndent.class, proxy).decrementIndent();}
    @Override public void incrementIndent ()  {qi(XIndent.class, proxy).incrementIndent();}
    @Override public void setArrayTokens (FormulaToken[] arg0)  {qi(XArrayFormulaTokens.class, proxy).setArrayTokens(arg0);}
    @Override public FormulaToken[] getArrayTokens ()  {return qi(XArrayFormulaTokens.class, proxy).getArrayTokens();}
    @Override public XSheetCellRanges queryDependents (boolean arg0)  {return qi(XFormulaQuery.class, proxy).queryDependents(arg0);}
    @Override public XSheetCellRanges queryPrecedents (boolean arg0)  {return qi(XFormulaQuery.class, proxy).queryPrecedents(arg0);}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
    @Override public int replaceAll (XSearchDescriptor arg0)  {return qi(XReplaceable.class, proxy).replaceAll(
            arg0 instanceof Proxiable ? qi(XSearchDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XReplaceDescriptor createReplaceDescriptor ()  {return qi(XReplaceable.class, proxy).createReplaceDescriptor();}
    @Override public XIndexAccess findAll (XSearchDescriptor arg0)  {return qi(XReplaceable.class, proxy).findAll(
            arg0 instanceof Proxiable ? qi(XSearchDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public Object findFirst (XSearchDescriptor arg0)  {return qi(XReplaceable.class, proxy).findFirst(
            arg0 instanceof Proxiable ? qi(XSearchDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XSearchDescriptor createSearchDescriptor ()  {return qi(XReplaceable.class, proxy).createSearchDescriptor();}
    @Override public Object findNext (Object arg0, XSearchDescriptor arg1)  {return qi(XReplaceable.class, proxy).findNext(arg0, 
            arg1 instanceof Proxiable ? qi(XSearchDescriptor.class, ((Proxiable)arg1).getProxy()) : arg1
            );}
    @Override public XSheetFilterDescriptor createFilterDescriptorByObject (XSheetFilterable arg0)  {return qi(XSheetFilterableEx.class, proxy).createFilterDescriptorByObject(
            arg0 instanceof Proxiable ? qi(XSheetFilterable.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void filter (XSheetFilterDescriptor arg0)  {qi(XSheetFilterableEx.class, proxy).filter(
            arg0 instanceof Proxiable ? qi(XSheetFilterDescriptor.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public XSheetFilterDescriptor createFilterDescriptor (boolean arg0)  {return qi(XSheetFilterableEx.class, proxy).createFilterDescriptor(arg0);}
    @Override public CellAddress getCellAddress ()  {return qi(XCellAddressable.class, proxy).getCellAddress();}
    @Override public Object[][] getDataArray ()  {return qi(XCellRangeData.class, proxy).getDataArray();}
    @Override public void setDataArray (Object[][] arg0)  {qi(XCellRangeData.class, proxy).setDataArray(arg0);}
    @Override public void setTableOperation (CellRangeAddress arg0, TableOperationMode arg1, CellAddress arg2, CellAddress arg3)  {qi(XMultipleOperation.class, proxy).setTableOperation(arg0, arg1, arg2, arg3);}
    @Override public void removeModifyListener (XModifyListener arg0)  {qi(XModifyBroadcaster.class, proxy).removeModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void addModifyListener (XModifyListener arg0)  {qi(XModifyBroadcaster.class, proxy).addModifyListener(
            arg0 instanceof Proxiable ? qi(XModifyListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public String[] getRowDescriptions ()  {return qi(XChartDataArray.class, proxy).getRowDescriptions();}
    @Override public void setRowDescriptions (String[] arg0)  {qi(XChartDataArray.class, proxy).setRowDescriptions(arg0);}
    @Override public String[] getColumnDescriptions ()  {return qi(XChartDataArray.class, proxy).getColumnDescriptions();}
    @Override public void setColumnDescriptions (String[] arg0)  {qi(XChartDataArray.class, proxy).setColumnDescriptions(arg0);}
    @Override public double[][] getData ()  {return qi(XChartDataArray.class, proxy).getData();}
    @Override public void setData (double[][] arg0)  {qi(XChartDataArray.class, proxy).setData(arg0);}
    @Override public double getNotANumber ()  {return qi(XChartDataArray.class, proxy).getNotANumber();}
    @Override public void addChartDataChangeEventListener (XChartDataChangeEventListener arg0)  {qi(XChartDataArray.class, proxy).addChartDataChangeEventListener(
            arg0 instanceof Proxiable ? qi(XChartDataChangeEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public void removeChartDataChangeEventListener (XChartDataChangeEventListener arg0)  {qi(XChartDataArray.class, proxy).removeChartDataChangeEventListener(
            arg0 instanceof Proxiable ? qi(XChartDataChangeEventListener.class, ((Proxiable)arg0).getProxy()) : arg0
            );}
    @Override public boolean isNotANumber (double arg0)  {return qi(XChartDataArray.class, proxy).isNotANumber(arg0);}
    @Override public XEnumeration createEnumeration ()  {return qi(XEnumerationAccess.class, proxy).createEnumeration();}
    @Override public Type getElementType ()  {return qi(XEnumerationAccess.class, proxy).getElementType();}
    @Override public boolean hasElements ()  {return qi(XEnumerationAccess.class, proxy).hasElements();}
    @Override public void setActionLocks (short arg0)  {qi(XActionLockable.class, proxy).setActionLocks(arg0);}
    @Override public short resetActionLocks ()  {return qi(XActionLockable.class, proxy).resetActionLocks();}
    @Override public boolean isActionLocked ()  {return qi(XActionLockable.class, proxy).isActionLocked();}
    @Override public void addActionLock ()  {qi(XActionLockable.class, proxy).addActionLock();}
    @Override public void removeActionLock ()  {qi(XActionLockable.class, proxy).removeActionLock();}
    @Override public void setFormulaResult (double arg0)  {qi(XCell2.class, proxy).setFormulaResult(arg0);}
    @Override public void setFormulaString (String arg0)  {qi(XCell2.class, proxy).setFormulaString(arg0);}
    @Override public double getValue ()  {return qi(XCell2.class, proxy).getValue();}
    @Override public void setValue (double arg0)  {qi(XCell2.class, proxy).setValue(arg0);}
    @Override public CellContentType getType ()  {return qi(XCell2.class, proxy).getType();}
    @Override public String getFormula ()  {return qi(XCell2.class, proxy).getFormula();}
    @Override public void setFormula (String arg0)  {qi(XCell2.class, proxy).setFormula(arg0);}
    @Override public int getError ()  {return qi(XCell2.class, proxy).getError();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public PropertyValue[] createImportDescriptor (boolean arg0)  {return qi(XImportable.class, proxy).createImportDescriptor(arg0);}
    @Override public void doImport (PropertyValue[] arg0)  {qi(XImportable.class, proxy).doImport(arg0);}
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
    @Override public double computeFunction (GeneralFunction arg0) throws Exception  {return qi(XSheetOperation.class, proxy).computeFunction(arg0);}
    @Override public void clearContents (int arg0)  {qi(XSheetOperation.class, proxy).clearContents(arg0);}
    @Override public SetPropertyTolerantFailed[] setPropertyValuesTolerant (String[] arg0, Object[] arg1) throws IllegalArgumentException  {return qi(XTolerantMultiPropertySet.class, proxy).setPropertyValuesTolerant(arg0, arg1);}
    @Override public GetPropertyTolerantResult[] getPropertyValuesTolerant (String[] arg0)  {return qi(XTolerantMultiPropertySet.class, proxy).getPropertyValuesTolerant(arg0);}
    @Override public GetDirectPropertyTolerantResult[] getDirectPropertyValuesTolerant (String[] arg0)  {return qi(XTolerantMultiPropertySet.class, proxy).getDirectPropertyValuesTolerant(arg0);}
    @Override public void sort (PropertyValue[] arg0)  {qi(XSortable.class, proxy).sort(arg0);}
    @Override public PropertyValue[] createSortDescriptor ()  {return qi(XSortable.class, proxy).createSortDescriptor();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public XTableColumns getColumns ()  {return qi(XColumnRowRange.class, proxy).getColumns();}
    @Override public XTableRows getRows ()  {return qi(XColumnRowRange.class, proxy).getRows();}
    @Override public XSheetAnnotation getAnnotation ()  {return qi(XSheetAnnotationAnchor.class, proxy).getAnnotation();}
    @Override public void fillSeries (FillDirection arg0, FillMode arg1, FillDateMode arg2, double arg3, double arg4)  {qi(XCellSeries.class, proxy).fillSeries(arg0, arg1, arg2, arg3, arg4);}
    @Override public void fillAuto (FillDirection arg0, int arg1)  {qi(XCellSeries.class, proxy).fillAuto(arg0, arg1);}
}
    
