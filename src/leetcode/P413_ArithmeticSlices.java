package leetcode;

public class P413_ArithmeticSlices {
	public static void main(String[] args) {
		testSolution();
	}
    private static void testSolution() {
    	int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 1);
    	arr = new int[] {1, 2, 3, 4};
    	Solution s = new Solution();
    	System.out.println(s.numberOfArithmeticSlices(arr));
	}
    /*
     * 	AC
     * 	2 ms
     * 	35.34%
     */
	static class Solution {
        public int numberOfArithmeticSlices(int[] A) {
            if (A.length < 3) {
            	return 0;
            }
            int preRange = A[1] - A[0];
            int preIndex = 0;
            int index = 2;
            int newRange = 0;
            int ans = 0;
            while (index < A.length) {
            	newRange = A[index] - A[index - 1];
            	if (newRange != preRange) {
            		ans += generateNumber(index - preIndex);
            		preRange = newRange;
            		preIndex = index - 1;
            	}
            	index ++;
            }
            ans += generateNumber(index - preIndex);
            return ans;
        }
        public int generateNumber(int n) {
        	if (n < 3) {
        		return 0;
        	} else {
        		return (n - 1) * (n - 2) / 2;
        	}
        }
    }
}
