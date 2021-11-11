package construct.imlemented.element;

import construct.base.Holder;
import construct.base.Print;
import construct.base.Dimension_3;
import construct.base.Info;

import java.util.List;
import java.util.Objects;

public class Element extends Dimension_3 implements Print {
    protected Info info;
    protected int kolicina;
    protected int kt_duzina;
    protected int kt_srina;

    protected static Holder<Element> created = new Holder<>();

    public Element(int lenX, int lenY, int lenZ, Info info, int kolicina, String materijal) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.kolicina = kolicina;

        created.add(this);
    }

    public int getKolicina() {
        return kolicina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Element element = (Element) o;
        return kolicina == element.kolicina && kt_duzina == element.kt_duzina && kt_srina == element.kt_srina && info.equals(element.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, kolicina, kt_duzina, kt_srina);
    }

    public int duzina_trake() {
        return kt_duzina * lenY + kt_duzina * lenX;
    }

    @Override
    public void print() {
        List<Element> list = created.list();
        for (Element e : list)
            System.out.println(e);
    }

    @Override
    public String toString() {
        return "Element{" +
                "info=" + info +
                ", kolicina=" + kolicina +
                ", kt_duzina=" + kt_duzina +
                ", kt_srina=" + kt_srina +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    static public void print_static(){
        List<Element> list = created.list();
        for (Element e : list)
            System.out.println(e);
    }
}
