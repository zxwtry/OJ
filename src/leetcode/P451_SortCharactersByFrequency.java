package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 *  Given a string, sort it in decreasing order based on the frequency of characters.
 *  Example 1:
 *  Input:
 *  "tree"
 *  Output:
 *  "eert"
 *  Explanation:
 *  'e' appears twice while 'r' and 't' both appear once.
 *  So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 *  Example 2:
 *  
 *  Input:
 *  "cccaaa"
 *  Output:
 *  "cccaaa"
 *  
 *  Explanation:
 *  Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 *  Note that "cacaca" is incorrect, as the same characters must be together.
 *  Example 3:
 *  
 *  Input:
 *  "Aabb"
 *  
 *  Output:
 *  "bbAa"
 *  
 *  Explanation:
 *  "bbaA" is also a valid answer, but "Aabb" is incorrect.
 *  Note that 'A' and 'a' are treated as two different characters.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P451_SortCharactersByFrequency.java
 * @type        P451_SortCharactersByFrequency
 * @date        2017年3月14日 下午8:54:13
 * @details     Solution1: AC 15ms 90.79%
 */
public class P451_SortCharactersByFrequency {
    static class Solution1 {
        public String frequencySort(String s) {
            if (s == null || s.length() == 0) return s;
            int sn = s.length();
            int n = 256;
            int[] charCount = new int[n];
            Integer[] cCIndex = new Integer[n];
            for (int i = 0; i < n; i ++)
                cCIndex[i] = i;
            for (int i = 0; i < sn; i ++) {
                charCount[s.charAt(i)] ++;
            }
            Arrays.sort(cCIndex, new Comparator<Integer>() {
                @Override
                public int compare(Integer int1, Integer int2) {
                    if (charCount[int1] > charCount[int2]) return -1;
                    else if (charCount[int1] == charCount[int2]) return 0;
                    return 1;
                }
            });
            char[] ans = new char[sn];
            int ansIndex = 0;
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < charCount[cCIndex[i]]; j ++)
                    ans[ansIndex ++] = (char)((int)cCIndex[i]);
            }
            return new String(ans);
        }
    }
}
