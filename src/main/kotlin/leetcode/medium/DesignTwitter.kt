package peckb1.leetcode.medium

typealias UserId = Int
typealias Timestamp = Int

class Twitter {
  var timestampProxy = 0

  val following = mutableMapOf<UserId, MutableSet<UserId>>()
  val tweets = mutableMapOf<UserId, MutableSet<Tweet>>()

  fun postTweet(userId: Int, tweetId: Int) {
    tweets.getOrPut(userId) { mutableSetOf() }.add(Tweet(tweetId, userId, timestampProxy++))
  }

  fun getNewsFeed(userId: Int): List<Int> {
    val followingIds = following[userId] ?: emptyList()

    val feed = mutableListOf<Tweet>()
      .apply { tweets[userId]?.forEach { add(it) } }

    followingIds.forEach { followingId -> tweets[followingId]?.forEach { feed.add(it) } }

    return feed.sortedByDescending { it.timestamp }.map { it.tweetId }.take(10)
  }

  fun follow(followerId: Int, followeeId: Int) {
    if (followerId == followeeId) return
    following.getOrPut(followerId) { mutableSetOf() }.add(followeeId)
  }

  fun unfollow(followerId: Int, followeeId: Int) {
    following.getOrPut(followerId) { mutableSetOf() }.remove(followeeId)
  }
}

data class Tweet(val tweetId: Int, val userId: Int, val timestamp: Timestamp)
