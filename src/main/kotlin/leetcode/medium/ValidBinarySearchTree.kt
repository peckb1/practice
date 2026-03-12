package peckb1.leetcode.medium

class ValidBinarySearchTree {
  fun isValidBST(root: TreeNode?): Boolean {
    if (root == null) return false

    return (root.left == null  || isValidBST(root.left,  max = root.`val`,    min = Int.MIN_VALUE))
        && (root.right == null || isValidBST(root.right, max = Int.MAX_VALUE, min = root.`val`))
  }

  private fun isValidBST(root: TreeNode?, max: Int, min: Int): Boolean {
    if (root == null) return true

    return min < root.`val` && root.`val` < max
      && (root.left == null  || isValidBST(root.left,  max = root.`val`, min))
      && (root.right == null || isValidBST(root.right, max = max, min = root.`val`))
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}
