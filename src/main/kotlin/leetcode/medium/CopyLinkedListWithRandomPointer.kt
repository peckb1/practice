package peckb1.leetcode.medium

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

fun main() {
  print(CopyLinkedListWithRandomPointer().copyRandomList(buildExample()))
}

fun buildExample(): Node {
  val n1 = Node(7)
  val n2 = Node(13)
  val n3 = Node(11)
  val n4 = Node(10)

  // next pointers (the main list)
  n1.next = n2
  n2.next = n3
  n3.next = n4

  // random pointers
  n1.random = n3   // 7 -> 11
  n2.random = n1   // 13 -> 7
  n3.random = n4   // 11 -> 10
  n4.random = n2   // 10 -> 13

  return n1 // head
}