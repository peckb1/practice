package peckb1.leetcode.medium

typealias StartingPosition = Int
typealias ArrivalTime = Double

class CarFleet {
  val startPositionToArrivalTimes = mutableMapOf<StartingPosition, ArrivalTime>()

  fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    position.indices.forEach { i ->
      val distanceToTravel = (target - position[i]).toDouble()
      val speed = speed[i]
      val arrivalTime = distanceToTravel / speed
      startPositionToArrivalTimes[position[i]] = arrivalTime
    }

    val sortedTimes = startPositionToArrivalTimes.toSortedMap { startPos1, startPos2 ->
      startPos2.compareTo(startPos1) // reverse comparison to get descending
    }

    var fleets = 0
    var lastFleetArrival = 0.0
    for ((_, arrivalTime) in sortedTimes) {
      if (arrivalTime > lastFleetArrival) {
        fleets++
        lastFleetArrival = arrivalTime
      }
    }

    return fleets
  }
}

fun main() {
  val target = 10
  val position = intArrayOf(4,1,0,7)
  val speed = intArrayOf(2,2,1,1)

  println(CarFleet().carFleet(target, position, speed))

//  val a = 102
//  val b = 10
//
//  // Works only for positive 'a' and 'b'
//  val result: Int = (a + b - 1) / b
//
//  println("$a / $b (ceiling) = $result") // Output: 102 / 10 (ceiling) = 11
}