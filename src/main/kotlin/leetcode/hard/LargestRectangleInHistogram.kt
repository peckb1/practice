package peckb1.leetcode.hard

class LargestRectangleInHistogram {
  fun largestRectangleArea(heights: IntArray): Int {
    val indexStack = ArrayDeque<Int>()
    var maxArea = 0

    for (heightIndex in 0..heights.size) {
      // allow us to extend passed and calculate again for the last index
      val currentHeight = if (heightIndex == heights.size) 0 else heights[heightIndex]

      while (indexStack.isNotEmpty() && currentHeight < heights[indexStack.last()]) {
        val height = heights[indexStack.removeLast()]
        val width = if (indexStack.isEmpty()) {
          heightIndex
        } else {
          heightIndex - indexStack.last() - 1
        }
        maxArea = maxOf(maxArea, height * width)
      }

      indexStack.addLast(heightIndex)
    }

    return maxArea
  }
}

fun main() {

  /*
      | |
      | |
      | |
      | |  |
      | |  |
      | ||||
      ||||||
   */
  val heights = intArrayOf(7,1,7,2,2,4)

  print(LargestRectangleInHistogram().largestRectangleArea(heights))
}