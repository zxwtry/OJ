package leetcode;

import java.util.Arrays;


/*
 * 	Given a list of non negative integers, arrange them such that they form the largest number.

	For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
	
	Note: The result may be very large, so you need to return a string instead of an integer.
 */

public class P179_LargestNumber {
	public static void main(String[] args) {
//		int[] nums = new int[] {9, 9, 990, 991, 3, 0};
//		Solution s = new Solution();
//		System.out.println(s.largestNumber(nums));
		int[] nums = tools.Random随机生成器.A_生成一个随机数据(20000, 0, 1001);
//		int[] nums = new int[] {0, 0};
//		String s = tools.Utils.LEETCODE_int_array_序列化_(num);
//		System.out.println(s);
		
//		int[] nums = tools.Utils.LEETCODE_int_array_反序列化_("[89, 899]");
		Solution s = new Solution();
		String s1 = s.largestNumber(nums);
		
		SolutionOwnSort sCut = new SolutionOwnSort();
		String s2 = sCut.largestNumber(nums);
		
		System.out.println(s1.equals(s2));
		
	}
	/*
	 * 
	 * 	用Object数据的方式
	 * 	143 ms
	 * 	22.51%
	 */
	static class Solution {
	    public String largestNumber(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return "";
	    	}
	    	My[] ms = new My[nums.length];
	    	int lenOfAns = 0;
	    	for (int i = 0; i < nums.length; i ++) {
	    		ms[i] = new My(nums[i]);
	    		lenOfAns += ms[i].arr.length;
	    	}
	    	Arrays.sort(ms);
	    	char[] ans = new char[lenOfAns];
	    	int indexOfAns = 0;
	    	for (My m : ms) {
	    		System.arraycopy(m.arr, 0, ans, indexOfAns, m.arr.length);
	    		indexOfAns += m.arr.length;
	    	}
	    	if (ans[0] == '0') {
	    		return "0";
	    	}
	        return new String(ans);
	    }
	    static class My implements Comparable<My> {
	    	int val = 0;
	    	char[] arr = null;
	    	public My(int val) {
	    		this.val = val;
	    		generate();
	    	}
			@Override
			public int compareTo(My o) {
//				char[] arrOther = o.arr;
//				int minLen = Math.min(arr.length, arrOther.length);
//				for (int i = 0; i < minLen; i ++) {
//					if (arr[i] != arrOther[i]) {
//						return arrOther[i] - arr[i];
//					}
//				}
//				if (arr.length != minLen) {
//					
//					return 1;
//				}
//				if (arrOther.length != minLen) {
//					return -1;
//				}
				return compare(arr, o.arr);
			}
			int compare(char[] arr1, char[] arr2) {
				int len1 = arr1.length, len2 = arr2.length;
				int i1 = 0, i2 = 0, count = 0;
				boolean isEnd1 = false, isEnd2 = false;
				while (! (isEnd1 && isEnd2) ) {
					while (i1 < len1 && i2 < len2) {
						if (arr1[i1] != arr2[i2]) {
							return arr2[i2] - arr1[i1];
						}
						count ++;
						i1 ++;
						i2 ++;
					}
					if (count > 2 * (len1 + len2)) {
						return 0;
					}
					if (i1 == len1) {
						i1 = 0;
					}
					if (i2 == len2) {
						i2 = 0;
					}
				}
				return 0;
			}
			void generate() {
				int valCopy = val;
				if (valCopy == 0) {
					arr = new char[] {'0'};
					return;
				}
				int arrLen = (int)Math.log10(valCopy) + 1;
				arr = new char[arrLen];
				for (int i = arrLen - 1; i > -1; i --) {
					arr[i] = (char)('0' + valCopy % 10);
					valCopy = valCopy / 10;
				}
			}
//			@Override
//			public String toString() {
//				// TODO Auto-generated method stub
//				return new String(arr);
//			}
	    }
	}
	/*
	 * 	100 ms
	 * 	98.96%
	 */
	static class SolutionOwnSort {
	    public String largestNumber(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return "";
	    	}
	    	int lenNums = nums.length;
	    	char[][] bits = new char[lenNums][];
	    	int ansLen = generateBits(nums, bits);
	    	quickSort(bits, 0, lenNums - 1);
	    	char[] ans = new char[ansLen];
	    	int indexOfAns = 0;
	    	if (bits[0][0] == '0') {
	    		return "0";
	    	}
	    	for (int i = 0; i < lenNums; i ++) {
	    		System.arraycopy(bits[i], 0, ans, indexOfAns, bits[i].length);
	    		indexOfAns += bits[i].length;
	    	}
	    	return new String(ans);
	    }
		private void quickSort(char[][] bits, int sti, int eni) {
			if (sti >= eni) {
				return;
			}
			int p = partition(bits, sti, eni);
			quickSort(bits, sti, p - 1);
			quickSort(bits, p + 1, eni);
		}
		private int partition(char[][] bits, int sti, int eni) {
			char[] v = bits[sti];
			while (sti < eni) {
				while (sti < eni && compare(v, bits[eni]) <= 0)		eni --;
				bits[sti] = bits[eni];
				while (sti < eni && compare(v, bits[sti]) >= 0)		sti ++;
				bits[eni] = bits[sti];
			}
			bits[sti] = v;
			return sti;
		}
		private int generateBits(int[] nums, char[][] bits) {
			int ansLen = 0;
			for (int i = 0; i < nums.length; i ++) {
				bits[i] = generate(nums[i]);
				ansLen += bits[i].length;
			}
			return ansLen;
		}
		char[] generate(int val) {
			int valCopy = val;
			char[] arr = null;
			if (valCopy == 0) {
				arr = new char[] {'0'};
				return arr;
			}
			int arrLen = (int)Math.log10(valCopy) + 1;
			arr = new char[arrLen];
			for (int i = arrLen - 1; i > -1; i --) {
				arr[i] = (char)('0' + valCopy % 10);
				valCopy = valCopy / 10;
			}
			return arr;
		}
		int compare(char[] arr1, char[] arr2) {
			int len1 = arr1.length, len2 = arr2.length;
			int i1 = 0, i2 = 0, count = 0;
			boolean isEnd1 = false, isEnd2 = false;
			while (! (isEnd1 && isEnd2) ) {
				while (i1 < len1 && i2 < len2) {
					if (arr1[i1] != arr2[i2]) {
						return arr2[i2] - arr1[i1];
					}
					count ++;
					i1 ++;
					i2 ++;
				}
				if (count > 2 * (len1 + len2)) {
					return 0;
				}
				if (i1 == len1) {
					i1 = 0;
				}
				if (i2 == len2) {
					i2 = 0;
				}
			}
			return 0;
		}
	}
}
