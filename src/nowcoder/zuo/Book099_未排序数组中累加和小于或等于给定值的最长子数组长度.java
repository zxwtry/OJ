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
 * @details     Solution3: 时间O(N^logN)，空间O(N)
 * @details     耗时比例：1:2:3=100:30:1
 */
public class Book099_未排序数组中累加和小于或等于给定值的最长子数组长度 {
	static class Solution1 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int maxLen = 0;
			int v = 0;
			for (int i = 0; i < a.length; i ++) {
				for (int j = i; j < a.length; j ++) {
					v = i == j ? a[i] : v + a[j];
					if (v <= k)
						maxLen = Math.max(maxLen, j - i + 1);
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
			long max = 0;
			for (int i = 0; i < a.length; i ++) {
				v += a[i];
				max += a[i] > 0 ? a[i] : -a[i];
				SortedMap<Long, Long> set = t.subMap((long)(v-k), Math.max(max, max-k) + 1);
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
	static class Solution3 {
		public int getMaxLen(int[] a, int k) {
			if (a == null || a.length < 1) return 0;
			int[] h = new int[a.length + 1];
			int v = 0;
			h[0] = v;
			for (int i = 0; i < a.length; i ++) {
				v += a[i];
				h[i + 1] = Math.max(v, h[i]); 
			}
			v = 0;
			int maxLen = 0;
			int pre = 0;
			int len = 0;
			for (int i = 0; i < a.length; i ++) {
				v += a[i];
				pre = getLessIndex(h, v - k);
				len = pre == -1 ? 0 : i - pre + 1;
				maxLen = Math.max(maxLen, len);
			}
			return maxLen;
		}
		private int getLessIndex(int[] h, int num) {
			int low = 0;
			int high = h.length - 1;
			int mid = 0;
			int lessIndex = -1;
			while (low <= high) {
				mid = (low + high) / 2;
				if (h[mid] >= num) {
					lessIndex = mid;
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			return lessIndex;
		}
	}
}
