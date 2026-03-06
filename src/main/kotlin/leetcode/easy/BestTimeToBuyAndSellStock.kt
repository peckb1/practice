package peckb1.leetcode.easy

class BestTimeToBuyAndSellStock {
  fun maxProfit(prices: IntArray): Int {
    var lowestPrice: Int = Int.MAX_VALUE
    var maxProfit = 0

    for (i in prices.indices) {
      lowestPrice = minOf(lowestPrice, prices[i])

      val profit = prices[i] - lowestPrice

      maxProfit = maxOf(maxProfit, profit)
    }

    return maxProfit
  }
}