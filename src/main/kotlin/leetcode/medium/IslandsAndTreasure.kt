package peckb1.leetcode.medium

class IslandsAndTreasure {
  private typealias Location = Pair<Int, Int>

  companion object {
    const val TREASURE = 0
  }

  fun islandsAndTreasure(grid: Array<IntArray>) {
    grid.forEachIndexed { x, row ->
      row.forEachIndexed { y, value ->
        if (value == TREASURE) { walkFromTreasure(grid, Location(x, y)) }
      }
    }
  }

  private fun walkFromTreasure(grid: Array<IntArray>, location: Location) {
    val neighbors = location.landNeighbors(grid)

    neighbors.forEach { landNeighbor ->
      val neighborsDistanceToTreasure = grid[landNeighbor.first][landNeighbor.second]
      val myCost = grid[location.first][location.second]
      if (neighborsDistanceToTreasure > myCost + 1) {
        grid[landNeighbor.first][landNeighbor.second] = myCost + 1
        walkFromTreasure(grid, landNeighbor)
      }
    }
  }

  private fun Location.landNeighbors(grid: Array<IntArray>): List<Location> {
    return listOf(
      Location(first - 1, second + 0),
      Location(first + 1, second + 0),
      Location(first + 0, second - 1),
      Location(first + 0, second + 1)
    ).filter { (x, y) ->
      x in grid.indices && y in grid[x].indices && grid[x][y] > 0
    }
  }
}
