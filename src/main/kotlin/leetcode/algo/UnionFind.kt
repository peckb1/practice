package leetcode.algo

class UnionFind(size: Int) {
  private val parent = IntArray(size) { it }
  private val rank   = IntArray(size) { 0 }

  fun find(n: Int): Int {
    if (parent[n] != n) {
      parent[n] = find(parent[n])
    }
    return parent[n]
  }

  fun union(x: Int, y: Int) {
    val parentOfX = find(x)
    val parentOfY = find(y)
    if (parentOfX == parentOfY) return

    when {
      rank[parentOfX] < rank[parentOfY] -> parent[parentOfX] = parentOfY
      rank[parentOfX] > rank[parentOfY] -> parent[parentOfY] = parentOfX
      else -> {
        parent[parentOfY] = parentOfX
        rank[parentOfX]++
      }
    }
  }

  fun connected(x: Int, y: Int) = find(x) == find(y)
}

// Helper to union-find with 2D coordinates
fun UnionFind.connected(
  r1: Int, c1: Int,
  r2: Int, c2: Int,
  columnsInGrid: Int = 0
): Boolean {
  return connected(r1 * columnsInGrid + c1, r2 * columnsInGrid + c2)
}