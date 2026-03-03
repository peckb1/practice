package peckb1.leetcode.medium

import kotlin.math.max

class HouseRobber {
  typealias House = Int
  typealias Profit = Int

  val maxProfitCache = mutableMapOf<House, Profit>()

  fun rob(houseProfits: IntArray): Int {
    if (houseProfits.isEmpty()) return 0
    if (houseProfits.size == 1) return houseProfits.first()

    val robFirstHouse = rob(0, houseProfits)
    val robSecondHouse = rob(1, houseProfits)

    return max(robFirstHouse, robSecondHouse)
  }

  private fun rob(currentHouseIndex: Int, houseProfits: IntArray): Int {
    if (currentHouseIndex + 1 >= houseProfits.size) return houseProfits[currentHouseIndex]
    if (currentHouseIndex + 2 >= houseProfits.size) return houseProfits[currentHouseIndex]

    if (maxProfitCache.containsKey(currentHouseIndex)) return maxProfitCache[currentHouseIndex]!!

    val robThisHouse = houseProfits[currentHouseIndex] + rob(currentHouseIndex + 2, houseProfits)
    val skipThisHouse = rob(currentHouseIndex + 1, houseProfits)

    return max(robThisHouse, skipThisHouse).also {
      maxProfitCache[currentHouseIndex] = it
    }

  }
}