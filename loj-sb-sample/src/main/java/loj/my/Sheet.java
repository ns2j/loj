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
/**
 * Wrapper for a spreadsheet sheet.
 * Implements various sheet manipulation functions.
 */
public class Sheet extends P_Sheet {
    private static Logger logger = LoggerFactory.getLogger(Sheet.class);

    /**
     * Constructs a Sheet from a UNO object.
     *
     * @param object the UNO spreadsheet object
     */
    public Sheet(Object object) {
        super(object);
    }

    /**
     * Gets a cell by column and row index.
     *
     * @param col the column index
     * @param row the row index
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell getCell(int col, int row) throws IndexOutOfBoundsException {
        return new Cell(getCellByPosition(col, row));
    }

    /**
     * Gets a cell by name (e.g. "A1").
     *
     * @param cellName the cell name
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds or invalid name
     */
    public Cell getCell(String cellName) throws IndexOutOfBoundsException {
        return new Cell(getCellRangeByName(cellName).getCellByPosition(0, 0));
    }

    /**
     * Gets a range by coordinates.
     *
     * @param c1 the start column
     * @param r1 the start row
     * @param c2 the end column
     * @param r2 the end row
     * @return the Range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Range getRange(int c1, int r1, int c2, int r2) throws IndexOutOfBoundsException {
        return new Range(getCellRangeByPosition(c1, r1, c2, r2));
    }
    
    /**
     * Gets a range by name (e.g. "A1:B2").
     *
     * @param rangeName the range name
     * @return the Range
     */
    public Range getRange(String rangeName) {
        return new Range(getCellRangeByName(rangeName));
    }
    
    /**
     * Gets a range spanning between two cells.
     *
     * @param c1 the start cell
     * @param c2 the end cell
     * @return the Range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Range getRange(Cell c1, Cell c2) throws IndexOutOfBoundsException {
        var a1 = c1.getCellAddress();
        var a2 = c2.getCellAddress();
        return getRange(a1.Column, a1.Row, a2.Column, a2.Row);
    }

    /**
     * Sets a value to a cell by column and row index.
     *
     * @param col the column index
     * @param row the row index
     * @param o the value to set
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell set(int col, int row, Object o) throws IndexOutOfBoundsException {
        return getCell(col, row).set(o);
    }
    
    /**
     * Sets a value to a cell by name.
     *
     * @param cellName the cell name
     * @param o the value to set
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds or invalid name
     */
    public Cell set(String cellName, Object o) throws IndexOutOfBoundsException {
        return getCell(cellName).set(o);
    }

    /**
     * Gets a CellRangeAddress from a range name.
     *
     * @param rangeName the range name
     * @return the CellRangeAddress
     */
    public CellRangeAddress getRangeAddress(String rangeName) {
        return getRange(rangeName).getRangeAddress();
    }

    /**
     * Copies a range to a destination cell.
     *
     * @param destCell the destination top-left cell
     * @param srcRange the source range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public void copyRange(Cell destCell, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress destAddress = destCell.getCellAddress();
        CellRangeAddress srcRangeAddress = srcRange.getRangeAddress();
        copyRange(destAddress, srcRangeAddress);
        
    }

    /**
     * Gets the number of rows in a range.
     *
     * @param range the range
     * @return the number of rows
     */
    public int getNumRows(Range range) {
        CellRangeAddress ra = range.getRangeAddress();
        return  ra.EndRow - ra.StartRow + 1;
    }

    /**
     * Inserts rows at the specified index.
     *
     * @param row the start row index
     * @param numRows the number of rows to insert
     */
    public void insertRow(int row, int numRows) {
        getRows().insertByIndex(row, numRows);
    }

    /**
     * Inserts rows and copies content from a source range.
     *
     * @param row the start row index
     * @param srcRange the source range
     * @return the top-left cell of the new range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell insertAndCopyRow(int row, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress addr = getCell(0, row).getCellAddress();
        insertRow(row, getNumRows(srcRange));
        copyRange(addr, srcRange.getRangeAddress());
        return getCell(addr.Column, addr.Row);
    }
    
    /**
     * Inserts rows and copies content from a source range by destination cell name.
     *
     * @param destCellName the destination cell name
     * @param srcRange the source range
     * @return the top-left cell of the new range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell insertAndCopyRow(String destCellName, Range srcRange) throws IndexOutOfBoundsException {
        return insertAndCopyRow(getCell(destCellName).getCellAddress().Row, srcRange);
    }

    /**
     * Gets the number of columns in a range.
     *
     * @param range the range
     * @return the number of columns
     */
    public int getNumColumns(Range range) {
        CellRangeAddress ra = range.getRangeAddress();
        return  ra.EndColumn - ra.StartColumn + 1;
    }

    /**
     * Inserts columns at the specified index.
     *
     * @param col the start column index
     * @param numCols the number of columns to insert
     */
    public void insertColumn(int col, int numCols) {
        getColumns().insertByIndex(col, numCols);
    }

    /**
     * Inserts columns and copies content from a source range.
     *
     * @param col the start column index
     * @param srcRange the source range
     * @return the top-left cell of the new range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell insertAndCopyColumn(int col, Range srcRange) throws IndexOutOfBoundsException {
        CellAddress addr = getCell(col, 0).getCellAddress();
        insertColumn(col, getNumColumns(srcRange));
        copyRange(addr, srcRange.getRangeAddress());
        return getCell(addr.Column, addr.Row);
    }

    /**
     * Inserts columns and copies content from a source range by destination cell name.
     *
     * @param destCellName the destination cell name
     * @param srcRange the source range
     * @return the top-left cell of the new range
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell insertAndCopyColumn(String destCellName, Range srcRange) throws IndexOutOfBoundsException {
        return insertAndCopyColumn(getCell(destCellName).getCellAddress().Column, srcRange);
    }

    /**
     * Inserts cells into a destination range.
     *
     * @param destRange the destination range
     * @param mode the insertion mode
     */
    public void insertCells(Range destRange, CellInsertMode mode) {
        insertCells(destRange.getRangeAddress(), mode);
    }
}
