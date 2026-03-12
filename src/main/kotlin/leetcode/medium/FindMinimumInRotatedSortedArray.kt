package peckb1.leetcode.medium

import kotlin.math.min

class FindMinimumInRotatedSortedArray {
  fun findMin(nums: IntArray): Int {
    var lowIndex = 0
    var highIndex = nums.lastIndex

    while (lowIndex <= highIndex) {
      val midIndex = lowIndex + (highIndex - lowIndex) / 2

      val high = nums[highIndex]
      val mid = nums[midIndex]
      val low = nums[lowIndex]

      if (lowIndex == highIndex - 1) {
        return min(low, high)
      }

      when {
        low > mid  -> highIndex = midIndex
        mid > high -> lowIndex  = midIndex
        else -> return (lowIndex .. highIndex).minOf { nums[it] }
      }
    }

    return -1
  }
}
