package nowcoder.zuo;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
 * @details     Solution2: 时间O(N)，空间O(N)
 * @details     Solution3: 时间O(N*logN)，空间O(N)
 * @details     N=10000耗时Solution1:Solution2大概是：6:1
 */
public class Book100_计算数组的小和 {
	static class Solution1 {
		public int getArrMinSum(int[] arr) {
			if (arr == null || arr.length < 1) return 0;
			int arrMinSum = 0;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < arr.length; i ++) {
				if (arr[i] > min)
				for (int j = 0; j < i; j ++) {
					if (arr[j] < arr[i])
						arrMinSum += arr[j];
				}
				min = Math.min(min, arr[i]);
			}
			return arrMinSum;
		}
	}
	static class Solution2 {
		public int getArrMinSum(int[] arr) {
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			int min = Integer.MAX_VALUE;
			int arrMinSum = 0;
			for (int v : arr) {
				if (min < v) {
					SortedMap<Integer, Integer> sub = map.subMap(min, v);
					for (Entry<Integer, Integer> nv : sub.entrySet())
						arrMinSum += nv.getValue() * nv.getKey();
				}
				min = Math.min(v, min);
				Integer val = map.get(v);
				map.put(v, val == null ? 1 : val + 1);
			}
			return arrMinSum;
		}
	}
}
