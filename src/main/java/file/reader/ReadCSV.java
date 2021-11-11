package file.reader;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;

public class ReadCSV {
    static public String relative_path = "src/main/resources/list.csv";

    public void read() throws FileNotFoundException {
        CsvTable.list =
                new CsvToBeanBuilder(new FileReader(ReadCSV.relative_path))
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
