package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * 	Description:

	Count the number of prime numbers less than a non-negative number, n.
 */

public class P204_CountPrimes {
	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 2; i < Integer.MAX_VALUE; i ++) {
//			boolean isSushu = true;
//			for (int j = 2; isSushu && j <= i / 2; j ++) {
//				isSushu &= i % j != 0;
//			}
//			if (isSushu)
//			list.add(i);
//		}
//		System.out.println(list.size());
//		System.out.println(list);
//		Solution s = new Solution();
//		int count = 0;
//		for (int i = 0; i < 10000; i ++) {
//			int ans = s.binarySearch(i);
//			if (ans == 0) {
//				if (i > 2) {
//					count ++;
//				}
//			} else {
//				if (ans >= Solution.arr.length && i > 9973) {
//					System.out.println("到顶了等等");
//				} else {
//					if (Solution.arr[ans - 1] < i && Solution.arr[ans] >= i) {
//					} else {
//						count ++;
//					}
//				}
//			}
//		}
//		System.out.println(count);
//		System.out.println(s.countPrimes(9999));
		
		
//		Solution2 s2 = new Solution2();
//		
//		s2.countPrimes(63);
//		
//		s2.setPrime(63);
//		
//		System.out.println(s2.isPrime(60));
//		
//		System.out.println(s2.arr.length);
//		
//		tools.Utils.printArray(s2.arr, 10);
		
		Solution4 s4 = new Solution4();
		Solution3 s3 = new Solution3();
		Solution2 s2 = new Solution2();
		Solution s = new Solution();
		int val = 45454;
		System.out.println(s4.countPrimes(val) +"..."+s3.countPrimes(val) +"..."+ s2.countPrimes(val) + "..." + s.countPrimes(val));
		
	}
	static class Solution {
		static int[] arr = new int[] {
			2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43
		};
	    public int countPrimes(int n) {
	    	int max = arr[arr.length - 1];
	    	if (n <= max) {
	    		return binarySearch(n);
	    	}
	    	ArrayList<Integer> listOfSuShu = new ArrayList<>();
	    	for (int i = max + 1; i < n; i ++) {
	    		boolean isASuShu = true;
	    		for (int v : arr) {
	    			if (i % v == 0) {
	    				isASuShu = false;
	    				break;
	    			}
	    			if (v * v > n) {
	    				break;
	    			}
	    		}
	    		if (! isASuShu) {
	    			continue;
	    		}
	    		for (int v : listOfSuShu) {
	    			if (i % v == 0) {
	    				isASuShu = false;
	    				break;
	    			}
	    			if (v * v > n) {
	    				break;
	    			}
	    		}
	    		if (isASuShu) {
	    			listOfSuShu.add(i);
	    		}
	    	}
	        return arr.length + listOfSuShu.size();
	    }
	    /*
	     * 	目的：返回的ans表示
	     * 	arr[ans] >= n && arr[ans - 1] < n
	     */
		int binarySearch(int n) {
			if (n > arr[arr.length - 1]) {
				return arr.length;
			}
			if (n <= arr[0]) {
				return 0;
			}
			int sti = 1, eni = arr.length - 1;
			while (sti < eni) {
				int mid = (sti + eni) / 2;
				boolean isBigThanMid_1 = arr[mid - 1] < n;
				boolean isSmallOrEqualMid = arr[mid] >= n;
				if (isBigThanMid_1 && isSmallOrEqualMid) {
					return mid;
				}
				if (! isBigThanMid_1) {
					eni = mid - 1;
				}
				if (! isSmallOrEqualMid) {
					sti = mid + 1;
				}
			}
			return sti;
		}
	}
	/*
	 * 	61 ms
	 * 	14.04%
	 */
	static class Solution2 {
		int[] arr = null;
		public int countPrimes(int n) {
			if (n <= 2) {
				return 0;
			}
			generateArray(n);
			for (int  i = 2; i * i < n; i ++) {
				if (! isPrime(i)) {
					continue;
				}
				for (int j = i * i; j < n; j += i) {
					if (isPrime(j))
						setNotPrime(j);
				}
			}
			int count = 0;
			for (int i = 2; i < n; i ++) {
				if (isPrime(i)) {
					count ++;
				}
			}
			return count;
		}
		void generateArray(int n) {
			this.arr = new int[(n + 1) / 32 + 1];
		}
		boolean isPrime(int index) {
			int arrIndex = index / 32;
			int intIndex = index % 32;
			int val = arr[arrIndex];
			val = val >>> intIndex;
			return val % 2 == 0;
		}
		void setNotPrime(int index) {
			int arrIndex = index / 32;
			int intIndex = index % 32;
			int val = arr[arrIndex];
			val += 1 << intIndex;
			arr[arrIndex] = val;
		}
	}
}
