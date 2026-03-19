package leetcode.medium

class CopyLinkedListWithRandomPointer {
  fun copyRandomList(head: Node?): Node? {
    if (head == null) return null

    val randoms = mutableMapOf<Int, Node?>()
    val newHead = Node(head.`val`).also { randoms[head.hashCode()] = it }

    var newNext: Node? = newHead
    var next = head.next

    // build next chain
    while (next != null) {
      val n = Node(next.`val`).also { randoms[next.hashCode()] = it }

      newNext?.next = n
      newNext = newNext?.next
      next = next.next
    }

    // reassign to loop from the start again
    newNext = newHead
    next = head

    while (next != null) {
      val random = next.random
      newNext?.random = if (random != null) randoms[random.hashCode()] else null

      next = next.next
      newNext = newNext?.next
    }

    return newHead
  }
}
class Node(var `val`: Int) {
  var next: Node? = null
  var random: Node? = null

  override fun toString(): String {
    return "${`val`}, $next"
  }
}
