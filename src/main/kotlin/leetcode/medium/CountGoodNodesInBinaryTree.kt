package peckb1.leetcode.medium

import kotlin.math.max

class CountGoodNodesInBinaryTree {
  fun goodNodes(root: TreeNode?): Int {
    if (root == null) return 0

    val leftGoods  = root.left?.let  { left  -> goodNodeCount(left,  max(root.`val`, left.`val`)) }
    val rightGoods = root.right?.let { right -> goodNodeCount(right, max(root.`val`, right.`val`)) }

    return 1 + (leftGoods ?: 0) + (rightGoods ?: 0)
  }

  private fun goodNodeCount(node: TreeNode, max: Int) : Int {
    val amIGood = node.`val` >= max
    val newMax = max(node.`val`, max)

    val leftGoods  = node.left?.let  { left  -> goodNodeCount(left,  max(newMax, left.`val`)) }
    val rightGoods = node.right?.let { right -> goodNodeCount(right, max(newMax, right.`val`)) }

    return (leftGoods ?: 0) + (rightGoods ?: 0) + (if (amIGood) 1 else 0)
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}