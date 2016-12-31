package leetcode;

/**
 * 	题目的关键是要写出下面的动态转移方程:
	local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
	global[i][j]=max(local[i][j],global[i-1][j])，
	而这个方程的具体理解是这样的。
	首先global比较简单，不过是不断地和已经计算出的local进行比较，把大的保存在global中。
	然后看local,关键是要理解local的定义，local[i][j]表示，前i天进行了j次交易，并且第i天进行了第j次交易的最大利润，所以local[i][j]中必然有一次交易，也就是当近一次交易，发生在第i天。 local由两个部分的比较完成。
	
	第一部分是，global[i-1][j-1]+max(diff,0), 表示的就是，前面把之前的j - 1次交易，放在之前的i - 1天，然后让第i天来进行第j次交易，那么加入此时diff(price[i] - price[i - 1])大于零，那么正好可以可借助这次交易的机会增长里利润(利润= diff)，否则的话，如果diff小于零，那就在第i天当天进行一次买卖，凑一次交易的次数，但是产生利润为0.
	第二部分是, local[i-1][j]+diff， 这里的 local[i-1][j]表示的是，前面j次交易在第i -1天就已经完成了，可是因为说了local[a][b]一定要表达在第a天完成了b次交易的最大利润，所以就需要强制使得交易在第i天发生，为了实现这一点，只需要在local[i - 1][j]的基础上，加上diff ( = price[i] - price[i - 1])就可以了。如果diff < 0 那也没有办法，因为必须满足local的定义。接下来算global的时候，总会保证取得一个更大的值。
	
	关于正道题目的全文的分析，Code Ganker的还挺不错。 下面全文摘抄作者的分析:
	"这道题是Best Time to Buy and Sell Stock的扩展，现在我们最多可以进行两次交易。我们仍然使用动态规划来完成，事实上可以解决非常通用的情况，也就是最多进行k次交易的情况。
	
	这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”。我们维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），另一个是当前到达第i天，最多可进行j次交易，并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）。下面我们来看递推式。
	全局的比较简单，
	global[i][j]=max(local[i][j],global[i-1][j])，
	也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。
	对于局部变量的维护，递推式是
	local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
	也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）。
	
	上面的算法中对于天数需要一次扫描，而每次要对交易次数进行递推式求解，所以时间复杂度是O(n*k)，如果是最多进行两次交易，那么复杂度还是O(n)。空间上只需要维护当天数据皆可以，所以是O(k)，当k=2，则是O(1)。"
 */

/**
 * 	Say you have an array for which the ith element is the price of a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete at most k transactions.
	
	Note:
	You may not engage in multiple transactions at the same time (ie, you must sell the 
	stock before you buy again).
	
	Credits:
	Special thanks to @Freezen for adding this problem and creating all test cases.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P188_BestTimeToBuyAndSellStockIV.java
 * @type        P188_BestTimeToBuyAndSellStockIV
 * @date        2016年12月30日 下午6:12:37
 * @details     Solution1: AC 9ms 10.23%
 */
public class P188_BestTimeToBuyAndSellStockIV {
	static class Solution1 {
	    public int maxProfit(int k, int[] p) {
	    	if (k <= 0 || p == null || p.length < 2) return 0;
	        int n = p.length; 
	        if (k >= n / 2) {
	            int ans = 0;
	            for (int i = 1; i < n; ++i) {
	                if (p[i] - p[i - 1] > 0) {
	                    ans += p[i] - p[i - 1];
	                }
	            }
	            return ans;
	        }
	        int[][] l = new int[n][k + 1];
	        int[][] g = new int[n][k + 1];
	        for (int j = 1; j <= k; ++j) {
	            for (int i = 1; i < n; ++i) {
	                l[i][j]= Math.max(
	                    l[i - 1][j] + p[i] - p[i -1], 
	                    g[i - 1][j - 1] + Math.max(0, p[i] - p[i - 1]));
	                g[i][j] = Math.max(l[i][j], g[i - 1][j]);
	            }
	        }
	        return g[n - 1][k];
	    }
	}
}
