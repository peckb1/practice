package peckb1.leetcode.medium

class ValidSudoku {
  fun isValidSudoku(board: Array<CharArray>): Boolean {
    val grids = mutableMapOf<Pair<Int, Int>, MutableSet<Char>>().withDefault { mutableSetOf() }
    val columns = mutableMapOf<Int, MutableSet<Char>>().withDefault { mutableSetOf() }
    board.forEachIndexed { rowIndex, row ->
      val rowGrid = rowIndex / 3

      val rowValues = mutableSetOf<Char>()
      row.forEachIndexed { colIndex, value ->
        if (rowValues.contains(value)) { return false }
        if (columns.getValue(colIndex).contains(value)) { return false }

        val colGrid = colIndex / 3
        val gridKey = rowGrid to colGrid
        if (grids.getValue(gridKey).contains(value)) { return false }

        if (value != '.') {
          rowValues.add(value)
          columns[colIndex] = columns.getValue(colIndex).also { it.add(value) }
          grids[gridKey] = grids.getValue(gridKey).also { it.add(value) }
        }
      }
    }

    return true
  }
}
