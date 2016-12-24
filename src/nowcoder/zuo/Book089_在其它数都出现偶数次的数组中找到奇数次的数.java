package nowcoder.zuo;

/**
 * 	题目：
 * 		给定一个整型数组arr，其中只有一个数出现了奇数次，
 * 		其它数都出现了偶数次，打印这个数。
 * 	要求：时间O(N)，空间O(1)
 * 
 * 	进阶：
 * 		如果有两个数出现奇数次，其它的数都出现了偶数次，打印这两个数。	
 * 	要求：时间O(N)，空间O(1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book089_在其它数都出现偶数次的数组中找到奇数次的数.java
 * @type        Book089_在其它数都出现偶数次的数组中找到奇数次的数
 * @date        2016年12月24日 下午12:11:06
 * @details     原题目：Solution1
 * @details     进阶：Solution2
 */
public class Book089_在其它数都出现偶数次的数组中找到奇数次的数 {
	static class Solution1 {
		public int oddOne(int[] arr) {
			int ans = 0;
			for (int v : arr)
				ans ^= v;
			return ans;
		}
	}
	static class Solution2 {
		public int[] oddTwo(int[] arr) {
			int a = 0;
			for (int v : arr)
				a ^= v;
			int f = a & (~a + 1);
			int b = 0;
			for (int v : arr)
				if ((v & f) != 0)
					b ^= v;
			return new int[] {b, a ^ b};
		}
	}
}
