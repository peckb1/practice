package peckb1.leetcode.easy

import leetcode.data.TreeNode

class InvertBinaryTree {
  fun invertTree(root: TreeNode?): TreeNode? {
    return root?.invert()
  }

  private fun TreeNode.invert() : TreeNode = apply {
    val temp = left?.invert()

    left = right?.invert()
    right = temp
  }
}