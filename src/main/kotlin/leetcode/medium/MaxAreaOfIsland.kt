package peckb1.leetcode.medium

class MaxAreaOfIsland {
  val islandFinder = IslandFinder()

  fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    return islandFinder.getIslands(
      grid = grid.map { it.toTypedArray() }.toTypedArray(),
      valid = { it == 1 }
    ).maxOfOrNull { it.size } ?: 0
  }
}
