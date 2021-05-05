class ReverseString {

  static void main(String[] args) {
    reverse('marek et danae')
  }

  static reverse(String value) {
    def reversed = ""
    for(int i; i < value.length(); i++) {
      reversed = reversed + value[value.length() - 1 - i]
    }
    println(reversed)
  }

}
