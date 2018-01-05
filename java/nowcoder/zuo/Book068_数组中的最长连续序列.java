package nowcoder.zuo;

import java.util.HashMap;

/**
 * 	给定无序数组，返回其中最长的连续序列的长度。
 * 	举例：
 * 		arr=[100,4,200,1,3,2]
 * 		最长连续序列为[1,2,3,4]，所以返回4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book068_数组中的最长连续序列.java
 * @type        Book068_数组中的最长连续序列
 * @date        2016年12月16日 下午9:03:12
 * @details     
 */
public class Book068_数组中的最长连续序列 {
	static class Solution {
		public int longestConsecutive(int[] arr) {
			if (arr == null || arr.length == 0) return 0;
			int max = 1;
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < arr.length; i ++) {
				if (! map.containsKey(arr[i])) {
					map.put(arr[i], 1);
					if (map.containsKey(arr[i] - 1))
						max = Math.max(max, merge(map, arr[i] - 1, arr[i]));
					if (map.containsKey(arr[i] + 1))
						max = Math.max(max, merge(map, arr[i], arr[i] + 1));
				}
			}
			return max;
		}

		private int merge(HashMap<Integer, Integer> map, int less, int more) {
			int l = less - map.get(less) + 1;
			int r = more + map.get(more) - 1;
			int len = r - l + 1;
			map.put(l, len);
			map.put(r, len);
			return len;
		}
	}
}
