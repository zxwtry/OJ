package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        分解让复杂问题简单_字符串的排列.java
 * @date        2017年7月1日 上午9:35:06
 * @details     剑指Offer
 */
public class 分解让复杂问题简单_字符串的排列 {
    static public class Solution {
        public ArrayList<String> Permutation(String str) {
            if (str == null) return new ArrayList<String>(0);
            char[] cs = str.toCharArray();
            if (cs.length == 0) return new ArrayList<String>(0);
            ArrayList<String> l = new ArrayList<String> ();
            Arrays.sort(cs);
            search(cs, 0, cs.length, l);
            Collections.sort(l);
            return l;
        }
        void search(char[] cs, int csIndex, int csLength, ArrayList<String> l) {
            if (csIndex == csLength) {
                l.add(new String(cs));
                return;
            }
            for (int index = csIndex; index < csLength; index ++) {
                if (! isDuplicate(cs, csIndex, index)) {
                    swap(cs, csIndex, index);
                    search(cs, csIndex + 1, csLength, l);
                    swap(cs, csIndex, index);
                }
            }
        }
        void swap(char[] cs, int i, int j) {
            char t = cs[i];
            cs[i] = cs[j];
            cs[j] = t;
        }
        boolean isDuplicate(char[] cs, int csIndex, int index) {
            for (int i = csIndex; i < index; i ++)
                if (cs[i] == cs[index])
                    return true;
            return false;
        }
    }
}
