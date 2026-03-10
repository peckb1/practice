package peckb1.leetcode.medium

class GraphValidTree {
  /**
   * check:
   *  - All nodes are connected
   *  - no cycles
   * note: edges are undirected
   */
  fun validTree(n: Int, edges: Array<IntArray>): Boolean {
    if (n == 0) return true

    val nodeMap = mutableMapOf<Int, Node>().apply {
      repeat(n) { this[it] = Node(it) }
    }

    edges.forEach { (a, b) ->
      nodeMap[a]?.addEdge(nodeMap[b])
      nodeMap[b]?.addEdge(nodeMap[a])
    }
    
    val visited = mutableSetOf<Int>()
    val start = nodeMap.values.first()

    if (!dfs(start, null, visited)) return false

    // check connectivity
    return visited.size == nodeMap.size
  }

  fun dfs(node: Node, parent: Node?, visited: MutableSet<Int>): Boolean {
    if (node.id in visited) return false
    visited.add(node.id)

    return node.edges.all { it == parent || dfs(it, node, visited) }
  }

  data class Node(val id: Int, val edges: MutableList<Node> = mutableListOf()) {
    fun addEdge(node: Node?) = node?.let { edges.add(node) }

    override fun toString(): String = "$id -> ${edges.map { it.id }}"
  }
}
