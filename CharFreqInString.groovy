class CharFreqInString {
  // Write a Stream Expression to find the frequency of characters in a given string!

  static String inputString = "This is absolutely random sentence. Except that it is not random at all."

  static void main(String[] args) {
    println getCharFreq(inputString)
  }

  static def getCharFreq(String string) {
    def charFreq = [:]
    for(c in string) {
      if(charFreq.containsKey(c) == false) {
        charFreq.put(c, 1)
      } else {
        charFreq.put(c, charFreq[c] + 1)
      }
    }
    charFreq
  }
}
