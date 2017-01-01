package nowcoder.zuo;

/**
 * 	[题目]
 * 	数组小和定义如下：
 * 		以[1,3,5,2,4,6]为例，
 * 		在s[0]的左边小于等于s[0]的和为0，			在s[1]的左边小于等于s[1]的和为1，
 * 		在s[2]的左边小于等于s[2]的和为1+3=4，		在s[3]的左边小于等于s[3]的和为1，
 * 		在s[4]的左边小于等于s[4]的和为1+3+2=6，	在s[5]的左边小于等于s[5]的和为1+3+5+2+4=15，
 * 		那么数组的小和就是：0+1+4+1+6+15=27。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book100_计算数组的小和.java
 * @type        Book100_计算数组的小和
 * @date        2016年12月31日 下午10:24:15
 * @details     Solution1: 时间O(N^2)，空间O(1)
 */
public class Book100_计算数组的小和 {
	static class Solution1 {
		public int getArrMinSum(int[] arr) {
			if (arr == null || arr.length < 1) return 0;
			int arrMinSum = 0;
			for (int i = 0; i < arr.length; i ++) {
				for (int j = 0; j < i; j ++) {
					if (arr[j] < arr[i])
						arrMinSum += arr[j];
				}
			}
			return arrMinSum;
		}
	}
}
