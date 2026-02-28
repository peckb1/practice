package peckb1.leetcode.medium

class TopKFrequentElements {
  fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val counts = nums.toList().groupingBy { it }.eachCount()

    return counts
      .toList()
      .sortedByDescending { it.second }
      .take(k)
      .map { it.first }
      .toIntArray()
  }
}