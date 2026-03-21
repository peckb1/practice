package leetcode.algo

import java.util.PriorityQueue

@JvmInline
value class Distance(val value: Int) : Comparable<Distance> {
    override fun compareTo(other: Distance) = value.compareTo(other.value)

    operator fun plus(other: Distance) = Distance(value + other.value)
}

interface Graph<Node> {
    fun neighbors(node: Node): Iterable<Pair<Node, Distance>>
}

fun <Node> dijkstra(graph: Graph<Node>, start: Node, end: Node? = null): Map<Node, Distance> {

    data class State<Node>(
        val node: Node,
        val cost: Distance
    )

    val dist = mutableMapOf<Node, Distance>().withDefault { Distance(Int.MAX_VALUE) }

    val pq = PriorityQueue<State<Node>>(compareBy { it.cost })

    dist[start] = Distance(0)
    pq.add(State(start, Distance(0)))

    while (pq.isNotEmpty()) {
        val (node, cost) = pq.poll()

        if (cost > dist.getValue(node)) continue

        if (end != null && node == end) break

        for ((neighbor, edgeCost) in graph.neighbors(node)) {
            val newCost = cost + edgeCost

            if (newCost < dist.getValue(neighbor)) {
                dist[neighbor] = newCost
                pq.add(State(neighbor, newCost))
            }
        }
    }

    return dist
}