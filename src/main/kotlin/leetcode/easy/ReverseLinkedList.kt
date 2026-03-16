package peckb1.leetcode.easy

class ReverseLinkedList {
  fun reverseList(head: ListNode?): ListNode? {
    if (head == null) return head
    if (head.next == null) return head

    var last = ListNode(head.`val`)
    var next = head.next
    while (next != null) {
      val newItem = ListNode(next.`val`).apply { this.next = last }
      last = newItem
      next = next.next
    }

    return last
  }

  fun reverseInPlace(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var curr = head

    while (curr != null) {
      val nextTemp = curr.next
      curr.next = prev
      prev = curr
      curr = nextTemp
    }

    return prev
  }

  class ListNode(var `val`: Int) {
    var next: ListNode? = null
  }
}

