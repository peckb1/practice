package peckb1.leetcode.medium


class EvaluateReversePolishNotation {
  fun evalRPN(tokens: Array<String>): Int {
    when {
      tokens.isEmpty() -> return 0
      tokens.size == 1 -> return tokens[0].toInt()
    }

    val values = ArrayDeque<Int>()

    tokens.forEach { token ->
      val value = token.toIntOrNull()
      if (value == null) {
        val operand = Operand.from(token)
        val b = values.removeLast()
        val a = values.removeLast()
        values.addLast(operand.operation(a, b))
      } else {
        values.addLast(value)
      }
    }

    return values.last()
  }

  enum class Operand(val operation: (Int, Int) -> Int) {
    ADD(Int::plus),
    SUB(Int::minus),
    MULT(Int::times),
    DIV(Int::div);

    companion object {
      val operands = mapOf(
        "+" to ADD,
        "-" to SUB,
        "*" to MULT,
        "/" to DIV,
      )

      fun from(s: String): Operand {
        return when (s) {
          "+" -> ADD
          "-" -> SUB
          "*" -> MULT
          "/" -> DIV
          else -> throw IllegalArgumentException("Invalid operation: $s")
        }
      }
    }
  }
}

fun main() {
  println(EvaluateReversePolishNotation().evalRPN(arrayOf("1","2","+","3","*","4","-")))
}