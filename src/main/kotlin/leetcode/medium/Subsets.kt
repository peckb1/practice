package leetcode.medium

class Subsets {
  fun subsets(nums: IntArray): List<List<Int>> {
    if (nums.isEmpty()) return emptyList()

    return mutableListOf<List<Int>>(emptyList())
      .apply { addAll(subsets(nums, 0)) }
  }

  private fun subsets(nums: IntArray, index: Int): List<List<Int>> {
    val me = listOf(nums[index])
    val children = if (index + 1 in nums.indices) {
       subsets(nums, index + 1)
    } else {
      mutableListOf()
    }.flatMap {
      listOf(it, it.plus(nums[index]))
    }.toMutableList()

    return children.also { it.add(me) }
  }
}

fun main() {
  println(Subsets().subsets(intArrayOf(1, 2, 3)))
}