class DigitExtractor {
  // Write a program called ExtractDigits to extract each digit from an int,
  // in the reverse order.
  // For example, if the int is 15423, the output shall be "3 2 4 5 1",
  // with a space separating the digits.

  static Integer inputInt = 95874321

  static void main(String[] args) {
    println extractDigits(inputInt)
  }

  static List<Integer> extractDigits(Integer i) {
    List<Integer> extracted = []
    Integer res = i
    while(res > 0) {
      extracted << res % 10
      res = res / 10
    }
    extracted
  }
}
