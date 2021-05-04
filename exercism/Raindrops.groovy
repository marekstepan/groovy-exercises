class Raindrops {

    static void main(String[] args) {
      convert(105)
    }

    static def convert(num) {
        def sound = num.toString()
        def sound3
        def sound5
        def sound7
        if(num % 3 == 0) {
          sound3 = "Pling"
          sound = ""
        } else { sound3 = "" }
        if(num % 5 == 0) {
          sound5 = "Plang"
          sound = ""
        } else { sound5 = "" }
        if(num % 7 == 0) {
          sound7 = "Plong"
          sound = ""
        } else { sound7 = "" }
        println(sound + sound3 + sound5 + sound7)
    }

}
