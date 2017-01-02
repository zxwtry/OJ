package nowcoder.zuo;

import java.util.Arrays;

/**
 * 	[题目]
 * 	有N个长度不一的数组，所有的数组都是有序的。
 * 	从大到小打印这N个数组整体最大的前K个数
 * 	
 * 	[举例]
 * 	219, 405, 538, 845, 971
 * 	148, 558
 * 	52, 99, 348, 691
 * 	再输入整数K=5，则打印
 * 	Top5: 971, 845, 691, 558, 538
 * 
 * 	[要求]
 * 	1,	如果所有数组的元素个数小于K，则从大到小打印所有的数
 * 	2,	要求时间O(K*logN)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book107_找出N个数组整体最大的TopK.java
 * @type        Book107_找出N个数组整体最大的TopK
 * @date        2017年1月2日 上午11:38:19
 * @details     Solutioin1: 时间O(K*logN)，空间O(N+K)
 */
public class Book107_找出N个数组整体最大的TopK {
	static class Solution1 {
		public int[] getTopK(int[][] as, int k) {
			if (as == null || as.length == 0 || k < 1) return new int[0];
			int[] topk = new int[k];
			int[] ai = new int[as.length];
			int[] heap = new int[as.length];
			for (int i = 0; i < as.length; i ++) {
				ai[i] = as[i].length - 1;
				heap[i] = i;
			}
			for (int i = (heap.length - 2) / 2; i > -1; i --) {
				heapDn(as, ai, heap, i);
			}
			for (int ti = 0; ti < k; ti ++) {
				if (ai[heap[0]] < 0)
					return Arrays.copyOf(topk, ti);
				topk[ti] = as[heap[0]][ai[heap[0]]];
				ai[heap[0]] --;
				heapDn(as, ai, heap, 0);
			}
			return topk;
		}
		private void heapDn(int[][] as, int[] ai, int[] heap, int i) {
			int p = i, c = 2 * p + 1;
			while (c < heap.length) {
				acc(as, ai, heap, c+1);
				if (c+1 < heap.length && acc(as, ai, heap, c+1) > acc(as, ai, heap, c)) c++;
				if (acc(as, ai, heap, c) > acc(as, ai, heap, p)) swap(heap, p, c);
				else break;
				p = c;
				c = 2 * p + 1;
			}
		}
		private long acc(int[][] as, int[] ai, int[] heap, int i) {
			if (ai[heap[i]] < 0) return (long)Integer.MIN_VALUE - 1;
			return as[heap[i]][ai[heap[i]]];
		}
		void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
}
