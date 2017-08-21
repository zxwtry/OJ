package leetcode;

/*
 * 	Given an unsorted integer array, find the first missing positive integer.
 * 
	For example,
	Given [1,2,0] return 3,
	and [3,4,-1,1] return 2.
	
	Your algorithm should run in O(n) time and uses constant space.
 */

public class P041_FirstMissingPositive {
	public static void main(String[] args) {
//		System.out.println(new Solution1().firstMissingPositive(new int[]{1,2,0}));
//		System.out.println(new Solution1().firstMissingPositive(new int[]{3,4,-1,1}));
		System.out.println(new Solution1().firstMissingPositive(new int[]{8, 5, 4, 3, 2, 1}));
	}
	static class Solution2 {
	    public int firstMissingPositive(int[] ns) {
	        int nn = ns == null ? 0 : ns.length;
	        if (nn < 1) {
	            return 1;
	        }
	        //两个指针，维持最大可能和最小可能
	        int min = 0;
	        int max = nn - 1;
	        for (int i = 0; i < nn; i ++) {
	            int v = ns[i] - 1;
	            if (v < min || v > max) {
	                max --;
	                //意味着[min, max]之前肯定有空格
	                //max --肯定没错
	                continue;
	            }
	            if (v == min) {
	                min ++;
	                continue;
	            }
	            if (v == max) {
	                max --;
	                continue;
	            }
	            if (v == i) {
	                continue;
	            }
	            
	        }
	    }
	    //[i, j]
	    //k是候选下标
	    int place(int[] ns, int i, int j, int k) {
	        int index = ns[k] - 1;
	        if (ns[index] == index) {
	            
	        }
	    }
	}
	
	/*
	 * 	测试用例肯定特别多，HashMap完成不了
	 * 	时间O(N) 空间O(1)　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　
	 * 	巨魔性
	 * 	先假设没有重复数字
	 * 	AC了
	 * 	leetcode 却不告诉时间和排名
	 * 	1 ms
	 * 	18.19% 
	 */
	static class Solution1 {
	    public int firstMissingPositive(int[] nums) {
	    	if (nums == null || nums.length == 0)
	    		return 0;
	    	int l = 0, r = nums.length;
	    	while (l < r) {
	    		if (nums[l] == l + 1) {
	    			l ++;
	    		} else if (nums[l] <= 1 || nums[l] > r || nums[nums[l] - 1] == nums[l]) {
	    			nums[l] = nums[-- r];
	    		} else {
	    			int temp = nums[nums[l] - 1];
	    			nums[nums[l] - 1] = nums[l];
	    			nums[l] = temp;
	    		}
	    	}
	        return l + 1;
	    }
	}
}
