package peckb1.leetcode.medium

class PalindromicSubstrings {
  companion object {
    private const val MANACHER_DELIMITER = 'l'
  }

  fun countSubstrings(s: String): Int {
    if (s.isEmpty()) return 0

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

    var count = 0
    for (i in 1 until n - 1) {
      count += (p[i] + 1) / 2
    }

    return count
  }
}
