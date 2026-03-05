package peckb1.leetcode.medium

import java.util.PriorityQueue
import kotlin.math.sqrt

class KClosestPointsToOrigin {
  fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val closestPriorityQueue = PriorityQueue(Comparator<IntArray> { p1, p2 ->
      /*
       * a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
       * Comparing straight line distance from origin
       * sqrt((x1 - x2)^2 + (y1 - y2)^2))
       */
      val p1DistanceFromOrigin = sqrt((p1[0] * p1[0]).toDouble() + (p1[1] * p1[1]))
      val p2DistanceFromOrigin = sqrt((p2[0] * p2[0]).toDouble() + (p2[1] * p2[1]))

      // we want the closest, so invert the comparison
      -p1DistanceFromOrigin.compareTo(p2DistanceFromOrigin)
    })

    points.forEach {
      closestPriorityQueue.add(it)
      closestPriorityQueue.shrinkQueue(k)
    }

    return closestPriorityQueue.toTypedArray()
  }

  private fun PriorityQueue<*>.shrinkQueue(k: Int) {
    while (size > k) { poll() }
  }
}
