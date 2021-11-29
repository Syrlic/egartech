package helper;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.params.provider.Arguments;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LoadDataHelper {
    static Stream<Arguments> loadDataFromFile() throws IOException, CsvException {

        Map<String, String> rowData = new HashMap<>();
        List<String[]> results;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("file/data.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(is, "UTF-8"))) {
            results = reader.readAll();
        }
        for (String[] item : results) {
            rowData.put(item[0], item[1]);
        }
        return Stream.of(
                Arguments.of(
                        rowData
                )
        );
    }
}
