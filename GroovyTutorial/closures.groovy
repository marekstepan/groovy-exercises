class GroovyTut {
  static void main(String[] args) {

    def greeting = "Goodbye"
    def sayGoodbye = {theName -> println("$greeting $theName")}

    sayGoodbye("Derek")

    // each performs an operation on each item in list
    def numList = [1,2,3,4]
    numList.each { println(it) }

    // Do the same with a map
    def employees = [
      'Paul' : 34,
      'Sally' : 35,
      'Sam' : 36
    ]

    employees.each { println("${it.key} : ${it.value}") }

    // Print only evens
    def randNums = [1,2,3,4,5,6]
    randNums.each {num -> if(num % 2 == 0) println(num)}

    // find returns a match
    def nameList = ['Doug', 'Sally', 'Sue']
    def matchEle = nameList.find {item -> item == 'Sue'}
    println(matchEle)

    // findAll finds all matches
    def randNumList = [1,2,3,4,5,6]
    def numMatches = randNumList.findAll {item -> item > 4}
    println(numMatches)

    // any checks if any item matches
    println("> 5 : " + randNumList.any {item -> item > 5})

    // every checks that all items match
    println("> 1 : " + randNumList.every {item -> item > 1})

    // collect performs operations on every item
    println("Double : " + randNumList.collect { item -> item * 2})

    // pass closure to a method
    def getEven = {num -> return(num % 2 == 0)}
    def evenNums = listEdit(randNumList, getEven)
    println("Evens : " + evenNums)

  }

  static def listEdit(list, clo) {
    return list.findAll(clo)
  }

}
