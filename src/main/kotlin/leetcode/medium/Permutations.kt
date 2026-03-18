package leetcode.medium

import kotlin.collections.forEach

class Permutations {
  fun permute(nums: IntArray): List<List<Int>> {
    return PermutationGenerator()
      .generatePermutationSequence(nums.toTypedArray())
      .map { it.toList() }
      .toList()
  }
}

// https://github.com/peckb1/advent-of-code/blob/main/src/main/kotlin/me/peckb/aoc/generators/PermutationGenerator.kt
// updated here, need to update source as well
class PermutationGenerator {
  fun <T> generatePermutations(data: Array<T>): List<Array<T>> {
    return generatePermutations(data, 0)
  }

  fun <T> generatePermutationSequence(data: Array<T>): Sequence<Array<T>> = sequence {
    suspend fun SequenceScope<Array<T>>.generatePermutationSequence(index: Int) {
      if (index == data.lastIndex) {
        yield(data.clone())
      } else {
        for (i in index..data.lastIndex) {
          swap(data, index, i)
          generatePermutationSequence(index + 1)
          swap(data, index, i)
        }
      }
    }
    generatePermutationSequence(0)
  }

  private fun <T> generatePermutationSequence(data: Array<T>, index: Int): Sequence<Array<T>> = sequence {
    if (index == data.lastIndex) {
      yield(data.clone())
    } else {
      (index .. data.lastIndex).forEach { i ->
        swap(data, index, i)
        yieldAll(generatePermutationSequence(data, index + 1))
        swap(data, index, i)
      }
    }
  }

  private fun <T> generatePermutations(data: Array<T>, l: Int): List<Array<T>> {
    val permutations = mutableListOf<Array<T>>()

    if (l == data.lastIndex) {
      permutations.add(data.clone())
    } else {
      (l..data.lastIndex).forEach { i ->
        swap(data, l, i)
        permutations.addAll(generatePermutations(data, l + 1))
        swap(data, l, i)
      }
    }

    return permutations
  }

  private fun <T> swap(data: Array<T>, i: Int, j: Int) {
    val t = data[i]
    data[i] = data[j]
    data[j] = t
  }
}