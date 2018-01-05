package nowcoder.zuo;

/**
 * 	[题目]
 * 	有一个机器按自然数序列的方式吐出球(1号球，2号球，3号球，...)
 * 	你有一个袋子，袋子最多只能装下K个球，除袋子以外，没有其它空间。
 * 
 * 	设计一种选择方式，使得当机器吐出第N号球的时候(N>K)，
 * 	你袋子中的球数是K个，同时保证从1号球到N号球中的每一个，
 * 	被选进袋子的概率都是K/N。
 * 
 * 	[举例]
 * 	有一个能装下10个球的袋子，当吐出100个球时，
 * 	袋子里有10个球，并且1~1000号中的每一个球被选中的概率都是10/1000。
 * 	继续吐球，当吐出i个球时，袋子里有10个球，并且1~i号中的每一个球
 * 	被选中的概率都是10/i，即吐球的同时，已经吐出的球被选中的概率也动态地变化。
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book116_蓄水池算法.java
 * @type        Book116_蓄水池算法
 * @date        2017年1月4日 下午10:20:05
 * @details     题目的核心是蓄水池算法：
 * @details     1，	处理1~k号球，直接放进袋子里。
 * @details     2，	处理第i号球时(i>k)，以k/i的概率决定
 * @details       	是否将i号球放进袋子。如果不决定将第i号球
 * @details       	放进袋子，直接扔掉第i号球。如果决定将第i号球
 * @details       	放进袋子，从袋子里的k个球中随机扔掉一个，
 * @details       	然后把第i号球放入袋子。
 * @details     3,	处理第i+1号球时，重复步骤1或步骤2
 */
public class Book116_蓄水池算法 {
	static class Solution {
		public int[] getKNumsRand(int k, int max) {
			if (max < 1 || k < 1) return null;
			int[] kNumsRand = new int[Math.min(k, max)];
			for (int i = 0; i < kNumsRand.length; i ++)
				kNumsRand[i] = i + 1;
			for (int i = k+1; i <= max; i ++)
				if (rand(i) <= k)
					kNumsRand[rand(k) - 1] = i;
			return kNumsRand;
		}
		private int rand(int max) {
			return (int)(Math.random() * max) + 1;
		}
	}
}
