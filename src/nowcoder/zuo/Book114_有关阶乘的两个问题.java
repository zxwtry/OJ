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
 * @details     Solution1: 时间O(logN)，空间O(1)
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
}
