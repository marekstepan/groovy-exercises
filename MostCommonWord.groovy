class MostCommonWord {
  // Create a function called mostCommonWord that takes a string as an input,
  // and returns the most common word in it.
  //If there are more than one most common word in the string, return only one of them.

  static String inputString = "apple pear banana apple apple pear";

  static void main(String[] args) {
    println mostCommonWord(inputString)
  }

  static String mostCommonWord(String string) {
    List<String> words = string.split(" ")
    def wordsMap = [:]
    for(String w in words) {
      if(wordsMap.containsKey(w) == false) {
        wordsMap.put(w, 1)
      } else {
        wordsMap.put(w, wordsMap.get(w) + 1)
      }
    }
    String mostFrequentW = ""
    Integer maxValue = 0
    for(w in wordsMap) {
      if(wordsMap.get(w.key) > maxValue) {
        maxValue = wordsMap.get(w.key)
        mostFrequentW = w.key
      }
    }
    mostFrequentW
  }

}
