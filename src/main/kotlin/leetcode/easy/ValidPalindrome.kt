package peckb1.leetcode.easy

class ValidPalindrome {
  fun isPalindrome(s: String): Boolean {
    var startIndex = 0
    var endIndex = s.lastIndex

    while(startIndex < endIndex) {
      val start = s[startIndex]
      val end = s[endIndex]

      if (!start.isLetterOrDigit()) {
        startIndex++
      } else if (!end.isLetterOrDigit()) {
        endIndex--
      } else {
        if (start.lowercaseChar() != end.lowercaseChar()) {
          return false
        } else {
          startIndex++
          endIndex--
        }
      }
    }

    return true
  }
}
