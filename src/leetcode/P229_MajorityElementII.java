package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * 	Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * 	The algorithm should run in linear time and in O(1) space.

	Hint:
	
	How many majority elements could it possibly have?	
 */

public class P229_MajorityElementII {
	public static void main(String[] args) {
//		Solution1 s = new Solution1();
//		System.out.println(s.majorityElement(new int[] {3,2,3}));
		test();
//		debug();
	}
	/*
	 * 	3 ms
	 * 	62.30%
	 */
	static class Solution1 {
	    public List<Integer> majorityElement(int[] nums) {
	    	List<Integer> ans = new LinkedList<>();
	    	if (null == nums || nums.length == 0) {
	    		return ans;
	    	}
	    	if (nums.length < 3) {
	    		ans.add(nums[0]);
	    		if (nums.length == 2 && nums[1] != nums[0]) {
	    			ans.add(nums[1]);
	    		}
	    		return ans;
	    	}
	    	int v1 = 0, v2 = 0, t1 = 0, t2 = 0;
	    	for (int val : nums) {
	    		if (v1 == val) {
	    			t1 ++;
	    		} else if (v2 == val) {
	    			t2 ++;
	    		} else if (t1 <= 0) {
	    			v1 = val;
	    			t1 = 1;
	    		} else if (t2 <= 0) {
	    			v2 = val;
	    			t2 = 1;
	    		} else {
	    			t1 --;
	    			t2 --;
	    		}
	    	}
	    	t1 = 0;
	    	t2 = 0;
	    	for (int val : nums) {
	    		if (val == v1) {
	    			t1 ++;
	    		} else if (val == v2) {
	    			t2 ++;
	    		}
	    	}
	    	if (t1 > nums.length / 3) {
	    		ans.add(v1);
	    	}
	    	if (v1 != v2 && t2 > nums.length / 3) {
	    		ans.add(v2);
	    	}
	    	return ans;
	    }
	}
	
	/*
	 * 	5 ms
	 * 	31.52%
	 */
	static class Solution2 {
		final int K = 3;
	    public List<Integer> majorityElement(int[] nums) {
	    	List<Integer> ans = new LinkedList<>();
	    	if (null == nums || nums.length == 0) {
	    		return ans;
	    	}
	    	if (nums.length < 3) {
	    		ans.add(nums[0]);
	    		if (nums.length == 2 && nums[1] != nums[0]) {
	    			ans.add(nums[1]);
	    		}
	    		return ans;
	    	}
	    	int[] value = new int[K - 1];
	    	int[] times = new int[K - 1];
	    	for (int val : nums) {
	    		boolean isFound = false;
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (val == value[i]) {
	    				times[i] ++;
	    				isFound = true;
	    			}
	    		}
	    		if (! isFound) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				if (times[i] <= 0) {
	    					value[i] = val;
	    					times[i] = 1;
	    					isFound = true;
	    					break;
	    				}
	    			}
	    		}
	    		if (! isFound) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				times[i] --;
	    			}
	    		}
	    	}
	    	for (int i = 0; i < K - 1; i ++) {
	    		times[i] = 0;
	    	}
	    	for (int val : nums) {
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (value[i] == val) {
	    				times[i] ++;
	    			}
	    		}
	    	}
	    	boolean isHas0 = false;
	    	for (int i = 0; i < K - 1; i ++) {
	    		if (times[i] > nums.length / K) {
	    			if (value[i] == 0) {
	    				if (! isHas0) {
	    					isHas0 = true;
	    				} else {
	    					continue;
	    				}
	    			}
	    			ans.add(value[i]);
	    		}
	    	}
	    	return ans;
	    }
	}
	static class Solution3 {
		final int K;
		public Solution3(int K) {
			this.K = K;
		}
	    public List<Integer> majorityElement(int[] nums) {
	    	List<Integer> ans = new LinkedList<>();
	    	if (null == nums || nums.length == 0) {
	    		return ans;
	    	}
//	    	if (nums.length < K) {
	    		
//	    		ans.add(nums[0]);
//	    		if (nums.length == 2 && nums[1] != nums[0]) {
//	    			ans.add(nums[1]);
//	    		}
//	    		return ans;
//	    	}
	    	int[] value = new int[K - 1];
	    	int[] times = new int[K - 1];
	    	for (int index = 0; index < nums.length; index ++) {
	    		int val = nums[index];
//	    	for (int val : nums) {
	    		boolean isFound = false;
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (val == value[i]) {
	    				times[i] ++;
	    				isFound = true;
	    			}
	    		}
	    		if (! isFound) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				if (times[i] <= 0) {
	    					value[i] = val;
	    					times[i] = 1;
	    					isFound = true;
	    					break;
	    				}
	    			}
	    		}
	    		if (! isFound && (K != 1 && index % (K - 1) == K - 2)) {
	    			for (int i = 0; i < K - 1; i ++) {
	    				times[i] --;
	    			}
	    		}
	    	}
	    	for (int i = 0; i < K - 1; i ++) {
	    		times[i] = 0;
	    	}
	    	for (int val : nums) {
	    		for (int i = 0; i < K - 1; i ++) {
	    			if (value[i] == val) {
	    				times[i] ++;
	    			}
	    		}
	    	}
	    	boolean isHas0 = false;
	    	for (int i = 0; i < K - 1; i ++) {
	    		if (times[i] > nums.length / K) {
	    			if (value[i] == 0) {
	    				if (! isHas0) {
	    					isHas0 = true;
	    				} else {
	    					continue;
	    				}
	    			}
	    			ans.add(value[i]);
	    		}
	    	}
	    	return ans;
	    }
	}
	static List<Integer> standard(int K, int[] arr) {
		List<Integer> ans = new LinkedList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int val : arr) {
			if (map.containsKey(val)) {
				map.put(val, map.get(val) + 1);
			} else {
				map.put(val, 1);
			}
		}
		for (int key : map.keySet()) {
			if (map.get(key) > arr.length / K) {
				ans.add(key);
			}
		}
