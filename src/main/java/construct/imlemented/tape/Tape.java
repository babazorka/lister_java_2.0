package construct.imlemented.tape;

import construct.base.Holder;
import construct.imlemented.element.Element;
import construct.unique.MultiHash;
import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.NotUniqueException;

import java.util.List;
import java.util.Objects;

public class Tape extends Dimension_3 implements Price {
    protected Info info;
    protected int quantity;

    protected static Holder<Element> elements = new Holder<>();
    protected static MultiHash<Tape, Element> uniquee = new MultiHash<>();


    public Tape(int lenZ, Info info, int quantity, Element element) throws NotUniqueException {
        this(0, 0, lenZ, info, quantity);
        if (element.duzina_trake() == 0)
            throw new NotUniqueException();
        uniquee.add(this, element);
    }

    private Tape(int lenX, int lenY, int lenZ, Info info, int quantity) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.quantity = quantity;
    }

    static public String unique_string(String naziv, int lenZ) {
        return naziv + lenZ;
    }

    public int duzni_metri_trake() {
        int duzina_trake = 0;
        List<Element> list = elements.list();

        for (Element elem : list)
            duzina_trake += elem.duzina_trake();
        return duzina_trake * quantity;
    }

    @Override
    public int calculate() {
        return quantity * duzni_metri_trake();
    }

    public List elements() {
        return elements.list();
    }

    public void add_element(Element elem) {
        elements.add(elem);
    }

    @Override
    public String toString() {
        return "Traka{" +
                "info=" + info +
                ", quantity=" + quantity +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tape traka = (Tape) o;
        return quantity == traka.quantity && info.equals(traka.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, quantity);
    }

    static public void print_static() {
        uniquee.print();
    }
}

