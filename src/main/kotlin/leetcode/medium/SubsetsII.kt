package leetcode.medium

class SubsetsII {
  fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    nums.sort()

    val results = mutableListOf<List<Int>>()

    backtrack(0, nums, mutableListOf(), results)

    return results
  }

  fun backtrack(
    start: Int,
    nums: IntArray,
    path: MutableList<Int>,
    results: MutableList<List<Int>>
  ) {
    // Add current combination/subset
    results.add(ArrayList(path))

    for (i in start until nums.size) {
      // 🔑 Skip duplicates
      if (i > start && nums[i] == nums[i - 1]) continue

      // Choose
      path.add(nums[i])

      // Explore
      backtrack(i + 1, nums, path, results)

      // Un-choose
      path.removeAt(path.size - 1)
    }
  }
}