//		for (Entry<Integer, Integer> e : map.entrySet()) {
//			System.out.println("key : " + e.getKey() + "  ...value : " + e.getValue());
//		}
		return ans;
	}
	//测试，在随机K的情况下，是否正确
	static void test() {
		for (int i = 0; i < 10000000; i ++) {
			int arg0 = (int)(Math.random() * 1000);
			int arg1 = (int)(Math.random() * 100);
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(arg0, 0, arg1);
			int K = arg0 / (arg1 + 1) + 1;
			List<Integer> standard = standard(K, arr);
			Solution3 s = new Solution3(K);
			List<Integer> solve = s.majorityElement(arr);
			HashSet<Integer> ans = new HashSet<>();
			Iterator<Integer> it = standard.iterator();
			while (it.hasNext()) {
				ans.add(it.next());
			}
			it = solve.iterator();
			while (it.hasNext()) {
				int val = it.next();
				if (ans.contains(val)) {
					ans.remove(val);
				} else {
					System.out.println(val + "翻车啦");
					tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", tools.Utils.LEETCODE_int_array_序列化_(arr));
					tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", K+"");
					tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", "============================");
				}
			}
			if (! ans.isEmpty()) {
				System.out.println("翻车啦");
				StringBuilder st = new StringBuilder("标准的比计算的多");
				for (int val : ans) {
					st.append(" " + val);
				}
				tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", st.toString());
				tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", tools.Utils.LEETCODE_int_array_序列化_(arr));
				tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", K+"");
				tools.FileUtils.B_纪录String_append("D:/file/data/1020.txt", "============================");
			}
			System.out.println("到这里，测试就结束了" + ans.isEmpty());
		}
	}
	static void debug() {
		int K = 1;
		int[] arr = new int[] {27, 1};
		System.out.println(arr.length);
		List<Integer> standard = standard(K, arr);
		tools.Utils.B_打印List_Integer(standard);
//		Solution3 s = new Solution3(K);
//		System.out.println(s.majorityElement(arr));
	}
}
