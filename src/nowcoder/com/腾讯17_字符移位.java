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
}
