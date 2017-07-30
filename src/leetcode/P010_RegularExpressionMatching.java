package leetcode;

public class P010_RegularExpressionMatching {
	static class Solution1 {
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) return s == null && p == null;
            return isMatch(s, 0, p, 0);
        }
        private boolean isMatch(String s, int si, String p, int pi) {
            if (acc(p, pi) == '\0')   return acc(s, si) == '\0';
            if (acc(p, pi + 1) == '*') {
                while (matchNow(s, si, p, pi))
                    if (isMatch(s, si ++, p, pi + 2))   //匹配N次
                        return true;
                return isMatch(s, si, p, pi + 2);       //匹配0次
            } else {
                if (matchNow(s, si, p, pi))
                    return isMatch(s, si + 1, p, pi + 1);
                return false;
            }
        }
        boolean matchNow(String s, int si, String p, int pi) {
            return acc(s, si) == acc(p, pi) || (acc(p, pi) == '.' && acc(s, si) != '\0');
        }
        char acc(String s, int i) {
            return i == s.length() ? '\0' : s.charAt(i);
        }
    }
	static class Solution2 {
	    public boolean isMatch(String s, String p) {
	        if (s == null || p == null) return s == null && p == null;
	        int sn = s.length(), pn = p.length();
	        boolean[][] m = new boolean[sn + 1][pn + 1];
	        m[0][0] = true;
	        for (int pi = 0; pi < pn; pi ++)
	            if (p.charAt(pi) == '*' && m[0][pi - 1])
	                m[0][pi + 1] = true;
	        for (int si = 0; si < sn; si ++) {
	            for (int pi = 0; pi < pn; pi ++) {
	                char sc = s.charAt(si), pc = p.charAt(pi);
	                if (sc == pc || '.' == pc) {
	                    m[si + 1][pi + 1] = m[si][pi];     //匹配1次
	                } else if (pc == '*') {
	                    //[pi-1, pi]结成共同体
	                    if (sc == p.charAt(pi - 1) || '.' == p.charAt(pi - 1)) {
 	                        m[si + 1][pi + 1] = 
	                                m[si][pi + 1] ||       //匹配很多次 s[si]和{p[pi-1], p[pi]}已经匹配上
	                                                       //肯定s[0,si-1]还要和p[0,pi]去匹配嘛
	                                m[si + 1][pi - 1] ||   //匹配0次    按照道理应该是s[0,si]和p[0,pi-2]去匹配
	                                m[si][pi - 1];         //匹配1次    s[si]和{p[pi-1], p[pi]}匹配上
	                                                       //就是看，s[0,si-1]和p[0,pi-2]是否能够匹配上
	                    } else {
	                        m[si + 1][pi + 1] = m[si + 1][pi - 1];     //只能匹配0次
	                    }
	                } else {
	                    m[si + 1][pi + 1] = false;
	                }
	            }
	        }
	        return m[sn][pn];
	    }
	}
}
