package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {
    private final String expectedTwoCorrectFiles =
                """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
    private final String expectedOneFileEmpty =
                """
                {
                  + chars1: [a, b, c]
                  + chars2: false
                  + checked: true
                  + default: [value1, value2]
                  + id: null
                  + key2: value2
                  + numbers1: [1, 2, 3, 4]
                  + numbers2: [22, 33, 44, 55]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  + setting1: Another value
                  + setting2: 300
                  + setting3: none
                }""";

    private final String expectedPlain =
                """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";

    private final String expectedJson = "{\"chars1\":\"[a, b, c]\",\"- chars2\":\"[d, e, f]\",\""
            + "+ chars2\":\"false\",\"- checked\":\"false\",\"+ checked\":\"true\",\"- default\":\"null\",\""
            + "+ default\":\"[value1, value2]\",\"- id\":\"45\",\"+ id\":\"null\",\"- key1\":\"value1\",\""
            + "+ key2\":\"value2\",\"numbers1\":\"[1, 2, 3, 4]\",\"- numbers2\":\"[2, 3, 4, 5]\",\""
            + "+ numbers2\":\"[22, 33, 44, 55]\",\"- numbers3\":\"[3, 4, 5]\",\"+ numbers4\":\"[4, 5, 6]\",\""
            + "+ obj1\":\"{nestedKey=value, isNested=true}\",\"- setting1\":\"Some value\",\""
            + "+ setting1\":\"Another value\",\"- setting2\":\"200\",\"+ setting2\":\"300\",\"- setting3\":\"true\",\""
            + "+ setting3\":\"none\"}";

    @Test
    @DisplayName("JSON Test with 2 correct files")
    public void testDifferJSON() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "stylish";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedTwoCorrectFiles, actual);
    }

    @Test
    @DisplayName("JSON Test if 1st file doesn't exist")
    public void testDifferNoFileJSON() {
        String filepath1 = "src/test/resources/noSuchFile.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "stylish";
        Exception thrownException = assertThrows(Exception.class, () -> Differ.generate(filepath1, filepath2, format));
        assertEquals("No such file: src/test/resources/noSuchFile.json", thrownException.getMessage());
    }

    @Test
    @DisplayName("JSON Test if 1 file is empty")
    public void testDifferEmptyFileJSON() throws Exception {
        String filepath1 = "src/test/resources/empty.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "stylish";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedOneFileEmpty, actual);
    }

    @Test
    @DisplayName("YAML Test with 2 correct files")
    public void testDifferYAML() throws Exception {
        String filepath1 = "src/test/resources/file1.yml";
        String filepath2 = "src/test/resources/file2.yml";
        String format = "stylish";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedTwoCorrectFiles, actual);
    }

    @Test
    @DisplayName("YAML Test if 1st file doesn't exist")
    public void testDifferNoFileYAML() {
        String filepath1 = "src/test/resources/noSuchFile.yml";
        String filepath2 = "src/test/resources/file2.yml";
        String format = "stylish";
        Exception thrownException = assertThrows(Exception.class, () -> Differ.generate(filepath1, filepath2, format));
        assertEquals("No such file: src/test/resources/noSuchFile.yml", thrownException.getMessage());
    }

    @Test
    @DisplayName("YAML Test if 1 file is empty")
    public void testDifferEmptyFileYAML() throws Exception {
        String filepath1 = "src/test/resources/empty.yml";
        String filepath2 = "src/test/resources/file2.yml";
        String format = "stylish";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedOneFileEmpty, actual);
    }

    @Test
    @DisplayName("JSON Test with 2 correct files and Plain formatter")
    public void testDifferPlain() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "plain";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedPlain, actual);
    }

    @Test
    @DisplayName("JSON Test with 2 correct files and Json formatter")
    public void testDifferJson() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "json";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expectedJson, actual);
    }
}
