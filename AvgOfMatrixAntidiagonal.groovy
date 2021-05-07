class AvgOfMatrixAntidiagonal {

  static def sourceList = [[1,2,3],[3,5,8],[6,11,29]]

  static void main(String[] args) {
    println GetAntidiagonalAverage(sourceList)
  }

  static Double GetAntidiagonalAverage(List sourceList) {
    def sum = 0
    for(int i = 0; i < sourceList.size; i++) {
      sum += sourceList.get(i).get(sourceList.size - i - 1)
    }
    Double avg = sum / sourceList.size
    avg
  }
}
