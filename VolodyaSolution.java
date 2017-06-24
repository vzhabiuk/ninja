
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
  String currentMax = "";
  for (Char firstLetter : wordsByStartLetter.keySet()) {
    currentSequence.append(word);
    String newMax = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, firstLetter);
    if (newMax.length() > currentMax.length()) {
       newMax = currentMax;
    }
  }
  return currentMax;
} 
  //Poshyk v glubuny na grafi
  private static String findMaxSequenceRecursive(Map<Char, List<String>> wordsByStartLetter, 
                                               Set<String> visitedWords, 
                                               Char firstLetter) {
    String currentMax = "";
    for (String word : wordsByStartLetter.get(firstLetter)) {
      if (!visitedWords.contains(word)) {
        visitedWords.add(word);
        Char lastWordChar = word.charAt(word.length() - 1);
        String tail = findMaxSequenceRecursive(wordsByStartLetter, visitedWords, lastWordChar);
        if (currenMax.length() < tail.length() + word.length()) {
           currentMax = word + tail;
        }
        visitedWords.remove(word);
      }
    }
    return currenMax;
  }
}
