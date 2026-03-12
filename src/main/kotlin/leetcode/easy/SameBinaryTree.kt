package peckb1.leetcode.easy

class SameBinaryTree {
  fun isSameTree(p: TreeNode?, q: TreeNode?) = p.isTheSame(q)

  private fun TreeNode?.isTheSame(other: TreeNode?): Boolean {
    if (this == null && other == null) return true

    return other?.`val` == this?.`val`
      && this?.left.isTheSame(other?.left)
      && this?.right.isTheSame(other?.right)
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}

fun main() {
  println(SameBinaryTree().isSameTree(null, null))
}