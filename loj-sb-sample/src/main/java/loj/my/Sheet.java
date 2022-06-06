package loj.my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.sheet.CellInsertMode;
import com.sun.star.table.CellAddress;
import com.sun.star.table.CellRangeAddress;

import loproxy.table.P_Sheet;
/*
com.sun.star.container.XNamed com.sun.star.sheet.XCellRangeMovement com.sun.star.sheet.XSpreadsheets2 com.sun.star.table.XColumnRowRange
 */
public class Sheet extends P_Sheet {
    private static Logger logger = LoggerFactory.getLogger(Sheet.class);

    public Sheet(Object object) {
        super(object);
    }

    public Cell getCell(int col, int row) throws IndexOutOfBoundsException {
        return new Cell(getCellByPosition(col, row));
    }

    public Cell getCell(String cellName) throws IndexOutOfBoundsException {
        return new Cell(getCellRangeByName(cellName).getCellByPosition(0, 0));
    }

    public Range getRange(int c1, int r1, int c2, int r2) throws IndexOutOfBoundsException {
        return new Range(getCellRangeByPosition(c1, r1, c2, r2));
    }
    public Range getRange(String rangeName) {
        return new Range(getCellRangeByName(rangeName));
    }
    public Range getRange(Cell c1, Cell c2) throws IndexOutOfBoundsException {
        var a1 = c1.getCellAddress();
        var a2 = c2.getCellAddress();
        return getRange(a1.Column, a1.Row, a2.Column, a2.Row);
    }

    public Cell set(int col, int row, Object o) throws IndexOutOfBoundsException {
        return getCell(col, row).set(o);
    }
    
    public Cell set(String cellName, Object o) throws IndexOutOfBoundsException {
        return getCell(cellName).set(o);
    }

    public CellRangeAddress getRangeAddress(String rangeName) {
        return getRange(rangeName).getRangeAddress();
    }

    public void copyRange(Cell destCell, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress destAddress = destCell.getCellAddress();
        CellRangeAddress srcRangeAddress = srcRange.getRangeAddress();
        copyRange(destAddress, srcRangeAddress);
        
    }

    public int getNumRows(Range range) {
        CellRangeAddress ra = range.getRangeAddress();
        return  ra.EndRow - ra.StartRow + 1;
    }

    public void insertRow(int row, int numRows) {
        getRows().insertByIndex(row, numRows);
    }

    public Cell insertAndCopyRow(int row, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress addr = getCell(0, row).getCellAddress();
        insertRow(row, getNumRows(srcRange));
        copyRange(addr, srcRange.getRangeAddress());
        return getCell(addr.Column, addr.Row);
    }
    
    public Cell insertAndCopyRow(String destCellName, Range srcRange) throws IndexOutOfBoundsException {
        return insertAndCopyRow(getCell(destCellName).getCellAddress().Row, srcRange);
    }

    public int getNumColumns(Range range) {
        CellRangeAddress ra = range.getRangeAddress();
        return  ra.EndColumn - ra.StartColumn + 1;
    }

    public void insertColumn(int col, int numCols) {
        getColumns().insertByIndex(col, numCols);
    }

    public Cell insertAndCopyColumn(int col, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress addr = getCell(col, 0).getCellAddress();
        insertColumn(col, getNumColumns(srcRange));
        copyRange(addr, srcRange.getRangeAddress());
        return getCell(addr.Column, addr.Row);
    }

    public Cell insertAndCopyColumn(String destCellName, Range srcRange) throws IndexOutOfBoundsException {
        return insertAndCopyColumn(getCell(destCellName).getCellAddress().Column, srcRange);
    }

    public void insertCells(Range destRange, CellInsertMode mode) {
        insertCells(destRange.getRangeAddress(), mode);
    }
}
