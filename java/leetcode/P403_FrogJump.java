package leetcode;

/**
 * 	A frog is crossing a river. The river is divided into x units and
 *  at each unit there may or may not exist a stone. 
 *  The frog can jump on a stone, but it must not jump into the water.
 *	
 *	Given a list of stones' positions (in units) in sorted ascending order, 
 *	determine if the frog is able to cross the river by landing on the last stone.
 *	 Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 *	
 *	If the frog has just jumped k units, then its next jump must be either k - 1,
 *	 k, or k + 1 units. Note that the frog can only jump in the forward direction.
 *	
 *	Note:
 *	
 *	The number of stones is ≥ 2 and is < 1,100.
 *	Each stone's position will be a non-negative integer < 231.
 *	The first stone's position is always 0.
 *	Example 1:
 *	
 *	[0,1,3,5,6,8,12,17]
 *	
 *	There are a total of 8 stones.
 *	The first stone at the 0th unit, second stone at the 1st unit,
 *	third stone at the 3rd unit, and so on...
 *	The last stone at the 17th unit.
 *	
 *	Return true. The frog can jump to the last stone by jumping 
 *	1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
 *	2 units to the 4th stone, then 3 units to the 6th stone, 
 *	4 units to the 7th stone, and 5 units to the 8th stone.
 *	Example 2:
 *	
 *	[0,1,2,3,4,8,9,11]
 *	
 *	Return false. There is no way to jump to the last stone as 
 *	the gap between the 5th and 6th stone is too large.
 */


import java.util.ArrayList;
import java.util.HashSet;

public class P403_FrogJump {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.canCross(new int[] {0,1,3,5,6,8,12,17}));
		System.out.println(s.canCross(new int[] {0,1,2,3,4,8,9,11}));
	}
	/*
	 * 	AC
	 * 	148 ms
	 */
	static class Solution {
	    public boolean canCross(int[] stones) {
	    	ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>(stones.length);
	    	for (int i = 0; i < stones.length; i ++) {
	    		sets.add(new HashSet<Integer>());
	    	}
	    	sets.get(0).add(0);
	    	for (int i = 1; i < stones.length; i ++) {
	    		for (int h : sets.get(i - 1)) {
		    		int val =  h + stones[i - 1];
		    		int index = binary(stones, val);
		    		if (index != -1) {
		    			HashSet<Integer> set = sets.get(index);
		    			set.add(h);
		    		}
		    		val =  h + stones[i - 1] + 1;
		    		index = binary(stones, val);
		    		if (index != -1) {
		    			HashSet<Integer> set = sets.get(index);
		    			set.add(h + 1);
		    		}
		    		if (h - 1 <= 0) {
		    			continue;
		    		}
		    		val =  h + stones[i - 1] - 1;
		    		index = binary(stones, val);
		    		if (index != -1) {
		    			HashSet<Integer> set = sets.get(index);
		    			set.add(h - 1);
		    		}
	    		}
	    	}
	        return sets.get(stones.length - 1).size() > 0;
	    }
	    int binary(int[] stones, int val) {
	    	int sti = 0, eni = stones.length - 1;
	    	while (sti <= eni) {
	    		int mid = (sti + eni) / 2;
	    		if (stones[mid] == val) {
	    			return mid;
	    		} else if (stones[mid] > val) {
	    			eni = mid - 1;
	    		} else {
	    			sti = mid + 1;
	    		}
	    	}
	    	return -1;
	    }
	}
}
