package leetcode.easy

import leetcode.data.TreeNode

class MaximumDepthOfBinaryTree {
  fun maxDepth(root: TreeNode?) = root.depth()

  private fun TreeNode?.depth(): Int = when (this) {
    null -> 0
    else -> 1 + maxOf(left.depth(), right.depth())
  }
}
