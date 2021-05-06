class Leap {
  /*
  Given a year, report if it is a leap year.
  The tricky thing here is that a leap year in the Gregorian calendar occurs:
  on every year that is evenly divisible by 4
  except every year that is evenly divisible by 100
  unless the year is also evenly divisible by 400
  For example, 1997 is not a leap year, but 1996 is. 1900 is not a leap year,
  but 2000 is.
   */
  static Integer year = 1996

  static void main(String[] args) {
    if(isLeapYear(year)) {
      println year + " true"
    } else {
      println year + " false"
    }
  }

  static boolean isLeapYear(Integer year) {
    if(year % 4 == 0  && (year % 100 != 0 || year % 400 == 0)) {
      return true
    }
  }

}
