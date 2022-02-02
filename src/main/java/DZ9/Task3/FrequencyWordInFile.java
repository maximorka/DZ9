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
        Map<String, Integer> words = new HashMap();

        List<String> tmp = new ArrayList<>();

        for (String word : uniqueWords) {
            // System.out.println(word + ": " + Collections.frequency(list, word));
            words.put(word, Collections.frequency(list, word));


        }

        for (int i = uniqueWords.size(); i >= 0; i--) {
            for (String word : uniqueWords) {
                int freq = words.get(word);
                if (freq==i)
                    System.out.println(word+" "+freq);
            }
        }

    }

    public void getFrequencyWords(String path) {
        checkUniqueWords(putWordToList(getTextFromFile(read("word.txt"))));
    }

    public static void main(String[] args) throws FileNotFoundException {
        FrequencyWordInFile frequencyWordInFile = new FrequencyWordInFile();
        frequencyWordInFile.getFrequencyWords("word.txt");
    }
}
