package peckb1.leetcode.medium

class PermutationInString {
  /**
   * true if s2 has a permutation of s1
   */
  fun checkInclusion(s1: String, s2: String): Boolean {
    if (s1.isEmpty()) return true
    if (s2.isEmpty()) return false
    if (s1.length > s2.length) return false

    val s1counts = IntArray(26).apply {
      s1.forEach { this[it - 'a']++ }
    }

    val s2Counts = IntArray(26).apply {
      s1.indices.forEach { this[s2[it] - 'a']++ }
    }

    if (s1counts.contentEquals(s2Counts)) return true

    (s1.length ..s2.lastIndex).forEach { s2Index ->
      // remove left side
      val leftIndex = s2Index - s1.length
      val s2CharAtLeftIndex = s2[leftIndex] - 'a'
      s2Counts[s2CharAtLeftIndex]--

      // add in the new current
      val s2CharAtIndex = s2[s2Index] - 'a'
      s2Counts[s2CharAtIndex]++

      if (s1counts.contentEquals(s2Counts)) return true
    }

    return false
  }
}
