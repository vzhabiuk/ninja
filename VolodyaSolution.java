import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class VolodyaSolution {

    private static List<String> readWords(InputStream inputStream) {

        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return new BufferedReader(reader).lines().filter(line -> !line.isEmpty()).collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String findMaxSequenceMain(List<String> words) {
        Map<Character, List<String>> wordsByStartLetter = new HashMap<>();
        //instead of this for loop we could do words.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
        for (String word : words) {
            Character firstChar = word.charAt(0);
            wordsByStartLetter.computeIfAbsent(firstChar, key -> new ArrayList<>()).add(word);
        }
        Set<String> visitedWords = new HashSet<>();
        String currentMax = "";
        for (Character firstLetter : wordsByStartLetter.keySet()) {
            String newMax = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, firstLetter);
            if (newMax.length() > currentMax.length()) {
                currentMax = newMax;
            }
        }
        return currentMax;
    }
    //Poshyk v glubuny na grafi
    private static String findMaxSequenceRecursive(Map<Character, List<String>> wordsByStartLetter,
                                                   Set<String> visitedWords,
                                                   Character firstLetter) {
        String currentMax = "";
        for (String word : wordsByStartLetter.get(firstLetter)) {
            if (!visitedWords.contains(word)) {
                visitedWords.add(word);
                Character lastWordChar = word.charAt(word.length() - 1);
                String tail = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, lastWordChar);
                if (currentMax.length() < tail.length() + word.length()) {
                    currentMax = word + tail;
                }
                visitedWords.remove(word);
            }
        }
        return currentMax;
    }

    public static void main(String[] args) {
        System.out.println("Max sequence = " + findMaxSequenceMain(readWords(System.in)));
    }
}
