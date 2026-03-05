package peckb1.leetcode.medium

import java.util.PriorityQueue

typealias Time = Int

class TaskScheduler {
  fun leastInterval(tasks: CharArray, cooldownTime: Int): Int {
    val tasksCount = tasks.toList().groupingBy { it }.eachCount()

    val cooldownQueue = ArrayDeque<Pair<Job, Time>>()
    val jobsQueue = PriorityQueue<Job>(compareByDescending { it.remainingRuns })
        .apply { addAll(tasksCount.map { Job(it.key, it.value) }) }

    var currentTime = 0
    while (jobsQueue.isNotEmpty() || cooldownQueue.isNotEmpty()) {
      currentTime++

      if (cooldownQueue.isNotEmpty()) {
        val itemMaybeOffCooldown = cooldownQueue.first()
        if (itemMaybeOffCooldown.second == currentTime) {
          jobsQueue.add(cooldownQueue.removeFirst().first)
        }
      }

      val job = jobsQueue.poll()?.also { it.performJob() }

      if (job != null && job.remainingRuns > 0) {
        cooldownQueue.addLast(job to currentTime + cooldownTime + 1)
      }
    }

    return currentTime
  }
}

data class Job(val id: Char, var remainingRuns: Int) {
  fun performJob() { remainingRuns-- }
}

fun main() {
  print(TaskScheduler().leastInterval(charArrayOf('A','A','A','B','C'), 3))
}
