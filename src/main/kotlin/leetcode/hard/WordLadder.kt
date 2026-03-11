package peckb1.leetcode.hard

class WordLadder {
  fun ladderLength(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
    val wordSet = wordList.toMutableSet()
    if (endWord !in wordSet) return 0

    val queue: ArrayDeque<Pair<String, Int>> = ArrayDeque()
    queue.add(beginWord to 1)

    val visited = mutableSetOf(beginWord)

    while (queue.isNotEmpty()) {
      val (word, depth) = queue.removeFirst()

      if (word == endWord) return depth

      for (next in wordSet) {
        if (next !in visited && oneOff(word, next)) {
          visited.add(next)
          queue.add(next to depth + 1)
        }
      }
    }

    return 0
  }

  private fun oneOff(a: String, b: String): Boolean {
    return a.zip(b).count { it.first != it.second } == 1
  }

  fun fastLadderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    val dictionary = wordList.toMutableSet()
    if (endWord !in dictionary) return 0

    var forwardFrontier = mutableSetOf(beginWord)
    var backwardFrontier = mutableSetOf(endWord)
    var steps = 1

    while (forwardFrontier.isNotEmpty() && backwardFrontier.isNotEmpty()) {
      // Always expand the smaller frontier for efficiency
      if (forwardFrontier.size > backwardFrontier.size)
        forwardFrontier = backwardFrontier.also { backwardFrontier = forwardFrontier }

      val nextLevelFrontier = mutableSetOf<String>()

      for (currentWord in forwardFrontier) {
        val chars = currentWord.toCharArray()
        for (i in chars.indices) {
          val originalChar = chars[i]
          for (letter in 'a'..'z') {
            if (letter == originalChar) continue
            chars[i] = letter
            val candidateWord = String(chars)

            // Check if the two frontiers meet
            if (candidateWord in backwardFrontier) return steps + 1

            // Add to next level if in dictionary, and mark visited
            if (candidateWord in dictionary) {
              nextLevelFrontier.add(candidateWord)
              dictionary.remove(candidateWord)
            }
          }
          chars[i] = originalChar
        }
      }

      forwardFrontier = nextLevelFrontier
      steps++
    }

    return 0
  }
}
