package peckb1.leetcode.medium

class CourseSchedule {
  val scheduler = CourseScheduleII()

  fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val order = scheduler.findOrder(numCourses, prerequisites)
    return order.isNotEmpty()
  }
}
