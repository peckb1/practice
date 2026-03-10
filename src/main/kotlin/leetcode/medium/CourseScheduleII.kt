package peckb1.leetcode.medium

class CourseScheduleII {
  fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    if (prerequisites.isEmpty()) return (numCourses downTo 0).toList().toIntArray()

    val courses = mutableMapOf<Int, Course>()

    prerequisites.forEach { (courseId, prerequisite) ->
      courses
        .getOrPut(courseId) { Course(courseId) }
        .prerequisites.add(prerequisite)

      courses.getOrPut(prerequisite) { Course(prerequisite) }
    }

    val takenCourses = mutableSetOf<Int>()
    val blockedByPrerequisites = mutableSetOf<Int>()
    (0 until numCourses).forEach { courseId -> tryTakeCourse(courseId, courses, takenCourses, blockedByPrerequisites) }

    return if (takenCourses.size == numCourses) {
      takenCourses.toTypedArray().toIntArray()
    } else {
      intArrayOf()
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
        .also { if (it) {
          blockedByPrerequisites.remove(courseId)
          takenCourses.add(courseId)
        }
      }
    }
  }

  data class Course(val id: Int, val prerequisites: MutableSet<Int> = mutableSetOf())
}
