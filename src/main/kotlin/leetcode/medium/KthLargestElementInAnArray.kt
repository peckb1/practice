package peckb1.leetcode.medium

import java.util.PriorityQueue

class KthLargestElementInAnArray {

  fun findKthLargest(nums: IntArray, k: Int): Int {
    val minQueue = PriorityQueue<Int>()

    nums.forEach { minQueue.add(it) }

    minQueue.shrinkQueue(k)
    return minQueue.peek()
  }

  private fun PriorityQueue<*>.shrinkQueue(k: Int) {
    while (size > k) { poll() }
  }
}