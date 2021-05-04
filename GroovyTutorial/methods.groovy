class GroovyTut {
  static void main(String[] args) {

    sayHello()

    // Pass parameters
    println("5 + 4 = " + getSum(5,4))

    // Demonstrate pass by value
    def myName = "Marek"
    passByValue(myName)
    println("In Main : " + myName)

    // Pass a list for doubling
    def listToDouble = [1,2,3,4]
    listToDouble = doubleList(listToDouble)
    println(listToDouble)

    // Pass unknown number of elements to a method
    def nums = sumAll(1,2,3,4)
    println("Sum : " + nums)

    // Calculate factorial (Recursion)
    def fact4 = factorial(4)
    println("Factorial 4 : " + fact4)

  }

  static def sayHello() {
    println("Hello")
  }

  static def getSum(int1=0,int2=0) {
    return int1+int2
  }

  static def passByValue(name) {
    name = "Danae"
    println ("name : " + name)
  }

  static def doubleList(list) {
    def newList = list.collect {it * 2}
    return newList
  }

  static def sumAll(int... num) {
    def sum = 0
    num.each {sum += it}
    return sum
  }

  static def factorial(number) {
    if(number <= 1) {
      return 1
    } else {
      return number * factorial(number - 1)
    }
  }

}
