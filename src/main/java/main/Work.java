package main;

import construct.imlemented.Material;
import construct.imlemented.Product;
import construct.imlemented.Tape;
import fabric.Fabric;
import file.reader.ReadCSV;
import file.reader.CsvTable;
import file.writer.WriteCSV;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Work {
    public static void main(String[] args) {
        try {
//            ReadCSV readCSV = new ReadCSV("C:\\Users\\kaoko\\OneDrive\\Desktop\\list.csv");
            ReadCSV readCSV_relative = new ReadCSV(); // relative path
            Fabric fabric = new Fabric(CsvTable.list);

//        Element.print_static();
//        System.out.println("");
//
//            Product.print_static();
//            System.out.println("");
//
//            Tape.print_static();
//            System.out.println("");
//
//            Material.print_static();
//            System.out.println("");

//            WriteCSV.writeCSV(Product.csvList(), "Product"); // radi
//            WriteCSV.writeCSV(Material.csvList(), "Material"); // radi
            WriteCSV.writeCSV(Tape.csvList(), "Tape");
            WriteCSV.writeCSV(Material.statistic(), "StatisticsMaterial");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
