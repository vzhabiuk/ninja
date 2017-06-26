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
            return new BufferedReader(reader)
                    .lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static List<String> findMaxSequenceMain(List<String> words) {
        Map<Character, List<String>> wordsByStartLetter = new HashMap<>();
        //instead of this for loop we could do words.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
        for (String word : words) {
            Character firstChar = word.charAt(0);
            wordsByStartLetter.computeIfAbsent(firstChar, key -> new ArrayList<>()).add(word);
        }
        Set<String> visitedWords = new HashSet<>();
        List<String> currentMax = Collections.emptyList();
        for (Character firstLetter : wordsByStartLetter.keySet()) {
            List<String> newMax = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, firstLetter);
            if (newMax.size() > currentMax.size()) {
                currentMax = newMax;
            }
        }
        return currentMax;
    }

    //Poshyk v glubuny na grafi
    private static List<String> findMaxSequenceRecursive(Map<Character, List<String>> wordsByStartLetter,
                                                   Set<String> visitedWords,
                                                   Character firstLetter) {
        List<String> currentMax = Collections.emptyList();
        for (String word : wordsByStartLetter.getOrDefault(firstLetter, Collections.emptyList())) {
            if (!visitedWords.contains(word)) {
                visitedWords.add(word);
                Character lastWordChar = word.charAt(word.length() - 1);
                List<String> tail = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, lastWordChar);
                if (currentMax.size() <= tail.size()) {
                    currentMax = new ArrayList<>();
                    currentMax.add(word);
                    currentMax.addAll(tail);
                }
                visitedWords.remove(word);
            }
        }
        return currentMax;
    }

    public static void main(String[] args) {
        //System.out.println("Max sequence = " + findMaxSequenceMain(readWords(System.in)));
        List<String> words = Arrays.asList("ab", "bc", "bb", "bc", "cd", "ddd", "de", "azzzzzzzzzzzzzzzze");
        Collections.reverse(words);
        System.out.println("Max sequence = " + findMaxSequenceMain(words));
    }
}
