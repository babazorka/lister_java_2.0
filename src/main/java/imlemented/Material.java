package imlemented;

import base.Price;
import base.Dimension_3;
import unit.Unit;
import base.Info;

import java.util.*;

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

    static public ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        for (Material material : unique.values()) {
            list.add(new ArrayList<>());
            list.get(list.size() - 1).add(new String[]{
                    "lenX",
                    "kt",
                    "lenZ",
                    "kt",
                    "name",
                    "texture",
                    "quantity",
            });
            for (Element element : material.getElements())
                list.get(list.size() - 1).add(element.csvRow());
        }
        return list;
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

    static public ArrayList<ArrayList<String[]>> statistic() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                "name",
                "texture",
                "price",
                "note",
                "perimeter",
                "surface",
                "volume",
                "surface*calculate"
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
        return list;
    }
}
