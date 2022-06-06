package loproxy.chart;

import com.sun.star.awt.Rectangle;
import com.sun.star.container.NoSuchElementException;
import com.sun.star.container.XEnumeration;
import com.sun.star.container.XEnumerationAccess;
import com.sun.star.container.XIndexAccess;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.lang.XServiceInfo;
import com.sun.star.lang.XTypeProvider;
import com.sun.star.table.CellRangeAddress;
import com.sun.star.table.XTableCharts;
import com.sun.star.uno.Type;
import com.sun.star.uno.XAdapter;
import com.sun.star.uno.XWeak;
import com.sun.star.uno.UnoRuntime;
import loj.proxy.Proxiable;

public class P_TableCharts implements Proxiable, XIndexAccess, XTableCharts, XEnumerationAccess, XTypeProvider, XServiceInfo, XWeak {
    protected Object proxy;

    public P_TableCharts(Object object) {this.proxy = object;}

    public P_TableCharts qi(Object object) {
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
    @Override public void addNewByName (String arg0, Rectangle arg1, CellRangeAddress[] arg2, boolean arg3, boolean arg4)  {qi(XTableCharts.class, proxy).addNewByName(arg0, arg1, arg2, arg3, arg4);}
    @Override public void removeByName (String arg0)  {qi(XTableCharts.class, proxy).removeByName(arg0);}
    @Override public Object getByName (String arg0) throws NoSuchElementException, WrappedTargetException  {return qi(XTableCharts.class, proxy).getByName(arg0);}
    @Override public String[] getElementNames ()  {return qi(XTableCharts.class, proxy).getElementNames();}
    @Override public boolean hasByName (String arg0)  {return qi(XTableCharts.class, proxy).hasByName(arg0);}
    @Override public XEnumeration createEnumeration ()  {return qi(XEnumerationAccess.class, proxy).createEnumeration();}
    @Override public byte[] getImplementationId ()  {return qi(XTypeProvider.class, proxy).getImplementationId();}
    @Override public Type[] getTypes ()  {return qi(XTypeProvider.class, proxy).getTypes();}
    @Override public String[] getSupportedServiceNames ()  {return qi(XServiceInfo.class, proxy).getSupportedServiceNames();}
    @Override public boolean supportsService (String arg0)  {return qi(XServiceInfo.class, proxy).supportsService(arg0);}
    @Override public String getImplementationName ()  {return qi(XServiceInfo.class, proxy).getImplementationName();}
    @Override public XAdapter queryAdapter ()  {return qi(XWeak.class, proxy).queryAdapter();}
}
    
