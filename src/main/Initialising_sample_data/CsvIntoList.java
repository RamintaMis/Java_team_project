import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvIntoList {
    public List<List> readData(String directory) throws IOException {
        List<String> data = null;
        List<List> rows = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(directory));
            String line = "";
            while ((line = br.readLine()) != null) {
                String str[] = line.split(",");
                data = Arrays.asList(str);
                rows.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return rows;
    }
}
