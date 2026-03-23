package leetcode.medium

class UniquePaths {
  fun uniquePaths(m: Int, n: Int): Int {
    // (m - 1) down moves
    // (n - 1) right moves

    //  (m - 1) + (n - 1)
    // -------------------
    //      (n - 1)
    //
    // https://en.wikipedia.org/wiki/Binomial_coefficient
    //
    //  n        n!
    // --- = ----------
    //  k     k!(n-k)!

    val nCoeff = (m - 1) + (n - 1)
    val kCoeff = (n - 1)

    return binomialCoefficent(nCoeff, kCoeff)
  }

  private fun binomialCoefficent(n: Int, k: Int): Int {
    val nFactorial = factorial(n)
    val kFactorial = factorial(k)
    val nMinusKFactorial = factorial(n - k)

    return (nFactorial / (kFactorial * nMinusKFactorial)).toInt()
  }

  tailrec fun factorial(n: Int, acc: Long = 1): Long =
    if (n <= 1) acc
    else factorial(n - 1, acc * n)
}