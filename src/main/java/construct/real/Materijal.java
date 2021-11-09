package construct.real;

import construct.interfaces.Holder;
import construct.interfaces.Print;
import consturct.base.Cena;
import consturct.base.Dimension_3;
import consturct.base.Info;

import java.util.*;

public class Materijal extends Dimension_3 implements Cena, Print {

    Info info;
    String tektura;
    int cena;

    static public int DUZINA = 3000;
    static public int SIRINA = 2800;
    static public int PROSECNA_CENA = 1800;

    protected static Holder<Element> elements = new Holder<>();
    protected static Hashtable<String, Materijal> uniquee = new Hashtable<>();

    public Materijal(int lenZ, Info info, String tektura) {
        this(DUZINA, SIRINA, lenZ, info, tektura, PROSECNA_CENA);
    }

    public Materijal(int lenX, int lenY, int lenZ, Info info, String tektura, int cena) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.tektura = "-";
        this.cena = cena;

        uniquee.put(this.unique_string(), this);
    }

    static public String unique_string(String naziv, int lenZ, String tektura) {
        return naziv + lenZ + tektura;
    }

    private String unique_string() {
        return unique_string(this.info.getNaziv(), lenZ, tektura);
    }

    public static Materijal postoji(String mat) {
        if (!uniquee.containsKey(mat))
            return null;
        return uniquee.get(mat);
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
            povrsina += e.povrsina() * e.kolicina;
        return cena * povrsina;
    }

    @Override
    public void print() {
        Collection<Materijal> list = uniquee.values();
        for (Materijal m : list)
            System.out.println(m);
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

    static public void print_static(){
        Collection<Materijal> list = uniquee.values();
        for (Materijal e : list)
            System.out.println(e);
    }
}
