package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 	Design a simplified version of Twitter where users can post tweets, follow/unfollow 
 * 	another user and is able to see the 10 most recent tweets in the user's news feed. 
 * 	Your design should support the following methods:
 *	
 *	postTweet(userId, tweetId): Compose a new tweet.
 *	getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. 
 *	Each item in the news feed must be posted by users who the user followed or by the user herself. 
 *	Tweets must be ordered from most recent to least recent.
 *	follow(followerId, followeeId): Follower follows a followee.
 *	unfollow(followerId, followeeId): Follower unfollows a followee.
 *	Example:
 *	
 *	Twitter twitter = new Twitter();
 *	
 *	// User 1 posts a new tweet (id = 5).
 *	twitter.postTweet(1, 5);
 *	
 *	// User 1's news feed should return a list with 1 tweet id -> [5].
 *	twitter.getNewsFeed(1);
 *	
 *	// User 1 follows user 2.
 *	twitter.follow(1, 2);
 *	
 *	// User 2 posts a new tweet (id = 6).
 *	twitter.postTweet(2, 6);
 *	
 *	// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 *	// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 *	twitter.getNewsFeed(1);
 *	
 *	// User 1 unfollows user 2.
 *	twitter.unfollow(1, 2);
 *	
 *	// User 1's news feed should return a list with 1 tweet id -> [5],
 *	// since user 1 is no longer following user 2.
 *	twitter.getNewsFeed(1);
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P355_DesignTwitter.java
 * @type        P355_DesignTwitter
 * @date        2017年2月4日 下午7:40:55
 * @details     Twitter1: AC 194ms 37.22%
 */
class P355_DesignTwitter {
	static class Twitter1 {
	    ArrayList<ArrayList<Integer>> userTweets = null;
	    ArrayList<HashSet<Integer>> userFollowers = null;
	    int tweetTimeStamp = 0;
	    HashMap<Integer, Integer> tweetMap = null;
	    ArrayList<Integer> indices = null;
	    /** Initialize your data structure here. */
	    public Twitter1() {
	        userTweets = new ArrayList<ArrayList<Integer>>();
	        userFollowers = new ArrayList<HashSet<Integer>>();
	        tweetMap = new HashMap<Integer, Integer>();
	        indices = new ArrayList<Integer>();
	    }
	    
	    /** Compose a new tweet. */
	    public void postTweet(int userId, int tweetId) {
	        checkUserId(userId);
	        tweetMap.put(tweetTimeStamp, tweetId);
	        userTweets.get(userId).add(tweetTimeStamp);
	        tweetTimeStamp ++;
	    }
	    
	    private void checkUserId(int userId) {
	        while (userTweets.size() < userId + 1) {
	            userTweets.add(new ArrayList<Integer>());
	        }
	        while (userFollowers.size() < userId + 1) {
	            HashSet<Integer> newSet = new HashSet<Integer>();
	            newSet.add(userFollowers.size());
	            userFollowers.add(newSet);
	        }
	        while (indices.size() < userId + 1) {
	            indices.add(-1);
	        }
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
	    public List<Integer> getNewsFeed(int userId) {
	        checkUserId(userId);
	        List<Integer> answer = new LinkedList<Integer>();
	        for (int feId : userFollowers.get(userId)) {
	            indices.set(feId, userTweets.get(feId).size() - 1);
	        }
	        while (answer.size() < 10) {
	            int maxFei = -1;
	            for (int feId : userFollowers.get(userId)) {
	                if (indices.get(feId) != -1) {
	                    if (maxFei == -1) maxFei = feId;
	                    else if (userTweets.get(maxFei).get(indices.get(maxFei)) < userTweets.get(feId).get(indices.get(feId)))
	                        maxFei = feId;
	                }
	            }
	            if (maxFei == -1) break;
	            answer.add(tweetMap.get(userTweets.get(maxFei).get(indices.get(maxFei))));
	            indices.set(maxFei, indices.get(maxFei) - 1);
	        }
	        return answer;
	    }
	    
	    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
	    public void follow(int followerId, int followeeId) {
	        checkUserId(followerId);
	        checkUserId(followeeId);
	        userFollowers.get(followerId).add(followeeId);
	    }
	    
	    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
	    public void unfollow(int followerId, int followeeId) {
	        checkUserId(followerId);
            checkUserId(followeeId);
            if (followeeId == followerId) return;
            userFollowers.get(followerId).remove(followeeId);
	    }
	}
	/**
	 * Your Twitter object will be instantiated and called as such:
	 * Twitter obj = new Twitter();
	 * obj.postTweet(userId,tweetId);
	 * List<Integer> param_2 = obj.getNewsFeed(userId);
	 * obj.follow(followerId,followeeId);
	 * obj.unfollow(followerId,followeeId);
	 */
}
