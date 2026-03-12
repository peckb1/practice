package peckb1.leetcode.easy

class BinarySearch {
  fun optimizedSearch(nums: IntArray, target: Int): Int {
    var low = 0
    var high = nums.lastIndex

    while (low <= high) {
      val mid = low + (high - low) / 2
      val value = nums[mid]

      when {
        value < target -> low = mid + 1
        value > target -> high = mid - 1
        else -> return mid
      }
    }

    return -1
  }

  fun search(nums: IntArray, target: Int): Int {
    return search(target, 0, nums.lastIndex, nums)
  }

  private fun search(target: Int, lowIndex: Int, highIndex: Int, nums: IntArray): Int {
    if (highIndex < lowIndex) return -1

    val lowValue = nums[lowIndex]
    val highValue = nums[highIndex]

    if (lowValue == target) return lowIndex
    if (highValue == target) return highIndex

    if (lowIndex == highIndex) return -1

    val midIndex = (lowIndex + highIndex) / 2
    val midValue = nums[midIndex]

    return when {
      target <  midValue -> search(target, lowIndex, midIndex - 1, nums)
      target == midValue -> midIndex
      else               -> search(target, midIndex + 1, highIndex, nums)
    }
  }
}
