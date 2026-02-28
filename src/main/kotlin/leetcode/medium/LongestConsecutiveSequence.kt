package peckb1.leetcode.medium

class LongestConsecutiveSequence {
  class Node(val num: Int) {
    val children = mutableListOf<Node>()

    fun addChild(node: Node) {
      children.add(node)
    }

    val longestChain: Int by lazy {
      1 + (children.maxOfOrNull { it.longestChain } ?: 0)
    }
  }

  fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) return 0

    val nodeMap = mutableMapOf<Int, MutableList<Node>>()

    nums.sortedBy{ it }.forEach { num ->
      val node = Node(num)

      nodeMap.getOrPut(num) { mutableListOf() }.add(node)

      nodeMap[num - 1]?.let { n -> n.forEach { it.addChild(node) }}
    }

    return nodeMap.values.maxOf {
      it.maxOf {
        it.longestChain
      }
    }
  }
}
