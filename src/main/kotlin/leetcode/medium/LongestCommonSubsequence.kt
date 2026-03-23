package leetcode.medium

import kotlin.collections.get


class LongestCommonSubsequence {
  fun longestCommonSubsequence(t1: String, t2: String): Int {
    if (t1.isEmpty() || t2.isEmpty()) return 0
    val memoizations = mutableMapOf<Pair<Int, Int>, Int>()

    fun lcs(t1Index: Int, t2Index: Int) : Int {
      if (t1Index >= t1.length || t2Index >= t2.length) return 0

      val key = t1Index to t2Index
      memoizations[key]?.also { return it }

      val result = if (t1[t1Index] == t2[t2Index]) {
        1 + lcs(t1Index + 1, t2Index + 1)
      } else {
        maxOf(
          lcs(t1Index + 1, t2Index),
          lcs(t1Index, t2Index + 1)
        )
      }

      memoizations[key] = result
      return result
    }

    return lcs(0, 0)
  }
}

fun main() {
  println(LongestCommonSubsequence().longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"))
}