package peckb1.leetcode.easy

class ContainsDuplicate {
  fun hasDuplicate(nums: IntArray): Boolean {
    val numSet = mutableSetOf<Int>()

    nums.forEach { num ->
      if (numSet.contains(num)) {
        return true
      } else {
        numSet.add(num)
      }
    }

    return false
  }
}