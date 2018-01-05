package nowcoder.zuo;

/**
 * 	给定一个整型数组arr，代表数值不同的纸牌排成一条线。
 * 	玩家A和玩家B一次拿走每张纸牌，规定玩家A先拿。
 * 	每个玩家每次只能拿走最左或最右的纸牌。
 * 	玩家A和玩家B都绝顶聪明，返回最后获胜者的分数。
 * 
 * 	举例：
 * 		arr=[1,2,100,4]   返回101
 * 		arr=[1,100,4]     返回100
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book066_排成一条线的纸牌博弈问题.java
 * @type        Book066_排成一条线的纸牌博弈问题
 * @date        2016年12月16日 下午7:24:31
 * @details     
 */
public class Book066_排成一条线的纸牌博弈问题 {
	static class Solution1 {
		public int win(int[] arr) {
			if (arr == null || arr.length == 0) return 0;
			return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
		}
		private int f(int[] arr, int i, int j) {
			if (i == j) return arr[i];
			return Math.max(arr[i] + s(arr, i+1, j), arr[j] + s(arr, i, j-1));
		}
		private int s(int[] arr, int i, int j) {
			if (i == j) return 0;
			return Math.min(f(arr, i+1, j), f(arr, i, j-1));
		}
	}
	static class Solution2 {
		public int win(int[] arr) {
			if (arr == null || arr.length == 0) return 0;
			int[][] f = new int[arr.length][arr.length];
			int[][] s = new int[arr.length][arr.length];
			for (int i = 0; i < arr.length; i ++) {
				f[i][i] = arr[i];
				for (int j = i - 1; j >= 0; j --) {
					f[j][i] = Math.max(arr[j] + s[j+1][i], arr[i] + s[j][i-1]);
					s[j][i] = Math.min(f[j+1][i], f[j][i-1]);
				}
			}
			return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
		}
	}
}
