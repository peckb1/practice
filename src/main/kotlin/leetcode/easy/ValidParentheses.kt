package peckb1.leetcode.easy

class ValidParentheses {
  /**
   * The input string s is valid if and only if:
   *
   *     Every open bracket is closed by the same type of close bracket.
   *     Open brackets are closed in the correct order.
   *     Every close bracket has a corresponding open bracket of the same type.
   *
   */
  fun isValid(s: String): Boolean {
    if (s.isEmpty()) return true

    val openSections = ArrayDeque<Char>()
    s.indices.forEach { i ->
      when (val char = s[i]) {
        '{', '(', '[' -> openSections.addLast(char)
        '}', ')', ']' -> {
          val lastOpen = try {
            openSections.removeLast()
          } catch (_: NoSuchElementException) {
            null
          }
          when (lastOpen) {
            null -> return false
            '(' -> if (char != ')') return false
            '{' -> if (char != '}') return false
            '[' -> if (char != ']') return false
          }
        }
      }
    }

    return openSections.isEmpty()
  }
}

fun main() {
  println(ValidParentheses().isValid("()"))
}