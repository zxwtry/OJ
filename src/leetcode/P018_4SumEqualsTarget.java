package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class P018_4SumEqualsTarget {
	public static void main(String[] args) {
//		System.out.println(new Solution3().threeSum(new int[] {-4, -1, -1, 0, 1, 2}, -1, 0));
//		System.out.println(new Solution3().threeSum(new int[] {-2, 0, 0, 2, 2}, -1, 0));
		System.out.println(new Solution3().fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0));
		System.out.println(new Solution3().fourSum(new int[] {-2, 2, 2, 2, 2, 2}, 4));
		System.out.println(new Solution3().fourSum(new int[] {2, 2, 2, 2, 2, 2, 4, 0}, 8));
//		Node node = new Node(4, 3, 2, 1);
//		Set<Node> set = new HashSet<Node>();
//		set.add(node);
//		node = new Node(4, 3, 1, 2);
//		set.add(node);
//		System.out.println(set.size());
	}
	/*
	 * 	106 ms
	 * 	6.18%
	 * 	O(N^3)
	 */
	static class Solution1 {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	        if (nums == null || nums.length < 4)
	        	return ans;
	        Arrays.sort(nums);
	        for (int i = 0; i != nums.length; i ++) {
	        	if (i != 0 && nums[i] == nums[i - 1])
	        		continue;
	        	List<List<Integer>> temp = threeSum(nums, i, target - nums[i]);
	        	Iterator<List<Integer>> it = temp.iterator();
	        	while (it.hasNext()) {
	        		List<Integer> list = it.next();
	        		list.add(nums[i]);
	        		ans.add(list);
	        	}
	        }
	        return ans;
	    }
	    List<List<Integer>> threeSum(int[] nums, int bomb, int target) {
	    	List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    	int i = 0, j = 0, k = 0;
	    	for (i = bomb + 1; i != nums.length; i ++) {
	    		if (i != 0 && nums[i] == nums[i - 1] && (i != bomb + 1))	continue;
	    		j = i + 1;  k = nums.length - 1;
	    		while (j < k) {
	    			int sum = nums[i] + nums[j] + nums[k];
	    			if (sum == target) {
	    				List<Integer> list = new LinkedList<Integer>();
	    				list.add(nums[i]);   list.add(nums[j]);   list.add(nums[k]);
	    				ans.add(list);
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else if (sum > target) {
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else {
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    			}
	    		}
	    	}
	    	return ans;
	    }
	}
	/*
	 * 	87 ms
	 * 	11.81%
	 * 	O(N^3)
	 */
	static class Solution2 {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        if (nums == null || nums.length < 4)
	        	return ans;
	        Arrays.sort(nums);
	        for (int i = 0; i != nums.length; i ++) {
	        	if (i != 0 && nums[i] == nums[i - 1])
	        		continue;
	        	threeSum(nums, i, target - nums[i]);
	        }
	        return ans;
	    }
	    void threeSum(int[] nums, int bomb, int target) {
	    	int i = 0, j = 0, k = 0;
	    	for (i = bomb + 1; i != nums.length; i ++) {
	    		if (i != 0 && nums[i] == nums[i - 1] && (i != bomb + 1))	continue;
	    		j = i + 1;  k = nums.length - 1;
	    		while (j < k) {
	    			int sum = nums[i] + nums[j] + nums[k];
	    			if (sum == target) {
	    				List<Integer> list = new LinkedList<Integer>();
	    				list.add(nums[i]);   list.add(nums[j]);
	    				list.add(nums[k]);   list.add(nums[bomb]);
	    				ans.add(list);
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else if (sum > target) {
	    				do {
	    					k --;
	    				} while (k != -1 && nums[k] == nums[k + 1]);
	    			} else {
	    				do {
	    					j ++;
	    				} while (j != nums.length && nums[j] == nums[j - 1]);
	    			}
	    		}
	    	}
	    }
	}
	/*
	 * 	采用HashMap的方法来进行计算
	 * 	光荣TLE
	 */
	static class Solution3 {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	    	List<List<Integer>> ans = new LinkedList<List<Integer>>();
	    	if (nums == null || nums.length < 4)
	    		return ans;
	    	HashMap<Integer, HashSet<Node>> map = new HashMap<Integer, HashSet<Node>>();
	    	HashSet<Node> ansset = new HashSet<Node>();
	    	HashMap<Integer, Integer> nums_map = new HashMap<Integer, Integer>();
	    	for (int i = 0; i != nums.length; i ++) {
	    		if (nums_map.containsKey(nums[i]))
	    			nums_map.put(nums[i], nums_map.get(nums[i]) + 1);
	    		else
	    			nums_map.put(nums[i], 1);
	    		for (int j = i + 1; j != nums.length; j ++) {
	    			int sum = nums[i] + nums[j];
	    			if (map.containsKey(sum)) {
	    				map.get(sum).add(new Node(nums[i], nums[j]));
	    			} else {
	    				HashSet<Node> set = new HashSet<Node>();
	    				set.add(new Node(nums[i], nums[j]));
	    				map.put(sum, set);
	    			}
	    		}
	    	}
	    	for (int k : map.keySet()) {
	    		if (map.containsKey(target - k)) {
	    			HashSet<Node> set1 = map.get(k);
	    			HashSet<Node> set2 = map.get(target - k);
	    			for (Node node1 : set1) {
	    				for (Node node2 : set2) {
	    					nums_map.put(node1.a, nums_map.get(node1.a) - 1);
	    					nums_map.put(node1.b, nums_map.get(node1.b) - 1);
	    					nums_map.put(node2.a, nums_map.get(node2.a) - 1);
	    					nums_map.put(node2.b, nums_map.get(node2.b) - 1);
	    					if (nums_map.get(node1.a) > -1 && nums_map.get(node1.b) > -1 &&
	    							nums_map.get(node2.a) > -1 && nums_map.get(node2.b) > -1) {
	    						ansset.add(new Node(node1.a, node1.b, node2.a, node2.b));
	    					}
	    					nums_map.put(node1.a, nums_map.get(node1.a) + 1);
	    					nums_map.put(node1.b, nums_map.get(node1.b) + 1);
	    					nums_map.put(node2.a, nums_map.get(node2.a) + 1);
	    					nums_map.put(node2.b, nums_map.get(node2.b) + 1);
	    				}
	    			}
	    		}
	    	}
	    	for (Node node : ansset) {
	    		List<Integer> list = new LinkedList<Integer>();
	    		list.add(node.a);
	    		list.add(node.b);
	    		list.add(node.c);
	    		list.add(node.d);
	    		ans.add(list);
	    	}
	    	return ans;
	    }
	}
	 static class Node {
	    	int a, b, c, d;
	    	public Node (int a, int b) {
	    		this.a = a;
	    		this.b = b;
	    		this.c = Integer.MAX_VALUE;
	    		this.d = Integer.MAX_VALUE;
	    	}
	    	public Node (int a, int b, int c, int d) {
	    		int temp = 0;
	    		if (a > b) {
	    			temp = a;
	    			a = b;
	    			b = temp;
	    		}
	    		if (c > d) {
	    			temp = c;
	    			c = d;
	    			d = temp;
	    		}
	    		this.a = Math.min(a, c);
	    		this.d = Math.max(b, d);
	    		c = this.a == a ? c : a;
	    		b = this.d == d ? b : d;
	    		if (b < c) {
	    			this.b = b;
	    			this.c = c;
	    		} else {
	    			this.b = c;
	    			this.c = b;
	    		}
	    	}
			@Override
			public boolean equals(Object obj) {
				if (obj instanceof Node) {
					Node o = (Node) obj;
					return this.a == o.a && this.b == o.b && this.c == o.c && this.d == o.d;
				} else {
					return false;
				}
			}
	    	@Override
	    	public int hashCode() {
	    		return a * 11 + b * 22 + c * 33 + d * 44;
	    	}
	    }
}
