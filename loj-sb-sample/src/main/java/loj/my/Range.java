package loj.my;

import java.util.Iterator;

import com.sun.star.lang.IndexOutOfBoundsException;

import loproxy.table.P_Range;

/**
 * Wrapper for a spreadsheet cell range.
 */
public class Range extends P_Range {
    /**
     * Constructs a Range from a UNO object.
     *
     * @param object the UNO cell range object
     */
    public Range(Object object) {
        super(object);
    }

    /**
     * Returns a cell within this range by column and row index.
     *
     * @param col the column index within the range
     * @param row the row index within the range
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell getCell(int col, int row) throws IndexOutOfBoundsException {
        var a = getRangeAddress();
        return new Cell(getSpreadsheet().getCellByPosition(a.StartColumn + col, a.StartRow + row));
    }
    
    /**
     * Returns the top-left cell of this range.
     *
     * @return the Cell
     * @throws IndexOutOfBoundsException if out of bounds
     */
    public Cell getCell() throws IndexOutOfBoundsException {
        return getCell(0, 0);
    }

    /**
     * Returns an iterator that traverses the cells horizontally.
     *
     * @return an Iterable over Cells
     */
    public Iterable<Cell> getHoriIterator() {
        return new Iter(false);
    }
    
    /**
     * Returns an iterator that traverses the cells vertically.
     *
     * @return an Iterable over Cells
     */
    public Iterable<Cell> getVertIterator() {
        return new Iter(true);
    }
    
    /**
     * Internal iterator implementation for Range.
     */
    class Iter implements Iterator<Cell>, Iterable<Cell> {
        boolean vert;
        int c;
        int r;
        int numCols;
        int numRows ;
        Iter(boolean isVert) {
            vert = isVert;
            var a = getRangeAddress();
            numCols = a.EndColumn - a.StartColumn + 1;
            numRows = a.EndRow - a.StartRow + 1;
        }

        @Override
        public boolean hasNext() {
            if (vert && r == 0 && c == numCols)
                return false;
            if (!vert && c == 0 && r == numRows)
                return false;
            return true;
        }

        @Override
        public Cell next() {
            Cell cell = null;
            try {
                cell = getCell(c, r);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return null;
            }
            if (vert) {
                r++;
                if (r == numRows) {
                    r = 0;
                    c++;
                }
            } else {
                c++;
                if (c == numCols) {
                    c = 0;
                    r++;
                }
            }
            return cell;
        }

        @Override
        public Iterator<Cell> iterator() {
            return this;
        }
    }
}
