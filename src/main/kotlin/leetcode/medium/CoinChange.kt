package peckb1.leetcode.medium

class CoinChange {
  typealias Index = Int
  typealias Amount = Int
  typealias Coins = Int

  fun coinChange(coins: IntArray, amount: Int): Int {
    if (amount == 0) return 0
    if (coins.isEmpty()) return -1

    coins.sort()

    return coinChange(coins.lastIndex, amount, coins) ?: -1
  }

  private fun coinChange(
    currentCoinIndex: Int,
    totalAmount: Int,
    coins: IntArray,
    cache: MutableMap<Pair<Index, Amount>, Coins?> = mutableMapOf()
  ): Int? {
    if (totalAmount == 0) return null
    if (currentCoinIndex < 0) return null
    if (cache.contains(currentCoinIndex to totalAmount)) return cache[currentCoinIndex to totalAmount]

    val myAmount = coins[currentCoinIndex]
    if (myAmount == 1) return totalAmount
    if (totalAmount % myAmount == 0) return (totalAmount / myAmount).also {
      cache[currentCoinIndex to totalAmount] = it
    }

    val maxPossible = totalAmount / myAmount
    val myCounts = (0 .. maxPossible).mapNotNull { myCount ->
      val theirCount = coinChange(currentCoinIndex - 1, totalAmount - (myCount * myAmount), coins, cache)
      theirCount?.let { myCount to theirCount }
    }

    val bestCount = myCounts.minOfOrNull { (myCount, theirCount) ->
      myCount + theirCount
    }

    return bestCount.also { cache[currentCoinIndex to totalAmount] = it }
  }
}
