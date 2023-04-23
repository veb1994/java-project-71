package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;

public class Parser {
    public static Map<String, Object> parse(String filepath1) throws Exception {
        ObjectMapper objectMapper;
        if (filepath1.toLowerCase().endsWith(".json")) {
            objectMapper = new ObjectMapper();
        } else if (filepath1.toLowerCase().endsWith(".yml")) {
            objectMapper = new YAMLMapper();
        } else {
            throw new Exception("Unknown file format");
        }
        Map<String, Object> fileMap = new HashMap<>();
        if (!(Files.readString(Paths.get(filepath1)).equals(""))) {
            fileMap = objectMapper.readValue(new File(filepath1), new TypeReference<>() { });
        }
        fixNull(fileMap);
        Map<String, Object> parseResult = new HashMap<>();
        parseResult.put("fileMap", fileMap);
        return fileMap;
    }

    private static void fixNull(Map<String, Object> fileMap) {
        for (String key: fileMap.keySet()) {
            if (fileMap.get(key) == null) {
                fileMap.put(key, "null");
            }
        }
    }
}
