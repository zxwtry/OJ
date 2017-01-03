package nowcoder.zuo;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 	[题目]
 * 	给定一个整型数组arr，返回排序后的相邻两数的最大差值。
 * 
 * 	[举例]
 * 	arr=[9,3,1,10]，如果排序，结果为[1,3,9,10]，
 * 	9和3的差为最大差值，故返回6
 * 	arr=[5,5,5,5]，返回0
 * 
 * 	[要求]
 * 	时间O(N)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book112_数组排序之后相邻数的最大差值.java
 * @type        Book112_数组排序之后相邻数的最大差值
 * @date        2017年1月3日 下午9:58:42
 * @details     
 */
public class Book112_数组排序之后相邻数的最大差值 {
	static class Solution1 {
		public int getMaxGap(int[] arr) {
			if (arr == null || arr.length < 2) return 0;
			int[] ghost = Arrays.copyOf(arr, arr.length);
			Arrays.sort(ghost);
			int maxGap = Integer.MIN_VALUE;
			for (int i = 1; i < ghost.length; i ++) {
				maxGap = Math.max(maxGap, ghost[i] - ghost[i - 1]);
			}
			return maxGap;
		}
	}
	
}
