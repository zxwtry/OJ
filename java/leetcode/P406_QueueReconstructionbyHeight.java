package leetcode;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Suppose you have a random list of people standing in a queue. 
 * Each person is described by a pair of integers (h, k), 
 * where h is the height of the person and k is the number of people 
 * in front of this person who have a height greater than or equal to h. 
 * Write an algorithm to reconstruct the queue.
 *	
 *	Note:
 *	The number of people is less than 1,100.
 *	
 *	Example
 *	
 *	Input:
 *	[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *	
 *	Output:
 *	[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */

public class P406_QueueReconstructionbyHeight {
	public static void main(String[] args) {
		int[][] people = new int[][] {
			{8,2},{4,2},{4,5},{2,0},{7,2},{1,4},{9,1},{3,1},{9,0},{1,0}
//			{0,1}, {1,4}, {1,1}, {2,1}, {2,1}, {2,2},{3,1},{3,1},{3,1},{3,1},{4,1},{4,1},{4,1},{4,1}
		};
		Solution s = new Solution();
		int[][] ans = s.reconstructQueue(people);
		if (ans != null) {
			System.out.println(ans.length);
			tools.Utils.A_打印二维数组(ans);
		}
	}
	/*
	 * 	AC
	 * 	71 ms
	 */
	static class Solution {
	    public int[][] reconstructQueue(int[][] people) {
	    	if (people == null || people.length == 0 || people[0].length == 0) {
	    		return people;
	    	}
	    	Comparator<My> com_list = new Comparator<My>() {
				@Override
				public int compare(My o1, My o2) {
					return o1.m[0] == o2.m[0] ? o1.m[1] - o2.m[1] : o1.m[0] - o2.m[0];
				}
			};
			Comparator<My> com_set = new Comparator<My>() {
				@Override
				public int compare(My o1, My o2) {
					return o1.m[0] - o2.m[0];
				}
			};
	    	ArrayList<My> list = new ArrayList<>();
	    	TreeSet<My> set = new TreeSet<>(com_set);
	    	for (int i = 0; i < people.length; i ++) {
	    		int[] p_now = people[i];
	    		My new_my = new My(p_now);
	    		if (p_now[1] == 0) {
		    		set.add(new_my);
	    		} else {
	    			list.add(new_my);
	    		}
	    	}
			Collections.sort(list, com_list);
			int[][] ans = new int[people.length][2];
			int ans_index = 0;
			while (set.size() != 0) {
				My from_set = set.pollFirst();
				ans[ans_index ++] = from_set.m;
				if (list.size() == 0) {
					continue;
				}
				if (from_set.m[0] < list.get(0).m[0]) {
					continue;
				}
				int index = binary(list, from_set.m[0]);
				for (int i = 0; i <= index; i ++) {
					My from_list =  list.get(i);
					from_list.pre --;
					if (from_list.pre == 0) {
						set.add(from_list);
						list.remove(i);
						i --;
						index --;
					}
				}
			}
	    	return ans;
	    }
	    int binary(ArrayList<My> list, int m0) {
	    	int sti = 0, eni = list.size() - 1;
	    	while (sti < eni) {
	    		int mid = (sti + eni + 1) / 2;
	    		int cut = list.get(mid).m[0] - m0;
	    		if (cut > 0) {
	    			eni = mid - 1;
	    		} else {
	    			sti = mid;
	    		}
	    	}
	    	return sti;
	    }
	    void print(AbstractCollection<My> arr) {
	    	int count = 0;
	    	for (My my : arr) {
	    		System.out.printf("第%d个数据： %s\r\n", count ++, my);
	    	}
	    }
	    static class My{
	    	int[] m = null;
	    	int pre = 0;
	    	public My(int[] m) {
	    		this.m = m;
	    		pre = m[1];
	    	}
			@Override
			public String toString() {
				return String.format("%d %d %d", m[0], m[1], pre);
			}
	    }
	}
}
