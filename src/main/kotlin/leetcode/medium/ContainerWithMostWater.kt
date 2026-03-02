package peckb1.leetcode.medium

import kotlin.math.max

class ContainerWithMostWater {
  fun maxArea(heights: IntArray): Int {
    var lowerIndex = 0
    var upperIndex = heights.lastIndex

    var maxWater = 0
    while (lowerIndex < upperIndex) {
      val lowerHeight = heights[lowerIndex]
      val upperHeight = heights[upperIndex]

      if (lowerHeight < upperHeight) {
        val localWater = lowerHeight * (upperIndex - lowerIndex)
        maxWater = max(localWater, maxWater)
        lowerIndex++
      } else { // upper is equal or lower
        val localWater = upperHeight * (upperIndex - lowerIndex)
        maxWater = max(localWater, maxWater)
        upperIndex--
      }
    }

    return maxWater
  }
}
