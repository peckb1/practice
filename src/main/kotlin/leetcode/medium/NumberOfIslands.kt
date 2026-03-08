package peckb1.leetcode.medium

class NumberOfIslands {
  val islandFinder = IslandFinder()

  fun numIslands(grid: Array<CharArray>): Int {
    return islandFinder.getIslands(
      grid = grid.map { it.toTypedArray() }.toTypedArray(),
      valid = { it == '1' }
    ).count()
  }
}

class IslandFinder {
  typealias Location = Pair<Int, Int>

  fun <R> getIslands(grid: Array<Array<R>>, valid: (R) -> Boolean): List<Set<Location>> {
    val checkedLocations = mutableSetOf<Location>()

    val islands = mutableListOf<Set<Location>>()

    grid.forEachIndexed { x, row ->
      row.forEachIndexed { y, c ->
        if (valid(c) && !checkedLocations.contains(Location(x, y))) {
          checkedLocations.add(Location(x, y))
          val baseIsland = mutableSetOf(Location(x, y))
          fillOutIsland(grid, valid, baseIsland, Location(x, y), checkedLocations)
          islands.add(baseIsland)
        } // ignore 0s
      }
    }

    return islands
  }

  private fun <R> fillOutIsland(
    grid: Array<Array<R>>,
    valid: (R) -> Boolean,
    currentIsland: MutableSet<Location>,
    location: Location,
    checkedLocations: MutableSet<Location>
  ) : List<Location>{
    // BFS - we want everything anyway
    val newLandNeighbors = location
      .landNeighbors(grid, valid)
      .filterNot { checkedLocations.contains(it) }

    return newLandNeighbors.flatMap { l ->
      currentIsland.add(l)
      checkedLocations.add(l)
      fillOutIsland(grid, valid, currentIsland, l, checkedLocations)
    }
  }

  private fun <R> Location.landNeighbors(grid: Array<Array<R>>, valid: (R) -> Boolean): List<Location> {
    return listOf(
      Location(first - 1, second + 0),
      Location(first + 1, second + 0),
      Location(first + 0, second - 1),
      Location(first + 0, second + 1)
    ).filter { (x, y) ->
      x in grid.indices && y in grid[x].indices && valid(grid[x][y])
    }
  }
}