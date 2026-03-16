package peckb1.leetcode.easy

class MergeTwoSortedLinkedLists {
  fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) return null
    if (list1 == null) return list2
    if (list2 == null) return list1

    var a = list1
    var b = list2

    val discardableRoot = ListNode(-1)
    var tailOfFinalList: ListNode? = discardableRoot

    while(a != null || b != null) {
      when {
        a == null -> {
          tailOfFinalList?.next = ListNode(b!!.`val`)
          b = b!!.next
        }
        b == null -> {
          tailOfFinalList?.next = ListNode(a.`val`)
          a = a.next
        }
        a.`val` < b.`val` -> {
          tailOfFinalList?.next = ListNode(a.`val`)
          a = a.next
        }
        else -> {
          tailOfFinalList?.next = ListNode(b.`val`)
          b = b.next
        }
      }
      tailOfFinalList = tailOfFinalList?.next
    }

    return discardableRoot.next
  }

  class ListNode(var `val`: Int) {
    var next: ListNode? = null
  }
}