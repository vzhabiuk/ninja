
public class VolodyaSolution {

private static List<String> readWords(InputStream inputStream) {
  try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
    return new BufferedReader(reader).lines().filter(line -> !line.isEmpty()).collect(Collectors.toList());
  }
}
  
private static String findMaxSequenceMain(List<String> words) {
  Map<Char, List<String>> wordsByStartLetter = new HashMap<>();
  //instead of this for loop we could do words.stream().collect(Collectors.groupingBy(word -> word.charAt(0)));
  for (String word : words) {
    Char firstChar = word.charAt(0);
    wordsByStartLetter.computeIfAbsent(firstChar, key -> new ArrayList<>()).add(word);
  }
  Set<String> visitedWords = new HashSet<>();
  StringBuilder currentSequence = new StringBuilder();
  String currentMax = "";
  for (String word : words) {
    visitedWords.add(word);
    currentSequence.append(word);
    String newMax = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, currentSequence);
    if (newMax.length() > currentMax.length()) {
       newMax = currentMax;
    }
  }
}  


}
