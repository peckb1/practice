package peckb1.leetcode.hard

import java.util.PriorityQueue

class MedianFinder {

  val maxHeap = PriorityQueue<Int>(compareByDescending { it })
  val minHeap = PriorityQueue<Int>()

  fun addNum(num: Int) {
    maxHeap.add(num)
    minHeap.add(maxHeap.remove())

    if (maxHeap.size < minHeap.size) {
      maxHeap.add(minHeap.remove())
    }
  }

  fun findMedian(): Double {
    val totalValues = maxHeap.size + minHeap.size
    return if (totalValues % 2 == 0) {
      (maxHeap.peek().toDouble() + minHeap.peek().toDouble()) / 2
    } else {
      maxHeap.peek().toDouble()
    }
  }
}

fun main() {
  val medianFinder = MedianFinder()
  medianFinder.addNum(1) // arr = [1]
  medianFinder.findMedian() // return 1.0
  medianFinder.addNum(3) // arr = [1, 3]
  medianFinder.findMedian() // return 2.0
  medianFinder.addNum(2) // arr[1, 2, 3]
  medianFinder.findMedian() // return 2.0
}