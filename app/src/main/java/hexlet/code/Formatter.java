package hexlet.code;

public class Formatter {
    public static String format(String key, Object value1, Object value2, String comparisonResult, String format)
        throws Exception {
        if (format.equals("stylish")) {
            return formatStylish(key, value1, value2, comparisonResult);
        }
        throw new Exception("Invalid format: " + format);
    }

    private static String formatStylish(String key, Object value1, Object value2, String comparisonResult) {
        StringBuilder result = new StringBuilder();
        switch (comparisonResult) {
            case ("Equal") -> result.append(" ".repeat(4)).append(key).append(": ").append(value1).append("\n");
            case ("Change") -> {
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
            case ("Delete") -> result.append("  - ").append(key).append(": ").append(value1).append("\n");
            case ("Add") -> result.append("  + ").append(key).append(": ").append(value2).append("\n");
            default -> {
            }
        }
        return result.toString();
    }
}
