package leetcode;

/**
 * 	You are a product manager and currently leading a team to develop 
 * a new product. Unfortunately, the latest version of your product 
 * fails the quality check. Since each version is developed based 
 * on the previous version, all the versions after a bad version are also bad.

	Suppose you have n versions [1, 2, ..., n] and you want to 
	find out the first bad one, which causes all the following ones to be bad.
	
	You are given an API bool isBadVersion(version) which will 
	return whether version is bad. Implement a function to find 
	the first bad version. You should minimize the number of calls to the API.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P278_FirstBadVersion.java
 * @type        P278_FirstBadVersion
 * @date        2016年12月14日 下午10:21:45
 * @details     Solution: AC  18ms 35.75% 
 */
public class P278_FirstBadVersion {
	static class Solution extends VersionControl {
	    public int firstBadVersion(int n) {
	        int si = 1, ei = n, mi = 0;
	        if (isBadVersion(si))	return 1;
	        while (si < ei) {
	        	mi = (int)(((long)si + ei) / 2);
	        	boolean isBad = isBadVersion(mi);
	        	if (isBad) {
	        		ei = mi;
	        	} else {
	        		si = mi + 1;
	        	}
	        }
	        return ei;
	    }
	}
	static class VersionControl {
		public boolean isBadVersion(int v) {
			return v >= 3;
		}
	}
}
