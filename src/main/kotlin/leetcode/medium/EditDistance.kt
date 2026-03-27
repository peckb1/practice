package leetcode.medium

class EditDistance {
  fun minDistance(word1: String, word2: String): Int {
    val memoization = Array(word1.length) { IntArray(word2.length) { -1 } }
    return minDistance(word1, 0, word2, 0, memoization)
  }

  private fun minDistance(
    w1: String, w1Index: Int,
    w2: String, w2Index: Int,
    memoization: Array<IntArray>
  ): Int {
    // if we are finished with the first word, we can just add on what is left
    if (w1Index == w1.length) return w2.length - w2Index
    // if we are finished with the second word, we can just remove what remains
    if (w2Index == w2.length) return w1.length - w1Index
    // cache check!
    if (memoization[w1Index][w2Index] != -1) return memoization[w1Index][w2Index]

    val result = if (w1[w1Index] == w2[w2Index]) {
      // we match, so don't do anything!
      minDistance(w1, w1Index + 1, w2, w2Index + 1, memoization)
    } else {
      // we don't match, let's try one of the three operations
      // - delete one from us
      val delete  = minDistance(w1, w1Index + 1, w2, w2Index    , memoization)
      // - insert the value that is supposed to exist
      val insert  = minDistance(w1, w1Index    , w2, w2Index + 1, memoization)
      // - replace what is here with the value from there
      val replace = minDistance(w1, w1Index + 1, w2, w2Index + 1, memoization)

      // the cost of that operation, plus the smallest of any future becomes our result
      1 + minOf(delete, insert, replace)
    }

    memoization[w1Index][w2Index] = result
    return result
  }
}