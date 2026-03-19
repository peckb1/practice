package leetcode.medium

class GenerateParentheses {
  fun generateParenthesis(n: Int): List<String> {
    val results = mutableListOf<String>()

    generateParenthesis(n, results)

    return results
  }

  private fun generateParenthesis(
    n: Int,
    results: MutableList<String>,
    opens: Int = 0,
    closes: Int = 0,
    path: StringBuilder = StringBuilder(),
  ) {
    if (opens == n && closes == n) {
      results.add(path.toString())
    } else {
      if (opens < n) {
        // choose
        path.append('(')
        // explore
        generateParenthesis(n, results, opens+1, closes, path)
        // unchoose
        path.deleteAt(path.length - 1)
      }
      if (closes < n && opens > closes) {
        // choose
        path.append(')')
        // explore
        generateParenthesis(n, results, opens, closes+1, path)
        // unchoose
        path.deleteAt(path.length - 1)
      }
    }
  }
}
