package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Json {
    public static String formatJson(LinkedHashMap<String, HashMap<String, Object>> compareResult)
            throws JsonProcessingException {
        Map<String, Object> result = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (String key: compareResult.keySet()) {
            Map<String, Object> resultMap = compareResult.get(key);
            switch (resultMap.get("keyCompareResult").toString()) {
                case ("Equal") -> result.put(key, resultMap.get("value1").toString());
                case ("Change") -> {
                    result.put("- " + key, resultMap.get("value1").toString());
                    result.put("+ " + key, resultMap.get("value2").toString());
                }
                case ("Delete") -> result.put("- " + key, resultMap.get("value1").toString());
                case ("Add") -> result.put("+ " + key, resultMap.get("value2").toString());
                default -> {
                }
            }
        }
        return objectMapper.writeValueAsString(result);
    }
}
