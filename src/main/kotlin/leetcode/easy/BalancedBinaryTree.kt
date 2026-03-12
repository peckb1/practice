package peckb1.leetcode.easy

import kotlin.math.abs

class BalancedBinaryTree {
  fun isBalanced(root: TreeNode?): Boolean {
    return root.balanced()
  }

  private fun TreeNode?.balanced(): Boolean {
    if (this == null) return true

    return abs(left.depth() - right.depth()) <= 1 && left.balanced() && right.balanced()
  }

  private fun TreeNode?.depth(): Int = when (this) {
    null -> 0
    else -> 1 + maxOf(left.depth(), right.depth())
  }

  data class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}