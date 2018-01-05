package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个非负整数N，返回N!结果的末尾为0的数量
 * 
 * 	[举例]
 * 	3 ---> 返回0
 * 	5 ---> 返回1
 * 
 * 	[进阶题目]
 * 	给定一个非负整数N，返回N!的二进制表示中，
 * 	最低位的1在哪个位置上。
 * 	
 * 	[举例]
 * 	1 ---> 返回0
 * 	2 ---> 返回1
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book114_有关阶乘的两个问题.java
 * @type        Book114_有关阶乘的两个问题
 * @date        2017年1月4日 下午9:42:41
 * @details     Solution1: 时间O(logN)，空间O(1)	---原题目
 * @details     Solution2: 时间O(1)，空间O(1) 	  	---进阶题目
 * @details     Solution3: 时间O(1)，空间O(1) 		---进阶题目
 */
public class Book114_有关阶乘的两个问题 {
	static class Solution1 {
		public int getZeroNum(int n) {
			if (n < 0) return 0;
			int zeroNum = 0;
			while (n != 0) {
				zeroNum += n / 5;
				n /= 5;
			}
			return zeroNum;
		}
	}
	static class Solution2 {
		public int getMinIndexOne(int n) {
			if (n < 1) return -1;
			int minIndexOne = 0;
			while (n != 0) {
				n >>= 1;
				minIndexOne += n;
			}
			return minIndexOne;
		}
	}
	/**
	 * @details   N!的结果中因子2的总个数为Z  
	 * @details   N的二进制表达式中1的个数记为m
	 * @details   存在如下关系：Z=N-m
	 * @details   即：N/2+N/4+N/8+...=N-m (/是程序中的除法)
	 * @details   举例：N=10110B=10000B+100B+10B
	 * @details   返回N-3=22-3=19
	 */
	static class Solution3 {
		public int getMinIndexOne(int n) {
			if (n < 1) return -1;
			int ones = 0;
			int tmp = n;
			while (tmp != 0) {
				ones += (tmp & 1) != 0 ? 1 : 0;
				tmp >>= 1;
			}
			return n - ones;
		}
	}
}
