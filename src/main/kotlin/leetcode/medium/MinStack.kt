package peckb1.leetcode.medium

class MinStack() {
  data class Element(val value: Int, val minInStack: Int)

  private val stack = ArrayDeque<Element>()

  fun push(`val`: Int) {
    val min = minOf(`val`, stack.lastOrNull()?.value ?: Int.MAX_VALUE)

    stack.addLast(Element(`val`, min))
  }

  fun pop() = stack.removeLast()

  fun top(): Int = stack.last().value

  fun getMin(): Int = stack.last().minInStack
}