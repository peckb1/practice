package peckb1.leetcode.medium

class CloneGraph {
  fun cloneGraph(node: Node?): Node? {
    val newNodeMap = mutableMapOf<Int, Node>()

    return node?.let { cloneNode(it, newNodeMap) }
  }

  private fun cloneNode(node: Node, newNodeMap: MutableMap<Int, Node>): Node {
    return Node(node.`val`).apply {
      newNodeMap[`val`] = this

      neighbors = node.neighbors
        .filterNotNull()
        .map { newNodeMap[it.`val`] ?: cloneNode(it, newNodeMap) }
        .toCollection(ArrayList())
    }
  }

  class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
  }
}