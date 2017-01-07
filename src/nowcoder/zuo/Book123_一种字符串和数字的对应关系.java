package nowcoder.zuo;

/**
 * 	[题目]
 * 	一个char类型的数组chs，其中所有的字符都不同。
 * 	例如，chs=['A', 'B', 'C', ..., 'Z']，
 * 	则字符串与数组的对应关系如下：
 * 	A, B...Z, AA, AB...AZ, BA, BB...ZZ, AAA...ZZZ, AAAA...
 * 	1, 2...26,27, 28...52, 53, 54...702,703...18278, 18279...
 * 	例如，chs=['A', 'B', 'C']，则字符串与整数的对应关系如下：
 * 	A, B, C, AA, AB...CC, AAA...CCC, AAAA...
 * 	1, 2, 3,  4,  5...12,  13...39,    40...
 * 	给定一个数组chs，实现根据对应关系完成字符串与整数相互转换的两个函数
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book123_一种字符串和数字的对应关系.java
 * @type        Book123_一种字符串和数字的对应关系
 * @date        2017年1月7日 下午9:43:23
 * @details     
 */
public class Book123_一种字符串和数字的对应关系 {
	static class SolutionIntToString {
		public String getString(char[] chs, int n) {
			if (chs == null || chs.length == 0 || n < 1) return "";
			int cur = 1;
			int base = chs.length;
			int len = 0;
			while (n >= cur) {
				len ++;
				n -= cur;
				cur *= base;
			}
			char[] ans = new char[len];
			int index = 0;
			int ncur = 0;
			do {
				cur /= base;
				ncur = n / cur;
				ans[index ++] = getKthCharAtChs(chs, ncur + 1); 
				n %= cur;
			} while (index != ans.length);
			return String.valueOf(ans);
		}
		private char getKthCharAtChs(char[] chs, int k) {
			if (k < 1 || k > chs.length) {
				return 0;
			}
			return chs[k - 1];
		}
	}
}
