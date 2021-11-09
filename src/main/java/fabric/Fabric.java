package fabric;

import construct.real.Element;
import construct.real.Materijal;
import construct.real.Proizvod;
import construct.real.Traka;
import consturct.base.Info;
import file.reader.Table;

import java.util.List;

public class Fabric {

    public Fabric(List<Table> list) {
        for (Table b : list) {
            switch (b.getTip()) {
                case Proizvod -> new Proizvod(new Info(b.getNaziv(), "napomena "), b.getCena_dinari(), b.getQuantity());
                case Element -> {
                    Element element = new Element(
                            b.getLenX(),
                            b.getLenY(),
                            b.getLenZ(),
                            new Info(b.getNaziv(), "napomena "),
                            b.getQuantity(),
                            b.getMaterial()
                    );

                    String materijal_unique = b.materijal_unique();
                    String traka_unique = b.traka_unique();

                    Materijal materijal = Materijal.postoji(materijal_unique);
                    Traka traka = Traka.postoji(materijal_unique);

                    if (materijal == null)
                        materijal = new Materijal(b.getLenZ(), new Info(b.getMaterial(), "null"), "-");
                    materijal.add_element(element);

                    if (traka == null)
                        traka = new Traka(b.getLenZ(), new Info(b.getMaterial(), "null"), 120);
                    traka.add_element(element);
                }
            }
        }
    }
}
