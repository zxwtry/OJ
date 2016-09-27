package leetcode;

/*
 * 	There are N gas stations along a circular route, 
 * 	where the amount of gas at station i is gas[i].

	You have a car with an unlimited gas tank and 
	it costs cost[i] of gas to travel from station i 
	to its next station (i+1). You begin the journey with 
	an empty tank at one of the gas stations.
	
	Return the starting gas station's index if you can
	 travel around the circuit once, otherwise return -1.
	
	Note:
	The solution is guaranteed to be unique.
 */

public class P134_GasStation {
	public static void main(String[] args) {
		int[] gas = null;
		int[] cost = null;
		gas = new int[] {
//				2,4
				4, 3, 5
		};
		cost = new int[] {
//				3,4
				4, 4, 4
		};
		Solution2 s = new Solution2();
		System.out.println(s.canCompleteCircuit(gas, cost));
	}
	/*
	 * 	遍历所有可能肯定能够算出答案
	 * 	时间复杂度是：O(N^2)
	 * 	TLE
	 */
	static class Solution {
		int len = 0;
	    public int canCompleteCircuit(int[] gas, int[] cost) {
	    	if (gas == null || cost == null || 
	    			gas.length != cost.length || gas.length == 0) {
	    		return -1;
	    	}
	    	len = gas.length;
	    	if (len == 1) {
	    		return gas[0] >= cost[0] ? 0 : -1;
	    	}
	    	for (int i = 0; i < len; i ++) {
	    		if (search(i, gas, cost)) {
	    			return i;
	    		}
	    	}
	        return - 1;
	    }
	    private boolean search(int i, int[] gas, int[] cost) {
	    	int gas_total = 0;
	    	int i_tralvel = i;
	    	while (true) {
	    		if (next_i(i_tralvel) == i) {
	    			return gas_total + gas[i_tralvel] - cost[i_tralvel] >= 0;
	    		}
	    		gas_total += gas[i_tralvel] - cost[i_tralvel];
	    		if (gas_total < 0) {
	    			return false;
	    		}
	    		i_tralvel = next_i(i_tralvel);
	    	}
		}
		int next_i(int i) {
	    	return (i + 1) % len;
	    }
	}
	/*
	 * 	必须低于O(N^2)才能AC
	 * 	1 ms
	 * 	10.61%
	 */
	static class Solution2 {
		int len = 0;
	    public int canCompleteCircuit(int[] gas, int[] cost) {
	    	if (gas == null || cost == null || 
	    			gas.length != cost.length || gas.length == 0) {
	    		return -1;
	    	}
	    	len = gas.length;
	    	if (len == 1) {
	    		return gas[0] >= cost[0] ? 0 : -1;
	    	}
	    	for (int i = 0; i < len; i ++) {
	    		int save_false = search(i, gas, cost);
	    		if (save_false == -1) {
	    			return i;
	    		}
	    		i = Math.max(i, save_false);
	    	}
	        return - 1;
	    }
	    private int search(int i, int[] gas, int[] cost) {
	    	int gas_total = 0;
	    	int i_tralvel = i;
	    	while (true) {
	    		if (next_i(i_tralvel) == i) {
	    			return gas_total + gas[i_tralvel] - cost[i_tralvel] >= 0 ? -1 : i_tralvel;
	    		}
	    		gas_total += gas[i_tralvel] - cost[i_tralvel];
	    		if (gas_total < 0) {
	    			return i_tralvel;
	    		}
	    		i_tralvel = next_i(i_tralvel);
	    	}
		}
		int next_i(int i) {
	    	return (i + 1) % len;
	    }
	}
}
