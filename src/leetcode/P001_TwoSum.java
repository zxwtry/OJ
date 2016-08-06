package leetcode;

import java.util.Arrays;

public class P001_TwoSum {
	public static void main(String[] args) {
		int[] nums = new int[] {3,2,4};
		int[] ans = new Solution().twoSum(nums, 6);
		System.out.println(ans[0]);
		System.out.println(ans[1]);
	}
	static class Solution {
	    public int[] twoSum(int[] nums, int target) {
	        Node[] nodes = new Node[nums.length];
	        for (int i = 0; i != nums.length; i ++)
	        	nodes[i] = new Node(nums[i], i);
	        Arrays.sort(nodes);
	        int sta = 0, end = nums.length - 1;
	        while (sta < end) {
	        	int sum = nodes[sta].num + nodes[end].num;
	        	if (sum > target)
	        		end --;
	        	else if (sum < target)
	        		sta ++;
	        	else {
	        		if (nodes[sta].index == nodes[end].index)
	        			sta ++;
	        		else
	        			return new int[] {nodes[sta].index, nodes[end].index};
	        	}
	        }
	        return new int[] {0, 0};
	    }
	    static class Node implements  Comparable<Node>{
			int num;
			int index;
			public Node(int num, int index) {
				this.num = num;
				this.index = index;
			}
			@Override
			public int compareTo(Node o) {
				return this.num - o.num;
			}
		}
	}
}
