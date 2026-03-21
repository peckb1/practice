package leetcode.medium

import leetcode.algo.Distance
import leetcode.algo.Graph
import leetcode.algo.primMST

class MinCostToConnectPoints {
  fun minCostConnectPoints(points: Array<IntArray>): Int {
    val pts = points.map { it[0] to it[1] }

    val graph = PointsGraph(pts)

    val (totalCost, _) = primMST(graph, start = 0)

    return totalCost.value
  }

  class PointsGraph(
    private val points: List<Pair<Int, Int>>
  ) : Graph<Int> {

    override fun neighbors(node: Int): Iterable<Pair<Int, Distance>> {
      val (x1, y1) = points[node]

      return points.indices
        .asSequence()
        .filter { it != node }
        .map { i ->
          val (x2, y2) = points[i]
          val dist = kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)
          i to Distance(dist)
        }
        .asIterable()
    }
  }
}