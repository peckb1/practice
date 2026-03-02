package peckb1.leetcode.medium

class TwoSumII {
  /**
   * make a map and find the first one with a pair
   */
  fun twoSum(numbers: IntArray, target: Int): IntArray {
    var lowIndex = 0
    var highIndex = numbers.lastIndex

    while (lowIndex < highIndex) {
      val sum = numbers[lowIndex] + numbers[highIndex]
      when {
        sum > target -> highIndex--
        sum < target -> lowIndex++
        else -> return intArrayOf(lowIndex + 1, highIndex + 1)
      }
    }
    return intArrayOf()
  }

  fun allTwoSum(numbers: IntArray, target: Int): List<IntArray> {
    var lowIndex = 0
    var highIndex = numbers.lastIndex

    val sums = mutableListOf<IntArray>()

    while (lowIndex < highIndex) {
      val sum = numbers[lowIndex] + numbers[highIndex]
      when {
        sum > target -> highIndex--
        sum < target -> lowIndex++
        else -> sums.add(intArrayOf(lowIndex + 1, highIndex + 1)).also { lowIndex++ }
      }
    }

    return sums
  }
}