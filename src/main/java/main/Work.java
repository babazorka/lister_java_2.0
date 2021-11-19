package main;

import imlemented.Material;
import imlemented.Product;
import imlemented.Tape;
import fabric.Fabric;
import reader.ReadCSV;
import reader.CsvTable;
import writer.SpecificWrite;
import writer.WriteCSV;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Work {
    public static void main(String[] args) {
        try {
//            SpecificWrite.SET_SEPARATOR(SpecificWrite.SEPARATOR_COMMA); // ';' is default

//            ReadCSV readCSV = new ReadCSV("C:\\Users\\kaoko\\OneDrive\\Desktop\\list.csv");
            new ReadCSV("list"); // relative path
//            new ReadCSV("proba"); // relative path
            new Fabric(CsvTable.list);

            WriteCSV.writeCSV(Product.csvList());
            WriteCSV.writeCSV(Material.csvList());
            WriteCSV.writeCSV(Tape.statistic());
            WriteCSV.writeCSV(Material.statistic());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
