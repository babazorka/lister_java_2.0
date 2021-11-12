package imlemented;

import base.Price;
import base.Dimension_3;
import unit.Unit;
import base.Info;
import writer.OutHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Material extends Dimension_3 implements Price {
    static protected int LENGTH = 3000;
    static protected int WIDTH = 2800;
    static protected int AVERAGE_PRICE = 1800;

    protected Info info;
    protected String texture;
    protected int price;

    protected ArrayList<Element> elements = new ArrayList<>();
    protected static Map<Integer, Material> unique = new HashMap<>();


    public Material(int lenZ, Info info, String texture, Element element) throws IllegalArgumentException {
        this(Material.LENGTH, Material.WIDTH, lenZ, info, texture, Material.AVERAGE_PRICE);
        if (unique.containsKey(this.hashCode())) {
            unique.get(this.hashCode()).elements.add(element);
            throw new IllegalArgumentException("Not unique: " + this.toString());
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
        return price * surface();
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

    static public Map<String, ArrayList<ArrayList<String[]>>> csvList() {
        Map<String, ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        for (Material material : unique.values()) {
            ArrayList<ArrayList<String[]>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            map.put(material.getInfo().getName(), list);
            list.get(list.size() - 1).add(new String[]{
                    OutHeader.LenX,
                    OutHeader.KT,
                    OutHeader.LenZ,
                    OutHeader.KT,
                    OutHeader.TEXTURE,
                    OutHeader.QUANTITY,
                    OutHeader.NAME,
                    OutHeader.NOTE,
                    OutHeader.TAPE
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
        map.put("StatisticMaterial", list);
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                OutHeader.NAME,
                OutHeader.TEXTURE,
                OutHeader.PRICE,
                OutHeader.NOTE,
                OutHeader.PERIMETER,
                OutHeader.SURFACE,
                OutHeader.VOLUME,
                OutHeader.PRICE_SURFACE
        });

        for (Material material : unique.values())
            list.get(0).add(new String[]{
                    material.getInfo().getName(),
                    material.getTexture(),
                    String.valueOf(material.getPrice()),
                    material.getInfo().getNote(),
                    String.valueOf(material.perimeter()),
                    String.valueOf(material.surface()),
                    String.valueOf(material.volume()),
                    String.valueOf(material.calculate())
            });
        return map;
    }
}
