package construct.imlemented;

import construct.base.Holder;
import construct.unique.MultiHash;
import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.NotUniqueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tape extends Dimension_3 implements Price {
    protected Info info;
    protected int price;

    protected static Holder<Element> elements = new Holder<>();
    protected static MultiHash<Tape, Element> unique = new MultiHash<>();

    public Tape(int lenZ, Info info, int price, Element element) throws NotUniqueException {
        this(0, 0, lenZ, info, price);
        if (element.tapeLength() == 0)
            throw new NotUniqueException();
        unique.add(this, element);
    }

    private Tape(int lenX, int lenY, int lenZ, Info info, int quantity) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.price = quantity;
    }

    public int length() {
        int tapeLength = 0;
        List<Element> list = elements.list();

        for (Element elem : list)
            tapeLength += elem.tapeLength();
        return tapeLength * price;
    }

    @Override
    public int calculate() {
        return price * length();
    }

    @Override
    public String toString() {
        return "Traka{" +
                "info=" + info +
                ", quantity=" + price +
                ", lenZ=" + lenZ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tape tape = (Tape) o;
        return price == tape.price && info.equals(tape.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, price);
    }

    static public void print_static() {
        unique.print();
    }

    public Info getInfo() {
        return info;
    }

    static public ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                "name",
                "note",
                "lenZ",
                "length"
        });

        for (Tape tape : unique.keySet())
            list.get(0).add(new String[]{
                    tape.getInfo().getName(),
                    tape.getInfo().getNote(),
                    String.valueOf(tape.getLenZ()),
                    String.valueOf(tape.length())
            });

        return list;
    }
}

