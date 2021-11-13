package imlemented;

import base.Price;
import base.Dimension_3;
import base.Info;
import exception.NotUniqueTape;
import unit.Unit;
import writer.OutHeader;
import writer.OutFileName;
import writer.SpecificWrite;

import java.util.*;
import java.util.List;

public class Tape extends Dimension_3 implements Price {
    protected Info info;
    protected int price;

    protected ArrayList<Element> elements = new ArrayList<>();
    protected static Map<Integer, Tape> unique = new HashMap<>();

    public Tape(int lenX, int lenZ, Info info, int price, Element element) throws NotUniqueTape {
        this(lenX, Integer.MAX_VALUE, lenZ, info, price);
        if (unique.containsKey(this.hashCode())) {
            unique.get(this.hashCode()).elements.add(element);
            throw new NotUniqueTape(unique.get(this.hashCode()));
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
    public float surface() {
        float surface = 0;
        for (Element e : elements)
            surface += e.surface() * e.getQuantity();
        return surface;
    }

    @Override
    public float perimeter() {
        float perimeter = 0;
        for (Element e : elements)
            perimeter += e.tapeLength() * e.getQuantity();
        return perimeter;
    }

    @Override
    public float volume() {
        return surface() * lenZ * Unit.MREAL;
    }

    private int numberOfElements() {
        int num = 0;
        for (Element element : elements)
            num += element.getQuantity();
        return num;
    }

    public static Map<String, ArrayList<ArrayList<String[]>>> statistic() {
        Map<String, ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        map.put(OutFileName.STATISTIC + Tape.class.getSimpleName(), list);
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                OutHeader.NAME,
                OutHeader.LenX,
                OutHeader.LenZ,
                OutHeader.PRICE,
                SpecificWrite.NUMBEROF + Element.class.getSimpleName(),
                OutHeader.PERIMETER,
//                OutHeader.SURFACE,
                OutHeader.PRICE_PERIMETER,
                OutHeader.NOTE
        });
        float accumulatePerimeterPrice = 0;
        for (Tape tape : unique.values()) {
            float calculate = tape.calculate();
            list.get(0).add(new String[]{
                    tape.getInfo().getName(),
                    String.valueOf(tape.lenX),
                    String.valueOf(tape.getLenZ()),
                    String.valueOf(tape.getPrice()),
                    String.valueOf(tape.numberOfElements()),
                    SpecificWrite.floatToString(tape.perimeter()),
//                    String.valueOf(tape.surface()),
                    SpecificWrite.floatToString(calculate),
                    tape.getInfo().getNote()
            });
            accumulatePerimeterPrice += calculate;
        }
        list.get(0).add(new String[]{"", "", "", "", "", "", "", String.valueOf(accumulatePerimeterPrice), SpecificWrite.SUM});

        return map;
    }

    public float length() {
        float tapeLength = 0;
        List<Element> list = elements;

        for (Element elem : list)
            tapeLength += elem.tapeLength();
        return tapeLength;
    }

    @Override
    public float calculate() {
        return price * length();
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

    public Info getInfo() {
        return info;
    }

    static String convert(int kt) {
        if (kt == 1)
            return "(X,0)";
        if (kt == 2)
            return "(X,X)";
        return "";
    }
}

