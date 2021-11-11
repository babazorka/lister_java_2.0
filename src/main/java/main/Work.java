package main;

import construct.imlemented.element.Element;
import construct.imlemented.material.Material;
import construct.imlemented.product.Product;
import construct.imlemented.tape.Tape;
import fabric.Fabric;
import file.reader.ReadCSV;
import file.reader.CsvTable;

import java.io.FileNotFoundException;

public class Work {
    public static void main(String[] args) {
        ReadCSV beanReader = new ReadCSV();
        try {
            beanReader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Fabric fabric = new Fabric(CsvTable.list);

        Element.print_static();
        System.out.println("");

        Product.print_static();
        System.out.println("");

        Tape.print_static();
        System.out.println("");

        Material.print_static();
        System.out.println("");
    }
}
