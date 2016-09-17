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
//		List<String> ans = new Solution2().restoreIpAddresses("25525511135");
		List<String> ans = new Solution2().restoreIpAddresses("2552552563");
//		List<String> ans = new Solution2().restoreIpAddresses("255255255255");
//		List<String> ans = new Solution2().restoreIpAddresses("1231231231");
//		List<String> ans = new Solution2().restoreIpAddresses("1234");
//		List<String> ans = new Solution2().restoreIpAddresses("0000");
//		List<String> ans = new Solution2().restoreIpAddresses("11234");
//		List<String> ans = new Solution2().restoreIpAddresses("010010");
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
	    	int first_not_zero = 0;
	    	while (true) {
	    		if (first_not_zero > 3 || cs[first_not_zero] != 0) {
	    			break;
	    		} else {
	    			first_not_zero ++;
	    		}
	    	}
	    	if (cs.length == 4) {
		    	if (first_not_zero == 4) {
	    			ans.add("0.0.0.0");
	    			return ans;
		    	} else {
		    		ans.add(String.format("%d.%d.%d.%d", cs[0], cs[1], cs[2], cs[3]));
		    		return ans;
		    	}
    		} else if (first_not_zero == 0) {
		    	choice[0] = cs[0];
		    	choice[11] = cs[cs.length - 1];
		    	search(1, 10, 1, 1, cs.length - 2, 1);
	    	} else {
	    		for (int i = 0; i < first_not_zero * 3; i ++) {
	    			choice[i] = 0;
	    		}
	    		choice[3 * first_not_zero] = cs[first_not_zero];
	    		choice[11] = cs[cs.length - 1];
	    		search(3 * first_not_zero + 1, 10, 3 * first_not_zero + 1, first_not_zero + 1, cs.length - 2, first_not_zero + 1);
	    	}
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
		    	int this_count = 0;
		    	for (int j = 0; j < 3; j ++) {
		    		if (choice[index + j] == -1) {
		    			continue;
		    		}
		    		isAll_blank = false;
		    		this_count ++;
		    		count ++;
		    		if (j != 0 && parts[i] == 0 && choice[index + j] != 0) {
		    			return false;
		    		}
		    		parts[i] = parts[i] * 10 + choice[index + j];
		    	}
		    	if (parts[i] == 0 && this_count != 1) {
		    		count -= (this_count - 1);
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
	/*
	 * 	AC
	 * 	需要更加简化逻辑，现在的逻辑还是太复杂了.
	 * 	11 ms
	 */
	static class Solution2 {
		List<String> ans = new LinkedList<String>();
		int[] ar = null, ch = null;		// ar是输入的int[]版，ch是选择的12个数字中的一个
		int count = 0, arJ = 0, this_count = 0, del_count = 0;
		HashSet<String> set = new HashSet<>();
		public List<String> restoreIpAddresses(String s) {
			if (s == null || s.length() < 4 || s.length() > 12) {
	    		return ans;
	    	}
			ar = new int[s.length()];
			ch = new int[12];
			arJ = ar.length - 1;
			for (int  i = 0; i < ar.length; i ++) {
				ar[i] = s.charAt(i) - '0';
			}
			Arrays.fill(ch, -1);
			ch[0] = ar[0];
			ch[ch.length - 1] = ar[ar.length - 1];
			int ar_i = 1;
			int ch_min = 1, ch_max = ch.length - 2;
			search(ar_i, ch_min, ch_max);
			return ans;
		}
		// [ar_min, ar_max]  [ch_min, ch_max]
		void search(int ar_i, int ch_min, int ch_max) {
			if (this_count >= ar.length - 2) {
				if (judge()) {
					System.out.printf("第\t%d\t次数据\r\n", del_count ++);
					tools.Utils.printArray(ar, 20);
					tools.Utils.printArray(ch, 20);
				}
				return;
			}
			for (int ch_now = ch_min; ch_now <= ch_max; ch_now ++) {
				ch[ch_now] = ar[ar_i];
				this_count ++;
				search(ar_i + 1, ch_now + 1, ch_max);
				this_count --;
				ch[ch_now] = -1;
			}
		}
		boolean judge() {
			boolean isAllFailue = false;
			StringBuilder st = new StringBuilder(16);
			for (int i = 0; ! isAllFailue && i < 4; i ++) {
				int base = i * 3;
				if (ch[base + 0] == -1 && ch[base + 1] == -1 && ch[base + 2] == -1) {
					isAllFailue = true;
					continue;
				} else if (ch[base + 0] == 0 && (ch[base + 1] != 0 && ch[base + 1] != -1)) {
					isAllFailue = true;
					continue;
				} else if (ch[base + 0] == 0 && ch[base + 1] == 0 && ch[base + 2] != -1) {
					isAllFailue = true;
					continue;
				}
				int value = 0;
				boolean isFirstNot_N_1 = false;
				for (int j = 0; ! isAllFailue && j < 3; j ++) {
					if (ch[base + j] != -1) {
						int temp = value * 10 + ch[base + j];
						if (!isFirstNot_N_1 || (temp != value && value != 0)) {
							value = temp;
						} else {
							isAllFailue = true;
						}
						isFirstNot_N_1 = true;
					}
				}
				if (value < 0 || value > 255) {
					isAllFailue = true;
				}
				st.append(i == 0 ? "" : ".");
				st.append(value);
			}
			if (isAllFailue) {
				return false;
			}
			String now = st.toString();
			if (! set.contains(now)) {
				ans.add(now);
				set.add(now);
				return true;
			}
			return false;
		}
	}
}