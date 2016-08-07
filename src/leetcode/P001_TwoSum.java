package leetcode;

import java.util.Arrays;

public class P001_TwoSum {
	public static void main(String[] args) {
		int[] nums = new int[]{2,1,9,4,4,56,90,3};
		int[] ans = new Solution3().twoSum(nums, 8);
//		String nums_str = null;
//		String target_str = null;
//		File file = new File("./data/leetcode_P001_TwoSum.txt");
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			nums_str = br.readLine();
//			target_str = br.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (br != null) {
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		String[] nums_parts = nums_str.split(",");
//		int[] nums = new int[nums_parts.length];
//		for (int i = 0; i != nums_parts.length; i ++)
//			nums[i] = Integer.parseInt(nums_parts[i]);
//		int target = Integer.parseInt(target_str);
//		for (int i = 0; i != nums.length; i ++)
//			if ((nums[i] & 0x1) == 1)
//				System.out.println("是奇数："+nums[i]);
//		int[] ans = new Solution3().twoSum(nums, target);
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
	    static class Node implements Comparable<Node>{
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
	/*
	 * 	这份代码在自己的运行过程中是正确的，但是在OJ上是StackOverFlow
	 * 	OJ上面错误的用例是data/leetcode_P001_TwoSum.txt
	 */
	static class Solution2 {
		public int[] twoSum(int[] nums, int target) {
			int[] indexs = new int[nums.length];
			for (int i = 0; i != indexs.length; i ++)
				indexs[i] = i;
			int sta = 0, end = nums.length - 1;
			myQuickSort(nums, indexs, sta, end);
			while (sta < end) {
				int sum = nums[sta] + nums[end];
				if (sum > target)
					end --;
				else if (sum < target)
					sta ++;
				else {
					if (indexs[sta] == indexs[end])
						sta ++;
					else
						return new int[] {indexs[sta], indexs[end]};
				}
			}
			return new int[] {0, 0};
		}
		void myQuickSort(int[] nums, int[] indexs, int l, int r) {
			if (l >= r)
				return;
			int p = partition(nums, indexs, l, r);
			myQuickSort(nums, indexs, l, p - 1);
			myQuickSort(nums, indexs, p + 1, r);
		}
		int partition(int[] nums, int[] indexs, int l, int r) {
			int pivot = nums[l];
			int index = indexs[l];
			while (l < r) {
				while (l < r && nums[r] >= pivot)	r --;
				nums[l] = nums[r];
				indexs[l] = indexs[r];
				while (l < r && nums[l] <= pivot)	l ++;
				nums[r] = nums[l];
				indexs[r] = indexs[l];
			}
			nums[l] = pivot;
			indexs[l] = index;
			return l;
		}
	}
	/*
	 * 	Solution3想法是尽可能缩短时间
	 * 	Solution中的时间是9ms，最佳的时间很短。
	 * 	应该能够缩短到一半左右
	 * 	将时间缩短到5ms，超过Java中91.7%
	 * 
	 * 	不使用clone，改用System.arraycopy之后，时间4ms，超过Java中98.7%
	 * 	这个应该我能够想出来的最快的代码了。
	 */
	static class Solution3 {
		public int[] twoSum(int[] nums, int target) {
//			int[] nums_save = nums.clone();
			int[] nums_save = new int[nums.length];
			System.arraycopy(nums, 0, nums_save, 0, nums.length);
			Arrays.sort(nums);
			int sta = 0, end = nums.length - 1;
			while (sta < end) {
				int sum = nums[sta] + nums[end];
				if (sum < target)
					sta ++;
				else if (sum > target)
					end --;
				else
					return getIndexs(nums_save, nums[sta], nums[end]);
			}
			return null;
		}
		private int[] getIndexs(int[] nums, int sta, int end) {
			int i1 = 0, i2 = 0;
			boolean b1 = false, b2 = false;
			for (int i = 0; i != nums.length; i ++) {
				if (nums[i] == sta && !b1) {
					i1 = i;
					b1 = true;
				} else if (nums[i] == end && !b2) {
					i2 = i;
					b2 = true;
				}
				if (b1 & b2)
					break;
			}
			return new int[] {i1, i2};
		}
	}
}