package construct.imlemented.product;

import construct.base.Holder;
import construct.base.Print;
import construct.base.Price;
import construct.base.Dimension_3;
import construct.base.Info;

import java.util.Collection;
import java.util.List;

public class Product extends Dimension_3 implements Price, Print {
    Info info;
    int price;
    int quantity;

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
    public void print() {
        List<Product> list = created.list();
        for (Product e : list)
            System.out.println(e);
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "info=" + info +
                ", price=" + price +
                ", quantity=" + quantity +
                ", lenX=" + lenX +
                ", lenY=" + lenY +
                ", lenZ=" + lenZ +
                '}';
    }

    static public void print_static(){
        Collection<Product> list = created.list();
        for (Product e : list)
            System.out.println(e);
    }
}
