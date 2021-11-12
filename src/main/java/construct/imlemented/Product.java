package construct.imlemented;

import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;

import java.util.ArrayList;
import java.util.List;

public class Product extends Dimension_3 implements Price {
    protected Info info;
    protected int price;
    protected int quantity;

    protected static ArrayList<Product> created = new ArrayList<>();

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

    public Info getInfo() {
        return info;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
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
                ", price*quantity=" + calculate() +
                '}';
    }

    static public void print_static() {
        for (Product e : created)
            System.out.println(e);
    }

    static public ArrayList<ArrayList<String[]>> csvList() {
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{"name", "note", "price", "quantity", "price*quantity"});
        int accumulatedPrice = 0;
        for (Product p : created) {
            list.get(0).add(new String[]{
                    p.info.getName(),
                    p.info.getNote(),
                    String.valueOf(p.price),
                    String.valueOf(p.quantity),
                    String.valueOf(p.calculate())
            });
            accumulatedPrice += p.calculate();
        }
        list.get(0).add(new String[]{"", "", "", "", String.valueOf(accumulatedPrice)});
        return list;
    }
}
