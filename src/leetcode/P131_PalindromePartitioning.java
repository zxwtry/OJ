package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;



/*
 * 	Given a string s, partition s such that every substring 
 * 	of the partition is a palindrome.

	Return all possible palindrome partitioning of s.
	
	For example, given s = "aab",
	Return
	
	[
	  ["aa","b"],
	  ["a","a","b"]
	]
 */

public class P131_PalindromePartitioning {
	public static void main(String[] args) {
		char[] cs = "0123456".toCharArray();
		System.out.println(new String(cs, 0, 3));
//		Solution s = new Solution();
//		List<List<String>> ans = s.partition("aaaa");
//		Iterator<List<String>> it = ans.iterator();
//		while (it.hasNext()) {
//			tools.Utils.B_打印List_String_OneLine(it.next());
//		}
	}
	static class Solution {
		List<List<String>> ans_final = new LinkedList<>();
		HashSet<String> set = new HashSet<String>();
		char[] cs = null;
		public List<List<String>> partition(String s) {
			cs = s.toCharArray();
			List<List<Integer>> cut_now = new ArrayList<>(1);
			List<Integer> cut_this = new ArrayList<>(cs.length);
			for (int i = 0; i < cs.length; i ++) {
				cut_this.add(i);
			}
			cut_now.add(cut_this);
			
			
			for (int len = cs.length; len > 0; len --) {
				int[] arr = new int[len + 1];
//				int arr_index = 1;
				arr[0] = -1;
				
			}
			
			
	        return ans_final;
	    }
		void add_to_ans_final(int[] arr) {
			char[] cs_set = new char[arr.length - 2 + cs.length];
			int cs_set_index = 0;
			for (int i = 0; i < arr.length - 1; i ++) {
				for (int j = arr[i] + 1; j <= arr[i + 1]; j ++) {
					cs_set[cs_set_index ++] = cs[j];
				}
				if (i != arr.length - 2) {
					cs_set[cs_set_index ++] = '\0';
				}
			}
			String set_string = new String(cs_set);
			if (! set.contains(set_string)) {
				List<String> ans_this = new ArrayList<String>();
				for (int i = 0; i < arr.length - 1; i ++) {
					ans_this.add(new String(cs, arr[i] + 1, arr[i + 1] + 1));
				}
				set.add(set_string);
			}
		}
		String generate_string(int i, int j) {
			return new String(cs, i, j);
		}
		boolean judge(int i, int j) {
			while (i < j) {
				if (cs[i] != cs[j]) {
					return false;
				}
				i ++;
				j --;
			}
			return true;
		}
	}
}
