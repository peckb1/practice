package peckb1.leetcode.hard

import leetcode.data.ListNode
import java.util.PriorityQueue

class MergeKSortedLinkedLists {
  fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null
    if (lists.size == 1) return lists[0]

    val discardableRoot = ListNode(-1)
    var next: ListNode? = discardableRoot

    while(next != null) {
      val arrayWithMinValue = lists
        .asSequence()
        .withIndex()
        .filterNot { it.value == null }
        .minByOrNull { it.value!!.`val` }

      if (arrayWithMinValue == null) {
        next = null
      } else {
        val minItem: ListNode = lists[arrayWithMinValue.index]!!
        lists[arrayWithMinValue.index] = minItem.next

        next.next = minItem
        next = next.next
      }
    }

    return discardableRoot.next
  }


  fun mergeKListsQueue(lists: Array<ListNode?>): ListNode? {
    val queue = PriorityQueue<ListNode> { a, b -> a.`val` - b.`val` }
      .apply { lists.forEach { it?.also { add(it) } } }

    val discardableRoot = ListNode(-1)
    var current: ListNode? = discardableRoot

    // Process the heap
    while (queue.isNotEmpty()) {
      val minNode = queue.poll()
      current?.next = minNode
      current = current?.next

      minNode.next?.also { queue.add(it) }
    }

    return discardableRoot.next
  }
}
