package leetcode.medium

import leetcode.hard.WordSearchII

class WordSearch {
  val searcher = WordSearchII()

  fun exist(board: Array<CharArray>, word: String): Boolean {
    return searcher.findWords(board, arrayOf(word)).isNotEmpty()
  }
}