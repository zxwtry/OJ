package nowcoder.zuo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

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
 * @details     Solution2:进阶问题
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
	static class Solution2 {
		public void printKMajor(int[] arr, int K) {
			if ( K < 2 ) {
				System.out.println("the value of K is invalid.");
				return;
			}
			HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
			for (int i = 0; i != arr.length; i ++) {
				if (cands.containsKey(arr[i])) {
					cands.put(arr[i], cands.get(arr[i]+1));
				} else {
					if (cands.size() == K - 1)
						allCandsMinusOne(cands);
					else
						cands.put(arr[i], i);
				}
			}
			HashMap<Integer, Integer> reals = getReals(arr, cands);
			boolean hasPrint = false;
			for (Entry<Integer, Integer> set : cands.entrySet()) {
				int key = set.getKey();
				if (reals.get(key) > arr.length / K) {
					hasPrint = true;
					System.out.print(key + " ");
				}
			}
			System.out.println(hasPrint ? "" : "no such number.");
		}
		private HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
			HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
			for (int i = 0; i != arr.length; i ++) {
				int curNum = arr[i];
				if (cands.containsKey(curNum)) {
					if (reals.containsKey(curNum))
						reals.put(curNum, reals.get(curNum) + 1);
					else
						reals.put(curNum, 1);
				}
			}
			return reals;
		}
		private void allCandsMinusOne(HashMap<Integer, Integer> cands) {
			List<Integer> removeList = new LinkedList<Integer>();
			for (Entry<Integer, Integer> set : cands.entrySet()) {
				int key = set.getKey();
				int value = set.getValue();
				if (value == 1) removeList.add(key);
				cands.put(key, value - 1);
			}
			for (int removeKey : removeList)
				cands.remove(removeKey);
		}
	}
}
