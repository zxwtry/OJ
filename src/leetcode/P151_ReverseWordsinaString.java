package leetcode;

/*
 * 	Given an input string, reverse the string word by word.

	For example,
	Given s = "the sky is blue",
	return "blue is sky the".
	
	Update (2015-02-12):
	For C programmers: Try to solve it in-place in O(1) space.
 */

public class P151_ReverseWordsinaString {
	public static void main(String[] args) {
		Solution2 s = new Solution2();
//		String input = "  abcd  jjyy  uuu  ";
//		String input = "a ";
		String input = "  a   b    c    d   e   f   ";
		String line1 = null, line2 = null;
		System.out.println(line1 = s.reverseWords(input));
		Solution s1 = new Solution();
		System.out.println(line2 = s1.reverseWords(input));
		System.out.println(line1.equals(line2));
	}
	/*
	 * 	7 ms
	 * 	66.10%
	 */
	static class Solution {
	    public String reverseWords(String s) {
	    	char[] cs = s.toCharArray();
	    	reverse(cs, 0, cs.length - 1);
	    	int sti = 0, eni = cs.length - 1;
	    	while (sti < cs.length && cs[sti] == ' ') {
	    		sti ++;
	    	}
	    	while (eni > -1 && cs[eni] == ' ') {
	    		eni --;
	    	}
	    	int pre = sti - 1;
	        for (int i = sti; i <= eni + 1; i ++) {
	        	if (i == eni + 1 || cs[i] == ' ') {
	        		if (pre + 1 < i - 1) {
	        			reverse(cs, pre + 1, i - 1);
	        		}
	        		pre = i;
	        	}
	        }
	        if (sti > eni) {
	        	return "";
	        }
	        StringBuilder st = new StringBuilder(eni - sti + 1);
	        for (int i = sti; i <= eni; i ++) {
	        	if (i != sti && cs[i] == ' ' && cs[i - 1] == ' ') {
	        		
	        	} else {
	        		st.append(cs[i]);
	        	}
	        }
	        return st.toString();
	    }
		private void reverse(char[] cs, int i, int j) {
			while (i < j) {
				char c = cs[i];
				cs[i] = cs[j];
				cs[j] = c;
				i ++;
				j --;
			}
		}
	}
	/*
	 * 	Solution是可以在同等时间复杂度的情况下优化的。
	 * 	试试吧
	 */
	static class Solution2 {
	    public String reverseWords(String s) {
	    	char[] cs = s.toCharArray();
	    	reverse(cs, 0, cs.length - 1);
	    	int sti = 0, eni = cs.length - 1;
	    	while (sti < cs.length && cs[sti] == ' ') {
	    		sti ++;
	    	}
	    	while (eni > -1 && cs[eni] == ' ') {
	    		eni --;
	    	}
	    	int pre = sti - 1;
	    	char[] ch = new char[cs.length + 3];
	    	int chIndex = 0;
	    	for (int i = sti; i <= eni + 1; i ++) {
	        	if (i == eni + 1 || cs[i] == ' ') {
	        		if (pre + 1 <= i - 1) {
//	        			reverse(cs, pre + 1, i - 1);
//	        			System.out.printf("1: %d, 2:%d\r\n", pre + 1, i - 1);
	        			for (int k = i - 1; k >= pre + 1; k --) {
	        				ch[chIndex ++] = cs[k];
	        			}
	        			ch[chIndex ++] = ' ';
	        		}
	        		pre = i;
	        	}
	        }
	        if (sti > eni) {
	        	return "";
	        }
	        
//	        for (int i = sti; i <= eni; i ++) {
//	        	if (i != sti && cs[i] == ' ' && cs[i - 1] == ' ') {
//	        		
//	        	} else {
//	        		st.append(cs[i]);
//	        	}
//	        }
	        
	        return new String(ch, 0, chIndex - 1);
	    }
	    private void reverse(char[] cs, int i, int j) {
			while (i < j) {
				char c = cs[i];
				cs[i] = cs[j];
				cs[j] = c;
				i ++;
				j --;
			}
		}
	}
}
