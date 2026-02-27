package peckb1.leetcode.medium


class AddTwoNumbers {
  fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null || l2 == null) return null

    var l1Next = l1
    var l2Next = l2
    var sumString = ""
    var carry = 0

    while (l1Next != null || l2Next != null) {
      val nextIntBase = (l1Next?.`val` ?: 0) + (l2Next?.`val` ?: 0) + carry

      val nextInt = if (nextIntBase in 0..9) {
        carry = 0
        nextIntBase
      } else {
        carry = 1
        nextIntBase - 10
      }

      sumString += nextInt.toString()

      l1Next = l1Next?.next
      l2Next = l2Next?.next
    }

    if (carry == 1) sumString += 1

    val initial: ListNode? = null
    return sumString.reversed().toCharArray().fold(initial) { child, me ->
      ListNode(me.digitToInt()).apply { next = child }
    }
  }
}

class ListNode(var `val`: Int) {
  var next: ListNode? = null

  override fun toString(): String {
    return "${`val`}, $next"
  }
}
