package leetcode.medium

class CombinationSumII {
  fun combinationSum2(nums: IntArray, target: Int): List<List<Int>> {
    val allSums = mutableSetOf<List<Int>>()

    addSumToList(
      nums = nums,
      remainingTotal = target,
      allSums = allSums
    )
    return allSums.toList()
  }

  fun addSumToList(
    nums: IntArray,
    indexInNums: Int = 0,
    remainingTotal: Int,
    allSums: MutableSet<List<Int>>,
    currentSumValues: MutableList<Int> = mutableListOf(),
  ) {
    if (remainingTotal < 0) return
    if (remainingTotal == 0) {
      // we're modifying `currentSumValues` as we recurse - so make a copy before storing
      allSums.add(ArrayList(currentSumValues).also { it.sort() })
      return
    }

    for (i in indexInNums until nums.size) {
      val value = nums[i]

      currentSumValues.add(value)

      addSumToList(nums, i + 1, remainingTotal - value, allSums, currentSumValues)

      currentSumValues.remove(value)
    }
  }
}