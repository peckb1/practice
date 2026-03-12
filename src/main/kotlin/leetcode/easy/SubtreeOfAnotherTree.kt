package peckb1.leetcode.easy

class SubtreeOfAnotherTree {
  fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    return root?.contains(subRoot) ?: false
  }

  fun TreeNode.contains(other: TreeNode?): Boolean {
    if (other == null) return true

    val a = this.isTheSame(other)
    val b = this.left?.contains(other) ?: false
    val c = this.right?.contains(other) ?: false

    return a || b || c
  }

  private fun TreeNode?.isTheSame(other: TreeNode?): Boolean {
    if (this == null && other == null) return true

    return other?.`val` == this?.`val`
      && this?.left.isTheSame(other?.left)
      && this?.right.isTheSame(other?.right)
  }

//  data class TreeNode(var `val`: Int) {
//    var left: TreeNode? = null
//    var right: TreeNode? = null
//  }
}

data class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}

fun main() {
  val root = TreeNode(1).apply {
    left = TreeNode(2).apply {
      left = TreeNode(4).apply {
        left = TreeNode(6)
      }
      right = TreeNode(5)
    }
    right = TreeNode(3)
  }

  val subRoot = TreeNode(2).apply {
    left = TreeNode(4)
    right = TreeNode(5)
  }

  println(SubtreeOfAnotherTree().isSubtree(root, subRoot))
}