package leetcode.hard

class AlienDictionary {
  fun foreignDictionary(words: Array<String>): String {
    val kahnGraph = mutableMapOf<Char, MutableSet<Char>>()
    val numberOfPrerequisitesPerNode = mutableMapOf<Char, Int>()

    // Initialize graph
    words.forEach { word ->
      word.forEach { c ->
        kahnGraph.putIfAbsent(c, mutableSetOf())
        numberOfPrerequisitesPerNode.putIfAbsent(c, 0)
      }
    }

    // Build edges
    (0 until words.lastIndex).forEach { wordIndex ->
      val w1 = words[wordIndex]
      val w2 = words[wordIndex + 1]

      // Invalid case: prefix
      if (w1.length > w2.length && w1.startsWith(w2)) {
        return ""
      }

      val minLen = minOf(w1.length, w2.length)

      for (wordIndex in 0 until minLen) { // utilize `for` here so we can break
        val c1 = w1[wordIndex]
        val c2 = w2[wordIndex]

        if (c1 != c2) {
          if (kahnGraph[c1]!!.add(c2)) {
            numberOfPrerequisitesPerNode[c2] = numberOfPrerequisitesPerNode[c2]!! + 1
          }
          break
        }
      }
    }

    // Kahn's BFS
    val queue: ArrayDeque<Char> = ArrayDeque()
    for ((c, numberOfPrerequisites) in numberOfPrerequisitesPerNode) {
      if (numberOfPrerequisites == 0) queue.add(c)
    }

    val result = StringBuilder()

    while (queue.isNotEmpty()) {
      val curr = queue.removeFirst()
      result.append(curr)

      for (neighbor in kahnGraph[curr]!!) {
        numberOfPrerequisitesPerNode[neighbor] = numberOfPrerequisitesPerNode[neighbor]!! - 1
        if (numberOfPrerequisitesPerNode[neighbor] == 0) {
          queue.add(neighbor)
        }
      }
    }

    // If cycle exists
    return if (result.length == numberOfPrerequisitesPerNode.size) result.toString() else ""
  }
}

fun main() {
  println(AlienDictionary().foreignDictionary(arrayOf("hrn","hrf","er","enn","rfnn")))
}