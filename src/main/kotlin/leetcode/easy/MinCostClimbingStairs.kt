package peckb1.leetcode.easy

import kotlin.math.min

class MinCostClimbingStairs {
  typealias Stair = Int
  typealias Cost = Int

  val minCostCache = mutableMapOf<Stair, Cost>()

  fun minCostClimbingStairs(cost: IntArray): Int {
    val destination = cost.size

    val oneStep = minCostClimbingStairs(0, destination, cost)
    val twoStep = minCostClimbingStairs(1, destination, cost)

    return min(oneStep, twoStep)
  }

  private fun minCostClimbingStairs(currentStep: Int, destination: Int, cost: IntArray): Int {
    if (currentStep + 1 == destination) return cost[currentStep]
    if (currentStep + 2 == destination) return cost[currentStep]

    if (minCostCache.containsKey(currentStep)) return minCostCache[currentStep]!!

    val oneStep = minCostClimbingStairs(currentStep + 1, destination, cost)
    val twoStep = minCostClimbingStairs(currentStep + 2, destination, cost)

    val myCost = cost[currentStep] + min(oneStep, twoStep)
    return myCost.also { minCostCache[currentStep] = it }
  }
}
