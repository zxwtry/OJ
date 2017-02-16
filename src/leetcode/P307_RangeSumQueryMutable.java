package leetcode;

/**
 * 	Given an integer array nums, find the sum of the elements between indices 
 * 	i and j (i ≤ j), inclusive.

 *	The update(i, val) function modifies nums by updating the element at 
 *	index i to val.
 *	Example:
 *	Given nums = [1, 3, 5]
 *	
 *	sumRange(0, 2) -> 9
 *	update(1, 2)
 *	sumRange(0, 2) -> 8
 *	Note:
 *	The array is only modifiable by the update function.
 *	You may assume the number of calls to update and sumRange function 
 *	is distributed evenly.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P307_RangeSumQueryMutable.java
 * @type        P307_RangeSumQueryMutable
 * @date        2016年12月29日 下午8:52:11
 * @details     NumArray1: TLE
 * @details     NumArray2: AC 144ms 21.98%
 * @details     NumArray3: AC 130ms 43.10%
 */
public class P307_RangeSumQueryMutable {
	static class NumArray1 {
		long[] sum = null;
		int[] nums = null;
	    public NumArray1(int[] nums) {
	        if (nums == null || nums.length == 0) return;
	        sum = new long[nums.length];
	        sum[0] = nums[0];
	        for (int index = 1; index < nums.length; index ++) {
	        	sum[index] = sum[index - 1] + nums[index];
	        }
	        this.nums = nums;
	    }

	    void update(int i, int val) {
	        long add = -(long)nums[i] + val;
	        for (int index = i; index < sum.length; index ++) {
	        	sum[index] += add;
	        }
	        nums[i] = val;
	    }

	    public int sumRange(int i, int j) {
	        if (i == 0) return (int)sum[j];
	        return (int)(sum[j] - sum[i - 1]);
	    }
	}


	// Your NumArray object will be instantiated and called as such:
	// NumArray numArray = new NumArray(nums);
	// numArray.sumRange(0, 1);
	// numArray.update(1, 10);
	// numArray.sumRange(1, 2);
	
	
	static class NumArray2 {
	    class SegmentTreeNode {
	        int start, end;
	        SegmentTreeNode left, right;
	        int sum;
	        public SegmentTreeNode(int start, int end) {
	            this.start = start;
	            this.end = end;
	            this.left = null;
	            this.right = null;
	            this.sum = 0;
	        }
	    }
	    SegmentTreeNode root = null;
	    public NumArray2(int[] nums) {
	        root = buildTree(nums, 0, nums.length-1);
	    }
	    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
	        if (start > end) {
	            return null;
	        } else {
	            SegmentTreeNode ret = new SegmentTreeNode(start, end);
	            if (start == end) {
	                ret.sum = nums[start];
	            } else {
	                int mid = start  + (end - start) / 2;             
	                ret.left = buildTree(nums, start, mid);
	                ret.right = buildTree(nums, mid + 1, end);
	                ret.sum = ret.left.sum + ret.right.sum;
	            }         
	            return ret;
	        }
	    }
	    void update(int i, int val) {
	        update(root, i, val);
	    }
	    void update(SegmentTreeNode root, int pos, int val) {
	        if (root.start == root.end) {
	           root.sum = val;
	        } else {
	            int mid = root.start + (root.end - root.start) / 2;
	            if (pos <= mid) {
	                 update(root.left, pos, val);
	            } else {
	                 update(root.right, pos, val);
	            }
	            root.sum = root.left.sum + root.right.sum;
	        }
	    }
	    public int sumRange(int i, int j) {
	        return sumRange(root, i, j);
	    }
	    public int sumRange(SegmentTreeNode root, int start, int end) {
	        if (root.end == end && root.start == start) {
	            return root.sum;
	        } else {
	            int mid = root.start + (root.end - root.start) / 2;
	            if (end <= mid) {
	                return sumRange(root.left, start, end);
	            } else if (start >= mid+1) {
	                return sumRange(root.right, start, end);
	            }  else {    
	                return sumRange(root.right, mid+1, end) + sumRange(root.left, start, mid);
	            }
	        }
	    }
	}
}
