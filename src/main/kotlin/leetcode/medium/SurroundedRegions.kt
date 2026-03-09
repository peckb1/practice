package peckb1.leetcode.medium

class SurroundedRegions {
  companion object {
    val DELTAS = listOf(-1 to 0, 1 to 0, 0 to 1, 0 to -1)
  }

  fun solve(board: Array<CharArray>) {
    val ignored = mutableSetOf<Pair<Int, Int>>()

    board.indices.forEach { r ->
      board[r].indices.forEach { c ->
        if (board[r][c] == 'O') {
          if (!ignored.contains(Pair(r, c))) {
            // am I swappable
            val possible = ArrayDeque<Pair<Int, Int>>().apply { add(Pair(r, c)) }
            val toSwap = mutableSetOf<Pair<Int, Int>>()
            while(possible.isNotEmpty()) {
              val next = possible.removeFirst()

              val (os, xs) = DELTAS
                .mapNotNull { (x, y) ->
                  val nr = x + next.first
                  val nc = y + next.second
                  if (nr in board.indices && nc in board[nr].indices) { nr to nc } else { null }
                }
                .partition { (nr, nc) -> board[nr][nc] == 'O' }

              if ((os.size + xs.size) == 4) {
                toSwap.add(next)
                os.forEach { if (!toSwap.contains(it) && !possible.contains(it)) possible.addLast(it) }
              } else {
                // something in the group was near an edge can't surround
                ignored.addAll(toSwap)
                toSwap.clear()
                possible.clear()
              }
            }

            toSwap.forEach { (r, c) -> board[r][c] = 'X' }
          }
        }
      }
    }
  }
}
