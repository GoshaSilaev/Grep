import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGrep {

    private void equalsFiles(String inputName, String expectedOutputInFile) throws IOException {

        StringBuilder result1 = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(inputName))) {
            String str = read.readLine();
            while (str != null) {
                result1.append(str).append("\n");
                str = read.readLine();
            }
        }
        StringBuilder result2 = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(expectedOutputInFile))) {
            String str1 = read.readLine();
            while (str1 != null) {
                result2.append(str1).append("\n");
                str1 = read.readLine();
            }
        }

        assertEquals(result2.toString().trim(), result1.toString().trim());
    }

    @Test
    void test() throws IOException {
        grep.main("-i -v hollidays Input/Input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output1.txt");
        new File("Input/GrepRes.txt").delete();

        grep.main("-v I love pRogrammer input/input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output2.txt");
        new File("Input/GrepRes.txt").delete();

        grep.main("-v HellO , my Name is GoShA input/input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output3.txt");
        new File("Input/GrepRes.txt").delete();

        grep.main("-i test for university input/input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output4.txt");
        new File("Input/GrepRes.txt").delete();

        grep.main("-r Test fOr Uni input/input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output5.txt");
        new File("Input/GrepRes.txt").delete();

        grep.main("-r pArk of pOly input/input.txt".trim().split(" "));
        equalsFiles("Input/GrepRes.txt", "Checks/Output6.txt");
        new File("Input/GrepRes.txt").delete();
    }
}