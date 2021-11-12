package construct.imlemented;

import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.NotUniqueException;

import java.util.Objects;

public class Element extends Dimension_3 {
    protected Info info;
    protected int quantity;
    protected int cantedLength;
    protected int cantedWidth;

    public Element(int lenX, int lenY, int lenZ, Info info, int quantity, String material, int tapeLenZ, String tapeMaterial) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.quantity = quantity;

        try {
            new Material(lenZ, new Info(material, ""), "-", this);
        } catch (NotUniqueException e) {
        }
        try {
            new Tape(tapeLenZ, new Info(tapeMaterial, ""), 120, this);
        } catch (NotUniqueException e) {
        }
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Element element = (Element) o;
        return quantity == element.quantity && cantedLength == element.cantedLength && cantedWidth == element.cantedWidth && info.equals(element.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, quantity, cantedLength, cantedWidth);
    }

    public int tapeLength() {
        return cantedLength * lenY + cantedLength * lenX;
    }

    @Override
    public String toString() {
        return "Element{" +
                "info=" + info +
                ", quantity=" + quantity +
                ", kt_duzina=" + cantedLength +
                ", kt_srina=" + cantedWidth +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    public String[] csvRow() {
        return new String[]{
                String.valueOf(lenX),
                String.valueOf(cantedLength),
                String.valueOf(lenY),
                String.valueOf(cantedWidth),
                info.getName(),
                "-",
                String.valueOf(quantity)
        };
    }
}
