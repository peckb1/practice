package peckb1.leetcode.medium

class SearchInRotatedSortedArray {
  fun search(nums: IntArray, target: Int): Int {
    var lowIndex = 0
    var highIndex = nums.lastIndex

    while (lowIndex <= highIndex) {
      val midIndex = lowIndex + (highIndex - lowIndex) / 2

      val low  = nums[lowIndex]
      val mid  = nums[midIndex]
      val high = nums[highIndex]

      if (mid == target) return midIndex

      when {
        low <= mid -> {
          // the lower half is sorted
          val inLower = target in low until mid
          if (inLower) { highIndex = midIndex - 1 } else { lowIndex = midIndex + 1 }
        }
        else -> {
          // the upper half is sorted
          val inUpper = target in (mid + 1) .. high
          if (inUpper) { lowIndex = midIndex + 1 } else { highIndex = midIndex - 1 }
        }
      }
    }

    return -1
  }
}
