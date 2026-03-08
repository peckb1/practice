package peckb1.leetcode.hard

import java.awt.SystemColor.window
import java.util.PriorityQueue
import kotlin.math.min

class SlidingWindowMaximum {
  fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    if (nums.isEmpty()) return intArrayOf()
    if (k == 0) return intArrayOf()
    if (k == 1) return nums

    // we have k >= 2 but keep our window bounded by the size of nums
    val windowSize = min(nums.size, k)
    val maxValueQueue = PriorityQueue<Int>(compareByDescending { it }).apply {
      (0 until windowSize).forEach { index -> add(nums[index]) }
    }

    val maxValues = mutableListOf<Int>().apply {
      add(maxValueQueue.peek())
    }

    (windowSize .. nums.lastIndex).forEach { nextIndex ->
      val oldValue = nums[nextIndex - windowSize]
      maxValueQueue.remove(oldValue)
      maxValueQueue.add(nums[nextIndex])
      maxValues.add(maxValueQueue.peek())
    }

    return maxValues.toIntArray()
  }
}
