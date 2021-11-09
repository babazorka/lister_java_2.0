package file.reader;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.regex.Matcher;

public class BeanReader {
    static public String relative_path = "src/main/resources/list.csv";



    public void read() throws FileNotFoundException {
        Table.list =
                new CsvToBeanBuilder(new FileReader(BeanReader.relative_path))
                        .withType(Table.class)
                        .withFilter(line -> {
                                    Matcher matcher = Table.pattern_def_name.matcher(line[1]);
                                    boolean matchFound = matcher.find();
                                    return !matchFound;
                                }
                        )
                        .build()
                        .parse();
    }

    public static void main(String[] args) {

        try {
            Table.list =
                    new CsvToBeanBuilder(new FileReader(BeanReader.relative_path))
                            .withType(BeanReader.class)
                            .withFilter(line -> {
                                        Matcher matcher = Table.pattern_def_name.matcher(line[1]);
                                        boolean matchFound = matcher.find();
                                        return !matchFound;
                                    }
                            )
                            .build()
                            .parse();

            for (Table r : Table.list)
                System.out.println(r);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
