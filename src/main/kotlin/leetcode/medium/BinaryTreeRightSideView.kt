package peckb1.leetcode.medium

import leetcode.data.TreeNode

class BinaryTreeRightSideView {
  fun rightSideView(root: TreeNode?): List<Int> {
    if (root == null) return emptyList()

    return BinaryTreeLevelOrderTraversal().levelOrder(root)
      .map { it.last() }
  }
}