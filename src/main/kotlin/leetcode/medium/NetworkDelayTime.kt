package leetcode.medium

import leetcode.algo.Distance
import leetcode.algo.Graph
import leetcode.algo.dijkstra

class NetworkDelayTime {
  fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val adjacency = Array(n + 1) { mutableListOf<Pair<Int, Distance>>() }
      .apply {
        times.forEach { (source, destination, cost) -> this[source].add(Pair(destination, Distance(cost))) }
      }

    val graph: Graph<Int> = object : Graph<Int> {
      override fun neighbors(node: Int) = adjacency[node]
    }

    val nodes = dijkstra(graph, k)

    return if (nodes.size < n) { -1 } else { nodes.maxOfOrNull { it.value }?.value!! }
  }
}
