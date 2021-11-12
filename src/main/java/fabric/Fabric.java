package fabric;

import construct.imlemented.Element;
import construct.imlemented.Product;
import construct.base.Info;
import file.reader.CsvTable;

import java.util.List;

public class Fabric {

    public Fabric(List<CsvTable> list) {
        for (CsvTable b : list) {
            switch (b.getTip()) {
                case Proizvod -> {
                    new Product(new Info(b.getNaziv(), b.getNapomena()), b.getCena_dinari(), b.getQuantity());
                    break;
                }
                case Element -> {
                    Element element = new Element(
                            b.getLenX(),
                            b.getLenY(),
                            b.getLenZ(),
                            new Info(b.getNaziv(), b.getNapomena()),
                            b.getQuantity(),
                            b.getMaterial(),
                            b.getTraka_debljina(),
                            b.getTraka_materijal(),
                            b.getKant_duzina(),
                            b.getKant_sirina()
                    );
                }
            }
        }
    }
}
