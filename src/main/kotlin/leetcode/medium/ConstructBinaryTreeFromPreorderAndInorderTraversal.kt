package peckb1.leetcode.medium

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) return null

    val rootVal = preorder[0]
    val root = TreeNode(rootVal)

    // index of every time ... ??
    val mid = inorder.indexOf(rootVal)

    val leftInorder = inorder.sliceArray(0 until mid)
    val rightInorder = inorder.sliceArray(mid + 1 until inorder.size)

    val leftPreorder = preorder.sliceArray(1 until 1 + mid)
    val rightPreorder = preorder.sliceArray(1 + mid until preorder.size)

    root.left = buildTree(leftPreorder, leftInorder)
    root.right = buildTree(rightPreorder, rightInorder)

    return root
  }


  fun buildTreeFancy(preorder: IntArray, inorder: IntArray): TreeNode? {
    val indexMap = inorder.withIndex().associate { it.value to it.index }

    fun build(preOrderStartIndex: Int, inOrderStartIndex: Int, inOrderEndIndex: Int): TreeNode? {
      if (preOrderStartIndex >= preorder.size || inOrderStartIndex > inOrderEndIndex) return null

      val rootVal = preorder[preOrderStartIndex]
      val root = TreeNode(rootVal)

      val inOrderMidIndex = indexMap[rootVal]!!
      val leftSize = inOrderMidIndex - inOrderStartIndex

      root.left  = build(preOrderStartIndex + 1,            inOrderStartIndex = inOrderStartIndex, inOrderEndIndex = inOrderMidIndex - 1)
      root.right = build(preOrderStartIndex + leftSize + 1, inOrderStartIndex = inOrderMidIndex + 1,      inOrderEndIndex = inOrderEndIndex)

      return root
    }

    return build(0, 0, inorder.lastIndex)
  }


  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}

fun main() {
  val preorder = intArrayOf(8, 4, 2, 6, 5, 7, 12, 10, 14)
  val inorder  = intArrayOf(2, 4, 5, 6, 7, 8, 10, 12, 14)

  println(ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTreeFancy(preorder, inorder))
}