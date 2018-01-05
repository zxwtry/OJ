package leetcode;

import java.util.HashMap;
import java.util.List;

/*
    Given a string s and a dictionary of words dict, 
    determine if s can be segmented into a space-separated 
    sequence of one or more dictionary words.

	For example, given
	s = "leetcode",
	dict = ["leet", "code"].
	
	Return true because "leetcode" can be segmented as "leet code".
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P139_WordBreak.java
 * @date        2017年5月29日 上午10:20:42
 * @details     Solution1: AC 18ms 41.08%
 * @details     Solution2: AC 11ms 86.69%
 */
public class P139_WordBreak {
    static class Solution1 {
        public boolean wordBreak(String s, List<String> wd) {
            int sn = s == null ? 0 : s.length();
            HashMap<String, Boolean> nm = new HashMap<>(sn);
            for (String w : wd) nm.put(w, true);
            return search(s, 0, sn, nm);
        }
        private boolean search(String s, int si, int sn, HashMap<String, Boolean> nm) {
            if (si == sn) return true;
            String res = s.substring(si, sn);
            Boolean resVal = nm.get(res);
            if (resVal != null && (!resVal)) return false;
            boolean ans = false;
            for (int i = si+1; (!ans) && i <= sn; i ++) {
                String n = s.substring(si, i);
                Boolean nVal = nm.get(n);
                if (nVal != null && nVal)
                    ans |= search(s, i, sn, nm);
            }
            nm.put(res, ans);
            return ans;
        }
    }
    static class Solution2 {
        public boolean wordBreak(String s, List<String> wd) {
            int sn = s == null ? 0 : s.length();
            if (sn == 0) return true;
            boolean[] dp = new boolean[sn + 1];
            dp[0] = true;
            int wn = 0;
            for (int i = 0; i < sn; i ++) {
                if (! dp[i]) continue;
                for (String w : wd) {
                    wn = w == null ? 0 : w.length();
                    if (i + wn <= sn && (! dp[i+wn]) && compare(s, i, w, wn)) {
                        dp[i+wn] = true;
                    }
                }
            }
            return dp[sn];
        }
        private boolean compare(String s, int index, String t, int tn) {
            int ti = 0, si = index;
            for (; ti < tn; ti ++) {
                if (s.charAt(si ++) != t.charAt(ti))
                    return false;
            }
            return true;
        }
    }
}
