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


fun main() {
  val twitter = Twitter()
  println(twitter.postTweet(1, 10)) // User 1 posts a new tweet with id = 10.
  println(twitter.postTweet(2, 20)) // User 2 posts a new tweet with id = 20.
  println(twitter.getNewsFeed(1)) // User 1's news feed should only contain their own tweets -> [10].
  println(twitter.getNewsFeed(2)) // User 2's news feed should only contain their own tweets -> [20].
  println(twitter.follow(1, 2)) // User 1 follows user 2.
  println(twitter.getNewsFeed(1)) // User 1's news feed should contain both tweets from user 1 and user 2 -> [20, 10].
  println(twitter.getNewsFeed(2)) // User 2's news feed should still only contain their own tweets -> [20].
  println(twitter.unfollow(1, 2)) // User 1 unfollows user 2.
  println(twitter.getNewsFeed(1)) // User 1's news feed should only contain their own tweets -> [10].
}