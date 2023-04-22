package hexlet.code.formatters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Stylish {
    public static String formatStylish(LinkedHashMap<String, HashMap<String, Object>> compareResult) {
        StringBuilder result = new StringBuilder("{\n");
        for (String key: compareResult.keySet()) {
            Map<String, Object> resultMap = compareResult.get(key);
            switch (resultMap.get("keyCompareResult").toString()) {
                case ("Equal") -> result.append(" ".repeat(4)).append(key).append(": ")
                        .append(resultMap.get("value1")).append("\n");
                case ("Change") -> {
                    result.append("  - ").append(key).append(": ").append(resultMap.get("value1")).append("\n");
                    result.append("  + ").append(key).append(": ").append(resultMap.get("value2")).append("\n");
                }
                case ("Delete") -> result.append("  - ").append(key).append(": ").
                        append(resultMap.get("value1")).append("\n");
                case ("Add") -> result.append("  + ").append(key).append(": ")
                        .append(resultMap.get("value2")).append("\n");
                default -> {
                }
            }
        }
        result.append("}");
        return result.toString();
    }
}
