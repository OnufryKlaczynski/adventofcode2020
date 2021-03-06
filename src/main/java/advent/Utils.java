package advent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> readFile(String name) {
        try {
            return new ArrayList<String>(Files.readAllLines(Paths.get("src/main/resources/" + name)));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    };

}
