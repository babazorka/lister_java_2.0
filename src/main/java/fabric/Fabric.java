package fabric;

import construct.imlemented.element.Element;
import construct.imlemented.materijal.Materijal;
import construct.imlemented.proizvod.Proizvod;
import construct.imlemented.traka.Traka;
import construct.base.Info;
import construct.unique.NotUniqueException;
import file.reader.Table;

import java.util.List;

public class Fabric {

    public Fabric(List<Table> list) {
        for (Table b : list) {
            switch (b.getTip()) {
                case Proizvod -> {
                    new Proizvod(new Info(b.getNaziv(), "napomena "), b.getCena_dinari(), b.getQuantity());
                    break;
                }
                case Element -> {
                    Element element = new Element(
                            b.getLenX(),
                            b.getLenY(),
                            b.getLenZ(),
                            new Info(b.getNaziv(), "napomena "),
                            b.getQuantity(),
                            b.getMaterial()
                    );

                    try {
                        Traka traka = new Traka(b.getTraka_debljina(), new Info(b.getTraka_materijal(), "null"), 120, element);
                    } catch (NotUniqueException e) {
                    }
                    try {
                        Materijal materijal = new Materijal(b.getLenZ(), new Info(b.getMaterial(), "null"), "-", element);
                    } catch (NotUniqueException e) {
                    }
                }
            }
        }
    }
}
