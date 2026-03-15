package peckb1.leetcode.medium

class DailyTemperatures {
  fun dailyTemperatures(temperatures: IntArray): IntArray {
    val result = IntArray(temperatures.size)
    val stack = ArrayDeque<Int>()   // stores indices

    for (temperatureIndex in temperatures.indices) {
      while (stack.isNotEmpty() && temperatures[temperatureIndex] > temperatures[stack.last()]) {
        val prevIndex = stack.removeLast()
        result[prevIndex] = temperatureIndex - prevIndex
      }
      stack.addLast(temperatureIndex)
    }

    return result
  }
}
