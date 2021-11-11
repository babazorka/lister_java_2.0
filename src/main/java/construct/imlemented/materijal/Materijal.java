package construct.imlemented.materijal;

import construct.base.Holder;
import construct.base.Print;
import construct.imlemented.element.Element;
import construct.base.Cena;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.MultiHash;
import construct.unique.NotUniqueException;

import java.util.*;

public class Materijal extends Dimension_3 implements Cena, Print {

    Info info;
    String tektura;
    int cena;

    static public int DUZINA = 3000;
    static public int SIRINA = 2800;
    static public int PROSECNA_CENA = 1800;

    protected static Holder<Element> elements = new Holder<>();
    protected static MultiHash<Materijal, Element> uniquee = new MultiHash<>();

    public Materijal(int lenZ, Info info, String tektura, Element element) throws NotUniqueException {
        this(DUZINA, SIRINA, lenZ, info, tektura, PROSECNA_CENA);
        uniquee.add(this, element);
    }

    private Materijal(int lenX, int lenY, int lenZ, Info info, String tektura, int cena) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.tektura = "-";
        this.cena = cena;
    }

    static public String unique_string(String naziv, int lenZ, String tektura) {
        return naziv + lenZ + tektura;
    }

    public List created() {
        return (List) uniquee.values();
    }

    public List elements() {
        return elements.list();
    }

    public void add_element(Element elem) {
        elements.list().add(elem);
    }

    @Override
    public int racunaj() {
        int povrsina = 0;
        List<Element> list = elements.list();
        for (Element e : list)
            povrsina += e.povrsina() * e.getKolicina();
        return cena * povrsina;
    }

    @Override
    public void print() {
//        Collection<Materijal> list = uniquee.values();
//        for (Materijal m : list)
//            System.out.println(m);
    }

    @Override
    public String toString() {
        return "Materijal{" +
                "info=" + info +
                ", tektura='" + tektura + '\'' +
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
        Materijal materijal = (Materijal) o;
        return cena == materijal.cena && info.equals(materijal.info) && tektura.equals(materijal.tektura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, tektura, cena);
    }

    static public void print_static() {
        uniquee.print();
    }
}
