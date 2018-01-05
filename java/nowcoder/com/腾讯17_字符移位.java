package nowcoder.com;

import java.util.LinkedList;

/**
 * 	给一个char[] arr，
 * 	将小写字母移动到左边，大写字母移动到右边。
 * 	保留小写字母中相对顺序，大写字母一样。
 * 	例如：aAbBcCdD  -->  abcdABCD
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        腾讯17_字符移位.java
 * @type        腾讯17_字符移位
 * @date        2017年2月9日 上午11:54:12
 * @details     StandardSolution: 归并排序
 * @details     Solution1: 归并排序，　可能会栈溢出
 * @details     Solution2: 冒泡排序，　可能会栈溢出
 */
public class 腾讯17_字符移位 {
	static class StandardSolution {
		public void reArrange(char[] arr) {
			LinkedList<Character> lowerList = new LinkedList<Character>();
			LinkedList<Character> upperList = new LinkedList<Character>();
			for (char c : arr) {
				if (c >= 'a' && c <= 'z') {
					lowerList.add(c);
				} else {
					upperList.add(c);
				}
			}
			int arrIndex = 0;
			for (char c : lowerList) {
				arr[arrIndex ++] = c;
			}
			for (char c : upperList) {
				arr[arrIndex ++] = c;
			}
		}
	}
	static class Solution1 {
		public void reArrange(char[] arr) {
			reArrangeInternal(arr, 0, arr.length - 1);
		}
		private void reArrangeInternal(char[] arr, int i, int j) {
			if (i >= j) {
				return;
			} else if (i == j - 1) {
				if (arr[i] >= 'A' && arr[i] <= 'Z' && arr[j] >= 'a' && arr[j] <= 'z') {
					swap(arr, i, j);
				}
			} else {
				int mid = (i + j) / 2;
				reArrangeInternal(arr, i, mid);
				reArrangeInternal(arr, mid + 1, j);
				int firstIndexOfUpper1 = i, firstIndexOfUpper2 = mid + 1;
				while (firstIndexOfUpper1 <= mid) {
					if (arr[firstIndexOfUpper1] >= 'A' && arr[firstIndexOfUpper1] <= 'Z') break;
					else firstIndexOfUpper1 ++;
				}
				while (firstIndexOfUpper2 <= j) {
					if (arr[firstIndexOfUpper2] >= 'A' && arr[firstIndexOfUpper2] <= 'Z') break;
					else firstIndexOfUpper2 ++;
				}
				firstIndexOfUpper2 --;
				reverse(arr, firstIndexOfUpper1, firstIndexOfUpper2);
				reverse(arr, firstIndexOfUpper1, firstIndexOfUpper1 + (firstIndexOfUpper2 - mid - 1));
				reverse(arr, firstIndexOfUpper1 + (firstIndexOfUpper2 - mid - 1) + 1, firstIndexOfUpper2);
			}
		}
		private void reverse(char[] arr, int sti, int eni) {
			while (sti < eni) {
				swap(arr, sti ++, eni --);
			}
		}
		private void swap(char[] arr, int i, int j) {
			char t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
	}
	static class Solution2 {
		public void reArrange(char[] arr) {
			for (int i = arr.length - 1; i > -1; i --) {
				for (int j = i + 1; j < arr.length; j ++) {
					if (arr[j] >= 'a' && arr[j] <= 'z' && arr[j - 1] >= 'A' && arr[j - 1] <= 'Z') {
						swap(arr, j - 1, j);
					}
				}
			}
		}
		private void swap(char[] arr, int i, int j) {
			char t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
	}
}
