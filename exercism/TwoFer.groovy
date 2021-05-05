class TwoFer {
  /*
  Two-fer or 2-fer is short for two for one. One for you and one for me.
  Given a name, return a string with the message:
  One for X, one for me.
  Where X is the given name.
  However, if the name is missing, return the string:
  One for you, one for me.
  */

  static void main(String[] args) {
    twoFer("Danae")
  }

  static String twoFer(String name) {
      if(name.isEmpty()) {
        println("One for you, one for me.")
      } else {
        println("One for $name, one for me.")
      }
  }
}
