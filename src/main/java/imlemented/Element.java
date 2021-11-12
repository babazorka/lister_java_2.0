package imlemented;

import base.Dimension_3;
import base.Info;
import unit.Unit;

import java.util.Objects;

public class Element extends Dimension_3 {
    protected Info info;
    protected int quantity;
    protected int cantedLength;
    protected int cantedWidth;
    protected String tapeMaterial;

    public Element(int lenX, int lenY, int lenZ, Info info, int quantity, String material, int tapeLenZ, String tapeMaterial, int cantedLength, int cantedWidth) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.quantity = quantity;
        this.cantedLength = cantedLength;
        this.cantedWidth = cantedWidth;
        this.tapeMaterial = tapeMaterial;

        try {
            new Material(lenZ, new Info(material, ""), "-", this);
        } catch (IllegalArgumentException e) {
        }
        try {
            if (tapeLength() > 0)
                new Tape(lenZ, tapeLenZ, new Info(tapeMaterial, ""), 120, this);
        } catch (IllegalArgumentException e) {
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

    public float tapeLength() {
        return (cantedLength * lenY + cantedWidth * lenX) * Unit.MREAL;
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
                Tape.convert(cantedLength),
                String.valueOf(lenY),
                Tape.convert(cantedWidth),
                "-",
                String.valueOf(quantity),
                info.getName(),
                info.getNote(),
                tapeMaterial
        };
    }
}
