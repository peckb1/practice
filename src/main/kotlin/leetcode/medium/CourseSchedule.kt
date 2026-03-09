package peckb1.leetcode.medium

class CourseSchedule {
  fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    if (prerequisites.isEmpty()) return true

    val courses = mutableMapOf<Int, Course>()

    prerequisites.forEach { (courseId, prerequisite) ->
      courses
        .getOrPut(courseId) { Course(courseId) }
        .prerequisites.add(prerequisite)

      courses.getOrPut(prerequisite) { Course(prerequisite) }
    }

    val takenCourses = mutableSetOf<Int>()
    val blockedByPrerequisites = mutableSetOf<Int>()
    return (0 until numCourses).all { courseId ->
      tryTakeCourse(courseId, courses, takenCourses, blockedByPrerequisites)
    }
  }

  private fun tryTakeCourse(
    courseId: Int,
    courses: MutableMap<Int, Course>,
    takenCourses: MutableSet<Int>,
    blockedByPrerequisites: MutableSet<Int>,
  ): Boolean {
    if (blockedByPrerequisites.contains(courseId)) return false

    val prerequisites = courses[courseId]?.prerequisites ?: emptySet()

    return if (prerequisites.isEmpty()) {
      takenCourses.add(courseId)
      true
    } else {
      blockedByPrerequisites.add(courseId)
      prerequisites.all { tryTakeCourse(it, courses, takenCourses, blockedByPrerequisites) }
        .also { if (it) blockedByPrerequisites.remove(courseId) }
    }
  }

  data class Course(val id: Int, val prerequisites: MutableSet<Int> = mutableSetOf())
}

fun main() {
//  val numCourses=20
//  val prerequisites= arrayOf(
//    intArrayOf(0,10),
//    intArrayOf(3,18),
//    intArrayOf(5,5),
//    intArrayOf(6,11),
//    intArrayOf(11,14),
//    intArrayOf(13,1),
//    intArrayOf(15,1),
//    intArrayOf(17,4),
//  )

  val numCourses=5
  val prerequisites=arrayOf(
    intArrayOf(1,4),
    intArrayOf(2,4),
    intArrayOf(3,1),
    intArrayOf(3,2),
  )

  println(CourseSchedule().canFinish(numCourses, prerequisites))
}