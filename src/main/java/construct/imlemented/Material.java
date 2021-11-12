package construct.imlemented;

import construct.base.Holder;
import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;
import construct.unique.MultiHash;
import construct.unique.NotUniqueException;

import java.util.*;

public class Material extends Dimension_3 implements Price {

    Info info;
    String texture;
    int quantity;

    static public int LENGTH = 3000;
    static public int WIDTH = 2800;
    static public int AVERAGE_PRICE = 1800;

    protected static Holder<Element> elements = new Holder<>();
    protected static MultiHash<Material, Element> unique = new MultiHash<>();

    public Material(int lenZ, Info info, String texture, Element element) throws NotUniqueException {
        this(LENGTH, WIDTH, lenZ, info, texture, AVERAGE_PRICE);
        unique.add(this, element);
    }

    private Material(int lenX, int lenY, int lenZ, Info info, String texture, int quantity) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.texture = "-";
        this.quantity = quantity;
    }

    @Override
    public int calculate() {
        int square = 0;
        List<Element> list = elements.list();
        for (Element e : list)
            square += e.povrsina() * e.getQuantity();
        return quantity * square;
    }

    @Override
    public String toString() {
        return "Materijal{" +
                "info=" + info +
                ", texture='" + texture + '\'' +
                ", quantity=" + quantity +
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
        return quantity == material.quantity && info.equals(material.info) && texture.equals(material.texture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), info, texture, quantity);
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
}
