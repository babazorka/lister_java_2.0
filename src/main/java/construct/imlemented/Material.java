package construct.imlemented;

import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.MultiHash;
import construct.unique.NotUniqueException;

import java.util.*;

public class Material extends Dimension_3 implements Price {

    protected Info info;
    protected String texture;
    protected int price = AVERAGE_PRICE;

    static public int LENGTH = 3000;
    static public int WIDTH = 2800;
    static public int AVERAGE_PRICE = 1800;

    protected static ArrayList<Element> elements = new ArrayList<>();
    protected static MultiHash<Material, Element> unique = new MultiHash<>();

    public Material(int lenZ, Info info, String texture, Element element) throws NotUniqueException {
        this(LENGTH, WIDTH, lenZ, info, texture, AVERAGE_PRICE);
        unique.add(this, element);
    }

    private Material(int lenX, int lenY, int lenZ, Info info, String texture, int price) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.texture = "-";
        this.price = price;
    }

    @Override
    public int calculate() {
        return price * surface();
    }

    @Override
    public String toString() {
        return "Materijal{" +
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

    static public void print_static() {
        unique.print();
    }

    static public ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        for (Material material : unique.getMulitMap().keySet()) {
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
            for (Element element : unique.getMulitMap().get(material))
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
    public int surface() {
        int surface = 0;
        for (Element e : elements)
            surface += e.surface() * e.getQuantity();
        return surface;
    }

    @Override
    public int perimeter() {
        int perimeter = 0;
        for (Element e : elements)
            perimeter += e.perimeter() * e.getQuantity();
        return perimeter;
    }

    @Override
    public int volume() {
        int volume = 0;
        for (Element e : elements)
            volume += e.perimeter() * e.getQuantity();
        return volume;
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

        for (Material material : unique.keySet())
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
