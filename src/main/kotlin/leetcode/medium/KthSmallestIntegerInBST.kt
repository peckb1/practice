package peckb1.leetcode.medium

class KthSmallestIntegerInBST {
  fun kthSmallest(root: TreeNode?, k: Int): Int {
    if (root == null) return 0
    if (k == 0) return 0

    val stack = ArrayDeque<TreeNode>()
    var currentNode = root
    var currentKthSmallestFound = 0

    while (currentNode != null || stack.isNotEmpty()) {
      // go all the way left!
      while (currentNode != null) {
        stack.addLast(currentNode)
        currentNode = currentNode.left
      }

      // we hit the bottom!
      // go one back :D
      currentNode = stack.removeLast()
      currentKthSmallestFound++

      if (currentKthSmallestFound == k) return currentNode.`val`

      // we didn't find it yet. so let's "go right"
      // if this is null above we'll skip the "go left from it" section
      // and then end up pulling from our stack of "parents
      currentNode = currentNode.right
    }

    return -1
  }

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}