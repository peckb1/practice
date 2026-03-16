package peckb1.leetcode.medium

class LRUCache(val capacity: Int) : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {

  override fun get(key: Int): Int = super.get(key) ?: -1

  override fun removeEldestEntry(eldest: Map.Entry<Int, Int>): Boolean {
    return size > capacity
  }
}