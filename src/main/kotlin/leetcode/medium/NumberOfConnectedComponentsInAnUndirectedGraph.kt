package peckb1.leetcode.medium

import peckb1.leetcode.medium.GraphValidTree.Node
import kotlin.collections.set

class NumberOfConnectedComponentsInAnUndirectedGraph {
  fun countComponents(n: Int, edges: Array<IntArray>): Int {
    if (n == 0) return 0
    if (n == 1) return 1

    val nodeMap = mutableMapOf<Int, Node>().apply {
      repeat(n) { this[it] = Node(it) }
    }

    edges.forEach { (a, b) ->
      nodeMap[a]?.addEdge(nodeMap[b])
      nodeMap[b]?.addEdge(nodeMap[a])
    }

    val graphs = mutableListOf<MutableSet<Int>>()
    nodeMap.forEach { (nodeId, node) ->
      if (graphs.none { it.contains(node.id) }) {
        val visitedIds = mutableSetOf<Int>()
        dfs(node, visitedIds)
        graphs.add(visitedIds)
      }
    }

    return graphs.count()
  }

  fun dfs(node: Node, visited: MutableSet<Int>) {
    if (node.id in visited) return
    visited.add(node.id)

    node.edges.forEach {
      if (!visited.contains(it.id)) dfs(it, visited)
    }
  }
}

fun main() {
  println(NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(5, arrayOf(
    intArrayOf(0,1),
    intArrayOf(1,2),
    intArrayOf(3,4),
  )))
}