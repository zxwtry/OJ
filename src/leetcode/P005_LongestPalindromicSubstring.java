package leetcode;

/*
 * 	假定存在唯一一个最长子序列
 */
public class P005_LongestPalindromicSubstring {
	public static void main(String[] args) {
		System.out.println(new Solution3().longestPalindrome("abcdcban"));
	}
	/*
	 * 	毫无疑问manacher(不会拼错了吧)
	 * 	35.45%
	 * 	特别慢
	 * 	57ms
	 */
	static class Solution {
		private final char sign = '#';
	    public String longestPalindrome(String s) {
	    	if (s == null || s.length() == 0)
	    		return "";
	        char[] m = new char[(s.length() << 1) + 1];
	        for (int i = 0; i != m.length; i ++)
	        	m[i] = (i & 0x1) == 0 ? sign : s.charAt(i >>> 1);
	        int[] r = new int[m.length];
	        r[0] = 0; r[1] = 1;
	        int maxr = 2, core = 1, maxindex = 1;
	        for (int i = 2; i != m.length; i ++) {
	        	if (i >= maxr) {
	        		int newr = 1;
	        		while (i > newr - 1 && i + newr < m.length && m[i + newr] == m[i - newr])
	        			newr ++;
	        		r[i] = newr - 1;
	        		maxr = i + r[i];
	        		core = i;
	        	} else {
	        		int j = (core << 1) - i;
	        		if (r[j] + i < maxr) {
	        			r[i] = r[j];
	        		} else {
	        			int newr = 1;
	        			while (i > newr - 1 && i + newr < m.length && m[i + newr] == m[i - newr])
		        			newr ++;
	        			r[i] = newr - 1;
	        			maxr = i + r[i];
	        			core = i;
	        		}
	        	}
	        	if (r[maxindex] < r[i])
	        		maxindex = i;
	        	if (maxr == m.length)
	        		break;
	        }
	        StringBuilder st = new StringBuilder();
	        for (int i = maxindex - r[maxindex]; i != maxindex + r[maxindex] + 1; i ++)
	        	if ((i & 0x1) == 1)
	        		st.append(m[i]);
	        return st.toString();
	    }
	}
	/*
	 * 	没有使用StringBuilder
	 * 	40.94%
	 * 	43 ms
	 */
	static class Solution2 {
		private final char sign = '#';
	    public String longestPalindrome(String s) {
	    	if (s == null || s.length() == 0)
	    		return "";
	        char[] m = new char[(s.length() << 1) + 1];
	        for (int i = 0; i != m.length; i ++)
	        	m[i] = (i & 0x1) == 0 ? sign : s.charAt(i >>> 1);
	        int[] r = new int[m.length];
	        r[0] = 0; r[1] = 1;
	        int maxr = 2, core = 1, maxindex = 1;
	        for (int i = 2; i != m.length; i ++) {
	        	if (i >= maxr) {
	        		int newr = 1;
	        		while (i > newr - 1 && i + newr < m.length && m[i + newr] == m[i - newr])
	        			newr ++;
	        		r[i] = newr - 1;
	        		maxr = i + r[i];
	        		core = i;
	        	} else {
	        		int j = (core << 1) - i;
	        		if (r[j] + i < maxr) {
	        			r[i] = r[j];
	        		} else {
	        			int newr = 1;
	        			while (i > newr - 1 && i + newr < m.length && m[i + newr] == m[i - newr])
		        			newr ++;
	        			r[i] = newr - 1;
	        			maxr = i + r[i];
	        			core = i;
	        		}
	        	}
	        	if (r[maxindex] < r[i])
	        		maxindex = i;
	        	if (maxr == m.length)
	        		break;
	        }
	        int len = m[maxindex] == sign ?  ( (r[maxindex] + 1) >>> 1 ) << 1 : ( ( r[maxindex] >>> 1) << 1 ) + 1;
	        char[] ans = new char[len];
	        int j = 0;
	        for (int i = maxindex - r[maxindex]; i != maxindex + r[maxindex] + 1; i ++)
	        	if ((i & 0x1) == 1)
	        		ans[j ++] = m[i];
	        return new String(ans);
	    }
	}
	/*
	 * 	不用m　char 数组
	 * 	75ms
	 * 	27.05% 
	 */
	static class Solution3 {
		private final char sign = '#';
	    public String longestPalindrome(String s) {
	    	if (s == null || s.length() == 0)
	    		return "";
	        int len = (s.length() << 1) + 1;
	        int[] r = new int[len];
	        r[0] = 0; r[1] = 1;
	        int maxr = 2, core = 1, maxindex = 1;
	        for (int i = 2; i != len; i ++) {
	        	if (i >= maxr) {
	        		int newr = 1;
	        		while (i > newr - 1 && i + newr < len && getIndexOfM(s, i + newr) == getIndexOfM(s, i - newr))
	        			newr ++;
	        		r[i] = newr - 1;
	        		maxr = i + r[i];
	        		core = i;
	        	} else {
	        		int j = (core << 1) - i;
	        		if (r[j] + i < maxr) {
	        			r[i] = r[j];
	        		} else {
	        			int newr = 1;
	        			while (i > newr - 1 && i + newr < len && getIndexOfM(s, i + newr) == getIndexOfM(s, i - newr))
		        			newr ++;
	        			r[i] = newr - 1;
	        			maxr = i + r[i];
	        			core = i;
	        		}
	        	}
	        	if (r[maxindex] < r[i])
	        		maxindex = i;
	        	if (maxr == len)
	        		break;
	        }
	        len = getIndexOfM(s, maxindex) == sign ?  ( (r[maxindex] + 1) >>> 1 ) << 1 : ( ( r[maxindex] >>> 1) << 1 ) + 1;
	        char[] ans = new char[len];
	        int j = 0;
	        for (int i = maxindex - r[maxindex]; i != maxindex + r[maxindex] + 1; i ++)
	        	if ((i & 0x1) == 1)
	        		ans[j ++] = getIndexOfM(s, i);
	        return new String(ans);
	    }
	    char getIndexOfM(String s, int i) {
	    	if ((i & 0x1) == 0)
	    		return sign;
	    	else
	    		return s.charAt(i >>> 1);
	    }
	}
}