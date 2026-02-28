package peckb1.leetcode.medium

import peckb1.leetcode.easy.ValidAnagram

class GroupAnagrams {
  fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val anagramChecker = ValidAnagram()
    val groupings = mutableListOf<List<String>>()

    val usedIndices = mutableListOf<Int>()
    strs.indices.forEach check@ { i ->
      if (usedIndices.contains(i)) return@check

      usedIndices.add(i)
      val grouping = mutableListOf(strs[i])

      (i + 1 until strs.size).forEach { j ->
        if (anagramChecker.isAnagram(strs[i], strs[j])) {
          grouping.add(strs[j])
          usedIndices.add(j)
        }
      }

      groupings.add(grouping)
    }

    return groupings
  }
}