package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    @Test
    @DisplayName("JSON Test with 2 correct files")
    public void testDifferJSON() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "stylish";
        String expected =
                """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected, actual);
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
        String expected =
                """
                {
                  + host: hexlet.io
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("YAML Test with 2 correct files")
    public void testDifferYAML() throws Exception {
        String filepath1 = "src/test/resources/file1.yml";
        String filepath2 = "src/test/resources/file2.yml";
        String format = "stylish";
        String expected =
                """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected, actual);
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
        String expected =
                """
                {
                  + host: hexlet.io
                  + timeout: 20
                  + verbose: true
                }""";
        String actual = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected, actual);
    }
}
