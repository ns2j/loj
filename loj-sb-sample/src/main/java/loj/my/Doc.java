package loj.my;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.star.container.NoSuchElementException;
import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.uno.Exception;
import com.sun.star.util.CloseVetoException;

import loj.streams.LoOutputStream;
import loproxy.container.P_Container;
import loproxy.frame.P_Controller;
import loproxy.style.P_Style;
import loproxy.table.P_Doc;
import loproxy.table.P_Sheets;

public class Doc extends P_Doc implements AutoCloseable {
    final private static Logger logger = LoggerFactory.getLogger(Doc.class);
    
    public Doc(Object object) {
        super(object);
    }

    public Sheet getSheet(String sheetName) throws NoSuchElementException, WrappedTargetException {
        return new Sheet(getSheets().getByName(sheetName));
    }
    public Sheet getSheet(int i) throws IndexOutOfBoundsException, WrappedTargetException {
        return new Sheet(new P_Sheets(getSheets()).getByIndex(i));
    }
    
    public Sheet copySheet(String src, String dest) throws NoSuchElementException, WrappedTargetException {
        XSpreadsheets sheets = getSheets();
        sheets.copyByName(src, dest, (short)sheets.getElementNames().length);
        return getSheet(dest);
    }
    public Sheet copySheet(Sheet srcSheet, String dest) throws NoSuchElementException, WrappedTargetException {
        return copySheet(srcSheet.getName(), dest);
    }

    public Sheet importSheet(Doc srcDoc, String srcSheetName, String destSheetName) throws IllegalArgumentException, Exception {
        P_Sheets sheets = new P_Sheets(getSheets());
        //Util.writeType("proxy.properties", sheets, "sheets");
        sheets.importSheet(srcDoc, srcSheetName, sheets.getElementNames().length);

        Sheet srcSheet = srcDoc.getSheet(srcSheetName);
        P_Controller srcCont = srcDoc.activate(srcSheet);
        int splitCol = srcCont.getSplitColumn();
        int splitRow = srcCont.getSplitRow();
        int splitHori = srcCont.getSplitHorizontal();
        int splitVert = srcCont.getSplitVertical();
        logger.info("srcDoc split column: " + splitCol);
        logger.info("srcDoc split row: " + splitRow);
        logger.info("srcDoc split hori: " + splitHori);
        logger.info("srcDoc split vert: " + splitVert);
        String styleName = (String)srcSheet.getPropertyValue("PageStyle");
        P_Container pageStyles = srcDoc.getPageStyles();
        Object style = null;
        for (String n: pageStyles.getElementNames()) {
            if (!styleName.equals(n)) continue;
            try {
                style = pageStyles.getByName(n);
                break;
            } catch (NoSuchElementException | WrappedTargetException e) {
                e.printStackTrace();
                continue;
            }
        }
        
        copyPageStyle(style);

        Sheet destSheet = getSheet(srcSheetName);
        destSheet.setName(destSheetName);
        destSheet.prop("PageStyle", styleName);
        P_Controller destCont = activate(destSheet);
        if (splitCol > 0 || splitRow > 0) {
            if (splitRow > 1) splitRow--;  logger.warn("why need this");//bug? need this
            destCont.freezeAtPosition(splitCol, splitRow);
        } else if (splitHori > 0 || splitVert > 0) {
            destCont.splitAtPosition(splitHori, splitVert);
        }
        return destSheet;
    }

    public void writeToStream(OutputStream os, String filterValue)  throws IOException, com.sun.star.io.IOException {
        LoOutputStream loOs = new LoOutputStream();
        var pvs = Util.getPropertyValueArray(
                Map.of("OutputStream", loOs,
                        "FilterName", filterValue));
        storeToURL("private:stream", pvs);
        os.write(loOs.toByteArray());
    }

    public P_Controller activate(Sheet sheet) {
     //   qi(XSpreadsheetView.class, getCurrentController()).setActiveSheet(sheet.qi(XSpreadsheet.class));
//        new P_Controller(getCurrentController()).setActiveSheet(sheet.qi(XSpreadsheet.class));
        P_Controller cont = new P_Controller(getCurrentController());
        cont.setActiveSheet(sheet);
        return cont;
    }

    @Override
    public void close() throws CloseVetoException {
        close(true);
        //qi(XComponent.class).dispose();
    }

    public P_Container getCellStyles() throws NoSuchElementException, WrappedTargetException {
        return new P_Container(getStyleFamilies().getByName("CellStyles"));
    }
    public P_Container getPageStyles() throws NoSuchElementException, WrappedTargetException {
        return new P_Container(getStyleFamilies().getByName("PageStyles"));
    }
    
    public void copyPageStyle(Object origPageStyle) throws IllegalArgumentException, Exception {
        String styleName = new P_Style(origPageStyle).getName();
        logger.info("orig page style name: " + styleName);
        Object newPageStyle;;
        P_Container styles = getPageStyles();
        logger.info("has name: " + styles.hasByName(styleName));
        if (styles.hasByName(styleName)) {
            newPageStyle = styles.getByName(styleName);
        } else {
            newPageStyle = createInstance("com.sun.star.style.PageStyle");
            styles.insertByName(styleName, newPageStyle);
        }
        Prop.of(origPageStyle).copy(newPageStyle);
    }
}
