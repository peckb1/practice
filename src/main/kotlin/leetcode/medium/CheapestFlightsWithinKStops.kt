package leetcode.medium

import java.util.PriorityQueue

class CheapestFlightsWithinKStops {
  data class State(val node: Int, val cost: Int, val stops: Int)

  fun findCheapestPrice(
    numberOfAirports: Int,
    flights: Array<IntArray>,
    source: Int,
    destination: Int,
    maximumStops: Int
  ): Int {
    val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>().apply {
      flights.forEach { (depart, arrive, cost) ->
        getOrPut(depart) { mutableListOf() }.add(arrive to cost)
      }
    }

    val pq = PriorityQueue<State>(compareBy { it.cost })
      .apply { add(State(source, 0, 0)) }

    val bestCost = mutableMapOf<Pair<Int, Int>, Int>()
      .apply { put(source to 0, 0) }

    while (pq.isNotEmpty()) {
      val (node, cost, stops) = pq.poll()

      if (node == destination) return cost

      if (stops > maximumStops) continue

      graph[node]?.forEach { (next, price) ->
        val newCost = cost + price
        val key = next to (stops + 1)

        if (newCost < bestCost.getOrDefault(key, Int.MAX_VALUE)) {
          bestCost[key] = newCost
          pq.add(State(next, newCost, stops + 1))
        }
      }
    }

    return -1
  }
}
