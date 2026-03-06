package peckb1.leetcode.hard

class MinimumWindowSubstring {
  /**
   * if `s` contains a substring which has all letters in `t`
   * return the shortest substring which does so
   */
  fun minWindow(s: String, t: String): String {
    if (s.isEmpty()) return ""
    if (t.isEmpty()) return s
    if (s.length < t.length) return ""

    val lettersLookingFor = mutableMapOf<Char, Int>().withDefault { 0 }.apply {
      t.forEach { char -> this[char] = this.getValue(char) + 1 }
    }

    var leftIndex = 0
    var rightIndex = t.length

    val currentSubstringLetters = mutableMapOf<Char, Int>().withDefault { 0 }

    (leftIndex until rightIndex).forEach {
      val character = s[it]
      currentSubstringLetters[character] = currentSubstringLetters.getValue(character) + 1
    }

    if (lettersLookingFor.all { (c, count) -> currentSubstringLetters[c] == count }) return s.substring(leftIndex, rightIndex)

    var smallestSubString: String? = null

    ((rightIndex) .. s.lastIndex).forEach { newRightIndex ->
      rightIndex = newRightIndex
      // let's add the next letter and see what happens
      val rightCharacter = s[rightIndex]
      currentSubstringLetters[rightCharacter] = currentSubstringLetters.getValue(rightCharacter) + 1

      // Also, can we move the left index?
      // we are able to if
      // - the letter is not part of our target string
      // - the letter is an "Extra" (we need 2, but have 3)
      var leftCharacter = s[leftIndex]
      var leftCharacterCount = currentSubstringLetters[leftCharacter]!!
      var expectedCount = lettersLookingFor[leftCharacter]

      while ((rightIndex - leftIndex >= t.length) && (expectedCount == null || expectedCount < leftCharacterCount)) {
        leftIndex++
        if (leftCharacterCount > 1) {
          currentSubstringLetters[leftCharacter] = leftCharacterCount - 1
        } else {
          currentSubstringLetters.remove(leftCharacter)
        }

        leftCharacter = s[leftIndex]
        leftCharacterCount = currentSubstringLetters[leftCharacter]!!
        expectedCount = lettersLookingFor[leftCharacter]
      }

      if (lettersLookingFor.all { (c, count) -> (currentSubstringLetters[c] ?: 0) >= count }) {
        val newSubstring = s.substring(leftIndex, rightIndex + 1)
        if (newSubstring.length < (smallestSubString?.length ?: Int.MAX_VALUE)) {
          smallestSubString = newSubstring
        }
      }
    }

    return smallestSubString ?: ""
  }
}
