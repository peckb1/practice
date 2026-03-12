package peckb1.leetcode.medium

class KokoEatingBananas {
  fun minEatingSpeed(piles: IntArray, hours: Int): Int {
    var minSpeed = 1
    var maxSpeed = piles.max()

    while (minSpeed < maxSpeed) {
      val midSpeed = minSpeed + (maxSpeed - minSpeed) / 2
      var hoursNeeded = 0L

      for (pile in piles) {
        // ceiling division to calculate hours needed for this pile
        hoursNeeded += (pile + midSpeed - 1) / midSpeed
      }

      if (hoursNeeded <= hours) {
        // speed works → try slower speed
        maxSpeed = midSpeed
      } else {
        // too slow → must increase speed
        minSpeed = midSpeed + 1
      }
    }

    return minSpeed
  }
}