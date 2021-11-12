package construct.imlemented;

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

    protected static ArrayList<Element> elements = new ArrayList<>();
    protected static MultiHash<Tape, Element> unique = new MultiHash<>();

    public Tape(int lenZ, Info info, int price, Element element) throws NotUniqueException {
        this(0, 0, lenZ, info, price);
        unique.add(this, element);
    }

    private Tape(int lenX, int lenY, int lenZ, Info info, int quantity) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.price = quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int surface() {
        return perimeter() * lenZ;
    }

    @Override
    public int perimeter() {
        int perimeter = 0;
        for (Element e : elements)
            perimeter += e.tapeLength() * e.getQuantity();
        return perimeter;
    }

    @Override
    public int volume() {
        return 0;
    }

    public static ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                "name",
                "note",
                "price",
                "perimeter",
                "surface"
        });
        for (Tape tape : unique.keySet())
            list.get(0).add(new String[]{
                    tape.getInfo().getName(),
                    tape.getInfo().getNote(),
                    String.valueOf(tape.getPrice()),
                    String.valueOf(tape.perimeter()),
                    String.valueOf(tape.surface()),
            });
        return list;
    }

    public int length() {
        int tapeLength = 0;
        List<Element> list = elements;

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

    static String convert(int kt) {
        if (kt == 1)
            return "(X;0)";
        if (kt == 2)
            return "(X;X)";
        return "";
    }
}

