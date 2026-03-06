package peckb1.leetcode.hard

import java.util.PriorityQueue
import java.util.UUID

/**
 * notes: uses a lot of memory, and wastes time by creating a lot of intermediary object that could be simplified
 * - storing the full path instead of backtracking adds extra memory
 * - constantly making all the new objects for items on the path is also likely extraneous
 *
 * could be refactored under green at this point though
 */
class WordSearchII {
  data class Location(val x: Int, val y: Int) {
    val neighbors by lazy {
      listOf(
        Location(x - 1, y), // left
        Location(x + 1, y), // right
        Location(x, y - 1), // up
        Location(x, y + 1), // down
      )
    }
  }

  fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val cellLocations = mutableMapOf<Char, MutableList<Location>>()

    val cells = board.mapIndexed { x, chars ->
      chars.mapIndexed { y, c ->
        cellLocations.getOrPut(c) { mutableListOf() }.add(Location(x, y))
        Cell(c)
      }
    }

    return words.filter { word ->
      if (word.isEmpty()) return@filter false
      if (word.length == 1) return@filter cellLocations.containsKey(word[0])
      if (!word.all { c -> cellLocations[c] != null }) return@filter false

      var found = false

      val priorityQueue = PriorityQueue<Path>(compareByDescending { it.locations.size })
        .apply {
          val firstChar = word[0]
          cellLocations[firstChar]?.also { locations ->
            locations.forEach { l ->
              val cell = cells[l.x][l.y]
              val cellLocation = CellLocation(cell, l)
              add(Path(listOf(cellLocation)))
            }
          }
        }

      while (priorityQueue.isNotEmpty() && !found) {
        val path = priorityQueue.poll()

        // are we done with the word?
        if (path.locations.size == word.length) {
          found = true
        } else {
          val nextNeighbors = path.locations.last().location.neighbors
            .filter { l -> l.x in (0 .. cells.lastIndex) && l.y in (0 .. cells[l.x].lastIndex) }
            .filter { l ->
              val cell = cells[l.x][l.y]
              val foundLetter = cell.c == word[path.locations.size]
              val noReusedCells = path.locations.none { it.cell == cell }

              foundLetter && noReusedCells
            }

          nextNeighbors.forEach { neighborLocation ->
            val nextCellLocation = CellLocation(cells[neighborLocation.x][neighborLocation.y], neighborLocation)
            priorityQueue.add(Path(path.locations.plus(nextCellLocation)))
          }
        }
      }

      found
    }
  }

  data class Cell(val c: Char, val id: UUID = UUID.randomUUID())

  data class CellLocation(val cell: Cell, val location: Location)

  data class Path(val locations: List<CellLocation>)
}
