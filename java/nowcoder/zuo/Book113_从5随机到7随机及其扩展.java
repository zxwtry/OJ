package nowcoder.zuo;

/**
 * 	[题目]
 * 	给定一个等概率随机产生1~5的随机函数rand1To5如下：
 * 	public int rand1To5() {
 * 		return (int) (Math.random() * 5) + 1;
 * 	}
 * 	除此之外，不能使用任何额外的随机机制，请用rand1To5实现rand1To7。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book113_从5随机到7随机及其扩展.java
 * @type        Book113_从5随机到7随机及其扩展
 * @date        2017年1月3日 下午10:19:23
 * @details     
 */
public class Book113_从5随机到7随机及其扩展 {
	static class Solution1 {
		public int rand1To7Impl() {
			int newRand = rand1To5() * 5 + rand1To5();
			while (newRand > 21) {
				newRand = rand1To5() * 5 + rand1To5();
			}
			return newRand;
		}
		public int rand1To5() {
			return (int) (Math.random() * 5) + 1;
		}
	}
}
