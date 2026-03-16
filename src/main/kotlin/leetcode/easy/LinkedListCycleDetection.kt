package peckb1.leetcode.easy

import leetcode.data.ListNode

class LinkedListCycleDetection {
  /*
   * Floyd’s Tortoise and Hare algorithm
   */
  fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head

    while (fast?.next != null) {
      slow = slow?.next
      fast = fast.next?.next

      if (slow === fast) return true
    }

    return false
  }
}