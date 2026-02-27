package peckb1.leetcode.medium

class LongestPalindromicSubstring {
  companion object {
    private const val MANACHER_DELIMITER = 'l'
  }

  fun longestPalindrome(s: String): String {
    if (s.isEmpty()) return ""

    // Transform the string: "abba" -> "^lalblblal$"
    val transformed = buildString {
      append("^")
      for (c in s) {
        append(MANACHER_DELIMITER)
        append(c)
      }
      append("$MANACHER_DELIMITER$")
    }

    val n = transformed.length
    val p = IntArray(n) // Array to store palindrome radius
    var center = 0
    var right = 0

    (1 until n - 1).forEach { i ->
      val mirror = 2 * center - i

      if (i < right) {
        p[i] = minOf(right - i, p[mirror])
      }

      // Expand around center i
      while (transformed[i + (1 + p[i])] == transformed[i - (1 + p[i])]) {
        p[i]++
      }

      // Update center and right boundary
      if (i + p[i] > right) {
        center = i
        right = i + p[i]
      }
    }

    // Find the maximum palindrome length
    var maxLength = 0
    var centerIndex = 0
    for (i in 1 until n - 1) {
      if (p[i] > maxLength) {
        maxLength = p[i]
        centerIndex = i
      }
    }

    // Extract start index in original string
    val start = (centerIndex - maxLength) / 2
    return s.substring(start, start + maxLength)
  }
}
