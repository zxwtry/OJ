package nowcoder.zuo;

/**
 * 	给定排序数组arr和整数k。
 * 	不重复打印arr中所有相加和为k的不降序二元组
 * 	例如，arr=[-8,-4,-3,0,1,2,4,5,8,9]，k=10，打印结果为：
 * 	1,9
 * 	2,8
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book097_不重复打印排序数组中相加和为给定值的所有二元组和三元组.java
 * @type        Book097_不重复打印排序数组中相加和为给定值的所有二元组和三元组
 * @date        2016年12月31日 下午5:55:15
 * @details     Solution1: 打印所有二元和三元，包含重复
 * @details     Solution2: 打印所有二元，用 l==0 || a[l-1] != a[l]去重
 */
public class Book097_不重复打印排序数组中相加和为给定值的所有二元组和三元组 {
	static class Solution1 {
		int[] ss = new int[3];
		public void print(int[] arr, int k) {
			if (arr == null || arr.length < 2) return;
			for (int i = 0; i < arr.length; i ++) {
				ss[0] = arr[i];
				s(i + 1, k - arr[i], 1, arr);
			}
		}
		private void s(int i, int v, int c, int[] arr) {
			if (v == 0) {
				if (c == 3) {
					System.out.println(ss[0]+","+ss[1]+","+ss[2]);
					return;
				}
				if (c == 2)
					System.out.println(ss[0]+","+ss[1]);
			}
			if (v < 0 || c > 2) return;
			for (int j = i; j < arr.length; j ++) {
				ss[c] = arr[j];
				s(j + 1, v - arr[j], c + 1, arr);
			}
		}
	}
	static class Solution2 {
		public void print2(int[] a, int k) {
			if (a == null || a.length < 2) return;
			int l = 0, r = a.length - 1;
			while (l < r) {
				int j = a[l] + a[r] - k;
				if (j == 0) {
					if (l == 0 || a[l-1] != a[l])
						System.out.println(a[l] + "," + a[r]);
					l ++; r --;
				} else if (j < 0) l ++;
				else r --;
			}
		}
	}
}
