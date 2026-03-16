package peckb1.leetcode.medium

class FindTheDuplicateNumber {
  fun findDuplicate(nums: IntArray): Int {
    // Step 1: Initialize slow and fast pointers
    var slow = nums[0]
    var fast = nums[0]

    // Step 2: Detect cycle
    do {
      slow = nums[slow]
      fast = nums[nums[fast]]
    } while (slow != fast)

    // Step 3: Find entry point of the cycle (duplicate number)
    slow = nums[0]
    while (slow != fast) {
      slow = nums[slow]
      fast = nums[fast]
    }

    return slow
  }
}
