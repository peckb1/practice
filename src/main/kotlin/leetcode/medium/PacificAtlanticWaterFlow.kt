package peckb1.leetcode.medium

class PacificAtlanticWaterFlow {
  private typealias Location = Pair<Int, Int>

  fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
    val canPacific = mutableSetOf<Location>()
    val cannotPacific = mutableSetOf<Location>()

    val canAtlantic = mutableSetOf<Location>()
    val cannotAtlantic = mutableSetOf<Location>()

    heights.forEachIndexed { r, row ->
      row.forEachIndexed { c, _ ->
        val l = Location(r, c)
        if (!canPacific.contains(l)) {
          if (canReachPacific(Location(r, c), heights, canPacific, cannotPacific)) {
            canPacific.add(l)
          } else {
            cannotPacific.add(l)
          }
        }
      }
    }

    (heights.lastIndex downTo 0).forEach { r ->
      val row = heights[r]
      (row.lastIndex downTo 0).forEach { c ->
        val l = Location(r, c)
        if (!canAtlantic.contains(l)) {
          if (canReachAtlantic(Location(r, c), heights, canAtlantic, cannotAtlantic)) {
            canAtlantic.add(l)
          } else {
            cannotAtlantic.add(l)
          }
        }
      }
    }

    return canPacific.intersect(canAtlantic).map { l -> listOf(l.first, l.second) }
  }

  private fun canReachAtlantic(
    location: Location,
    heights: Array<IntArray>,
    canReach: MutableSet<Location>,
    cannotReach: MutableSet<Location>,
    currentPath: MutableSet<Location> = mutableSetOf(location)
  ): Boolean {
    if (location.first == heights.lastIndex) return true
    if (location.second == heights[location.first].lastIndex) return true

    return checkNeighbors(location, heights, canReach, cannotReach, currentPath, ::canReachAtlantic)
  }

  private fun canReachPacific(
    location: Location,
    heights: Array<IntArray>,
    canReach: MutableSet<Location>,
    cannotReach: MutableSet<Location>,
    currentPath: MutableSet<Location> = mutableSetOf(location)
  ): Boolean {
    if (location.first == 0) return true
    if (location.second == 0) return true

//    println("Checking $location")

    return checkNeighbors(location, heights, canReach, cannotReach, currentPath, ::canReachPacific)
  }

  private fun Location.lowerOrEqualNeighbors(grid: Array<IntArray>): List<Location> {
    return listOf(
      Location(first - 1, second + 0),
      Location(first + 1, second + 0),
      Location(first + 0, second - 1),
      Location(first + 0, second + 1)
    ).filter { (x, y) ->
      x in grid.indices && y in grid[x].indices && grid[x][y] <= grid[this.first][this.second]
    }
  }

  private fun checkNeighbors(
    location: Location,
    heights: Array<IntArray>,
    canReach: MutableSet<Location>,
    cannotReach: MutableSet<Location>,
    currentPath: MutableSet<Location>,
    cahReach : (Location, Array<IntArray>, MutableSet<Location>, MutableSet<Location>, MutableSet<Location>) -> Boolean
  ): Boolean {
    // what neighbors could I jump to?
    return location.lowerOrEqualNeighbors(heights)
      .filterNot { cannotReach.contains(it) || currentPath.contains(it) }
      .any {
        canReach.contains(it) || run {
          currentPath.add(it)
          val result = cahReach(it, heights, canReach, cannotReach, currentPath)
          result
        }
      }
  }
}
