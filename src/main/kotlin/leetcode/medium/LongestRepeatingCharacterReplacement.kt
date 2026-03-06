package peckb1.leetcode.medium

class LongestRepeatingCharacterReplacement {
  fun characterReplacement(s: String, k: Int): Int {
    val counts = mutableMapOf<Char, Int>().withDefault { 0 }

    var left = 0
    var maxFreq = 0
    var result = 0

    for (right in s.indices) {
      val char = s[right]
      counts[char] = counts.getValue(char) + 1
      maxFreq = maxOf(maxFreq, counts[char]!!)

      while ((right - left + 1) - maxFreq > k) {
        val leftChar = s[left]
        counts[leftChar] = counts.getValue(leftChar) - 1
        left++
      }

      result = maxOf(result, right - left + 1)
    }

    return result
  }
}
