package peckb1.leetcode.medium

class RottingFruit {
  private typealias Location = Pair<Int, Int>

  companion object {
    const val EMPTY = 0
    const val FRESH = 1
    const val ROTTEN = 2
  }

  fun orangesRotting(grid: Array<IntArray>): Int {
    val rottenFruit = mutableSetOf<Location>()
    val fruitCloseToSpoiling = mutableMapOf<Location, List<Location>>()
    val freshFruit = mutableSetOf<Location>()

    grid.forEachIndexed { x, row ->
      row.forEachIndexed { y, element ->
        if (element == ROTTEN) {
          val rottenLocation = Location(x, y)
          rottenFruit.add(rottenLocation)
          fruitCloseToSpoiling[rottenLocation] = rottenLocation.freshFruitNeighbors(grid)
        } else if (element == FRESH) {
          freshFruit.add(Location(x, y))
        }
      }
    }

    if (rottenFruit.isEmpty() && freshFruit.isNotEmpty()) return -1
    if (freshFruit.isEmpty()) return 0
    if (rottenFruit.all { fruitCloseToSpoiling[it]!!.isEmpty() }) return -1

    var time = 0
    while (rottenFruit.isNotEmpty()) {

      val newRotting = mutableSetOf<Location>()
      rottenFruit.forEach { badFood ->
        fruitCloseToSpoiling[badFood]!!.forEach { maybeSpoiling ->
          if (grid[maybeSpoiling.first][maybeSpoiling.second] == FRESH) {
            newRotting.add(maybeSpoiling)
            grid[maybeSpoiling.first][maybeSpoiling.second] = ROTTEN
          }
        }
      }
      rottenFruit.clear()
      rottenFruit.addAll(newRotting)

      fruitCloseToSpoiling.clear()
      rottenFruit.forEach { badFood ->
        fruitCloseToSpoiling[badFood] = badFood.freshFruitNeighbors(grid)
      }

      if (rottenFruit.isNotEmpty()) time++
    }

    // all fruit spoiling has happened - is there any fresh left?
    if (grid.any { it.any { it == FRESH }}) return -1

    return time
  }

  private fun Location.freshFruitNeighbors(grid: Array<IntArray>): List<Location> {
    return listOf(
      Location(first - 1, second + 0),
      Location(first + 1, second + 0),
      Location(first + 0, second - 1),
      Location(first + 0, second + 1)
    ).filter { (x, y) ->
      x in grid.indices && y in grid[x].indices && grid[x][y] == FRESH
    }
  }
}
