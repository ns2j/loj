package loj.sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.star.lang.IllegalArgumentException;
import com.sun.star.table.BorderLine2;
import com.sun.star.table.BorderLineStyle;
import com.sun.star.uno.Exception;

import loj.my.Cell;
import loj.my.Doc;
import loj.my.Range;
import loj.my.Sheet;
import loj.my.Util;

@Service
public class IndexService {
    private static Logger logger = LoggerFactory.getLogger(IndexService.class);

    public void make(Doc origDoc, Doc newDoc) throws IllegalArgumentException, Exception {
        Sheet origSheet = newDoc.importSheet(origDoc, "何かの原本", "原本");
        Sheet newSheet = newDoc.copySheet(origSheet, "新シート");
        newDoc.activate(newSheet);
        Range range = newSheet.getRange("A2:D5");
        Range srcRange = origSheet.getRange("A3:D3"); 
//        Util.writeType("proxy.properties", newDoc, "doc");
//        Util.writeType("proxy.properties", origSheet, "sheet");
//        Util.writeType("proxy.properties", range, "range");

        int numData = 14;
        Cell last = null;
        for (int i = 0; i < numData; i++) {
            Cell c = range.getCell(0, i);
            if  (i == 0 || i == numData - 1) {
                newSheet.copyRange(c, srcRange);
            } else {
                c = newSheet.insertAndCopyRow(c.getCellAddress().Row, srcRange);
            }
            c.set(i + 100).right()
            .set(i + 3).right()
            .set(i + 10);
            c.prop("CellBackColor", Util.getColor("#AAAAAA"));
            last = c;
        }
        Cell sum = last.down();
        Cell sumEnd = sum.right(3);
        Range sumRange = newSheet.getRange(sum, sumEnd);
        BorderLine2 tb = new BorderLine2();
        tb.LineStyle = BorderLineStyle.DOUBLE_THIN;
        tb.LineWidth = 50;
        sum.prop("LeftBorder2", tb);
        sumRange.prop("TopBorder2", tb).prop("BottomBorder2", tb);
        sumEnd.prop("RightBorder2", tb);
        
        Range r = newSheet.getRange("F2:H4");
        int i = 0;
        for (Cell c0: r.getHoriIterator()) {
            c0.set(i);
            i++;
        }
        r = newSheet.getRange("F8:H12");
        i = 0;
        for (Cell c0: r.getVertIterator()) {
            c0.set(i);
            i++;
        }
                
    }
}
