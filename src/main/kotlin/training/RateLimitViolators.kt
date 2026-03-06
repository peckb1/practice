package peckb1.training

import java.util.ArrayDeque
import java.util.Queue
import java.util.TreeSet

/**
 * The rate limit policy is:
 *  - A user may make at most N requests within any W second window.
 *
 * Your task:
 *  - Return all users who violated the rate limit at least once.
 *
 * N = max requests allowed
 * W = window size in seconds
 *
 * logs = [
 *     (timestamp, user_id)
 * ]
 */
typealias UserId = String

class RateLimitViolators {

  companion object {
    // if we've processed a message from time 61, and then a message from time 1 comes it which _would_ have caused a
    // violation we will not catch it. This is our "acceptability" window for not having to store all the logs
    private const val ACCEPTABLE_LOSS_WINDOW_SECONDS = 60
    private val LOG_COMPARATOR = Comparator<Log> { log1, log2 -> log1.timestamp.compareTo(log2.timestamp) }

    /**
     * WARNING: This method will mutate the data structures passed in
     */
    private fun processLog(
      log: Log,
      maxRequests: Int,
      windowTimeInSeconds: Int,
      userLogs: MutableMap<UserId, Queue<Log>>,
    ) : Boolean {
      val existingLogs = userLogs.getOrPut(log.userId) { ArrayDeque<Log>() }
      while (existingLogs.isNotEmpty() && existingLogs.peek().timestamp <= log.timestamp - windowTimeInSeconds) {
        existingLogs.remove() // old log can be purged
      }

      if (existingLogs.size >= maxRequests) {
        userLogs.remove(log.userId)
        return true
      } else {
        existingLogs.add(log)
        return false
      }
    }
  }

  val inOrderLogs = mutableMapOf<UserId, Queue<Log>>()
  val outOfOrderLogs = mutableMapOf<UserId, TreeSet<Log>>()

  fun processLogOutOfOrder(
    log: Log,
    maxRequests: Int,
    windowTimeInSeconds: Int,
  ) : Set<String> {
    val logs = outOfOrderLogs.getOrPut(log.userId) { TreeSet(LOG_COMPARATOR) }

    while(logs.isNotEmpty() && logs.first().timestamp <= log.timestamp - ACCEPTABLE_LOSS_WINDOW_SECONDS) {
      logs.removeFirst()
    }

    val logsWithinWindow = logs.tailSet(Log(log.timestamp - windowTimeInSeconds, log.userId))
    if (logsWithinWindow.size >= maxRequests) {
      return setOf(log.userId)
    } else {
      logs.add(log)
      return emptySet()
    }
  }

  fun processLogInOrder(
    log: Log,
    maxRequests: Int,
    windowTimeInSeconds: Int
  ): Set<String> {

    return if (processLog(log, maxRequests, windowTimeInSeconds, inOrderLogs)) {
      setOf(log.userId)
    } else {
      emptySet()
    }
  }

  /**
   * Logs are NOT globally sorted
   * Logs are NOT grouped by user
   */
  fun findRateLimitViolations(
    logs: List<Log>,
    maxRequests: Int,
    windowTimeInSeconds: Int
  ): Set<String> {
    val userLogs = mutableMapOf<UserId, Queue<Log>>()

    val sortedLogs = logs.sortedBy { it.timestamp }

    val violatingUsers = mutableSetOf<UserId>()

    sortedLogs.forEach { log ->
      if (violatingUsers.contains(log.userId)) return@forEach

      if (processLog(log, maxRequests, windowTimeInSeconds, userLogs)) {
        violatingUsers.add(log.userId)
      }
    }

    return violatingUsers
  }
}

data class Log(
  val timestamp: Int,
  val userId: String
)

fun main() {
  val rateLimiter = RateLimitViolators()
  val N = 3
  val W = 10

  // Mixed, out-of-order logs
  val logs = listOf(
    Log(1, "alice"),
    Log(2, "bob"),
    Log(3, "alice"),
    Log(4, "charlie"),
    Log(5, "alice"),    // Alice: 3rd log in window 1..5, still allowed
    Log(6, "bob"),
    Log(7, "alice"),    // Alice: 4th log in 1..7 → Violation!
    Log(8, "bob"),      // Bob: 3rd log in 2..8, still allowed
    Log(3, "charlie"),  // Out-of-order, within buffer; charlie window 3..4 → 2 logs
    Log(15, "charlie"), // charlie 3rd log in window 6..15, still allowed
    Log(16, "charlie"), // charlie 4th log in window 7..16 → Violation!
    Log(70, "dave"),    // New user, first log, within acceptable buffer
    Log(5, "dave"),     // Too old (70-5 > 60) → ignored
    Log(72, "dave"),    // 2nd log for Dave, within window, allowed
    Log(73, "dave"),    // 3rd log for Dave, within window, allowed
    Log(74, "dave")     // 4th log → Violation!
  )

  logs.forEach { log ->
    val violators = rateLimiter.processLogOutOfOrder(log, N, W)
    if (violators.isNotEmpty()) {
      println("Violation detected: $violators at log $log")
    } else {
      println("No violation at log $log")
    }
  }
}