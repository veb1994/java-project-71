package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Path path1 = Paths.get(filepath1);
        Path path2 = Paths.get(filepath2);
        if (!Files.exists(path1)) {
            throw new Exception("No such file: " + filepath1);
        }
        if (!Files.exists(path2)) {
            throw new Exception("No such file: " + filepath2);
        }
        return Parser.parse(filepath1, filepath2, path1, path2, format);
    }
}
