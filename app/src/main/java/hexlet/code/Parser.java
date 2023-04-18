package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Parser {
    public static String parse(String filepath1, String filepath2, Path path1, Path path2, String format)
            throws Exception {
        ObjectMapper objectMapper;
        if (filepath1.toLowerCase().endsWith("json")) {
            objectMapper = new ObjectMapper();
        } else if (filepath1.toLowerCase().endsWith("yml")) {
            objectMapper = new YAMLMapper();
        } else {
            throw new Exception("Unknown file format");
        }
        Map<String, Object> fileMap1 = new HashMap<>();
        Map<String, Object> fileMap2 = new HashMap<>();
        if (!(Files.readString(path1).equals(""))) {
            fileMap1 = objectMapper.readValue(new File(filepath1), new TypeReference<>() { });
        }
        if (!(Files.readString(path2).equals(""))) {
            fileMap2 = objectMapper.readValue(new File(filepath2), new TypeReference<>() { });
        }
        fixNull(fileMap1);
        fixNull(fileMap2);
        return compare(fileMap1, fileMap2, format);
    }

    private static void fixNull(Map<String, Object> fileMap) {
        for (String key: fileMap.keySet()) {
            if (fileMap.get(key) == null) {
                fileMap.put(key, "null");
            }
        }
    }

    private static String compare(Map<String, Object> fileMap1, Map<String, Object> fileMap2, String format)
            throws Exception {
        StringBuilder result = new StringBuilder("{\n");
        String comparisonResult = "";
        for (String key: mergeKeySet(fileMap1, fileMap2)) {
            Object value1 = fileMap1.get(key);
            Object value2 = fileMap2.get(key);
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (value1.equals(value2)) {
                    comparisonResult = "Equal";
                    result.append(Formatter.format(key, value1, value2, comparisonResult, format));
                } else {
                    comparisonResult = "Change";
                    result.append(Formatter.format(key, value1, value2, comparisonResult, format));
                }
            } else if (fileMap1.containsKey(key)) {
                comparisonResult = "Delete";
                result.append(Formatter.format(key, value1, value2, comparisonResult, format));
            } else {
                comparisonResult = "Add";
                result.append(Formatter.format(key, value1, value2, comparisonResult, format));
            }
        }
        result.append("}");
        return result.toString();
    }
    private static List<String> mergeKeySet(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        List<String> keys = new ArrayList<>(fileMap1.keySet());
        for (String key: fileMap2.keySet()) {
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }
        Collections.sort(keys);
        return keys;
    }
}
