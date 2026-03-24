package leetcode.hard

import leetcode.data.ListNode

class ReverseNodesInKGroup {
  fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    if (k == 0) return head
    if (head == null) return null

    val discardableRoot = ListNode(-1)
      .apply { next = head }

    var parent: ListNode? = discardableRoot
    var leftPointer = head
    var rightPointer = head

    while(rightPointer != null) {
      var counter = 0
      while (counter < k) {
        if (rightPointer == null) {
          counter = k + 1
        } else {
          rightPointer = rightPointer.next
        }
        counter++
      }

      if (counter == k) { // we'd be larger if we ran off the edge of the list
        val items = reverseInPlace(leftPointer, k)

        parent?.next = items
        leftPointer?.next = rightPointer

        parent = leftPointer
        leftPointer = leftPointer?.next
      }
    }

    return discardableRoot.next
  }

  fun reverseInPlace(head: ListNode?, maxHops: Int): ListNode? {
    var prev: ListNode? = null
    var curr = head

    var hops = 0
    while (curr != null && hops < maxHops) {
      val nextTemp = curr.next
      curr.next = prev
      prev = curr
      curr = nextTemp
      hops++
    }

    return prev
  }
}
