package peckb1.leetcode.medium

class BinaryTreeLevelOrderTraversal {
  fun levelOrder(root: TreeNode?): List<List<Int>> {
    val nodesByDepth = mutableMapOf<Int, MutableList<Int>>()

    if (root == null) return emptyList()
    updateMap(root, nodesByDepth)

    return nodesByDepth.values.toList()
  }

  private fun updateMap(
    root: TreeNode,
    nodesByDepth: MutableMap<Int, MutableList<Int>>,
    depth: Int = 1
  ) {
    nodesByDepth.getOrPut(depth) { mutableListOf() }.add(root.`val`)
    root.left?.also { updateMap(it, nodesByDepth, depth + 1) }
    root.right?.also { updateMap(it, nodesByDepth, depth + 1) }
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}