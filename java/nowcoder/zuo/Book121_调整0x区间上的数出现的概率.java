package nowcoder.zuo;

/**
 * 	Math.random()等概率随机返回一个在[0,1)范围上的数。
 * 	[0, x)区间上的数出现的概率为x(0<x<=1)，
 * 	给定一个大于0的整数k，并且可以使用Math.random()函数，
 * 	在[0, x)区间上的数出现的概率为x^k(0<x<=1)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book121_调整0x区间上的数出现的概率.java
 * @type        Book121_调整0x区间上的数出现的概率
 * @date        2017年1月6日 下午4:27:37
 * @details     
 */
public class Book121_调整0x区间上的数出现的概率 {
	static class Solution1 {
		public double randXPowerK(int k) {
			if (k < 1) return 0;
			double ans = -1;
			for (int i = 0; i < k; i ++)
				ans = Math.max(ans,  Math.random());
			return ans;
		}
	}
}
