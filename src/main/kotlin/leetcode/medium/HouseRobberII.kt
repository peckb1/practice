package peckb1.leetcode.medium

import kotlin.math.max

class HouseRobberII {
  typealias House = Int
  typealias Profit = Int

  val maxProfitCache = mutableMapOf<Pair<House, Boolean>, Profit>()

  fun rob(houseProfits: IntArray): Int {
    if (houseProfits.isEmpty()) return 0
    if (houseProfits.size == 1) return houseProfits.first()

    val robFirstHouse = rob(0, houseProfits, true)
    val robSecondHouse = rob(1, houseProfits, false)

    return max(robFirstHouse, robSecondHouse)
  }

  private fun rob(currentHouseIndex: Int, houseProfits: IntArray, robbedFirstHouse: Boolean): Int {
    if (currentHouseIndex + 1 >= houseProfits.size) {
      if (robbedFirstHouse) {
        return 0
      } else {
        return houseProfits[currentHouseIndex]
      }
    }
    if (currentHouseIndex + 2 >= houseProfits.size) {
      if (robbedFirstHouse) {
        return houseProfits[currentHouseIndex]
      } else {
        return max(houseProfits[currentHouseIndex], rob(currentHouseIndex + 1, houseProfits, robbedFirstHouse))
      }
    }

    if (maxProfitCache.containsKey(currentHouseIndex to robbedFirstHouse)) return maxProfitCache[currentHouseIndex to robbedFirstHouse]!!

    val robThisHouse = houseProfits[currentHouseIndex] + rob(currentHouseIndex + 2, houseProfits, robbedFirstHouse)
    val skipThisHouse = if (!(robbedFirstHouse && currentHouseIndex == 0)) {
      rob(currentHouseIndex + 1, houseProfits, robbedFirstHouse)
    } else {
      0
    }

    return max(robThisHouse, skipThisHouse).also {
      maxProfitCache[currentHouseIndex to robbedFirstHouse] = it
    }
  }
}
