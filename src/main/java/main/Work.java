package main;

import construct.real.Element;
import construct.real.Materijal;
import construct.real.Proizvod;
import construct.real.Traka;
import fabric.Fabric;
import file.reader.BeanReader;
import file.reader.Table;

import java.io.FileNotFoundException;

public class Work {
    public static void main(String[] args) {
        BeanReader beanReader = new BeanReader();
        try {
            beanReader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Fabric fabric = new Fabric(Table.list);

        Element.print_static();
        System.out.println("");

        Proizvod.print_static();
        System.out.println("");

        Traka.print_static();
        System.out.println("");

        Materijal.print_static();
        System.out.println("");
    }
}
