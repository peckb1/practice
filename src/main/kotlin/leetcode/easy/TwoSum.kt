package peckb1.leetcode.easy

class TwoSum {
  /**
   * Basic Brute force
   */
  fun twoSum(nums: IntArray, target: Int): IntArray {
    var lowIndex = 0
    var highIndex = 1
    while (nums[lowIndex] + nums[highIndex] != target) {
      highIndex++

      if (highIndex == nums.size) {
        lowIndex++
        highIndex = lowIndex + 1
      }
    }

    return intArrayOf(lowIndex, highIndex)
  }
}