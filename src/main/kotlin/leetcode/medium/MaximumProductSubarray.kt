package peckb1.leetcode.medium

import kotlin.math.max

class MaximumProductSubarray {
  fun maxProduct(nums: IntArray): Int = maxOf(
    maxProduct(nums, (0..nums.lastIndex)),
    maxProduct(nums, (nums.lastIndex downTo 0)),
  )

  private fun maxProduct(nums: IntArray, progression: IntProgression): Int {
    var globalMax = Int.MIN_VALUE
    var localMax = 1
    progression.forEach { i ->
      val next = nums[i]
      if (next == 0) {
        localMax = 1
        globalMax = max(globalMax, 0)
      } else {
        localMax *= next
        globalMax = max(globalMax, localMax)
      }
    }

    return globalMax
  }
}
