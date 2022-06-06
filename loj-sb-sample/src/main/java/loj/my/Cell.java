package loj.my;

import com.sun.star.lang.IndexOutOfBoundsException;

import loproxy.table.P_Cell;

public class Cell extends P_Cell {
    public Cell(Object object) {
        super(object);
    }
    
    public Cell offset(int cOff, int rOff) throws IndexOutOfBoundsException {
        var a = getCellAddress();
        return new Cell(getSpreadsheet().getCellByPosition(a.Column + cOff, a.Row + rOff));
    }
    public Cell up(int rOff) throws IndexOutOfBoundsException {
        return offset(0, -rOff);
    }
    public Cell down(int rOff) throws IndexOutOfBoundsException {
        return offset(0, rOff);
    }
    public Cell left(int cOff) throws IndexOutOfBoundsException {
        return offset(-cOff, 0);
    }
    public Cell right(int cOff) throws IndexOutOfBoundsException {
        return offset(cOff, 0);
    }
    public Cell up() throws IndexOutOfBoundsException {
        return up(1);
    }
    public Cell down() throws IndexOutOfBoundsException {
        return down(1);
    }
    public Cell left() throws IndexOutOfBoundsException {
        return left(1);
    }
    public Cell right() throws IndexOutOfBoundsException {
        return right(1);
    }

    public Cell set(Object o) throws IndexOutOfBoundsException {
        if (o instanceof Double)
            setValue((double)o);
        else
            setFormula("" + o);
        return this;
    }
}
