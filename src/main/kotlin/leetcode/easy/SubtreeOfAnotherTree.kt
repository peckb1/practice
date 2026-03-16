package peckb1.leetcode.easy

import leetcode.data.TreeNode

class SubtreeOfAnotherTree {
  fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    return root?.contains(subRoot) ?: false
  }

  fun TreeNode.contains(other: TreeNode?): Boolean {
    if (other == null) return true

    val a = this.isTheSame(other)
    val b = this.left?.contains(other) ?: false
    val c = this.right?.contains(other) ?: false

    return a || b || c
  }

  private fun TreeNode?.isTheSame(other: TreeNode?): Boolean {
    if (this == null && other == null) return true

    return other?.`val` == this?.`val`
      && this?.left.isTheSame(other?.left)
      && this?.right.isTheSame(other?.right)
  }
}
