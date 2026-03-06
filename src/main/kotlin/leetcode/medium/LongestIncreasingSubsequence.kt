package peckb1.leetcode.medium

typealias Index = Int
typealias Length = Int

class LongestIncreasingSubsequence {
  fun lengthOfLIS(nums: IntArray): Int {
    val cache = mutableMapOf<Index, Length>()

    return nums.indices.maxOfOrNull { index ->
      lengthOfLIS(index, nums, cache)
    } ?: 0
  }

  private fun lengthOfLIS(currentIndex: Int, nums: IntArray, cache: MutableMap<Index, Length> = mutableMapOf()): Int {
    if (currentIndex > nums.size) return 0
    if (cache.contains(currentIndex)) return cache[currentIndex]!!

    val childrenLength = ((currentIndex + 1) .. nums.lastIndex).maxOfOrNull { index ->
      if (nums[index] > nums[currentIndex]) {
        lengthOfLIS(index, nums, cache)
      } else {
        0
      }
    }

    val myLength = 1 + (childrenLength ?: 0)

    return myLength.also { cache[currentIndex] = it }
  }
}
