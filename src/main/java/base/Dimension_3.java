package base;

import unit.Unit;

import java.util.Objects;

public abstract class Dimension_3 extends Dimension_2 {
    protected int lenZ;

    public float volume() {
        return surface() * lenZ * Unit.MREAL;
    }

    public Dimension_3(int lenY, int lenX, int lenZ) {
        super(lenX, lenY);
        this.lenZ = lenZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dimension_3 that = (Dimension_3) o;
        return lenY == that.lenY;
    }

    public int getLenZ() {
        return lenZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lenY);
    }

    @Override
    public String toString() {
        return "Dimension_3{" +
                "lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }
}
