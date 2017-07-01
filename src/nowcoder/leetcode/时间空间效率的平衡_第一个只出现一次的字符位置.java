package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间空间效率的平衡_第一个只出现一次的字符位置.java
 * @date        2017年7月1日 下午12:40:33
 * @details     
 */
public class 时间空间效率的平衡_第一个只出现一次的字符位置 {
    static public class Solution {
        public int FirstNotRepeatingChar(String str) {
            int len = str == null ? 0 : str.length();
            int[] map = new int[128];
            for (int i = 0; i < len; i ++) {
                map[str.charAt(i)] ++;
            }
            for (int i = 0; i < len; i ++) {
                char c = str.charAt(i);
                if (map[c] == 1) return i;
            }
            return -1;
        }
    }
}
