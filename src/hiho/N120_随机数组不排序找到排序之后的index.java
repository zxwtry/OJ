package hiho;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 	做这样的限定，
 * 		0 - 11
 * 	生成这样的
 * 		0000 　0001   ...  1001 ... 1111的数，
 * 	对这样的限定下，能够实现O(N)
 * 	完成这样的工作吗？
 * 	[14, 21, 41, 11, 12, 13, 20, 30]
 * 	[4,  6,  8,  1,  2,  3,  5,  7]
 * 	如果数据是随机的，那么是无法在O(N)的时间内完成相应的工作的。
 * 	现在给定一个数，这个数是在程序进行的上一步可以得到的。
 * 	假定给的叫M，那么所有的数是以这样的方式组织的 [0-M][0-M]
 * 	并且保证如果[][]是同时存在，那么第二个[]一定和M拥有相同的位数，
 * 	意思就是：假设M是12，那么不会出现13这种数，出现的形式会是103
 * 	在这种情况下，是能够保证O(N)的时间内排出对应结果吗？
 * 
 * 	
 */

public class N120_随机数组不排序找到排序之后的index {
	public static void main(String[] args) {
//		int[] arr = {14, 21, 41, 11, 12, 13, 20, 30};
//		int M = 100;
//		solve(arr, M);
//		standard(arr);
//		tools.Utils.printArray(arr, 100);
		test();
	}
	static void test() {
		int M = 999;
		int maxMbits = 1000;
		int[] arr = new int[200];
		int arrIndex = 0;
		while (arrIndex < arr.length) {
			int valBig = (int)(Math.random() * (M + 1));
			int valSmall = (int)(Math.random() * (M + 1));
			int val = valBig * maxMbits + valSmall;
			arr[arrIndex ++] = val;
		}
		int[] arrSolve = arr.clone();
		int[] arrStandard = arr.clone();
		solve(arrSolve, M);
		standard(arrStandard);
		boolean isAllSame = true;
		for (int i = 0; i < arr.length; i ++) {
			if (arrSolve[i] == arrStandard[i]) {
				isAllSame &= true;
			}
		}
		System.out.println(isAllSame);
	}
	//M是最大值，可以达到的最大值[0-M]
	//怎么有种脱裤子放屁的感觉。。。先测试吧
	static void solve(int[] arr, int M) {
		int Mcopy = M;	
		int maxMbits = 1;	//大于这个值，代表两位
		while (Mcopy != 0) {
			Mcopy = Mcopy / 10;
			maxMbits = maxMbits * 10;
		}
		int[][] map = new int[M+1][M+1];
		for (int i = 0; i <= M; i ++) {
			for (int j = 0; j <= M; j ++) {
				map[i][j] = -1;
			}
		}
		for (int i = 0; i < arr.length; i ++) {
			int valBig = 0;
			int valSmall = 0;
			if (arr[i] >= maxMbits) {
				valBig = arr[i] / maxMbits;
				valSmall = arr[i] % maxMbits;
			} else {
				valBig = 0;
				valSmall = arr[i];
			}
			map[valBig][valSmall] = 0;
		}
		int maxLabel = 0;
		for (int i = 0; i <= M; i ++) {
			for (int j = 0; j <= M; j ++) {
				if (map[i][j] == 0) {
					map[i][j] = maxLabel ++;
				}
			}
		}
		for (int i = 0; i < arr.length; i ++) {
			int valBig = 0;
			int valSmall = 0;
			if (arr[i] >= maxMbits) {
				valBig = arr[i] / maxMbits;
				valSmall = arr[i] % maxMbits;
			} else {
				valBig = 0;
				valSmall = arr[i];
			}
			arr[i] = map[valBig][valSmall];
		}
	}
	static void standard(int[] arr) {
		HashMap<Integer, Integer> valToIndex = new HashMap<>();
		for (int i = 0; i < arr.length; i ++) {
			valToIndex.put(arr[i], i);
		}
		int[] arrClone = arr.clone();
		Arrays.sort(arrClone);
		for (int i = 0; i < arr.length; i ++) {
			arr[valToIndex.get(arrClone[i])] = i;
		}
	}
}
