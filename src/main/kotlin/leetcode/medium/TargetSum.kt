package leetcode.medium

class TargetSum {
  fun findTargetSumWays(nums: IntArray, target: Int): Int {
    val totalSum = nums.sum()
    // If the target is impossible
    if (target > totalSum || target < -totalSum) return 0

    val totalNumbers = nums.size
    val sumsCounts = Array(totalNumbers + 1) { IntArray(2 * totalSum + 1) { 0 } }

    sumsCounts[0][totalSum] = 1 // base case: sum 0 at index 0

    for (number in 1..totalNumbers) {
      for (sum in -totalSum..totalSum) {
        val index = sum + totalSum

        val add = if ((sum - nums[number - 1] + totalSum) in 0..2 * totalSum) {
          sumsCounts[number - 1][sum - nums[number - 1] + totalSum]
        } else 0
        val subtract = if ((sum + nums[number - 1] + totalSum) in 0..2 * totalSum) {
          sumsCounts[number - 1][sum + nums[number - 1] + totalSum]
        } else 0
        sumsCounts[number][index] = add + subtract
      }
    }

    return sumsCounts[totalNumbers][target + totalSum]
  }
}

fun main() {
  println(TargetSum().findTargetSumWays(intArrayOf(2, 2, 2), 2))
}