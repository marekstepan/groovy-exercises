class ResistorColor {
  static colors = ['Black', 'Brown', 'Red', 'Orange', 'Yellow', 'Green', 'Blue',
  'Violet', 'Grey', 'White']

  static void main(String[] args) {
    colorCode('Orange')
    println colors

  }

  static def colorCode(color) {
    println(colors.indexOf(color))
  }

}
