package peckb1.leetcode.medium

class WordDictionary {

  val trie = PrefixTree()

  fun addWord(word: String) {
    trie.insert(word)
  }

  fun search(word: String): Boolean {
    return trie.search(word)
  }
}

fun main() {
  /*
  ["WordDictionary","addWord","addWord","search","search"]
[[],["a"],["abcdefghijklmnopqrst"],["a"],["abcdefghijklmnopqrst"]]
   */
  val wordDictionary = WordDictionary()
  wordDictionary.addWord("a")
  wordDictionary.search("a")
}