class Strain {
  /*
  Implement the keep and discard operation on collections. Given a collection
  and a predicate on the collection's elements, keep returns a new collection
  containing those elements where the predicate is true, while discard returns
  a new collection containing those elements where the predicate is false.
  For example, given the collection of numbers:
      1, 2, 3, 4, 5
  And the predicate:
      is the number even?
  Then your keep operation should produce:
      2, 4
  While your discard operation should produce:
      1, 3, 5
  Note that the union of keep and discard is all the elements.
  The functions may be called keep and discard, or they may need different names
  in order to not clash with existing functions or concepts in your language.
  Restrictions
  Keep your hands off that filter/reject/whatchamacallit functionality provided
  by your standard library! Solve this one yourself using other basic tools instead.
   */

  static Collection collection = [1, 2, 3, 4, 5]

  static void main(String[] args) {
    println keep(collection, predicate)
    println discard(collection, predicate)
  }

  static def predicate = {
    it % 2 == 0
  }

  static Collection keep(Collection collection, Closure predicate) {
    def kept = []
    for(int i = 0; i < collection.size; i++) {
      if(predicate(collection[i]) == true) {
        kept << collection[i]
      }
    }
    return kept
  }

  static Collection discard(Collection collection, Closure predicate) {
    def discarded = []
    for(int i = 0; i < collection.size; i++) {
      if(predicate(collection[i]) == false) {
        discarded << collection[i]
      }
    }
    return discarded
  }
}
