package leetcode;

/*
 * 	Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

	Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
	
	Could you do this in O(n) runtime?
	
	Example:
	
	Input: [3, 10, 5, 25, 2, 8]
	
	Output: 28
	
	Explanation: The maximum result is 5 ^ 25 = 28.
 */

public class P421_MaximumXOROfTwoNumbersInAnArray {
	public static void main(String[] args) {
		syso
	}
//	static class Solution {
//	    public int findMaximumXOR(int[] nums) {
//	        int ans = 0;
//	        boolean[] bit1Excluded = new boolean[nums.length];
//	        boolean[] bit0Excluded = new boolean[nums.length];
//	        int startIndex = 30;
//	        for (; startIndex > -1; startIndex --) {
//	        	for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
//	        		if (getBitOfVal(nums[numsIndex], startIndex) == 1) {
//	        			break;
//	        		}
//	        	}
//	        }
//	        if (startIndex == -1) {
//	        	return 0;
//	        }
//	        boolean[] isBit0 = new boolean[nums.length];
//	        for (int index = startIndex; index > -1; index --) {
//	        	Arrays.fill(isBit0, false);
//	        	int countBit0Valuable = 0; 
//	        	int countBit1Valuable = 0; 
//	        	for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
//        			int bit = getBitOfVal(nums[numsIndex], index);
//        			if (bit == 0) {
//        				isBit0[numsIndex] = true;
//        				if (! bit0Excluded[numsIndex] || )
//        			}
//	        	}
//	        	for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
//	        		if (! bit0Excluded[numsIndex] && isBit0[numsIndex]) {
//	        			
//	        		}
//	        	}
//	        }
//	        return ans;
//	    }
//	    //bitIndex是指31-0这样的方式
//	    int getBitOfVal(int val, int bitIndex) {
//	    	return (val >> bitIndex) & 0x1;
//	    }
//	}
	
	static class Soution2 {
		int ans = 0;
		int[] nums = null;
		public int findMaximumXOR(int[] nums) {
			this.nums = nums;
			boolean[] list1Index = new boolean[nums.length];
			boolean[] list0Index = new boolean[nums.length];
	        int startBitIndex = 30;
	        for (; startBitIndex > -1; startBitIndex --) {
	        	boolean isAll0 = true;
	        	boolean isAll1 = true;
	        	for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
	        		if (getBitOfVal(nums[numsIndex], startBitIndex) == 1) {
	        			isAll0 = false;
	        		} else {
	        			isAll1 = false;
	        		}
	        	}
	        	if (! isAll0 && ! isAll1) {
	        		break;
	        	}
	        }
	        if (startBitIndex == -1) {
	        	return 0;
	        } else if (startBitIndex == 0) {
	        	return 1;
	        }
	        for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
	        	if (getBitOfVal(nums[numsIndex], startBitIndex) == 1) {
	        		list1Index[numsIndex] = true;
	        	} else {
	        		list0Index[numsIndex] = true;
	        	}
	        }
	        search(1 << startBitIndex, list1Index, list0Index, startBitIndex - 1);
	        return ans;
		}
		private void search(int ansTemp, boolean[] list1Index, boolean[] list0Index, int bitIndex) {
			if (bitIndex < 0) {
				return;
			}
			boolean isAll0 = true;
			boolean isAll1 = true;
			for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
				if (list0Index[numsIndex] || list1Index[numsIndex]) {
					if (getBitOfVal(nums[numsIndex], bitIndex) == 1) {
						isAll0 = false;
					} else {
						isAll1 = false;
					}
					if (! isAll0 && ! isAll1) {
						ansTemp += 1 << bitIndex;
		        		break;
		        	}
				}
			}
			if (bitIndex == 0) {
				ans = Math.max(ans, ansTemp);
				return;
			}
			for (int numsIndex = 0; numsIndex < nums.length; numsIndex ++) {
				int bit = getBitOfVal(nums[numsIndex], bitIndex - 1);
				if (bit == 0) {
					searchToFind1(ansTemp, list1Index, list0Index, bitIndex - 1);
				} else {
					searchToFind0(ansTemp, list1Index, list0Index, bitIndex - 1);
				}
			}
		}
		private void searchToFind0(int ansTemp, boolean[] list1Index, boolean[] list0Index, int bitIndex) {
			
		}
		private void searchToFind1(int ansTemp, boolean[] list1Index, boolean[] list0Index, int bitIndex) {
			
		}
		//bitIndex是指31-0这样的方式
	    int getBitOfVal(int val, int bitIndex) {
	    	return (val >> bitIndex) & 0x1;
	    }
	}
}