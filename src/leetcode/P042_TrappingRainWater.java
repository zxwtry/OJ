package leetcode;

/*
 * 	最大积水问题
 */

public class P042_TrappingRainWater {
	public static void main(String[] args) {
		int[] input = null;
		input = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
		input = new int[]{2,0,2};
		System.out.println(new Solution2().trap(input));
	}
	/*
	 * 	AC
	 */
	static class Solution {
	    public int trap(int[] height) {
	    	if (height == null || height.length < 3)
	    		return 0;
	    	int maxI = 0;
	    	for (int i = 1; i != height.length; i ++)
	    		if (height[i] > height[maxI])
	    			maxI = i;
	    	int[] hori = new int[height.length];
	    	hori[0] = height[0];
	    	hori[maxI] = height[maxI];
	    	hori[height.length - 1] = height[height.length - 1];
	    	int ans = 0;
	    	for (int i = 1; i < maxI; i ++) {
	    		hori[i] = height[i] > hori[i - 1] ? height[i] : hori[i - 1];
	    		ans += hori[i] - height[i];
	    	}
	    	for (int i = height.length - 2; i > maxI; i --) {
	    		hori[i] = height[i] > hori[i + 1] ? height[i] : hori[i + 1];
	    		ans += hori[i] - height[i];
	    	}
	        return ans;
	    }
	}
	/*
	 * 	AC
	 */
	static class Solution2 {
	    public int trap(int[] height) {
	    	if (height == null || height.length < 3)
	    		return 0;
	    	int maxI = 0;
	    	for (int i = 1; i != height.length; i ++)
	    		if (height[i] > height[maxI])
	    			maxI = i;
	    	int ans = 0, pre = height[0];
	    	for (int i = 1; i < maxI; i ++) {
	    		pre = height[i] > pre ? height[i] : pre;
	    		ans += pre - height[i];
	    	}
	    	pre = height[height.length - 1];
	    	for (int i = height.length - 2; i > maxI; i --) {
	    		pre = height[i] > pre ? height[i] : pre;
	    		ans += pre - height[i];
	    	}
	        return ans;
	    }
	}
}
