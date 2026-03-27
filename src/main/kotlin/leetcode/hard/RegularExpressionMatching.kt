package leetcode.hard

class RegularExpressionMatching {
  fun isMatch(s: String, p: String): Boolean {
    val regexPattern = "^${p.replace("*", ".*")}$"
    return Regex(regexPattern).matches(s)
  }
}