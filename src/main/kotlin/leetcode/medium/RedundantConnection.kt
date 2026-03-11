package peckb1.leetcode.medium

import kotlin.collections.set

class RedundantConnection {
  fun findRedundantConnection(edges: Array<IntArray>): IntArray {
    val loopEdges = findLoopEdges(edges).toSet()

    return edges.reversed().firstOrNull { (a, b) -> loopEdges.contains(a to b) }
      ?: intArrayOf()
  }

  private fun findLoopEdges(edges: Array<IntArray>): List<Pair<Int, Int>> {
    val uf = UnionFind()
    val loops = mutableListOf<Pair<Int, Int>>()

    for (edge in edges) {
      val (a, b) = edge

      if (!uf.union(a, b)) {
        loops.add(a to b)
      }
    }

    return loops
  }

  class UnionFind {

    private val parentOf = mutableMapOf<Int, Int>()
    private val sizeofTree = mutableMapOf<Int, Int>()

    private fun find(x: Int): Int {
      parentOf.putIfAbsent(x, x)
      sizeofTree.putIfAbsent(x, 0)

      if (parentOf[x] != x) {
        parentOf[x] = find(parentOf[x]!!)
      }

      return parentOf[x]!!
    }

    fun union(a: Int, b: Int): Boolean {
      val rootA = find(a)
      val rootB = find(b)

      if (rootA == rootB) return false // cycle edge

      val sizeOfA = sizeofTree[rootA]!!
      val sizeOfB = sizeofTree[rootB]!!

      when {
        sizeOfA < sizeOfB -> parentOf[rootA] = rootB
        sizeOfA > sizeOfB -> parentOf[rootB] = rootA
        else -> {
          parentOf[rootB] = rootA
          sizeofTree[rootA] = sizeOfA + 1
        }
      }

      return true
    }
  }
}

fun main() {
  print(RedundantConnection().findRedundantConnection(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(1, 3),
      intArrayOf(3, 4),
      intArrayOf(2, 4),
    )
  ).toList())
}