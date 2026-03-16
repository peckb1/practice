package peckb1.leetcode.easy

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

  class ListNode(var `val`: Int) {
    var next: ListNode? = null
  }
}