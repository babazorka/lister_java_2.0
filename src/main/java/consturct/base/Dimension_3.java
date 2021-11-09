package consturct.base;

import java.util.Objects;

public abstract class Dimension_3 extends Dimension_2 {
    protected int lenZ;

    public int zapremina() {
        return povrsina() * lenZ;
    }

    public Dimension_3(int lenX, int lenY, int lenZ) {
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
