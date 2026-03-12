package peckb1.leetcode.easy

class InvertBinaryTree {
  fun invertTree(root: TreeNode?): TreeNode? {
    return root?.invert()
  }

  private fun TreeNode.invert() : TreeNode = apply {
    val temp = left?.invert()

    left = right?.invert()
    right = temp
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}