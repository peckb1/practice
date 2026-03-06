package peckb1.leetcode.medium

class PrefixTree {
  private val roots = mutableMapOf<Char, Node>()

  fun insert(word: String) {
    if (word.isEmpty()) return
    if (word.length == 1) {
      roots.getOrPut(word[0]) { Node(word[0]) }.setTerminal()
    } else {
      roots.getOrPut(word[0]) { Node(word[0]) }.also { node -> node.addWord(word, 1) }
    }
  }

  fun search(word: String): Boolean {
    if (word.isEmpty()) return false

    val wildcard = word[0] == '.'

    if (word.length == 1) {
      return if (wildcard) {
        roots.any { (_, node) -> node.isTerminal }
      } else {
        roots[word[0]]?.isTerminal ?: false
      }
    }

    return if (wildcard) {
      roots.any { (_, node) -> node.search(word, 1) }
    } else {
      roots[word[0]]?.search(word, 1) ?: false
    }
  }

  fun startsWith(prefix: String): Boolean {
    if (prefix.isEmpty()) return false
    if (prefix.length == 1) return roots[prefix[0]] != null

    return roots[prefix[0]]?.startsWith(prefix, 1) ?: false
  }

  private class Node(val c: Char) {
    val children = mutableMapOf<Char, Node>()
    var isTerminal = false
      private set

    fun setTerminal() {
      isTerminal = true
    }

    fun addWord(word: String, index: Int) {
      val nextChar = word[index]

      if (index < word.lastIndex) {
        children.getOrPut(nextChar) { Node(nextChar) }.addWord(word, index + 1)
      } else {
        children.getOrPut(nextChar) { Node(nextChar) }.setTerminal()
      }
    }

    fun startsWith(prefix: String, index: Int): Boolean {
      if (index == prefix.lastIndex) return children[prefix[index]] != null

      return children[prefix[index]]?.startsWith(prefix, index + 1) ?: false
    }

    fun search(word: String, index: Int): Boolean {
      val wildcard = word[index] == '.'

      if (index == word.lastIndex) {
        return if (wildcard) {
          children.any { (_, node) -> node.isTerminal }
        } else {
          children[word[index]]?.isTerminal ?: false
        }
      }

      return if (wildcard) {
        children.any { (_, node) -> node.search(word, index + 1) }
      } else {
        children[word[index]]?.search(word, index + 1) ?: false
      }
    }
  }
}
