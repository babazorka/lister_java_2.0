package construct.imlemented;

import construct.base.Holder;
import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Product extends Dimension_3 implements Price {
    protected Info info;
    protected int price;
    protected int quantity;

    protected static Holder<Product> created = new Holder<>();

    public Product(Info info, int price, int quantity) {
        this(0, 0, 0, info, price, quantity);
    }

    public Product(int lenX, int lenY, int lenZ, Info info, int price, int quantity) {
        super(lenX, lenY, lenZ);
        this.info = info;
        this.price = price;
        this.quantity = quantity;

        created.add(this);
    }

    public int calculate() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "info=" + info +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    static public void print_static() {
        Collection<Product> list = created.list();
        for (Product e : list)
            System.out.println(e);
    }

    static public ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{"name", "note", "price", "quantity",});
        for (Product p : (List<Product>) created.list())
            list.get(0).add(new String[]{
                    p.info.getName(),
                    p.info.getNote(),
                    String.valueOf(p.price),
                    String.valueOf(p.quantity)
            });
        return list;
    }
}
