package nowcoder.zuo;

/**
 * 	题目：
 * 		给定一个整型数组arr和一个大于1的整数k
 * 		已知arr中只有1个数出现一次，其余的数都出现k次
 * 		返回只出现一次的数。
 * 	要求：
 * 		时间O(N)，空间O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book090_在其它数都出现k次的数组中找到只出现一次的数.java
 * @type        Book090_在其它数都出现k次的数组中找到只出现一次的数
 * @date        2016年12月24日 下午1:40:45
 * @details     有一个特性：一个k进制的数，每位加k次，对k取余，得到0。
 * @details     举例：5进制数 412，每位加5次，对5取余，得到000。
 */
public class Book090_在其它数都出现k次的数组中找到只出现一次的数 {
	static class Solution {
		public int onceNum(int[] arr, int k) {
			int[] s = new int[32];
			int si = 0;
			for (int a : arr) {
				si = 0;
				while (a != 0) {
					s[si ++] += a % k;
					a = a / k;
				}
			}
			int ans = 0;
			for (int i = s.length - 1; i > -1; i --)
				ans = ans * k + (s[i] % k);
			return ans;
		 }
	 }
}
