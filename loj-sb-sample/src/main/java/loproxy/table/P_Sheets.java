package loproxy.table;

import com.sun.star.container.ElementExistException;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.sheet.XCellRangesAccess;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets2;
import com.sun.star.table.XCell;
import com.sun.star.table.XCellRange;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_Sheets implements Proxiable, XIndexAccess, XEnumerationAccess, XTypeProvider, XCellRangesAccess, XServiceInfo, XSpreadsheets2, XWeak {
    protected Object proxy;

    public P_Sheets(Object object) {this.proxy = object;}

    public P_Sheets qi(Object object) {
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

    @Override public int getCount ()  {return qi(XIndexAccess.class, proxy).getCount();}
    @Override public Object getByIndex (int arg0) throws IndexOutOfBoundsException, WrappedTargetException  {return qi(XIndexAccess.class, proxy).getByIndex(arg0);}
    @Override public Type getElementType ()  {return qi(XIndexAccess.class, proxy).getElementType();}
    @Override public boolean hasElements ()  {return qi(XIndexAccess.class, proxy).hasElements();}
    @Override public XEnumeration createEnumeration ()  {return qi(XEnumerationAccess.class, proxy).createEnumeration();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public XCell getCellByPosition (int arg0, int arg1, int arg2) throws IndexOutOfBoundsException  {return qi(XCellRangesAccess.class, proxy).getCellByPosition(arg0, arg1, arg2);}
    @Override public XCellRange getCellRangeByPosition (int arg0, int arg1, int arg2, int arg3, int arg4) throws IndexOutOfBoundsException  {return qi(XCellRangesAccess.class, proxy).getCellRangeByPosition(arg0, arg1, arg2, arg3, arg4);}
    @Override public XCellRange[] getCellRangesByName (String arg0) throws IllegalArgumentException  {return qi(XCellRangesAccess.class, proxy).getCellRangesByName(arg0);}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public int importSheet (XSpreadsheetDocument arg0, String arg1, int arg2) throws IllegalArgumentException, IndexOutOfBoundsException  {return qi(XSpreadsheets2.class, proxy).importSheet(
            arg0 instanceof Proxiable ? qi(XSpreadsheetDocument.class, ((Proxiable)arg0).getProxy()) : arg0
            , arg1, arg2);}
    @Override public void insertNewByName (String arg0, short arg1)  {qi(XSpreadsheets2.class, proxy).insertNewByName(arg0, arg1);}
    @Override public void copyByName (String arg0, String arg1, short arg2)  {qi(XSpreadsheets2.class, proxy).copyByName(arg0, arg1, arg2);}
    @Override public void moveByName (String arg0, short arg1)  {qi(XSpreadsheets2.class, proxy).moveByName(arg0, arg1);}
    @Override public void removeByName (String arg0) throws NoSuchElementException, WrappedTargetException  {qi(XSpreadsheets2.class, proxy).removeByName(arg0);}
    @Override public void insertByName (String arg0, Object arg1) throws IllegalArgumentException, ElementExistException, WrappedTargetException  {qi(XSpreadsheets2.class, proxy).insertByName(arg0, arg1);}
    @Override public void replaceByName (String arg0, Object arg1) throws IllegalArgumentException, NoSuchElementException, WrappedTargetException  {qi(XSpreadsheets2.class, proxy).replaceByName(arg0, arg1);}
    @Override public Object getByName (String arg0) throws NoSuchElementException, WrappedTargetException  {return qi(XSpreadsheets2.class, proxy).getByName(arg0);}
    @Override public String[] getElementNames ()  {return qi(XSpreadsheets2.class, proxy).getElementNames();}
    @Override public boolean hasByName (String arg0)  {return qi(XSpreadsheets2.class, proxy).hasByName(arg0);}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
