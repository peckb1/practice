package peckb1.leetcode.easy

import java.util.PriorityQueue

class KthLargest(val k: Int, nums: IntArray) {
  val minPriorityQueue = PriorityQueue<Int>()
    .apply { nums.forEach(::add) }

  init {
    shrinkQueue()
  }

  fun add(number: Int): Int {
    minPriorityQueue.add(number)
    shrinkQueue()
    return minPriorityQueue.peek()
  }

  private fun shrinkQueue() {
    while (minPriorityQueue.size > k) {
      minPriorityQueue.poll()
    }
  }
}
