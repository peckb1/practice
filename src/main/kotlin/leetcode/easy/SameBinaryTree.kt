package peckb1.leetcode.easy

import leetcode.data.TreeNode

class SameBinaryTree {
  fun isSameTree(p: TreeNode?, q: TreeNode?) = p.isTheSame(q)

  private fun TreeNode?.isTheSame(other: TreeNode?): Boolean {
    if (this == null && other == null) return true

    return other?.`val` == this?.`val`
      && this?.left.isTheSame(other?.left)
      && this?.right.isTheSame(other?.right)
  }
}
