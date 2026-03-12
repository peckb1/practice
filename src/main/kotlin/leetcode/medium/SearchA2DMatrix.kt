package peckb1.leetcode.medium

import peckb1.leetcode.easy.BinarySearch

class SearchA2DMatrix {
  val binarySearch = BinarySearch()

  fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    val rowIndex = findRow(matrix, target)

    return when (rowIndex) {
      null -> false
      else -> findInRow(matrix, rowIndex, target) != null
    }
  }

  private fun findInRow(matrix: Array<IntArray>, rowIndex: Int, target: Int) : Int? {
    return when (val index = binarySearch.optimizedSearch(matrix[rowIndex], target)) {
      -1 -> null
      else -> index
    }
  }

  private fun findRow(matrix: Array<IntArray>, target: Int): Int? {
    var lowRowIndex = 0
    var highRowIndex = matrix.lastIndex

    while (lowRowIndex <= highRowIndex) {
      val midRowIndex = lowRowIndex + (highRowIndex - lowRowIndex) / 2
      val rowStartValue = matrix[midRowIndex].first()
      val rowEndValue = matrix[midRowIndex].last()

      when {
        target < rowStartValue -> highRowIndex = midRowIndex - 1
        target > rowEndValue   -> lowRowIndex = midRowIndex + 1
        else                   -> return midRowIndex
      }
    }

    return null
  }
}