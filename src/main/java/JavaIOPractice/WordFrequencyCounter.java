package JavaIOPractice;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencyCounter {
    public static void countWordFrequency(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Map<String, Integer> wordFrequencyMap = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
                }
            }

            while (!wordFrequencyMap.isEmpty()) {
                String maxKey = null;
                Integer maxValue = null;

                for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
                    if (maxValue == null || entry.getValue() > maxValue) {
                        maxValue = entry.getValue();
                        maxKey = entry.getKey();
                    }
                }

                System.out.println(maxKey + " " + maxValue);

                wordFrequencyMap.remove(maxKey);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
