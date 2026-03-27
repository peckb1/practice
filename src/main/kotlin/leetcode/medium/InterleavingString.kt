package leetcode.medium

class InterleavingString {
  fun isInterleave(s1: String, s2: String, target: String): Boolean {
    if (s1.length + s2.length != target.length) return false
    val memoization = Array(s1.length + 1) {
      Array<Boolean?>(s2.length + 1) { null }
    }
    return isInterleave(0, 0, s1, s2, target, memoization)
  }

  private fun isInterleave(
    s1Index: Int, s2Index: Int,
    s1: String, s2: String,
    target: String,
    memo: Array<Array<Boolean?>>
  ): Boolean {
    // If already computed, return memoized result
    memo[s1Index][s2Index]?.let { return it }

    val targetIndex = s1Index + s2Index // Current index in target
    if (targetIndex == target.length) return true

    // Try taking from s1 if possible
    var result = if (s1Index < s1.length && s1[s1Index] == target[targetIndex]) {
      isInterleave(s1Index + 1, s2Index, s1, s2, target, memo)
    } else false

    // Try taking from s2 if possible
    if (!result && s2Index < s2.length && s2[s2Index] == target[targetIndex]) {
      result = isInterleave(s1Index, s2Index + 1, s1, s2, target, memo)
    }

    memo[s1Index][s2Index] = result
    return result
  }
}

fun main() {
  println(InterleavingString().isInterleave("aaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
}