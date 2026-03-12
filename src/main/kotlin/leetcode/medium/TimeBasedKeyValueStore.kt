package peckb1.leetcode.medium

import java.util.SortedSet

class TimeMap() {

  companion object {
    val eventComparator = Comparator<Event> { e1, e2 -> e1.timestamp.compareTo(e2.timestamp) }
  }

  val events = mutableMapOf<String, SortedSet<Event>>()

  fun set(key: String, value: String, timestamp: Int) {
    val event = Event(value, timestamp)
    events.getOrPut(key) { sortedSetOf(eventComparator) }.add(event)
  }

  fun get(key: String, timestamp: Int): String {
    val events = events[key] ?: return ""

    var lowIndex = events.indices.first
    var highIndex = events.indices.last

    while (lowIndex <= highIndex) {
      val midIndex = lowIndex + (highIndex - lowIndex) / 2
      val mid = events.elementAt(midIndex)

      when {
        mid.timestamp < timestamp -> lowIndex = midIndex + 1
        mid.timestamp > timestamp -> highIndex = midIndex - 1
        else -> return mid.value
      }
    }

    // we didn't find it. so we need to show the last item before our timestamp, or ""
    // STATES:
    // 1. We kept lowering the high index, hit the bottom, and nothing was there
    //    so there was nothing before our timestamp
    if (lowIndex == 0) return ""
    // 2. We at some point raised the lower index, so there is going to be some value
    //    that was before us, take that one instead
    return events.elementAt(lowIndex - 1).value
  }

  data class Event(val value: String, val timestamp: Int)
}
