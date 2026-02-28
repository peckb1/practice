package peckb1.leetcode.medium

class ProductsOfArrayExceptSelf {
  fun productExceptSelf(nums: IntArray): IntArray {
    var zeroCount = 0

    val totalProduct = nums.fold(1) { acc, i ->
      if (i == 0) {
        zeroCount++
        acc
      } else {
        acc * i
      }
    }

    return nums.map {
      if (zeroCount > 1) {
        0
      } else {
        if (it == 0) {
          totalProduct
        } else {
          if (zeroCount > 0) {
            0
          } else {
            totalProduct / it
          }
        }
      }
    }.toIntArray()
  }
}