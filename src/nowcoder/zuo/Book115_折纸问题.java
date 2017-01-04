package nowcoder.zuo;

/**
 * 	[题目]
 * 	将一段纸条竖着放在桌子上，然后从纸条的下边向上方对折一次，
 * 	对折N次展开，此时共有2^N-1条折痕，从上到下打印所有折痕的方向。
 * 	
 * 	[举例]
 * 	N=1打印：	down
 * 	N=2打印：	down
 * 	        down
 * 			up
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book115_折纸问题.java
 * @type        Book115_折纸问题
 * @date        2017年1月4日 下午10:05:24
 * @details     对折第1次产生的折痕：				下
 * @details     对折第2次产生的折痕：		     上			        下
 * @details     对折第1次产生的折痕：	      上           下		上	  下
 * @details     从上到下打印，就是右->中->左。
 */
public class Book115_折纸问题 {
	static class Solution {
		public void printAllFolds(int N) {
			printProcess(1, N, true);
		}
		private void printProcess(int i, int n, boolean isDown) {
			if (i < n) {
				printProcess(i + 1, n, true);
				System.out.println(isDown ? "down" : "up");
				printProcess(i + 1, n, false);
			}
		}
	}
}
