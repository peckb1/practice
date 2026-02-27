package peckb1.leetcode

class TwoSum {
  /**
   * Basic Brute force
   */
  fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
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

  /**
   * make a map and find the first one with a pair
   */
  fun twoSumMap(nums: IntArray, target: Int): IntArray {
    val numbers = nums.withIndex().associate { it.value to it.index }
    val indexOne = nums.withIndex().first { n ->
      val missing = target - n.value
      val possibleAnswer = numbers[missing]

      possibleAnswer != null && possibleAnswer != n.index
    }
    val indexTwo = numbers[target - indexOne.value]!!

    return intArrayOf(indexOne.index, indexTwo).apply { sort() }
  }
}
