package construct.base;

import java.util.Objects;

public abstract class Dimension_2 {
    protected int lenX;
    protected int lenY;

    public int surface() {
        return lenX * lenY;
    }

    public int perimeter() {
        return 2 * (lenX + lenY);
    }

    public Dimension_2(int lenX, int lenY) {
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
