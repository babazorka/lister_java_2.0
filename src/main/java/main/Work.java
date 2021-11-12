package main;

import construct.imlemented.Element;
import construct.imlemented.Material;
import construct.imlemented.Product;
import construct.imlemented.Tape;
import fabric.Fabric;
import file.reader.ReadCSV;
import file.reader.CsvTable;
import file.writer.WriteCSV;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Work {
    public static void main(String[] args) {
        try {
//            ReadCSV readCSV = new ReadCSV("C:\\Users\\kaoko\\OneDrive\\Desktop\\list.csv");
            ReadCSV readCSV_realative = new ReadCSV(); // relative path
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

            WriteCSV.writeCSV(Product.csvList(),"Product");
            WriteCSV.writeCSV(Material.csvList(),"Material");
            WriteCSV.writeCSV(Tape.csvList(),"Tape");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
