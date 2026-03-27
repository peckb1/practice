package leetcode.hard

class DistinctSubsequences {
  fun numDistinct(s: String, t: String): Int {
    val memoization = Array(s.length) { IntArray(t.length) { -1 } }

    return distinctSubsequences(s, 0, t, 0, memoization)
  }

  private fun distinctSubsequences(s: String, sIndex: Int, t: String, tIndex: Int, memoization: Array<IntArray>): Int {
    // we found all the letters we need, so we're good!
    if (tIndex == t.length) return 1
    // we hit the end of s without finishing t, so no bueno
    if (sIndex == s.length) return 0
    // is it even possible to continue from here?
    if (s.length - sIndex < t.length - tIndex) return 0
    // maybe we've been to this position in `s` with the same remaining items in `t`
    if (memoization[sIndex][tIndex] != -1) return memoization[sIndex][tIndex]

    // if I am not used
    val withoutMe = distinctSubsequences(s, sIndex + 1, t, tIndex, memoization)

    // if I am Used
    val withMe = if (s[sIndex] == t[tIndex]) {
      distinctSubsequences(s, sIndex + 1, t, tIndex + 1, memoization)
    } else 0

    val result = withoutMe + withMe

    memoization[sIndex][tIndex] = result
    return result
  }
}
