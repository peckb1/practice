package leetcode.medium

class CombinationSum {
  fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
    val allSums = mutableListOf<List<Int>>()

    addSumToList(
      nums = nums,
      remainingTotal = target,
      allSums = allSums
    )
    return allSums
  }

  fun addSumToList(
    nums: IntArray,
    indexInNums: Int = 0,
    remainingTotal: Int,
    allSums: MutableList<List<Int>>,
    currentSumValues: MutableList<Int> = mutableListOf(),
  ) {
    if (remainingTotal < 0) return
    if (remainingTotal == 0) {
      // we're modifying `currentSumValues` as we recurse - so make a copy before storing
      allSums.add(ArrayList(currentSumValues))
      return
    }

    for (i in indexInNums until nums.size) {
      val value = nums[i]

      currentSumValues.add(value)

      // don't need to increment `i` as we can use it multiple times
      addSumToList(nums, i, remainingTotal - value, allSums, currentSumValues)

      currentSumValues.removeAt(currentSumValues.size - 1)
    }
  }
}