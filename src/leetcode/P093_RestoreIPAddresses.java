package leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given a string containing only digits, 
 * 	restore it by returning all possible valid IP address combinations.

	For example:
	Given "25525511135",
	
	return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */


public class P093_RestoreIPAddresses {
	public static void main(String[] args) {
//		List<String> ans = new Solution().restoreIpAddresses("25525511135");
//		List<String> ans = new Solution().restoreIpAddresses("255255255255");
//		List<String> ans = new Solution().restoreIpAddresses("1231231231");
//		List<String> ans = new Solution().restoreIpAddresses("10004");
		List<String> ans = new Solution().restoreIpAddresses("010010");
		tools.Utils.B_打印List_String(ans);
	}
	static class Solution {
		List<String> ans = new LinkedList<String>();
		HashSet<String> set = new HashSet<>();
		int[] cs = null;
		int[] choice = new int[12];
	    public List<String> restoreIpAddresses(String s) {
	    	if (s == null || s.length() < 4 || s.length() > 12) {
	    		return ans;
	    	}
	    	cs = new int[s.length()];
	    	for (int i = cs.length - 1; i > -1; i --) { 
	    		cs[i] = s.charAt(i) - '0';
	    	}
	    	Arrays.fill(choice, - 1); 
	    	choice[0] = cs[0];
	    	choice[11] = cs[cs.length - 1];
	    	search(1, 10, 1, 1, cs.length - 2, 1);
	        return ans;
	    }
	    // chi,chj : choice的开始和结束index
	    // csi,csj : cs的开始和结束index
	    private void search(int chi, int chj, int chk, int csi, int csj, int csk) {
	    	if (chk > chj || csk > csj) {
	    		isLegal();
	    		return;
	    	}
	    	if (csj - csk > chj - chk) {
	    		return;
	    	}
	    	for (int cht = chk; cht <= chj; cht ++) {
	    		choice[cht] = cs[csk];
	    		search(chi, chj, cht + 1, csi, csj, csk + 1);
	    		choice[cht] = -1;
	    	}
	    }
		// i从0开始
	    boolean isLegal() {
	    	int[] parts = new int[4];
	    	int count = 0;
	    	for (int i = 0; i < parts.length; i ++) {
	    		parts[i] = 0;
		    	int index = i * 3;
		    	boolean isAll_blank = true;
		    	for (int j = 0; j < 3; j ++) {
		    		if (choice[index + j] == -1) {
		    			continue;
		    		}
		    		isAll_blank = false;
		    		count ++;
		    		parts[i] = parts[i] * 10 + choice[index + j];
		    	}
		    	if (isAll_blank) {
		    		return false;
		    	}
		    	if (parts[i] < 0 || parts[i] > 255) {
		    		return false;
		    	}
	    	}
	    	String temp = String.format("%d.%d.%d.%d", parts[0], parts[1], parts[2], parts[3]);
	    	if (count == cs.length  && ! set.contains(temp)) {
		    	ans.add(temp);
		    	set.add(temp);
	    	}
	    	return true;
	    }
	}
}
