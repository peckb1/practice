package peckb1.leetcode.easy

class ValidAnagram {
  fun isAnagram(s: String, t: String): Boolean {
    val sCounts = s.groupingBy { it }.eachCount()
    val tCounts = t.groupingBy { it }.eachCount()

    return sCounts.all { (sChar, sCount) -> (tCounts[sChar] ?: -1) == sCount } &&
      tCounts.all { (tChar, tCount) -> (sCounts[tChar] ?: -1) == tCount }
  }
}