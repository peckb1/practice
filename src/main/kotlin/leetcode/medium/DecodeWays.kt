package peckb1.leetcode.medium

class DecodeWays {
  typealias Index = Int
  typealias Decoding = Int

  fun numDecodings(s: String): Int {
    if (s.isEmpty()) return 0

    return numDecodings(0, s)
  }

  private fun numDecodings(index: Int, s: String, cache: MutableMap<Index, Decoding> = mutableMapOf()): Int {
    if (cache.contains(index)) return cache[index]!!
    if (index >= s.length) return 1
    if (s[index] == '0') {
      cache[index] = 0
      return 0
    }

    val shouldSingleDigit = s[index] in '1'..'9'
    val singleDigitEncodingPathCount = if (shouldSingleDigit) { numDecodings(index + 1, s, cache) } else { 0 }

    val shouldDoubleDigit = index + 1 < s.length && "${s[index]}${s[index + 1]}".matches(Regex("^1[0-9]|2[0-6]$"))
    val doubleDigitEncodingPathCount = if (shouldDoubleDigit) { numDecodings(index + 2, s, cache) } else { 0 }

    return (singleDigitEncodingPathCount + doubleDigitEncodingPathCount)
      .also { cache[index] = it }
  }
}
