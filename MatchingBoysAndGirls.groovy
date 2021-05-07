class MatchingBoysAndGirls {
  // Write a method that joins the two lists by matching one girl with one boy into a new list
  //If someone has no pair, he/she should be the element of the list too
  //Expected output: "Eve", "Joe", "Ashley", "Fred"...
  //ArrayList<String> girls = new ArrayList<String>(Arrays.asList("Eve","Ashley","Claire","Kat","Jane"));
  //ArrayList<String> boys = new ArrayList<String>(Arrays.asList("Joe","Fred","Tom","Todd","Neef","Jeff"))
  //System.out.println(makingMatches(girls, boys));

  static List<String> girls = ["Eve","Ashley","Claire","Kat","Jane"]
  static List<String> boys = ["Joe","Fred","Tom","Todd","Neef","Jeff"]

  static void main(String[] args) {
    println getPairs(girls, boys)
  }

  static List<String> getPairs(girls, boys) {
    List<String> pairs = []
    Integer girlsCount = girls.size
    Integer boysCount = boys.size
    Integer maxSize = Math.max(girlsCount, boysCount)

    for(int i = 0; i < maxSize; i++) {
      if(i < girlsCount) {
        pairs << girls.get(i)
      } else {
        continue
      }
      if(i < boysCount) {
        pairs << boys.get(i)
      }
    }
  return pairs
  }
}
