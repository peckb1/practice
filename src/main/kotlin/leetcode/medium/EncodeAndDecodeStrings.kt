package peckb1.leetcode.medium

import java.util.Base64

class EncodeAndDecodeStrings {
  companion object {
    // choose items not present in base64 encodings
    private const val DELIMITER = ":"
    private const val EMPTY = "?"
  }

  val encoder = Base64.getEncoder()
  val decoder = Base64.getDecoder()

  fun encode(strs: List<String>): String {
    if (strs.isEmpty()) return EMPTY

    return strs.joinToString(DELIMITER) { encoder.encodeToString(it.encodeToByteArray()) }
  }

  fun decode(str: String): List<String> {
    if (str == EMPTY) return emptyList()

    return str.split(DELIMITER).map { decoder.decode(it).decodeToString() }
  }
}
