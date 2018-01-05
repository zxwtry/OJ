package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  In the computer world, use restricted resource you have to 
 *  generate maximum benefit is what we always want to pursue.
 *  
 *  For now, suppose you are a dominator of m 0s and n 1s respectively.
 *  On the other hand, there is an array with strings consisting of only 0s and 1s.
 *
 *  Now your task is to find the maximum number of strings that
 *  you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 *
 *  Note:
 *  The given numbers of 0s and 1s will both not exceed 100
 *  The size of given string array won't exceed 600.
 *  Example 1:
 *  Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 *  Output: 4
 *
 *  Explanation: This are totally 4 strings can be formed by
 *  the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 *  Example 2:
 *  Input: Array = {"10", "0", "1"}, m = 1, n = 1
 *  Output: 2
 *
 *  Explanation: You could form "10", but then you'd have
 *  nothing left. Better form "0" and "1".
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P474_OnesAndZeroes.java
 * @type        P474_OnesAndZeroes
 * @date        2017年3月8日 下午2:11:07
 * @details     Solution1: TLE
 * @details     Solution2: AC 48ms 61.52%
 */
public class P474_OnesAndZeroes {
    static class Solution1 {
        boolean[] iv = null;
        int maxForm = 0;
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] sign = new int[strs.length][2];
            iv = new boolean[strs.length];
            int signIndex = 0;
            int countOfOne = 0;
            for (String str : strs) {
                countOfOne = 0;
                for (int index = str.length() - 1; index > -1; index --)
                    countOfOne += str.charAt(index) == '1' ? 1 : 0;
                sign[signIndex][0] = str.length() - countOfOne;
                sign[signIndex ++][1] = countOfOne;
            }
            Arrays.sort(sign, new Comparator<int[]>() {
                @Override
                public int compare(int[] arr1, int[] arr2) {
                    if (arr1[0] > arr2[0]) return -1;
                    else if (arr1[0] < arr2[0]) return 1;
                    else {
                        if (arr1[1] > arr2[1]) return -1;
                        else if (arr1[1] < arr2[1])return 1;
                        return 0;
                    }
                }
            });
            for (int index = strs.length - 1; index >= maxForm; index --) {
                if (m - sign[index][0] < 0 || n - sign[index][1] < 0) continue;
                search(sign, new int[] {m - sign[index][0], n - sign[index][1]}, index, 1);
            }
            return maxForm;
        }
        private void search(int[][] sign, int[] mn, int index, int count) {
            maxForm = Math.max(maxForm, count);
            for (int i = index - 1; i > -1 && i >= maxForm - count; i --) {
                if (mn[0] < sign[i][0] || mn[1] < sign[i][1]) continue;
                mn[0] -= sign[i][0]; 
                mn[1] -= sign[i][1]; 
                search(sign, mn, i, count + 1);
                mn[0] += sign[i][0]; 
                mn[1] += sign[i][1]; 
            }
        }
    }
    static class Solution2 {
        public int findMaxForm(String[] strs, int m, int n) {
            int[][] sign = new int[strs.length][2];
            for (int index = 0; index < strs.length; index ++)
                for (int j = 0; j < strs[index].length(); j ++)
                    sign[index][strs[index].charAt(j) - '0'] ++;
            int[][] map = new int[m + 1][n + 1];
            for (int index = 0; index < strs.length; index ++) {
                for (int i = m; i >= sign[index][0]; i --) {
                    for (int j = n; j >= sign[index][1]; j --) {
                        map[i][j] = Math.max(map[i][j], 1 + map[i - sign[index][0]][j - sign[index][1]]);
                    }
                }
            }
            return map[m][n];
        }
    }
}
