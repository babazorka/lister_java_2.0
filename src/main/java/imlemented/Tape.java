package imlemented;

import base.Price;
import base.Dimension_3;
import unit.Unit;
import base.Info;

import java.util.*;
import java.util.List;

public class Tape extends Dimension_3 implements Price {
    protected Info info;
    protected int price;

    protected ArrayList<Element> elements = new ArrayList<>();
    protected static Map<Integer, Tape> unique = new HashMap<>();

    public Tape(int lenZ, Info info, int price, Element element) throws IllegalArgumentException {
        this(0, 0, lenZ, info, price);
        if (unique.containsKey(this.hashCode())) {
            unique.get(this.hashCode()).elements.add(element);
            throw new IllegalArgumentException("Not unique: " + this.toString());
        }
        unique.put(this.hashCode(), this);
        elements.add(element);
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
        return lenZ * perimeter();
    }

    @Override
    public int perimeter() {
        int perimeter = 0;
        for (Element e : elements)
            perimeter += e.tapeLength() * e.getQuantity();
        return perimeter;
    }

    @Override
    public float volume() {
        return 0;
    }

    public static ArrayList<ArrayList<String[]>> statistic() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        for (Tape tape : unique.values()) {
            list.add(new ArrayList<>());
            list.get(list.size() - 1).add(new String[]{
                    "name",
                    "note",
                    "price",
                    "elements",
                    "perimeter",
                    "surface",
                    "price*perimeter"
            });
            list.get(list.size() - 1).add(new String[]{
                    tape.getInfo().getName(),
                    tape.getInfo().getNote(),
                    String.valueOf(tape.getPrice()),
                    String.valueOf(tape.elements.size()),
                    String.valueOf(tape.perimeter() / Unit.PERIMETER),
                    String.valueOf(tape.surface() / Unit.SURFACE),
                    String.valueOf(tape.calculate())
            });
        }
        return list;
    }

    public int length() {
        int tapeLength = 0;
        List<Element> list = elements;

        for (Element elem : list)
            tapeLength += elem.tapeLength();
        return tapeLength;
    }

    @Override
    public float calculate() {
        return price * length() / Unit.PERIMETER;
    }

    @Override
    public String toString() {
        return "Tape{" +
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

//    static public void print_static() {
//        unique.print();
//    }

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

