package peckb1.leetcode.easy

class ClimbingStairs {
  typealias Stair = Int
  typealias Count = Int

  val stairsCache = mutableMapOf<Stair, Count>()

  fun climbStairs(n: Int): Int {
    if (n == 0) return 0
    if (n == 1) return 1
    if (n == 2) return 2

    if (stairsCache.containsKey(n)) return stairsCache[n]!!

    return (climbStairs(n - 1) + climbStairs(n - 2)).also {
      stairsCache[n] = it
    }
  }
}