package leetcode.hard

import leetcode.algo.UnionFind
import leetcode.algo.connected

class SwimInRisingWater {
  fun swimInWater(grid: Array<IntArray>): Int {
    val columns = grid.size
    val unionFind = UnionFind(columns * columns)
    val rows = grid.size
    val cols = grid[0].size

    // (row, col, height)
    // sorted by elevation
    val cells = mutableListOf<Triple<Int, Int, Int>>().apply {
      grid.forEachIndexed { c, column ->
        column.forEachIndexed { r, elevation ->
          this.add(Triple(r, c, elevation))
        }
      }
    }.sortedBy { it.third }

    val flooded = Array(columns) { BooleanArray(columns) { false } }
    val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)

    for ((r, c, h) in cells) {
      flooded[r][c] = true

      // Union with flooded neighbors
      for ((dr, dc) in directions) {
        val nr = r + dr
        val nc = c + dc
        if (nr in 0 until columns && nc in 0 until columns && flooded[nr][nc]) {
          unionFind.union(r * columns + c, nr * columns + nc)
        }
      }

      // Check if start and end are connected
      if (unionFind.connected(0, 0, rows - 1, cols - 1, columns)) {
        return h
      }
    }

    error("Should never reach here")
  }
}
