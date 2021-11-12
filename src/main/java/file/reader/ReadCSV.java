package file.reader;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;

public class ReadCSV {
    static protected String relativePath = "src/main/resources/list.csv";

    public ReadCSV() throws FileNotFoundException {
        this(ReadCSV.relativePath);
    }

    public ReadCSV(String absolute_path) throws FileNotFoundException {
        CsvTable.list =
                new CsvToBeanBuilder(new FileReader(absolute_path))
                        .withType(CsvTable.class)
                        .withFilter(line -> {
                                    Matcher matcher = CsvTable.pattern_def_name.matcher(line[1]);
                                    boolean matchFound = matcher.find();
                                    return !matchFound;
                                }
                        )
                        .build()
                        .parse();
    }
}
