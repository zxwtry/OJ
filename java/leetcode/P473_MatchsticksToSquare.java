package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 	Remember the story of Little Match Girl? By now, you know exactly what 
 * 	matchsticks the little match girl has, please find out a way you can 
 * 	make one square by using up all those matchsticks. You should not break 
 * 	any stick, but you can link them up, and each matchstick must be used
 * 	exactly one time.
 *	
 *	Your input will be several matchsticks the girl has, represented with 
 *	their stick length. Your output will either be true or false, to represent 
 *	whether you can save this little girl or not.
 *	
 *	Example 1:
 *	Input: [1,1,2,2,2]
 *	Output: true
 *	
 *	Explanation: You can form a square with length 2, one side of the square 
 *	came two sticks with length 1.
 *	Example 2:
 *	Input: [3,3,3,3,4]
 *	Output: false
 *	
 *	Explanation: You cannot find a way to form a square with all the matchsticks 
 *	and you cannot save this little girl.
 *	Note:
 *	The length sum of the given matchsticks is in the range of 0 to 10^9.
 *	The length of the given matchstick array will not exceed 15.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P473_MatchsticksToSquare.java
 * @type        P473_MatchsticksToSquare
 * @date        2016年12月18日 上午11:09:15
 * @details     Solution1: AC 171ms 37.23%
 * @details     Solution2: AC  81ms 47.16%
 * @details     Solution3: AC  45ms 63.55%
 */
public class P473_MatchsticksToSquare {
	static class Solution1 {
		int lay = 0;
		boolean[] iv = null;
		boolean isS = false;
		int sum = 0;
		public boolean makesquare(int[] nums) {
	        sum = 0;
	        int max = 0;
	        for (int v : nums) {
	        	sum += v; 
	        	max = Math.max(max, v);
	        }
	        if (sum % 4 != 0) return false;
	        sum = sum / 4;
	        if (max > sum) return false;
	        Arrays.sort(nums);
	        iv = new boolean[nums.length];
	        search(nums, sum, 0);
	        return isS;
	    }
		private void search(int[] n, int need, int l) {
			if (need == 0) {
				l ++;
				if (l == 4) {
					isS = true;
					return;
				}
				need = sum;
			}
			int i = n.length - 1;
			for (; i > -1; i --) {
				if (i != n.length - 1 && n[i] == n[i+1] && !iv[i] && !iv[i+1])
					continue;
				if (! iv[i] && n[i] <= need && ! isS) {
					iv[i] = true;
					search(n, need - n[i], l);
					iv[i] = false;
				}
			}
		}
	}
	static class Solution2 {
	    public boolean makesquare(int[] nums) {
	        if (nums == null || nums.length < 4) return false;
	        ArrayList<Integer> list = new ArrayList<>(nums.length);
	        int sum = 0, max = 0;
	        for (int num : nums) {
	            sum += num;
	            max = Math.max(num, max);
	            list.add(num);
	        }
	        if ((sum & 3) != 0) return false;
	        sum = sum >> 2;
			if (max > sum) return false;
	        Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer val1, Integer val2) {
                    return val2 - val1;
                }
            });
	        return dfs(list, new int[4], 0, sum);
	    }
        private boolean dfs(ArrayList<Integer> nums, int[] sums, int index, int target) {
            if (index == nums.size()) {
                boolean isAllT = true;
                for (int i = 0; isAllT && i < sums.length; i ++)
                    isAllT = sums[i] == target;
                return isAllT;
            }
            for (int i = 0; i < sums.length; i ++) {
                if (sums[i] + nums.get(index) > target) continue;
                sums[i] += nums.get(index);
                if (dfs(nums, sums, index + 1, target)) return true;
                sums[i] -= nums.get(index);
            }
            return false;
        }
	}
	static class Solution3 {
        public boolean makesquare(int[] nums) {
            if (nums == null || nums.length < 4) return false;
            int sum = 0, max = 0;
            for (int num : nums) {
                sum += num;
                max = Math.max(num, max);
            }
            if ((sum & 3) != 0) return false;
            sum = sum >> 2;
            if (max > sum) return false;
            Arrays.sort(nums);
            return dfs(nums, new int[4], nums.length - 1, sum);
        }
        private boolean dfs(int[] nums, int[] sums, int index, int target) {
            if (index == -1) {
                int i = 0;
                for (; i < sums.length && sums[i] == target; i ++){}
                return i == sums.length;
            }
            for (int i = 0; i < sums.length; i ++) {
                if (sums[i] + nums[index] > target) continue;
                sums[i] += nums[index];
                if (dfs(nums, sums, index - 1, target)) return true;
                sums[i] -= nums[index];
            }
            return false;
        }
    }
}
