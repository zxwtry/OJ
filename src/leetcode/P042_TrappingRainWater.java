package leetcode;

/*
 * 	最大积水问题
 */

public class P042_TrappingRainWater {
	static class Solution {
	    /**
	     * 积水问题，最重要的是找到最高的那块
	     * 从两边往最高的那块：
	     *     之前的最大值，就是能到的最大高度
	     */
	    public int trap(int[] height) {
	        int heightLength = height == null ? 0 : height.length;
	        if (heightLength < 3) {
	            return 0;
	        }
	        int maxIndex = 0;
	        int ans = 0;
	        for (int index = 1; index < heightLength; index ++) {
	            if (height[index] > height[maxIndex]) {
	                maxIndex = index;
	            }
	        }
	        int leftMax = height[0];
	        for (int index = 1; index < maxIndex; index ++) {
	            leftMax = Math.max(leftMax, height[index]);
	            ans += leftMax - height[index];
	        }
	        int rightMax = height[heightLength - 1];
	        for (int index = heightLength - 2; index > maxIndex; index --) {
	            rightMax = Math.max(rightMax, height[index]);
	            ans += rightMax - height[index];
	        }
	        return ans;
	    }
	}
}
