package leetcode.easy

import leetcode.data.TreeNode

class DiameterOfBinaryTree {
  private var maxDiameter = 0

  fun diameterOfBinaryTree(root: TreeNode?): Int {
    maxDiameter
    root?.diameter()
    return maxDiameter
  }

  private fun TreeNode?.diameter(): Int {
    if (this == null) return 0

    val left = left.diameter()
    val right = right.diameter()

    maxDiameter = maxOf(maxDiameter, left + right)

    return 1 + maxOf(left, right)
  }
}
