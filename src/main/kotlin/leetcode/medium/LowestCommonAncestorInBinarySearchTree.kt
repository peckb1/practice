package peckb1.leetcode.medium

class LowestCommonAncestorInBinarySearchTree {
  tailrec fun lowestCommonAncestor(root: TreeNode?, p: TreeNode, q: TreeNode): TreeNode? {
    if (root == null) return null

    if (root.`val` == p.`val` || root.`val` == q.`val`) return root

    val lowest  = if (p.`val` < q.`val`) { p } else { q }
    val highest = if (p.`val` < q.`val`) { q } else { p }

    return when {
      lowest.`val` < root.`val` && highest.`val` < root.`val`  -> lowestCommonAncestor(root.left, lowest, highest)
      lowest.`val` > root.`val` /* & highest.val > root.val */ -> lowestCommonAncestor(root.right, lowest, highest)
      else -> root
    }
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}