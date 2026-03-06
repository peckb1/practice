package peckb1.leetcode.medium

import kotlin.math.max

class LongestSubstringWithoutRepeatingCharacters {
  fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) return 0

    var headIndex = 0
    var tailIndex = 1
    var longestSoFar = 1

    val indexToCharMap = mutableMapOf<Int, Char>().apply {
      this[headIndex] = s[headIndex]
    }
    val existingChars = mutableSetOf(s[headIndex])

    while(headIndex < s.length) {
      if (tailIndex >= s.length) {
        if ((tailIndex - headIndex) + 1 < longestSoFar) {
          // no need to continue, we can't get "longer"
          headIndex = s.length
        } else {
          val itemToCull = indexToCharMap[headIndex++]!!
          existingChars.remove(itemToCull)
          tailIndex--
        }
      } else {
        val nextChar = s[tailIndex]

        // if the next item we already know about; advance our head
        while (existingChars.contains(nextChar)) {
          val itemToCull = indexToCharMap[headIndex++]!!
          existingChars.remove(itemToCull)
        }

        // we can add this to our substring
        longestSoFar = max(longestSoFar, (tailIndex - headIndex) + 1)
        indexToCharMap[tailIndex++] = nextChar
        existingChars.add(nextChar)
      }
    }

    return longestSoFar
  }
}
