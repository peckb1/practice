package peckb1.leetcode.hard

import kotlin.math.max
import kotlin.math.min

class TrappingRainWater {
  // technically this is a two pointer question
  // so an Ideal solution (not this) would only be moving two pointers the entire time

  fun trap(heights: IntArray): Int {
    val indicesUnderWater = mutableSetOf<Int>()

    val sortedHeights = heights.mapIndexed { index, height -> height to index }
      .sortedByDescending { it.first }

    var totalWater = 0

    sortedHeights.forEachIndexed { index, pair ->
      // my details
      val (myHeight, myIndex) = pair

      // could I form a well?
      if (myHeight == 0) return@forEachIndexed

      // am I already underwater?
      if (indicesUnderWater.contains(myIndex)) return@forEachIndexed

      // what are the items which are above or equal to my height
      val itemsAtOrAboveMe = sortedHeights.subList(0, index)

      // iterate through them and see if We would form a well
      itemsAtOrAboveMe.forEach { (_, otherIndex) ->
        if (indicesUnderWater.contains(myIndex)) return@forEach

        val wallToWallRange = min(myIndex, otherIndex) .. max(myIndex, otherIndex)
        val innerRange = (wallToWallRange.first + 1)..< wallToWallRange.last

        val innerHeights = mutableListOf<Int>()
        val isWell = innerRange.none { index ->
          val height = heights[index]

          innerHeights.add(height)
          height >= myHeight
        }

        if (isWell) {
          val size = innerRange.last - innerRange.first + 1
          if (size > 0) { // the space between us is at least "one" element

            val maxPossibleWater = size * myHeight
            totalWater += (maxPossibleWater - innerHeights.sum())
            innerRange.forEach { indicesUnderWater.add(it) }
          }
        }
      }
    }

    return totalWater
  }
}
