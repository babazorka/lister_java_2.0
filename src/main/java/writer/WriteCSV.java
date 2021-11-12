package writer;

import com.opencsv.CSVWriter;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class WriteCSV {

    //    static protected String folder_path = "C:\\Users\\kaoko\\OneDrive\\Desktop\\Lists\\";
    static protected String relative_folder_path = "src/main/resources/";

    static public void writeCSV(ArrayList<ArrayList<String[]>> files, String fileName) throws IOException {
        for (ArrayList<String[]> file : files)
            WriteCSV.write(file, fileName + RandomStringUtils.random(2, false, true));
    }

    static private void write(ArrayList<String[]> csvTable, String fileName) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/output/" + fileName + ".csv"));
             CSVWriter csvWriter = new CSVWriter(writer,
                     CSVWriter.DEFAULT_SEPARATOR,
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END);
        ) {
            for (String[] row : csvTable)
                csvWriter.writeNext(row);
        }
    }

    private WriteCSV() {
    }
}