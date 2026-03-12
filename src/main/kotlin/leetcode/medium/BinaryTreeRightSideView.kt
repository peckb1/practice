package peckb1.leetcode.medium

class BinaryTreeRightSideView {
  fun rightSideView(root: BinaryTreeLevelOrderTraversal.TreeNode?): List<Int> {
    if (root == null) return emptyList()

    return BinaryTreeLevelOrderTraversal().levelOrder(root)
      .map { it.last() }
  }
}