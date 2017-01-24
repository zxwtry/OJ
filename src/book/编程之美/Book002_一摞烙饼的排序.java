package book.编程之美;

import java.util.Arrays;

/**
 * 	[题目]
 * 	每次只能将最上面几张饼，颠倒个个
 * 	最后，小的在上面，大的在下面。
 * 	
 * 	[要求]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     book.编程之美
 * @file        Book002_一摞烙饼的排序.java
 * @type        Book002_一摞烙饼的排序
 * @date        2017年1月22日 下午10:51:49
 * @details     
 */
public class Book002_一摞烙饼的排序 {
	static class Solution1 {
		public void sort(int[] a) {
			if (a == null || a.length < 2) return;
			int mi = -1;
			for (int ei = a.length - 1; ei > -1; ei --) {
				mi = -1;
				for (int i = 0; i < ei; i ++)
					if (-1 == mi || a[mi] < a[i])
						mi = i;
				if (mi == -1 || a[mi] <= a[ei]) continue;
				revser(a, 0, mi);
				revser(a, 0, ei);
			}
		}
		public void revser(int[] a, int i, int j) {
			while (i < j) {
				swap(a, i, j);
				i ++;
				j --;
			}
		}
		public void swap(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}
	static class Solution2 {
		private int[] cakeArray, swapArray, reverseCakeArray, reverseCakeArraySwap;
		private int cakeCnt, maxSwap, nSearch;
		public Solution2() {
			cakeCnt = 0;
			maxSwap = 0;
		}
		public void sort(int[] pCakeArray) throws Exception {
			Init(pCakeArray);
			nSearch = 0;
			search(nSearch);
		}
		public void output() {
			tools.Utils.printArray(swapArray, swapArray.length);
			System.out.println("Search Times: " + nSearch);
			System.out.println("Total Swap Times: " + maxSwap);
		}
		private void Init(int[] pCakeArray) throws Exception {
			if (pCakeArray == null || pCakeArray.length < 1) {
				throw new Exception("Init 方法  pCakeArray 为  空");
			}
			cakeCnt = pCakeArray.length;
			cakeArray = Arrays.copyOf(pCakeArray, cakeCnt);
			maxSwap = upBound(cakeCnt);
			swapArray = new int[maxSwap];
			reverseCakeArray = Arrays.copyOf(cakeArray, cakeCnt);
			reverseCakeArraySwap = new int[maxSwap];
		}
		private int upBound(int cakeCnt2) {
			return cakeCnt2 * 2;
		}
		private int lowerBound(int[] pCakeArray, int nCakeCnt) {
			int t = 0, ret = 0;
			//根据当前数组的排序信息情况来判断最少需要交换几次
			for (int i = 1; i < nCakeCnt; i ++) {
				//判断位置相邻的两个烙饼，是否尺寸排序上相邻
				t = pCakeArray[i] - pCakeArray[i - 1];
				if (t != 1 && t != -1) {
					ret ++;
				}
			}
			return ret;
		}
		private void search(int step) {
			int i = 0, nEstimate = 0;
			nSearch ++;
			nEstimate = lowerBound(reverseCakeArray, cakeCnt);
			if (step + nEstimate > maxSwap) {
				return;
			}
			//对于已经排好序，即翻转完成，输出结果
			if (isSorted(reverseCakeArray, cakeCnt)) {
				if (step < maxSwap) {
					maxSwap = step;
					for (i = 0; i < maxSwap; i ++) {
						swapArray[i] = reverseCakeArraySwap[i];
					}
				}
				return;
			}
			for (i = 1; i < cakeCnt; i ++) {
				reverse(0, i);
				reverseCakeArraySwap[step] = i;
				search(step + 1);
				reverse(0, i);
			}
			
		}
		private void reverse(int sti, int eni) {
			int t = 0;
			while (sti < eni) {
				t = reverseCakeArray[sti];
				reverseCakeArray[sti] = reverseCakeArray[eni];
				reverseCakeArray[eni] = t;
				sti ++;
				eni --;
			}
		}
		private boolean isSorted(int[] pReverseCakeArray, int nCakeCnt) {
			for (int i = 1; i < nCakeCnt; i ++) {
				if (pReverseCakeArray[i - 1] > pReverseCakeArray[i]) {
					return false;
				}
			}
			return true;
		}
	}
}
