package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

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
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> fileMap1 = new HashMap<>();
        Map<String, Object> fileMap2 = new HashMap<>();
        if (!(Files.readString(path1).equals(""))) {
            fileMap1 = objectMapper.readValue(new File(filepath1), new TypeReference<>() { });
        }
        if (!(Files.readString(path2).equals(""))) {
            fileMap2 = objectMapper.readValue(new File(filepath2), new TypeReference<>() { });
        }
        return compare(fileMap1, fileMap2);
    }

    public static String compare(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        List<String> keys = new ArrayList<>(fileMap1.keySet());
        for (String key: fileMap2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }
        Collections.sort(keys);
        StringBuilder result = new StringBuilder("{\n");
        for (String key: keys) {
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (fileMap1.get(key).equals(fileMap2.get(key))) {
                    result.append("    ").append(key).append(": ").append(fileMap1.get(key)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(fileMap1.get(key)).append("\n");
                    result.append("  + ").append(key).append(": ").append(fileMap2.get(key)).append("\n");
                }
            } else if (fileMap1.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(fileMap1.get(key)).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(fileMap2.get(key)).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
