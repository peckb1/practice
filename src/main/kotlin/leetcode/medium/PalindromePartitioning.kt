package leetcode.medium

class PalindromePartitioning {
  fun partition(s: String): List<List<String>> {
    val results = mutableListOf<List<String>>()

    partition(s, 0, mutableListOf(), results)
    return results
  }

  fun partition(
    s: String,
    startIndex: Int,
    path: MutableList<String>,
    results: MutableList<List<String>>
  ) {
    if (startIndex == s.length) {
      results.add(path.toList())
      return
    }

    for (endIndex in startIndex until s.length) {
      if (isPalindrome(s, startIndex, endIndex)) {
        path.add(s.substring(startIndex, endIndex + 1))
        partition(s, endIndex + 1, path, results)
        path.removeAt(path.lastIndex)
      }
    }
  }

  fun isPalindrome(s: String, leftIndex: Int, rightIndex: Int): Boolean {
    var l = leftIndex
    var r = rightIndex
    while (l < r) {
      if (s[l++] != s[r--]) return false
    }
    return true
  }
}
