package peckb1.leetcode.hard

class BinaryTreeMaximumPathSum {
  fun maxPathSum(root: TreeNode?): Int {
    return root?.maxPathOptions()?.let { (a, b, c) -> maxOf(a, b, c) } ?: 0
  }

  fun TreeNode?.maxPathOptions(): Options {
    if (this == null) return Options(-5000, -5000, -5000)

    val (maxLeftChild, soloLeft, usingLeft)   = this.left.maxPathOptions()
    val (maxRightChild, soloRight, usingRight) = this.right.maxPathOptions()

    return Options(
      maxChild = maxOf(maxLeftChild, soloLeft, usingLeft, maxRightChild, soloRight, usingRight),
      maxContainedValue = (usingLeft + this.`val` + usingRight),
      maxWithAvailableConnection = maxOf(
        usingLeft + this.`val`,
        usingRight + this.`val`,
        this.`val`
      )
    )
  }

  data class Options(val maxChild: Int, val maxContainedValue: Int, val maxWithAvailableConnection: Int)

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}

