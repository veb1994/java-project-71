package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        if (!Files.exists(Paths.get(filepath1))) {
            throw new Exception("No such file: " + filepath1);
        }
        if (!Files.exists(Paths.get(filepath2))) {
            throw new Exception("No such file: " + filepath2);
        }
        //parse
        Map<String, Object> fileMap1 = Parser.parse(filepath1);
        Map<String, Object> fileMap2 = Parser.parse(filepath2);

        //compare
        LinkedHashMap<String, HashMap<String, Object>> compareResult = compare(fileMap1, fileMap2);

        //format
        return Formatter.format(compareResult, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }
    private static LinkedHashMap<String, HashMap<String, Object>> compare(Map<String, Object> fileMap1,
                                                                          Map<String, Object> fileMap2) {
        String keyCompareResult;
        LinkedHashMap<String, HashMap<String, Object>> compareResult = new LinkedHashMap<>();
        for (String key: mergeKeySet(fileMap1, fileMap2)) {
            Object value1 = fileMap1.get(key);
            Object value2 = fileMap2.get(key);
            HashMap<String, Object> values = new HashMap<>();
            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                if (value1.equals(value2)) {
                    keyCompareResult = "Equal";
                } else {
                    keyCompareResult = "Change";
                }
                values.put("keyCompareResult", keyCompareResult);
                values.put("value1", value1);
                values.put("value2", value2);
                compareResult.put(key, values);
            } else if (fileMap1.containsKey(key)) {
                keyCompareResult = "Delete";
                values.put("keyCompareResult", keyCompareResult);
                values.put("value1", value1);
                compareResult.put(key, values);
            } else {
                keyCompareResult = "Add";
                values.put("keyCompareResult", keyCompareResult);
                values.put("value2", value2);
                compareResult.put(key, values);
            }
        }
        return compareResult;
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
