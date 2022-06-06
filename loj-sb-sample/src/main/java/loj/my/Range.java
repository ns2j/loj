package loj.my;

import java.util.Iterator;

import com.sun.star.lang.IndexOutOfBoundsException;

import loproxy.table.P_Range;

public class Range extends P_Range {
    public Range(Object object) {
        super(object);
    }

    public Cell getCell(int col, int row) throws IndexOutOfBoundsException {
        var a = getRangeAddress();
        return new Cell(getSpreadsheet().getCellByPosition(a.StartColumn + col, a.StartRow + row));
    }
    public Cell getCell() throws IndexOutOfBoundsException {
        return getCell(0, 0);
    }

    public Iterable<Cell> getHoriIterator() {
        return new Iter(false);
    }
    public Iterable<Cell> getVertIterator() {
        return new Iter(true);
    }
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
