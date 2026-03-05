package peckb1.leetcode.easy

import java.util.PriorityQueue
import kotlin.math.abs

class LastStoneWeight {
  fun lastStoneWeight(stones: IntArray): Int {
    if (stones.isEmpty()) return 0
    if (stones.size == 1) return stones[0]

    val maxPriorityQueue = PriorityQueue<Int>(compareByDescending { it })

    stones.forEach { maxPriorityQueue.add(it) }

    while(maxPriorityQueue.size > 2) {
      val smash = maxPriorityQueue.smash()
      if (smash != 0) { maxPriorityQueue.add(smash) }
    }

    if (maxPriorityQueue.isEmpty()) return 0
    if (maxPriorityQueue.size == 1) return maxPriorityQueue.poll()

    return maxPriorityQueue.smash()
  }

  private fun PriorityQueue<Int>.smash(): Int = abs(poll() - poll())
}