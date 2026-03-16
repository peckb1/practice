package peckb1.leetcode.medium

import leetcode.data.ListNode


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
