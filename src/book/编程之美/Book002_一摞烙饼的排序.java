package book.编程之美;

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
}
