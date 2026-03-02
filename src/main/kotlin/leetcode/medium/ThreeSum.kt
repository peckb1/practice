package peckb1.leetcode.medium

class ThreeSum {
  val twoSum = TwoSumII()

  fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()

    val results = mutableSetOf<List<Int>>()
    val usedStartNumbers = mutableSetOf<Int>()

    nums.forEachIndexed { iIndex, iValue ->
      if (usedStartNumbers.contains(iValue)) return@forEachIndexed

      val oneIndexPairs = twoSum.allTwoSum(nums, -iValue)

      oneIndexPairs.forEach { (kPlusOne, jPlusOne) ->
        val possibleTripleIndices = setOf(iIndex, kPlusOne - 1, jPlusOne - 1)
        if (possibleTripleIndices.size == 3) {
          results.add(possibleTripleIndices.map { nums[it] }.sorted())
          usedStartNumbers.add(iValue)
        }
      }
    }

    return results.toList()
  }
}
