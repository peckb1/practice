package peckb1.leetcode.medium

class PartitionEqualSubsetSum {
  fun canPartition(nums: IntArray): Boolean {
    val total = nums.sum()

    if (total % 2 != 0) return false
    val goal = total / 2

    nums.sortDescending() // in place with IntArray

    return canPartition(goal, 0, 0, nums)
  }

  // move an item from P2 to P1 and check if goal!
  // P2 should be sorted in DESCENDING order
  private fun canPartition(goal: Int, currentTotal: Int, currentIndex: Int, nums: IntArray): Boolean {
    if (currentIndex >= nums.size) return false

    val totalWithMe = nums[currentIndex] + currentTotal
    if (nums[currentIndex] + currentTotal == goal) return true
    val canSucceedWithMe = if (nums[currentIndex] + currentTotal < goal) {
      // we can include this, but we now need to continue
      canPartition(goal, totalWithMe, currentIndex + 1, nums)
    } else {
      false
    }
    return canSucceedWithMe || canPartition(goal, currentTotal, currentIndex + 1, nums)
  }
}
