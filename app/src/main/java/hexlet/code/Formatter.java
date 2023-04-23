package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Formatter {
    public static String format(LinkedHashMap<String, HashMap<String, Object>> compareResult, String format)
        throws Exception {
        if (format.equals("stylish")) {
            return Stylish.formatStylish(compareResult);
        } else if (format.equals("plain")) {
            return Plain.formatPlain(compareResult);
        } else if (format.equals("json")) {
            return Json.formatJson(compareResult);
        }
        throw new Exception("Invalid format: " + format);
    }
}
