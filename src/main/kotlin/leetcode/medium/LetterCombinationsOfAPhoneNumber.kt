package leetcode.medium

class LetterCombinationsOfAPhoneNumber {
  fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()

    val results = mutableListOf<String>()

    fun letterCombinations(data: List<Int>, index: Int, path: StringBuilder) {
      if (index > digits.lastIndex) {
        results.add(path.toString())
        return
      }

      val number = data[index]
      val letters = MAPPINGS[number] ?: throw IllegalArgumentException("Unknown Digit found")

      letters.forEach { letter ->
        // add
        path.append(letter)

        // recurse
        letterCombinations(data, index + 1, path)

        // remove
        path.setLength(path.length - 1)
      }
    }

    val data = digits.map { it.digitToIntOrNull() ?: throw IllegalArgumentException("Non digit found in String") }
    letterCombinations(data, 0, StringBuilder())
    return results
  }

  companion object {
    private val MAPPINGS = mapOf(
      1 to emptyList(),
      2 to listOf('a', 'b', 'c'),
      3 to listOf('d', 'e', 'f'),
      4 to listOf('g', 'h', 'i'),
      5 to listOf('j', 'k', 'l'),
      6 to listOf('m', 'n', 'o'),
      7 to listOf('p', 'q', 'r', 's'),
      8 to listOf('t', 'u', 'v'),
      9 to listOf('w', 'x', 'y', 'z'),
      0 to listOf('+'),
    )
  }
}