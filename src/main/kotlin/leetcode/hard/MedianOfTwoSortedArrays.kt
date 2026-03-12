package peckb1.leetcode.hard

class MedianOfTwoSortedArrays {
  fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val totalSizeOfBothArrays = nums1.size + nums2.size

    if (totalSizeOfBothArrays == 0) return 0.0

    val (lowerIndex, upperIndex) = if (totalSizeOfBothArrays % 2 == 0) {
      val lowerIndex = (totalSizeOfBothArrays / 2) - 1
      val upperIndex = totalSizeOfBothArrays / 2

      lowerIndex to upperIndex
    } else {
      val midIndex = totalSizeOfBothArrays / 2

      midIndex to midIndex
    }

    var currentNums1Index = 0
    var currentNums2Index = 0
    var currentCombinedEnd = -1
    var currentCombinedPreviousEnd = -1
    var currentCombinedIndex = 0

    while(currentCombinedIndex <= upperIndex) {
      currentCombinedPreviousEnd = currentCombinedEnd

      val nums1Value = if (nums1.size > currentNums1Index) {
        nums1[currentNums1Index]
      } else {
        null
      }
      val nums2Value = if (nums2.size > currentNums2Index) {
        nums2[currentNums2Index]
      } else {
        null
      }

      when {
        nums1Value != null && nums2Value != null -> {
          if (nums1Value < nums2Value) {
            currentNums1Index++
            currentCombinedEnd = nums1Value
          } else {
            currentNums2Index++
            currentCombinedEnd = nums2Value
          }
        }
        nums1Value != null -> {
          currentNums1Index++
          currentCombinedEnd = nums1Value
        }
        nums2Value != null -> {
          currentNums2Index++
          currentCombinedEnd = nums2Value
        }
      }

      currentCombinedIndex++
    }

    return if (lowerIndex != upperIndex) {
      (currentCombinedEnd.toDouble() + currentCombinedPreviousEnd.toDouble()) / 2.0
    } else {
      currentCombinedEnd.toDouble()
    }
  }

  fun findMedianSortedArraysBinaryPartition(nums1: IntArray, nums2: IntArray): Double {
    // Always binary search the smaller array
    val (smallerArray, largerArray) = if (nums1.size <= nums2.size) { nums1 to nums2 } else { nums2 to nums1 }

    var minPartition = 0
    var maxPartition = smallerArray.size

    val totalLength = smallerArray.size + largerArray.size
    val leftHalfSize = (totalLength + 1) / 2

    while (minPartition <= maxPartition) {
      val partitionA = (minPartition + maxPartition) / 2
      val partitionB = leftHalfSize - partitionA

      val maxLeftSmallArray  = if (partitionA == 0)                 Int.MIN_VALUE else smallerArray[partitionA - 1]
      val minRightSmallArray = if (partitionA == smallerArray.size) Int.MAX_VALUE else smallerArray[partitionA]

      val maxLeftLargeArray  = if (partitionB == 0)                 Int.MIN_VALUE else largerArray[partitionB - 1]
      val minRightLargeArray = if (partitionB == largerArray.size)  Int.MAX_VALUE else largerArray[partitionB]

      if (maxLeftSmallArray <= minRightLargeArray && maxLeftLargeArray <= minRightSmallArray) {
        // Correct partition found! Calculate the median
        return if (totalLength % 2 == 0) {
          (maxOf(maxLeftSmallArray, maxLeftLargeArray) + minOf(minRightSmallArray, minRightLargeArray)) / 2.0
        } else {
          maxOf(maxLeftSmallArray, maxLeftLargeArray).toDouble()
        }
      } else if (maxLeftSmallArray > minRightLargeArray) {
        maxPartition = partitionA - 1
      } else {
        minPartition = partitionA + 1
      }
    }

    throw IllegalArgumentException("Arrays not sorted")
  }
}
