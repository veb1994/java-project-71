package hexlet.code.formatters;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Plain {
    public static String formatPlain(LinkedHashMap<String, HashMap<String, Object>> compareResult) {
        StringBuilder result = new StringBuilder();
        for (String key: compareResult.keySet()) {
            Map<String, Object> resultMap = compareResult.get(key);
            Object value1 = null;
            Object value2 = null;
            if (resultMap.containsKey("value1")) {
                value1 = resultMap.get("value1");
                if (value1.getClass().toString().startsWith("class java.util.")) {
                    value1 = "[complex value]";
                } else if (value1.getClass().toString().equals("class java.lang.String") && !value1.equals("null")) {
                    value1 = String.format("'%s'", value1);
                }
            }
            if (resultMap.containsKey("value2")) {
                value2 = resultMap.get("value2");
                if (value2.getClass().toString().startsWith("class java.util.")) {
                    value2 = "[complex value]";
                } else if (value2.getClass().toString().equals("class java.lang.String") && !value2.equals("null")) {
                    value2 = String.format("'%s'", value2);
                }
            }
            switch (resultMap.get("keyCompareResult").toString()) {
                case ("Change") ->
                        result.append(String.format("Property '%s' was updated. From %s to %s\n", key, value1, value2));
                case ("Delete") -> result.append(String.format("Property '%s' was removed\n", key));
                case ("Add") -> result.append(String.format("Property '%s' was added with value: %s\n", key, value2));
                default -> {
                }
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
