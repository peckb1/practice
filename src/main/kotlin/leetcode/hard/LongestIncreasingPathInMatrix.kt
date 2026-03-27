package leetcode.hard

class LongestIncreasingPathInMatrix {
  fun longestIncreasingPath(matrix: Array<IntArray>): Int {
    val longestPathLengths = Array(matrix.size) { IntArray(matrix[0].size) { -1 }  }

    var best = 0

    matrix.forEachIndexed { y, row ->
      row.forEachIndexed { x, _ ->
        best = maxOf(best, updateLongestPathLengths(matrix, x, y, longestPathLengths))
      }
    }

    return best
  }

  private fun updateLongestPathLengths(matrix: Array<IntArray>, x: Int, y: Int, longestPathLengths: Array<IntArray>) : Int {
    if (longestPathLengths[y][x] != -1) return longestPathLengths[y][x]

    var best = 1
    for ((dx, dy) in DIRECTION_DELTAS) {
      val nx = x + dx
      val ny = y + dy

      if (ny in matrix.indices && nx in matrix[ny].indices && matrix[ny][nx] > matrix[y][x]) {
        best = maxOf(best, 1 + updateLongestPathLengths(matrix, nx, ny, longestPathLengths))
      }
    }

    longestPathLengths[y][x]= best
    return best
  }

  companion object {
    private val DIRECTION_DELTAS = arrayOf(
      intArrayOf(-1,  0),
      intArrayOf( 1,  0),
      intArrayOf( 0,  1),
      intArrayOf( 0, -1)
    )
  }
}
