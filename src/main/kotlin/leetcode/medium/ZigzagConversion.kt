package peckb1.leetcode.medium

class ZigzagConversion {
  fun convert(s: String, numRows: Int): String {
    if (numRows == 1) return s

    val diagonalItems = numRows - 2
    val resetTotal = numRows + diagonalItems

    return buildString {
      // index zero
      var index = 0
      while(index < s.length) {
        append(s[index])
        index += resetTotal
      }

      // the ones with diagonals
      index = 0
      for(delta in 1 until numRows - 1) {
        while(index - delta < s.length) {
          if (index - delta in 0 until s.length) { append(s[index - delta]) }
          if (index + delta in 0 until s.length) { append(s[index + delta]) }
          index += resetTotal
        }
        index = 0
      }

      // last index
      index = numRows - 1
      while(index < s.length) {
        append(s[index])
        index += resetTotal
      }
    }
  }
}
