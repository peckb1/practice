package leetcode.medium

class BestTimeToBuyAndSellStockWithCooldown {
  fun maxProfitDP(prices: IntArray): Int {
    var hold = Int.MIN_VALUE
    var sold = 0
    var rest = 0

    for (price in prices) {
      val prevSold = sold
      sold = hold + price
      hold = maxOf(hold, rest - price)
      rest = maxOf(rest, prevSold)
    }

    return maxOf(sold, rest)
  }

  fun maxProfit(prices: IntArray): Int {
    return max(prices)
  }

  private fun max(
    prices: IntArray,
    day: Int = 0,
    ownCoin: Boolean = false,
    onCooldown: Boolean = false,
    memoizations: MutableMap<Triple<Int, Boolean, Boolean>, Int> = mutableMapOf(),
  ): Int {
    if (day >= prices.size) return 0

    val key = Triple(day, ownCoin, onCooldown)
    memoizations[key]?.let { return it }

    val result = if (ownCoin) {
      // if sell
      val sell = prices[day] + max(
        prices = prices,
        day = day + 1,
        ownCoin = false,
        onCooldown = true,
        memoizations = memoizations,
      )
      // if hold for a different day
      val hold = max(
        prices = prices,
        day = day + 1,
        ownCoin = true,
        onCooldown = false,
        memoizations = memoizations,
      )

      maxOf(sell, hold)
    } else {
      // if purchase
      val buy =
        if (onCooldown) Int.MIN_VALUE
        else -prices[day] + max(
          prices = prices,
          day = day + 1,
          ownCoin = true,
          onCooldown = false,
          memoizations = memoizations,
        )
      // if wait before purchasing
      val wait = max(
        prices = prices,
        day = day + 1,
        ownCoin = false,
        onCooldown = false,
        memoizations = memoizations,
      )

      maxOf(buy, wait)
    }

    memoizations[key] = result
    return result
  }
}
