package leetcode.hard

import java.util.PriorityQueue

/**
 * https://en.wikipedia.org/wiki/Eulerian_path
 */
class ReconstructFlightPath {
  fun findItinerary(tickets: List<List<String>>): List<String> {
    val graph = mutableMapOf<String, PriorityQueue<String>>()

    for ((source, destination) in tickets) {
      graph.computeIfAbsent(source) { PriorityQueue() }.add(destination)
    }

    val result = mutableListOf<String>()

    fun findItinerary(node: String) {
      val heap = graph[node]
      while (!heap.isNullOrEmpty()) {
        findItinerary(heap.poll()) // always take smallest lexical option
      }
      result.add(node)
    }

    findItinerary("JFK")

    return result.reversed()
  }
}
