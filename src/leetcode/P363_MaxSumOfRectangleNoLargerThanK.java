package leetcode;

import java.util.TreeSet;

/**
 * 	Given a non-empty 2D matrix matrix and an integer k,
 *  find the max sum of a rectangle in the matrix 
 *  such that its sum is no larger than k.
 *  
 *  Example:
 *  Given matrix = [
 *    [1,  0, 1],
 *    [0, -2, 3]
 *  ]
 *  k = 2
 *  The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 
 *  and 2 is the max number no larger than k (k = 2).
 *  
 *  Note:
 *  The rectangle inside the matrix must have an area > 0.
 *  What if the number of rows is much larger than the number of columns?
 *  Credits:
 *  Special thanks to @fujiaozhu for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P363_MaxSumOfRectangleNoLargerThanK.java
 * @type        P363_MaxSumOfRectangleNoLargerThanK
 * @date        2016年12月8日 下午10:37:04
 * @details     Solution1: AC 755ms  2.88%
 * @details     Solution2: AC 118ms 97.26%
 * @details     Solution3: AC 183ms 51.67%
 */
public class P363_MaxSumOfRectangleNoLargerThanK {
	static class Solution1 {
	    int row = 0, col = 0;
	    public int maxSumSubmatrix(int[][] matrix, int k) {
	        row = matrix.length;
	        col = matrix[0].length;
	        long[][] sum = new long[row][col];
	        int i = 0, j = 0;
            long answer = Integer.MIN_VALUE;
	        for (i = 0; i < row; i ++)
	            for (j = 0; j < col; j ++) {
	                sum[i][j] = (long)(accessMatrix(matrix, i, j)) - 
	                        (long)(accessSum(sum, i - 1, j - 1)) +
	                        (long)(accessSum(sum, i, j - 1)) +
	                        (long)(accessSum(sum, i - 1, j));
	                if (sum[i][j] == k || matrix[i][j] == k) return k;
	                if (matrix[i][j] < k) answer = Math.max(answer, matrix[i][j]);
	            }
	        for (i = 0; i < row; i ++)
                for (j = 0; j < col; j ++) {
                    for (int ii = 0; ii <= i; ii ++)
                        for (int jj = 0; jj <= j; jj ++) {
                            long target = accessSum(sum, i, j) -
                                    accessSum(sum, ii - 1, j) - 
                                    accessSum(sum, i, jj - 1) +
                                    accessSum(sum, ii - 1, jj - 1);
                            if (target == k) return k;
                            else if (target < k) answer = Math.max(answer, target);
                        }
                }
	        return (int)answer;
	    }
	    private long accessSum(long[][] sum, int i, int j) {
            if (i < 0 || j < 0 || i >= row || j >= col) return 0;
            return sum[i][j];
        }
	    private int accessMatrix(int[][] matrix, int i, int j) {
	        if (i < 0 || j < 0 || i >= row || j >= col) return 0;
	        return matrix[i][j];
	    }
	}
	static class Solution2 {
	    public int maxSumSubmatrix(int[][] matrix, int k) {
	        int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
	        long[] sum = new long[m+1]; // stores sum of rect[0..p][i..j]
	        for (int i = 0; i < n; ++i) {
	            long[] sumInRow = new long[m];
	            for (int j = i; j < n; ++j) { // for each rect[*][i..j]
	                for (int p = 0; p < m; ++p) {
	                    sumInRow[p] += matrix[p][j];
	                    sum[p+1] = sum[p] + sumInRow[p];
	                }
	                ans = Math.max(ans, mergeSort(sum, 0, m+1, k));
	                if (ans == k) return k;
	            }
	        }
	        return ans;
	    }
	    int mergeSort(long[] sum, int start, int end, int k) {
	        if (end == start+1) return Integer.MIN_VALUE; // need at least 2 to proceed
	        int mid = start + (end - start)/2, cnt = 0;
	        int ans = mergeSort(sum, start, mid, k);
	        if (ans == k) return k;
	        ans = Math.max(ans, mergeSort(sum, mid, end, k));
	        if (ans == k) return k;
	        long[] cache = new long[end-start];
	        for (int i = start, j = mid, p = mid; i < mid; ++i) {
	            while (j < end && sum[j] - sum[i] <= k) ++j;
	            if (j-1 >= mid) {
	                ans = Math.max(ans, (int)(sum[j-1] - sum[i]));
	                if (ans == k) return k;
	            }
	            while (p < end && sum[p] < sum[i]) cache[cnt++] = sum[p++];
	            cache[cnt++] = sum[i];
	        }
	        System.arraycopy(cache, 0, sum, start, cnt);
	        return ans;
	    }
	}
}
