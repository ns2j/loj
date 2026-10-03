package loj.my;

import com.sun.star.lang.IndexOutOfBoundsException;

import loproxy.table.P_Cell;

/**
 * Wrapper for a spreadsheet cell.
 */
public class Cell extends P_Cell {
    /**
     * Constructs a Cell from a UNO object.
     *
     * @param object the UNO cell object
     */
    public Cell(Object object) {
        super(object);
    }
    
    /**
     * Returns a cell offset from this one by given columns and rows.
     *
     * @param cOff the column offset
     * @param rOff the row offset
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if the resulting cell is out of bounds
     */
    public Cell offset(int cOff, int rOff) throws IndexOutOfBoundsException {
        var a = getCellAddress();
        return new Cell(getSpreadsheet().getCellByPosition(a.Column + cOff, a.Row + rOff));
    }
    /**
     * Returns a cell offset upward.
     *
     * @param rOff the row offset
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell up(int rOff) throws IndexOutOfBoundsException {
        return offset(0, -rOff);
    }
    /**
     * Returns a cell offset downward.
     *
     * @param rOff the row offset
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell down(int rOff) throws IndexOutOfBoundsException {
        return offset(0, rOff);
    }
    /**
     * Returns a cell offset to the left.
     *
     * @param cOff the column offset
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell left(int cOff) throws IndexOutOfBoundsException {
        return offset(-cOff, 0);
    }
    /**
     * Returns a cell offset to the right.
     *
     * @param cOff the column offset
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell right(int cOff) throws IndexOutOfBoundsException {
        return offset(cOff, 0);
    }
    /**
     * Returns a cell one row up.
     *
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell up() throws IndexOutOfBoundsException {
        return up(1);
    }
    /**
     * Returns a cell one row down.
     *
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell down() throws IndexOutOfBoundsException {
        return down(1);
    }
    /**
     * Returns a cell one column left.
     *
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell left() throws IndexOutOfBoundsException {
        return left(1);
    }
    /**
     * Returns a cell one column right.
     *
     * @return the offset Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell right() throws IndexOutOfBoundsException {
        return right(1);
    }

    /**
     * Sets a value or formula to the cell.
     *
     * @param o the value to set, Double or otherwise stringified formula
     * @return this Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell set(Object o) throws IndexOutOfBoundsException {
        if (o instanceof Double)
            setValue((double)o);
        else
            setFormula("" + o);
        return this;
    }
}
