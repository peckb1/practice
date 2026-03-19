package leetcode.adjacent

typealias PersonId = Int
typealias RowIndex = Int

class MinSwapCouples {
  fun minSwapsCouples(row: IntArray): Int {
    // edge cases row is not an odd number length
    if (row.size % 2 != 0) {
      // SHOULD never happen
      throw IllegalArgumentException("row size must be even")
    }

    val peopleIndices = mutableMapOf<PersonId, RowIndex>()
    row.forEachIndexed { index, personId -> peopleIndices[personId] = index }

    return swapCouples(0, row, peopleIndices)
  }

  /**
   * Look at the front pair of items
   * if that pair is "ok" shrink that side of the list until we find a "bad" one (or are done)
   *
   * If that is not ok, swap it out with the missing friend
   */
  private tailrec fun swapCouples(
    startIndex: Int,
    row: IntArray,
    peopleIndices: MutableMap<PersonId, RowIndex>,
    numSwaps: Int = 0,
  ) : Int {
    if (startIndex >= row.lastIndex) {
      return numSwaps
    } else {
      val personIDA = row[startIndex]
      val personIDB = row[startIndex + 1]

      // find out which of the two items is "smaller"
      // i.e. if we get 3, 4 smaller is 3, and larger is 4
      val smallerOfTheseTwo = minOf(personIDA, personIDB)
      val largerOfTheseTwo = maxOf(personIDA, personIDB)

      val newSwaps = if (smallerOfTheseTwo + 1 == largerOfTheseTwo && smallerOfTheseTwo % 2 == 0) {
        //if the numbers are one away from each other AND the lower one is an even number
        //we don't need to swap!
        //i.e. 2, 3 - no swap
        //     3, 4 - should swap
        numSwaps
      } else {
        val indexOfItemToReplace = listOf(startIndex, startIndex + 1).maxBy { row[it] }
        val ourMissingFriendId: PersonId = if (smallerOfTheseTwo % 2 == 0) {
          // we're an even number, we need our larger pair
          smallerOfTheseTwo + 1
        } else {
          // we're an odd number, we need our smaller pair
          smallerOfTheseTwo - 1
        }
        val indexOfReplacementItem = peopleIndices[ourMissingFriendId]!!

        // swap (row)
        row[indexOfItemToReplace] = ourMissingFriendId
        row[indexOfReplacementItem] = largerOfTheseTwo

        // swap pointers
        peopleIndices[ourMissingFriendId] = indexOfItemToReplace
        peopleIndices[largerOfTheseTwo] = indexOfReplacementItem

        numSwaps + 1
      }

      // recurse!
      return swapCouples(startIndex + 2, row, peopleIndices, newSwaps)
    }
  }
}

fun main() {
  // (0, 1), (2, 3), (5, 4)
  println(MinSwapCouples().minSwapsCouples(intArrayOf(5,6,4,0,2,1,9,3,8,7,11,10)))
}