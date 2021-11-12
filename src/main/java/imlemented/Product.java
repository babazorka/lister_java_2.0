package imlemented;

import base.Price;
import base.Dimension_3;
import base.Info;
import writer.OutFileName;
import writer.OutHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public float calculate() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "info=" + info +
                ", price=" + price +
                ", quantity=" + quantity +
                ", price*quantity=" + calculate() +
                '}';
    }

    static public Map<String, ArrayList<ArrayList<String[]>>> csvList() {
        Map<String, ArrayList<ArrayList<String[]>>> map = new HashMap<>();
        ArrayList<ArrayList<String[]>> list = new ArrayList<>();
        map.put(OutFileName.STATISTIC + Product.class.getSimpleName(), list);
        list.add(new ArrayList<>());
        list.get(0).add(new String[]{
                OutHeader.NAME,
                OutHeader.NOTE,
                OutHeader.PRICE,
                OutHeader.QUANTITY,
                OutHeader.PRICE_QUANTITY
        });
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
        return map;
    }
}
