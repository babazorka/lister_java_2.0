package base;

import unit.Unit;

import java.util.Objects;

public abstract class Dimension_2 {
    protected int lenX;
    protected int lenY;

    public float surface() {
        return lenX * lenY * Unit.M2REAL;
    }

    public int getLenX() {
        return lenX;
    }

    public int getLenY() {
        return lenY;
    }

    public float perimeter() {
        return 2 * (lenX + lenY) * Unit.MREAL;
    }

    public Dimension_2(int lenY, int lenX) {
        this.lenX = lenX;
        this.lenY = lenY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension_2 that = (Dimension_2) o;
        return lenX == that.lenX && lenY == that.lenY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lenX, lenY);
    }

    @Override
    public String toString() {
        return "Dimension_2{" +
                "lenX=" + lenX +
                ", lenY=" + lenY +
                '}';
    }
}
