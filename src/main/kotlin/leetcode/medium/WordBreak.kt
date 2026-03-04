package peckb1.leetcode.medium

class WordBreak {
  fun wordBreak(s: String, wordDict: List<String>): Boolean {
    return wordBreak(s, wordDict, mutableMapOf())
  }

  private fun wordBreak(s: String, wordDict: List<String>, cache: MutableMap<String, Boolean>) : Boolean {
    if (s.isEmpty()) return true
    if (cache.containsKey(s)) return cache[s]!!

    val possiblePrefix = wordDict.filter { s.startsWith(it) }
    if (possiblePrefix.isEmpty()) {
      cache[s] = false
    } else {
      cache[s] = possiblePrefix.any {
        wordBreak(s.removePrefix(it), wordDict, cache)
      }
    }

    return cache[s]!!
  }
}

fun main() {
//  println(WordBreak().wordBreak("applepenapple", listOf("apple","pen","ape")))
    println(WordBreak().wordBreak("catsincars", listOf("cats","cat","sin","in","car")))
}