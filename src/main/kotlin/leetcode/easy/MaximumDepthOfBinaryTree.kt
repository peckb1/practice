package peckb1.leetcode.easy

class MaximumDepthOfBinaryTree {
  fun maxDepth(root: TreeNode?) = root.depth()

  private fun TreeNode?.depth(): Int = when (this) {
    null -> 0
    else -> 1 + maxOf(left.depth(), right.depth())
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}
