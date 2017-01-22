package book.编程之美;

/**
 * 	[题目]
 * 	A表示'将'，B表示'帅'
 * 	A,B限制在3*3的格子里运动。
 * 	在书中，d,e,f是AB的三条竖线
 * 	在书中，1,2,3是B的运动行
 * 	在书中，8,9,10是A的运动行
 * 	输出A,B所有合法位置。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     book.编程之美
 * @file        Book001_中国象棋将帅问题.java
 * @type        Book001_中国象棋将帅问题
 * @date        2017年1月22日 下午10:15:31
 * @details		
 */
public class Book001_中国象棋将帅问题 {
	static class Solution1 {
		public void print() {
			for (char ai = 'd'; ai <= 'f'; ai ++) {
				for (char bi = 'd'; bi <= 'f'; bi ++) {
					if (ai != bi) {
						for (int aj = 8; aj <= 10; aj ++) {
							for (int bj = 1; bj <= 3; bj ++) {
								System.out.printf("将：%s%c\t\t帅：%s%c\r\n", ""+aj, ai, ""+bj, bi);
							}
						}
					}
				}
			}
		}
	}
}
