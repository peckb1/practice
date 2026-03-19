package leetcode.hard

import kotlin.collections.plusAssign

class NQueens {
  fun solveNQueensUsingAiGOlf(n: Int): List<List<String>> {
    return q(n)
  }

  fun q(n:Int,f:Int=0,a:Int=0,b:Int=0,c:Int=0,p:List<Int> = listOf()):List<List<String>> =
    if(f==n)listOf(p.map{".".repeat(it)+"Q"+".".repeat(n-it-1)})else(0 until n).flatMap{i->val d=1 shl i;if(a and d or b and d or c and d==0)q(n,f+1,a or d,(b or d) shl 1,(c or d) shr 1,p+i)else listOf()}

  fun solveNQueens(n: Int): List<List<String>> {
    val results = mutableListOf<List<Pair<Int, Int>>>()

    nQueensPositions(0, n, mutableListOf(), results)

    return formatBoards(results, n)
  }

  private fun nQueensPositions(
    row: Int,
    boardSize: Int,
    queens: MutableList<Pair<Int, Int>>,
    results: MutableList<List<Pair<Int, Int>>>
  ) {
    if (row == boardSize) {
      results += queens.toList()
      return
    }

    for (col in 0 until boardSize) {
      val safe = queens.none { (r, c) ->
        c     == col       || // same column
        r + c == row + col || // diagonal down right
        r - c == row - col    // diagonal down left
      }

      if (safe) {
        // add
        queens += row to col
        // recurse
        nQueensPositions(row + 1, boardSize, queens, results)
        // remove
        queens.removeAt(queens.lastIndex)
      }
    }
  }

  private fun formatBoards(solutions: List<List<Pair<Int, Int>>>, boardSize: Int): List<List<String>> {
    return solutions.map { queens ->
      List(boardSize) { row ->
        val col = queens.first { it.first == row }.second

        ".".repeat(col) + "Q" + ".".repeat(boardSize - col - 1)
      }
    }
  }
}
