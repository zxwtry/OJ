package nowcoder.zuo;

/**
 * 	题目：给定两个32位整数a和b，返回a和b中较大的。
 * 	要求：不使用任何比较判断。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book087_不用任何比较判断找出两个数中较大的数.java
 * @type        Book087_不用任何比较判断找出两个数中较大的数
 * @date        2016年12月24日 上午10:10:46
 * @details     如果出现这样的题目，写Solution1吧
 * @details     Solution2未能清晰理解，但结果正确
 */
public class Book087_不用任何比较判断找出两个数中较大的数 {
	static class Solution1 {
		public int getMax(int a, int b) {
			long c = (long)a - (long)b;
			int sb = (int)((c >>> 63) & 1);
			int sa = sb ^ 1;
			return a * sa + b * sb;
		}
	}
	static class Solution2 {
		public int getMax(int a, int b) {
			int c = a - b;
			int sa = ((a >>> 31) & 1) ^ 1;
			int sb = ((b >>> 31) & 1) ^ 1;
			int sc = ((c >>> 31) & 1) ^ 1;
			int yh = sa ^ sb;
			int ra = yh * sa + (yh ^ 1) * sc;
			return ra * a + (ra ^ 1) * b;
		}
	}
}
