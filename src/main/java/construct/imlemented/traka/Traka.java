package construct.imlemented.traka;

import construct.base.Holder;
import construct.imlemented.element.Element;
import construct.imlemented.materijal.Materijal;
import construct.imlemented.proizvod.Proizvod;
import construct.unique.MultiHash;
import construct.base.Cena;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.NotUniqueException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Traka extends Dimension_3 implements Cena {
    protected Info info;
    protected int cena;

    protected static Holder<Element> elements = new Holder<>();
    protected static MultiHash<Traka, Element> uniquee = new MultiHash<>();


    public Traka(int lenZ, Info info, int cena, Element element) throws NotUniqueException {
        this(0, 0, lenZ, info, cena);
        if (element.duzina_trake() == 0)
            throw new NotUniqueException();
        uniquee.add(this, element);
    }

    private Traka(int lenX, int lenY, int lenZ, Info info, int cena) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.cena = cena;
    }

    static public String unique_string(String naziv, int lenZ) {
        return naziv + lenZ;
    }

    public int duzni_metri_trake() {
        int duzina_trake = 0;
        List<Element> list = elements.list();

        for (Element elem : list)
            duzina_trake += elem.duzina_trake();
        return duzina_trake * cena;
    }

    @Override
    public int racunaj() {
        return cena * duzni_metri_trake();
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
                ", cena=" + cena +
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
        Traka traka = (Traka) o;
        return cena == traka.cena && info.equals(traka.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, cena);
    }

    static public void print_static() {
        uniquee.print();
    }
}

