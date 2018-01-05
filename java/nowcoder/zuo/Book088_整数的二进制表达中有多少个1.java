package nowcoder.zuo;

/**
 * 	给定一个32位整数，返回这个整数的二进制表达中有多少个1
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book088_整数的二进制表达中有多少个1.java
 * @type        Book088_整数的二进制表达中有多少个1
 * @date        2016年12月24日 上午11:47:59
 * @details     
 */
public class Book088_整数的二进制表达中有多少个1 {
	static class Solution1 {
		public int numOfOne(int n) {
			int count = 0;
			while (n != 0) {
				if ((n & 0x1) == 1) {
					count ++;
				}
				n = n >>> 1;
			}
			return count;
		}
	}
	static class Solution2 {
		public int numOfOne(int n) {
			int count = 0;
			while (n != 0) {
				count ++;
				n = n & (n - 1);
			}
			return count;
		}
	}
	static class Solution3 {
		public int numOfOne(int n) {
			int count = 0;
			while (n != 0) {
				count ++;
				n = n - (n & (~n + 1));
			}
			return count;
		}
	}
	static class Solution4 {
		public int numOfOne(int n) {
			n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
			n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
			n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
			n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
			n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
			return n;
		}
	}
}
