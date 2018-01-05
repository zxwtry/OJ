package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 	Given a List of words, return the words that can be typed using 
 *  letters of alphabet on only one row's of American keyboard like the image below.
 *  American keyboard
 *  Example 1:
 *  Input: ["Hello", "Alaska", "Dad", "Peace"]
 *  Output: ["Alaska", "Dad"]
 *  Note:
 *  You may use one character in the keyboard more than once.
 *  You may assume the input string will only contain letters of alphabet.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P500_KeyboardRow.java
 * @type        P500_KeyboardRow
 * @date        2017年2月8日 下午11:23:25
 * @details     Solution1: AC 5ms 42.49% 
 */
public class P500_KeyboardRow {
	static class Solution1 {
	    public String[] findWords(String[] words) {
	        String s1 = "qwertyuiop";
	        String s2 = "asdfghjkl";
	        String s3 = "zxcvbnm";
	        HashMap<Character, Integer> map = new HashMap<>();
	        for (int i = 0; i < s1.length(); i ++)
	            map.put(s1.charAt(i), 0);
	        for (int i = 0; i < s2.length(); i ++)
                map.put(s2.charAt(i), 1);
	        for (int i = 0; i < s3.length(); i ++)
                map.put(s3.charAt(i), 2);
	        ArrayList<String> ans = new ArrayList<>();
	        for (String nw : words) {
	            String w = nw.toLowerCase();
	            int v = -1;
	            for (int i = 0; v != -3 && i < w.length(); i ++) {
	                if (v == -1) v = map.get(w.charAt(i));
	                else if (v != map.get(w.charAt(i))) v = -3;
	            }
	            if (v != -3) ans.add(nw);
	        }
	        String[] answer = new String[ans.size()];
	        return ans.toArray(answer);
	    }
	}
}
