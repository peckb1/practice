package leetcode.hard

class BurstBalloons {
  fun maxCoins(nums: IntArray): Int {
    val totalNumbers = nums.size
    // Add virtual boundaries
    val arr = IntArray(totalNumbers + 2).apply {
      this[0] = 1
      this[totalNumbers + 1] = 1
    }
    for (numberIndex in nums.indices) arr[numberIndex + 1] = nums[numberIndex]

    // Memoization table, -1 means uncomputed
    val memoization = Array(totalNumbers + 2) { IntArray(totalNumbers + 2) { -1 } }

    // Recursive function: max coins in (left, right), exclusive
    fun dp(left: Int, right: Int): Int {
      // no balloons to pop
      if (left + 1 == right) return 0
      // we've popped this branch before
      if (memoization[left][right] != -1) return memoization[left][right]

      var maxCoins = 0
      for (k in left + 1 until right) {
        val coins = arr[left] * arr[k] * arr[right] +
          dp(left, k) +
          dp(k, right)
        maxCoins = maxOf(maxCoins, coins)
      }
      memoization[left][right] = maxCoins
      return maxCoins
    }

    return dp(0, totalNumbers + 1)
  }
}

fun main() {
  println(BurstBalloons().maxCoins(intArrayOf(4,2,3,7)))
}