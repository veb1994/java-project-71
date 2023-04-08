package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest {

    @Test
    @DisplayName("Test with 2 correct files")
    public void testDiffer() throws Exception {
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
    @DisplayName("Test if 1st file doesn't exist")
    public void testDifferNoFile() {
        String filepath1 = "src/test/resources/noSuchFile.json";
        String filepath2 = "src/test/resources/file2.json";
        String format = "stylish";
        Exception thrownException = assertThrows(Exception.class, () -> Differ.generate(filepath1, filepath2, format));
        assertEquals("No such file: src/test/resources/noSuchFile.json", thrownException.getMessage());
    }

    @Test
    @DisplayName("Test if 1 file is empty")
    public void testDifferEmptyFile() throws Exception {
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
}
