package leetcode;

import java.util.ArrayList;
import java.util.HashSet;

/*
 * 	A binary watch has 4 LEDs on the top which represent the hours (0-11),
 *  and the 6 LEDs on the bottom represent the minutes (0-59).

	Each LED represents a zero or one, with the least significant bit on the right.
	
	
	For example, the above binary watch reads "3:25".
	
	Given a non-negative integer n which represents the number of LEDs 
	that are currently on, return all possible times the watch could represent.
	
	Example:
	
	Input: n = 1
	Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08",
	 "0:16", "0:32"]
	Note:
	The order of output does not matter.
	The hour must not contain a leading zero, for example "01:00" is not valid, 
	it should be "1:00".
	The minute must be consist of two digits and may contain a leading zero, 
	for example "10:2" is not valid, it should be "10:02".
 */


import java.util.LinkedList;
import java.util.List;

public class P401_BinaryWatch {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.readBinaryWatch(8));
	}
	/*
	 * 	AC
	 * 	24 ms
	 */
	static class Solution {
		List<String> ans = new LinkedList<String>();
		HashSet<String> set = new HashSet<>();
		List<String> h_List = new ArrayList<String>();
		List<String> m_List = new ArrayList<String>();
		int[] h = new int[]{
				1, 2, 4, 8
		}, m = new int[] {
				1, 2, 4, 8, 16, 32
		};
	    public List<String> readBinaryWatch(int num) {
	    	if (num < 0) {
	    		return ans;
	    	}
	    	if (num > 10) {
	    		num = 10;
	    	}
	    	for (int h_count = 0; h_count < h.length; h_count ++) {
	    		my_1(h_count, num - h_count);
	    	}
	        return ans;
	    }
	    void my_1(int h_count, int m_count) {
	    	if (m_count < 0) {
	    		return;
	    	}
	    	h_List.clear();
	    	m_List.clear();
	    	for (int val = 0; val < 12; val ++) {
	    		int val_now = val;
	    		int count_1 = 0;
	    		while (val_now != 0) {
	    			if ((val_now & 1) == 1) {
	    				count_1 ++;
	    			}
	    			val_now = val_now >> 1;
	    		}
	    		if (count_1 == h_count)
	    			h_List.add(String.valueOf(val));
	    	}
	    	for (int val = 0; val < 60; val ++) {
	    		int val_now = val;
	    		int count_1 = 0;
	    		while (val_now != 0) {
	    			if ((val_now & 1) == 1) {
	    				count_1 ++;
	    			}
	    			val_now = val_now >> 1;
	    		}
	    		if (count_1 == m_count) {
	    			m_List.add(String.format("%02d", val));
	    		}
	    	}
	    	for (int i = 0; i < h_List.size(); i ++) {
	    		for (int j = 0; j < m_List.size(); j ++) {
	    			ans.add(String.format("%s:%s", h_List.get(i), m_List.get(j)));
	    		}
	    	}
	    }
	}
}
