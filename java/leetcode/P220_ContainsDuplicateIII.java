package leetcode;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 	Given an array of integers, find out whether there are 
 * 	two distinct indices i and j in the array such that the difference 
 * 	between nums[i] and nums[j] is at most t and the difference between 
 * 	i and j is at most k.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P220_ContainsDuplicateIII.java
 * @type        P220_ContainsDuplicateIII
 * @date        2016年12月29日 下午8:57:50
 * @details     Solution1 : 54 ms 37.03% 这个解法太BUG了
 */
public class P220_ContainsDuplicateIII {
	static class Solution1 {
	    public boolean containsNearbyAlmostDuplicate(int[] arr, int k, int t) {
	        if(k < 1 || t < 0 || arr == null || arr.length < 2) return false;
	        SortedSet<Long> set = new TreeSet<Long>();
	        for(int j = 0; j < arr.length; j ++) {
	            SortedSet<Long> subSet =  set.subSet((long)arr[j]-t, (long)arr[j]+t+1);
	            if(!subSet.isEmpty()) return true;
	            if(j >= k) set.remove((long)arr[j-k]);
	            set.add((long)arr[j]);
	        }
	        return false;
	    }
	}
}
