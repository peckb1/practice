package peckb1.leetcode.medium


class RemoveNodeFromEndOfLinkedList {
  fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    val discardableRoot = ListNode(-1)
    discardableRoot.next = head

    var front: ListNode? = discardableRoot
    var back: ListNode? = discardableRoot

    repeat(n + 1) {
      front = front?.next
    }

    while (front != null) {
      front = front?.next
      back = back?.next
    }

    back?.next = back?.next?.next

    return discardableRoot.next
  }
}

class ListNode(var `val`: Int) {
  var next: ListNode? = null

  override fun toString(): String {
    return "${`val`}, $next"
  }
}

fun main() {
  val head = ListNode(1)
  head.next = ListNode(2)
  head.next!!.next = ListNode(3)
  head.next!!.next!!.next = ListNode(4)
  head.next!!.next!!.next!!.next = ListNode(5)

  val n = 2

  val result = RemoveNodeFromEndOfLinkedList().removeNthFromEnd(head, n)

  println(result)

}