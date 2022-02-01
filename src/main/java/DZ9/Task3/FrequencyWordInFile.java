package DZ9.Task3;

import DZ9.ReaderFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class FrequencyWordInFile extends ReaderFile {

    private String getTextFromFile(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine() + " ");
        }
        return stringBuilder.toString();
    }

    private List putWordToList(String string) {
        List<String> list = new ArrayList<>();
        String[] words = string.split(" ");

        for (String word : words) {
            if (word.matches("\\w+")) {
                list.add(word);
            }
        }
        return list;
    }

    private void checkUniqueWords(List list) {
        Set<String> uniqueWords = new HashSet<String>(list);
        for (String word : uniqueWords) {
            System.out.println(word + ": " + Collections.frequency(list, word));
        }
    }

    public void getFrequencyWords(String path) {
        checkUniqueWords(putWordToList(getTextFromFile(read("word.txt"))));
    }

    public static void main(String[] args) throws FileNotFoundException {
        FrequencyWordInFile frequencyWordInFile = new FrequencyWordInFile();
        System.out.println("frequencyWord.getTextFromFile(frequencyWord.read(\"word.txt\")) = " + frequencyWordInFile.getTextFromFile(frequencyWordInFile.read("word.txt")));
        frequencyWordInFile.getFrequencyWords("word.txt");
    }
}
