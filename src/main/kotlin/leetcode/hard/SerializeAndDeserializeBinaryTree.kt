package peckb1.leetcode.hard

class Codec {
  fun serialize(root: TreeNode?): String {
    if (root == null) return ""

    val stack = ArrayDeque<MaybeTreeNode>().apply { add(MaybeTreeNode(root)) }

    return buildString {
      while (stack.isNotEmpty()) {
        val next = stack.removeFirst().also { append(it.node?.`val`) }

        if (next.node != null) {
          stack.add(MaybeTreeNode(next.node.left))
          stack.add(MaybeTreeNode(next.node.right))
        }
        if (stack.isNotEmpty()) { append(',') }
      }
    }
  }

  fun deserialize(data: String): TreeNode? {
    if (data.isBlank()) return null
    val items = data.split(',')
    if (items.isEmpty()) return null

    var currentIndex = 0
    val root = TreeNode(items[currentIndex++].toInt())
    val stack = ArrayDeque<TreeNode>().apply {
      root.left  = items[currentIndex++].toIntOrNull()?.let(::TreeNode)?.also { add(it) }
      root.right = items[currentIndex++].toIntOrNull()?.let(::TreeNode)?.also { add(it) }
    }

    while (stack.isNotEmpty()) {
      val next = stack.removeFirst()
      next.left = items[currentIndex++].toIntOrNull()?.let(::TreeNode)?.also { stack.add(it) }
      next.right = items[currentIndex++].toIntOrNull()?.let(::TreeNode)?.also { stack.add(it) }
    }

    return root
  }

  // Wrapper class as the implementation of ArrayDeque can sometimes not allow `null` values to be placed
  class MaybeTreeNode(val node: TreeNode?)

  class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
  }
}
