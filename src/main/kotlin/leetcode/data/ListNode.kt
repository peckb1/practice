package leetcode.data

class ListNode(var `val`: Int) {
  var next: ListNode? = null

  override fun toString(): String {
    return "${`val`}, $next"
  }

  companion object {
    fun buildList(values: List<Int>): ListNode? {
      if (values.isEmpty()) return null

      val head = ListNode(values[0])
      var current = head

      for (i in 1 until values.size) {
        current.next = ListNode(values[i])
        current = current.next!!
      }

      return head
    }
  }
}