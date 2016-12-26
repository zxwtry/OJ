package nowcoder.zuo;

/**
 * 	给定一个整型数组arr，打印其中出现次数大于一半的数，
 * 	如果没有这样的数，打印提示信息
 * 	要求：时间O(N)，空间O(1)	
 * 
 * 	进阶：
 * 		给定一个整型数组arr，和整数K，打印其中出现次数大于N/K的数，
 * 		如果没有这样的数，打印提示信息
 * 		要求：时间O(N*K)，空间O(K)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book094_找到数组中出现次数大于N_K的数.java
 * @type        Book094_找到数组中出现次数大于N_K的数
 * @date        2016年12月26日 下午9:31:03
 * @details     Solution1:原问题
 */
public class Book094_找到数组中出现次数大于N_K的数 {
	static class Soltuion1 {
		public void printHalfMajor(int[] arr) {
			int cand = 0;
			int times = 0;
			for (int i = 0; i != arr.length; i ++) {
				if (times == 0) {
					cand = arr[i];
					times = 1;
				} else if (arr[i] == cand) {
					times ++;
				} else {
					times --;
				}
			}
			times = 0;
			for (int i = 0; i != arr.length; i ++) {
				if (arr[i] == cand)
					times ++;
			}
			if (times > arr.length / 2)
				System.out.println(cand);
			else
				System.out.println("no such number.");
		}
	}
}
