package construct.real;

import construct.interfaces.Holder;
import consturct.base.Cena;
import consturct.base.Dimension_3;
import consturct.base.Info;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

public class Traka extends Dimension_3 implements Cena {
    protected Info info;
    protected int cena;


    protected static Holder<Element> elements = new Holder<>();
    protected static Hashtable<String, Traka> uniquee = new Hashtable<>();

    public Traka(int lenZ, Info info, int cena) {
        this(0, 0, lenZ, info, cena);
    }

    public Traka(int lenX, int lenY, int lenZ, Info info, int cena) {

        super(lenX, lenY, lenZ);
        this.info = info;
        this.cena = cena;

        uniquee.put(this.unique_string(), this);
    }

    public static Traka postoji(String mat) {
        if (!uniquee.containsKey(mat))
            return null;
        return uniquee.get(mat);
    }

    static public String unique_string(String naziv, int lenZ) {
        return naziv + lenZ;
    }

    private String unique_string() {
        return unique_string(this.info.getNaziv(), lenZ);
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

    public List created() {
        return (List) uniquee.values();
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

    static public void print_static() {
        Collection<Traka> list = uniquee.values();
        for (Traka e : list)
            System.out.println(e);
    }
}

