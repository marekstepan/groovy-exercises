class GroovyTut {
  static void main(String[] args) {

    def king = new Animal('King', 'Growl')

    println("${king.name} says ${king.sound}")

    // Change an object attribute with a setter
    king.setSound('Grrrr')
    println("${king.name} says ${king.sound}")

    king.run()

    println(king.toString())

    // With inheritance a class can inherit all fields
    // and methods of another class
    def grover = new Dog('Mishidlo', 'Grrrrr', 'Marek')

    king.makeSound()
    grover.makeSound()

    // Mammal inherits from the abstract class Thing
    def hamster = new Mammal('Furry')
    hamster.getInfo()

  }

}
