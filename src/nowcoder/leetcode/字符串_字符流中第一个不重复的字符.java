package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        字符串_字符流中第一个不重复的字符.java
 * @date        2017年7月2日 下午9:27:01
 * @details     剑指Offer
 */
public class 字符串_字符流中第一个不重复的字符 {
    static public class Solution {
        //Insert one char from stringstream
        int[] map = new int[128];
        StringBuilder st = new StringBuilder();
        public void Insert(char ch)
        {
            map[ch] ++;
            st.append(ch);
        }
      //return the first appearence once char in current stringstream
        public char FirstAppearingOnce()
        {
            for (int i = 0, len = st.length(); i < len; i ++) {
                if (map[st.charAt(i)] == 1) return st.charAt(i);
            }
            return '#';
        }
    }
}
