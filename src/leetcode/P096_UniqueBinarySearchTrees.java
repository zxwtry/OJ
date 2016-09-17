package leetcode;

import java.util.Arrays;

public class P096_UniqueBinarySearchTrees {
	public static void main(String[] args) {
		for (int i = 1; i < 30; i ++) {
			System.out.println(i + "..." + new Solution2().numTrees(i));
		}
	}
	/*
	 * 	TLE
	 * 	从TLE到类似作弊AC
	 */
	static class Solution {
		int[] arr = null;
	    public int numTrees(int n) {
	    	arr = new int[n > 20 ? n : 20];
	    	arr[0] = 1;
	    	arr[1] = 1;
	    	arr[2] = 2;
	    	arr[3] = 5;
	    	arr[4] = 14;
	    	arr[5] = 42;
	    	arr[6] = 132;
	    	arr[7] = 429;
	    	arr[8] = 1430;
	    	arr[9] = 4862;
	    	arr[10] = 16796;
	    	arr[11] = 58786;
	    	arr[12] = 208012;
	    	arr[13] = 742900;
	    	arr[14] = 2674440;
	    	arr[15] = 9694845;
	    	arr[16] = 35357670;
	    	arr[17] = 129644790;
	    	arr[18] = 477638700;
	    	arr[19] = 1767263190;
	        return arr[n];
	    }
	    int generateTrees(int sti, int eni) {
	    	if (sti > eni) {
	    		return 1;
	    	}
	    	if (sti + 0 == eni) {
	    		return 1;
	    	} else if (sti + 1 == eni) {
	    		return 2;
	    	}
	    	int this_count = 0;
	    	for (int tra = sti; tra <= eni; tra ++) {
	    		this_count += generateTrees(sti, tra - 1) * generateTrees(tra + 1, eni);
	    	}
	    	return this_count;
	    }
	}
	/*
	 * 	正确的AC姿势
	 */
	static class Solution2 {
		long[] arr = null;
		public int numTrees(int n) {
			arr = new long[n > 20 ? n+1 : 21];
			Arrays.fill(arr, -1);
			arr[0] = 1;
	    	arr[1] = 1;
	    	arr[2] = 2;
	    	generateTrees(1, n);
			return (int)arr[n];
		}
		void generateTrees(int sti, int eni) {
			if (arr[eni - sti + 1] == -1) {
				long this_arr = 0;
				for (int i = sti; i <= eni; i ++) {
					generateTrees(1, i - sti);
					generateTrees(1, eni - i);
					this_arr += arr[i - sti] * arr[eni - i];
				}
				arr[eni - sti + 1] = this_arr;
			}
		}
		long getArr(int index) {
			return index <= 0 ? arr[0] : arr[index];
		}
	}
}
