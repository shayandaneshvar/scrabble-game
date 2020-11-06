import com.scrabble.server.WordUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrabbleServerTests {
    private List<String> strings = new ArrayList<>(9900);

    @Test
    public void extractWords() throws IOException {
        File file = new File("C:\\Users\\TOP\\Desktop\\scrabble-game\\scrabble-server\\src\\test\\java\\word.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (word.length() < 2) {
                continue;
            }
            if (word.length() == 2) {
                if (!(word.contains("o") && word.contains("u") &&
                        word.contains("i") && word.contains("e") &&
                        word.contains("a"))) {
                    continue;
                }
            }
            strings.add(word);
        }
        scanner.close();
        FileWriter fileWriter = new FileWriter("C:\\Users\\TOP\\Desktop\\scrabble-game\\scrabble-server\\src\\test\\java\\dic.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        strings.forEach(printWriter::println);
        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();
    }



}
