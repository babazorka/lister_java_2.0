package imlemented;

import base.Price;
import base.Dimension_3;
import exception.NotUniqueMaterial;
import unit.Unit;
import base.Info;
import writer.OutFileName;
import writer.OutHeader;
import writer.SpecificWrite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Material extends Dimension_3 implements Price {
    static protected int LENGTH = 3000;
    static protected int WIDTH = 2800;
    static private final int CUT_PRICE_DEFAULT = 40;
    static private int CUT_PRICE = CUT_PRICE_DEFAULT;

    static public int CUT_PRICE() {
        return CUT_PRICE;
    }

    ;

    static public void SET_CUT_PRICE(int cutPrice) {
        CUT_PRICE = cutPrice;
    }

    protected Info info;
    protected String texture;
    protected int price;

    protected ArrayList<Element> elements = new ArrayList<>();
    protected static Map<Integer, Material> unique = new HashMap<>();

    public Material(int lenZ, Info info, String texture, int price, Element element) throws NotUniqueMaterial {
        this(Material.LENGTH, Material.WIDTH, lenZ, info, texture, price);
        if (unique.containsKey(this.hashCode())) {
            unique.get(this.hashCode()).elements.add(element);
            throw new NotUniqueMaterial(unique.get(this.hashCode()));
        }
        unique.put(this.hashCode(), this);
        elements.add(element);
    }

    private Material(int lenX, int lenY, int lenZ, Info info, String texture, int price) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.texture = texture;
        this.price = price;
    }

    @Override
    public float calculate() {
        if (info.getName().contains("Ploca"))
            return price * length();
        return price * surface();
    }

    @Override
    public float length() {
        float length = 0;
        for (Element e : elements)
            length += e.length();
        return length;
    }


    @Override
    public String toString() {
        return "Material{" +
                "info=" + info +
                ", texture='" + texture + '\'' +
                ", price=" + price +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Material material = (Material) o;
        return price == material.price && info.equals(material.info) && texture.equals(material.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, texture, price);
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    static public Map<String, ArrayList<ArrayList<String[]>>>  csvList() {
        Map<String, ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        for (Material material : unique.values()) {
            ArrayList<ArrayList<String[]>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            map.put(material.getInfo().getName(), list);
            list.get(list.size() - 1).add(new String[]{
                    OutHeader.LenY,
                    OutHeader.KT,
                    OutHeader.LenX,
                    OutHeader.KT,
                    OutHeader.TEXTURE,
                    OutHeader.QUANTITY,
                    OutHeader.TAPE,
                    OutHeader.NAME,
                    OutHeader.NOTE
            });
            for (Element element : material.getElements())
                list.get(list.size() - 1).add(element.csvRow());
        }
        return map;
    }

    public Info getInfo() {
        return info;
    }

    public String getTexture() {
        return texture;
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
            perimeter += e.perimeter() * e.getQuantity();
        return perimeter;
    }

    @Override
    public float volume() {
        return surface() * lenZ * Unit.MREAL;
    }

    static public Map<String, ArrayList<ArrayList<String[]>>> statistic() {
        Map<String, ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        map.put(OutFileName.STATISTIC + Material.class.getSimpleName(), list);
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                OutHeader.NAME,
                OutHeader.TEXTURE,
                OutHeader.PRICE,
                OutHeader.PERIMETER,
                OutHeader.SURFACE,
                OutHeader.VOLUME,
                OutHeader.PRICE_SURFACE
//                ,OutHeader.NOTE
        });
        float accumulateMaterialPrice = 0;
        float accumulatePerimeter = 0;
        for (Material material : unique.values()) {
            float materialPrice = material.calculate();
            float perimeterPrice = material.perimeter();
            list.get(0).add(new String[]{
                    material.getInfo().getName(),
                    material.getTexture(),
                    String.valueOf(material.getPrice()),
                    SpecificWrite.floatToString(perimeterPrice),
                    SpecificWrite.floatToString(material.surface()),
                    SpecificWrite.floatToString(material.volume()),
                    SpecificWrite.floatToString(materialPrice)
//                    , material.getInfo().getNote()
            });
            accumulateMaterialPrice += materialPrice;
            accumulatePerimeter += perimeterPrice;
        }
        list.get(0).add(new String[]{
                "", "", "", SpecificWrite.floatToString(accumulatePerimeter * Material.CUT_PRICE()),
                "", "", SpecificWrite.floatToString(accumulateMaterialPrice), SpecificWrite.DESCRIPTION_1
        });
        list.get(0).add(new String[]{
                "", "", "", "", "", "", SpecificWrite.floatToString(accumulateMaterialPrice + accumulatePerimeter * Material.CUT_PRICE())
//                ,SpecificWrite.SUM
        });
        return map;
    }
}
