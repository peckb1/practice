package leetcode.algo

import java.util.PriorityQueue

fun <Node> primMST(
  graph: Graph<Node>,
  start: Node
): Pair<Distance, Set<Node>> {

  data class State<Node>(
    val node: Node,
    val cost: Distance
  )

  val visited = mutableSetOf<Node>()
  val pq = PriorityQueue<State<Node>>(compareBy { it.cost })

  var totalCost = Distance(0)

  pq.add(State(start, Distance(0)))

  while (pq.isNotEmpty()) {
    val (node, cost) = pq.poll()

    if (node in visited) continue

    visited += node
    totalCost += cost

    for ((neighbor, edgeCost) in graph.neighbors(node)) {
      if (neighbor !in visited) {
        pq.add(State(neighbor, edgeCost)) // NOTE: no accumulation!
      }
    }
  }

  return totalCost to visited
}