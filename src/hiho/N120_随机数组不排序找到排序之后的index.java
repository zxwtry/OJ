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
//		test();
		testRadixSolution();
	}
	static void testRadixSolution() {
		RadixSolution rs = new RadixSolution();
		int[] arr = rs.get题目的样式(10, 0);
//		arr = new int[] {1, 9, 9, 9, 1, 1, 44, 4, 1};
		tools.Utils.printArray(arr, 100, 5);
		int newMax = rs.radixSort(arr, 10);
		tools.Utils.printArray(arr, 100, 5);
		System.out.println(newMax);
	}
	static void test() {
		int max = (int) ( Math.random() * 100 );
		int len = (int) ( Math.random() * 10000 );
		RadixSolution rs = new RadixSolution();
		int[] arr = rs.get题目的样式(len, max);
		int[] arrSolve = arr.clone();
		int[] arrStandard = arr.clone();
		standard(arrStandard);
		rs.radixSort(arrSolve, max);
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
	static class RadixSolution {
		/*
		 * 	 更改成从0开始，避免逻辑混乱
		 */
		public int[] get题目的样式(int len, int max) {
			int[] arr = new int[len];
			for (int i = 0; i < len; i ++) {
				arr[i] = getNumber(max) * (max + 1) + getNumber(max);
			}
			return arr;
		}
		private int getNumber(int max) {
			return (int) (Math.random() * (max + 1));
		}
		/*
		 * 	使用基数排序
		 * 	返回，排序之后的max
		 */
		public int radixSort(int[] arr, int max) {
			int bits = getBits(max);
			int LEN = 1 << bits;
			int[][] save = new int[LEN][arr.length];
			int[] count = new int[LEN];
			int[] conn = new int[arr.length];
			for (int connIndex = 0; connIndex != conn.length; connIndex ++) {
				conn[connIndex] = connIndex;
			}
			int arrIndex = 0;
			for (int radixIndex = 0; radixIndex != 2; radixIndex ++) {
				int arrValueRightBits = radixIndex * bits;
				for (arrIndex = 0; arrIndex != arr.length; arrIndex ++) {
					int saveI = ( arr[conn[arrIndex]] >>> arrValueRightBits ) & (LEN - 1);
					save[saveI][count[saveI] ++] = conn[arrIndex];
				}
				arrIndex = 0;
				for (int countIndex = 0; countIndex != LEN; countIndex ++) {
					for (int countValue = 0; countValue != count[countIndex]; countValue ++) {
						conn[arrIndex++] = save[countIndex][countValue];
					}
					count[countIndex] = 0;
				}
			}
			int[] newArr = new int[arr.length];
			int newArrValue = -1;
			for (int connIndex = 0; connIndex != conn.length; connIndex ++) {
				if (connIndex != 0 && arr[conn[connIndex]] == arr[conn[connIndex - 1]]) {
					newArr[conn[connIndex]] = newArr[conn[connIndex - 1]];
				} else {
					newArr[conn[connIndex]] = ++ newArrValue;
				}
			}
			System.arraycopy(newArr, 0, arr, 0, arr.length);
			return newArrValue;
		}
		private int getBits(int max) {
			int bits = 1;
			while (max > 1) {
				max = max >>> 1;
				bits ++;
			}
			return bits;
		}
	}
}
