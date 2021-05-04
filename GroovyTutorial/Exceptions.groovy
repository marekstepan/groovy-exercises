class GroovyTut {
  static void main(String[] args) {
    try {
      File testFile
      testFile.append('Line 5')
    }
    catch(NullPointerException ex){

      // Prints exception
      println(ex.toString())

      // Prints error message
      println(ex.getMessage())
    }
    catch(Exception ex){
      println("I Catch Everything")
    }
    finally {
      println("I perform clean up")
    }
  }
}
