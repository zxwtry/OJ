package nowcoder.zuo;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 	[题目]
 * 	给定一个无序数组a，其中元素可正、可负、可0。
 * 	给定一个整数k，求a的所有子数组累加和小于或等于k的最长子数组长度。
 * 
 * 	[举例]
 * 	a=[3,-2,-4,0,6]，k=-2，相加和小于或等于-2的最长子数组
 * 	为[3,-2,4,0]，所以返回4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book099_未排序数组中累加和小于或等于给定值的最长子数组长度.java
 * @type        Book099_未排序数组中累加和小于或等于给定值的最长子数组长度
 * @date        2016年12月31日 下午9:36:00
 * @details     Solution1: 时间O(N^2)，空间O(1)
 * @details     Solution2: 时间O(N)，空间O(N)
 * @details     看似2比1快很多，但是经测试其实在N=10000时，耗时差不多。。。
 */
public class Book099_未排序数组中累加和小于或等于给定值的最长子数组长度 {
	public static void main(String[] args) throws InterruptedException {
		int n = 10000, min = -20, max = 20;
		int[] a = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		
		
		long t = System.currentTimeMillis();
		for (int k = -200; k <= 200; k ++) {
			Solution1 sol1 = new Solution1();
			sol1.getMaxLen(a, k);
		}
		System.out.println(System.currentTimeMillis()-t);
		Thread.sleep(1000);
		t = System.currentTimeMillis();
		for (int k = -200; k <= 200; k ++) {
			Solution2 sol2 = new Solution2();
			sol2.getMaxLen(a, k);
		}
		System.out.println(System.currentTimeMillis()-t);
	}
	static class Solution1 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int maxLen = 0;
			int v = 0;
			for (int i = 0; i < a.length; i ++) {
				for (int j = i; j < a.length; j ++) {
					v = i == j ? a[i] : v + a[j];
					if (v <= k) {
						maxLen = Math.max(maxLen, j - i + 1);
					}
				}
			}
			return maxLen;
		}
	}
	static class Solution2 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int maxLen = 0;
			TreeMap<Long, Long> t = new TreeMap<Long, Long>();
			t.put((long)0, (long)-1);
			int v = 0;
			for (int i = 0; i < a.length; i ++) {
				v += a[i];
				SortedMap<Long, Long> set = t.subMap((long)(v-k), (long)Integer.MAX_VALUE + 1);
				if (! set.isEmpty()) {
					for (Entry<Long, Long> e : set.entrySet())
						maxLen = Math.max(maxLen, (int)(i - e.getValue()));
				}
				if (! t.containsKey((long)v))
					t.put((long)v, (long)i);
			}
			return maxLen;
		}
	}
}
