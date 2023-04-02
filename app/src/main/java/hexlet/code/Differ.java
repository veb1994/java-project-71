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
import java.util.Collections;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String result = "Both files exist";
        Path path1 = Paths.get(filepath1);
        Path path2 = Paths.get(filepath2);
        if (!Files.exists(path1)) {
            throw new Exception("No such file: " + filepath1);
        }
        if (!Files.exists(path2)) {
            throw new Exception("No such file: " + filepath2);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> fileMap1 = objectMapper.readValue(new File(filepath1), new TypeReference<Map<String, Object>>(){});
        Map<String, Object> fileMap2 = objectMapper.readValue(new File(filepath2), new TypeReference<Map<String, Object>>(){});

        List<String> keys = new ArrayList<>();
        keys.addAll(fileMap1.keySet());
        for (String key: fileMap2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }
        Collections.sort(keys);
        result = "{\n";
        for (String key: keys) {
            if (fileMap1.keySet().contains(key) && fileMap2.keySet().contains(key)) {
                if (fileMap1.get(key).equals(fileMap2.get(key))) {
                    result += "    " + key + ": " + fileMap1.get(key) + "\n";
                } else {
                    result += "  - " + key + ": " + fileMap1.get(key) + "\n";
                    result += "  + " + key + ": " + fileMap2.get(key) + "\n";
                }
            } else if (fileMap1.keySet().contains(key)) {
                result += "  - " + key + ": " + fileMap1.get(key) + "\n";
            } else {
                result += "  + " + key + ": " + fileMap2.get(key) + "\n";
            }
        }
        result += "}";
        return result;
    }
}
