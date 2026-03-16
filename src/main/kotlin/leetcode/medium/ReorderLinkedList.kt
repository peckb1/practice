package peckb1.leetcode.medium

import leetcode.data.ListNode

class ReorderLinkedList {
  fun reorderList(head: ListNode?) {
    if (head == null) return
    if (head.next == null) return
    if (head.next?.next == null) return

    // 1. Find middle
    val midWay = findMiddle(head)

    // 2. Reverse second half
    val reversedSecondHalf = reverseSecondHalf(midWay)

    // 3. Merge two lists
    merge(head, reversedSecondHalf)
  }

  private fun merge(head: ListNode, reversedSecondHalf: ListNode?) {
    var mutableFirstList: ListNode? = head
    var mutableSecondList = reversedSecondHalf

    while (mutableSecondList != null) {
      val tmp1 = mutableFirstList?.next
      val tmp2 = mutableSecondList.next

      mutableFirstList?.next = mutableSecondList
      mutableSecondList.next = tmp1

      mutableFirstList = tmp1
      mutableSecondList = tmp2
    }
  }

  private fun reverseSecondHalf(midWay: ListNode?): ListNode? {
    var second = midWay?.next
    midWay?.next = null
    var prev: ListNode? = null

    while (second != null) {
      val next = second.next
      second.next = prev
      prev = second
      second = next
    }

    return prev
  }

  private fun findMiddle(head: ListNode?): ListNode? {
    var slow = head
    var fast = head

    while (fast?.next?.next != null) {
      slow = slow?.next
      fast = fast.next?.next
    }

    return slow
  }
}

