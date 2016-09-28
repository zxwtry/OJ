package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Suppose you have a random list of people standing in a queue. 
 * Each person is described by a pair of integers (h, k), 
 * where h is the height of the person and k is the number of people 
 * in front of this person who have a height greater than or equal to h. 
 * Write an algorithm to reconstruct the queue.

	Note:
	The number of people is less than 1,100.
	
	Example
	
	Input:
	[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
	
	Output:
	[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */

public class P406_QueueReconstructionbyHeight {
	public static void main(String[] args) {
		int[][] people = new int[][] {
			{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
		};
		Solution s = new Solution();
		int[][] ans = s.reconstructQueue(people);
		if (ans != null) {
			System.out.println(ans.length);
		}
	}
	static class Solution {
	    public int[][] reconstructQueue(int[][] people) {
	    	ArrayList<My> ms_list = new ArrayList<My>(people.length * 2);
	    	ArrayList<Integer> large_pre = new ArrayList<>(people.length * 2);
	    	ArrayList<Integer> available = new ArrayList<>(people.length * 2);
	    	generateArrayListMS(people, ms_list, large_pre, available);
	    	while (available.size() != 0) {
	    		int i_select = available.get(0);
	    		if (large_pre.get(i_select) == ms_list.get(i_select).m[1]) {
	    			available.remove(0);
	    			continue;
	    		}
	    		int step = ms_list.get(i_select).m[1] - large_pre.get(i_select);
	    		int next_select = i_select + step;
	    		int available_remove = next_select;
	    		for (int i = i_select; i < next_select; i ++) {
	    			if (ms_list.get(i).m[1] == large_pre.get(i)) {
	    				available_remove --;
	    			}
	    		}
	    		ms_list.add(next_select + 1, ms_list.get(i_select));
	    		large_pre.add(next_select + 1, ms_list.get(i_select).m[1]);
	    		large_pre.remove(i_select);
	    		ms_list.remove(i_select);
	    		available.remove(available_remove);
	    	}
	    	My[] ans_ms = new My[ms_list.size()];
	    	for (int i = 0; i < ms_list.size(); i ++) {
	    		ans_ms[i] = ms_list.get(i);
	    	}
	    	print(ans_ms);
	        return null;
	    }
	    void generateArrayListMS(int[][] people, ArrayList<My> ms_list, 
	    		ArrayList<Integer> large_pre, ArrayList<Integer> available) {
	    	My[] ms = new My[people.length];
	    	for (int i = 0; i < people.length; i ++) {
	    		My new_my = new My(people[i]);
	    		ms[i] = new_my;
	    	}
	    	Arrays.sort(ms);
	    	for (int i = 0; i < ms.length; i ++) {
	    		if (i != 0 && ms[i].m[0] == ms[i - 1].m[0]) {
	    			large_pre.add(large_pre.get(i - 1) + 1);
	    		} else {
	    			large_pre.add(0);
	    		}
	    		ms_list.add(ms[i]);
	    		available.add(i);
	    	}
	    }
	    void print(My[] arr) {
	    	int count = 0;
	    	for (My my : arr) {
	    		System.out.printf("第%d个数据： %d...%d\r\n", count ++, my.m[0], my.m[1]);
	    	}
	    }
	    void swap(My[] ms, int i, int j) {
	    	My temp = ms[i];
	    	ms[i] = ms[j];
	    	ms[j] = temp;
	    }
	    static class My implements Comparable<My> {
	    	int[] m = null;
	    	int val = 0;
	    	public My(int[] m) {
	    		this.m = m;
	    		val += m[0] * 10000;
	    		val += m[1];
	    	}
			@Override
			public int compareTo(My o) {
				if (this.m[0] != o.m[0]) {
					return this.m[0] - o.m[0];
				} else {
					return this.m[1] - o.m[1];
				}
			}
	    }
	}
}
