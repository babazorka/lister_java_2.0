package construct.imlemented.proizvod;

import construct.base.Holder;
import construct.base.Print;
import construct.base.Cena;
import construct.base.Dimension_3;
import construct.base.Info;

import java.util.Collection;
import java.util.List;

public class Proizvod extends Dimension_3 implements Cena, Print {
    Info info;
    int cena;
    int kolicina;

    protected static Holder<Proizvod> created = new Holder<>();

    public Proizvod(Info info, int cena, int kolicina) {
        this(0, 0, 0, info, cena, kolicina);
    }

    public Proizvod(int lenX, int lenY, int lenZ, Info info, int cena, int kolicina) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.cena = cena;
        this.kolicina = kolicina;

        created.add(this);
    }

    public int racunaj() {
        return cena * kolicina;
    }

    @Override
    public void print() {
        List<Proizvod> list = created.list();
        for (Proizvod e : list)
            System.out.println(e);
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "info=" + info +
                ", cena=" + cena +
                ", kolicina=" + kolicina +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    static public void print_static(){
        Collection<Proizvod> list = created.list();
        for (Proizvod e : list)
            System.out.println(e);
    }
}
