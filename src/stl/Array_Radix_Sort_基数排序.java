package stl;

import java.util.Arrays;

public class Array_Radix_Sort_基数排序 {
	static void debugSolution_非位运算() {
		
	}
	static class Solution_非位运算 {
		final int NUM = 23;		//代表只能分成23组，故意取一个不利于计算的数字
		final int[][] save = new int[NUM][];
		final int[] count = new int[NUM];
		//那么就是每次对23取余
		public void radixSort_noneNegative(int[] arr) {
			if (arr == null || arr.length == 0) {
				return;
			}
			int len = arr.length;
			//先对save的每一行，申请内存空间
			for (int i = 0; i < NUM; i ++) {
				save[i] = new int[len];
			}
			//确定排序终点
			//一旦isAllZero是true，那么退出循环
			boolean isAllZero = false;
			int baseOfNum = 0;
			while (! isAllZero) {
				getBase(baseOfNum);
				
			}
		}
		private int getBase(int baseOfNum) {
			if (baseOfNum == 0) {
				return 1;
			} else {
				return baseOfNum * NUM;
			}
		}
	}
	static void testSolution_位运算() {
		boolean isAllTrue = true;
		for (int i = 0; i < 1000; i ++) {
			int n = (int)(Math.random() * (i + 100));
			int min = (int)(Math.random() * (i + 100));
			int max = min + (int)(Math.random() * (i + 100));
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			int[] arrStandard = arr.clone();
			int[] arrSolution_位运算 = arr.clone();
			Arrays.sort(arrStandard);
			Solution_位运算 sw = new Solution_位运算();
			sw.radixSort_noneNegative(arrSolution_位运算);
			boolean isTrue = true;
			for (int index = 0; isTrue && index < arr.length; index ++) {
				isTrue &= arrSolution_位运算[index] == arrStandard[index];
			}
			if (! isTrue) {
				System.out.println("原来的数组是：");
				tools.Utils.printArray(arr, n + 100, 5);
				System.out.println("标准排序后的数组是：");
				tools.Utils.printArray(arrStandard, n + 100, 5);
				System.out.println("位运算基数排序后的数组是：");
				tools.Utils.printArray(arrSolution_位运算, n + 100, 5);
				isAllTrue &= isTrue;
				break;
			}
		}
		System.out.println(isAllTrue);
	}
	static class Solution_位运算 {
		final int LEN_BITS = 4;
		final int LEN = 1 << LEN_BITS;		//设定基数排序总共设置多少个组，为了避免过多乘除，使用2^4=16
		//先比较低位，后比较高位
		public void radixSort_noneNegative(int[] arr) {
			if (arr == null || arr.length == 0) {
				return;
			}
			int radixTimes = getRadixTimes(arr);
			int[][] save = new int[LEN][arr.length];
			int[] count = new int[LEN];
			int arrValueRightBits = 0;
			int arrIndex = 0;
			for (int radixIndex = 0; radixIndex != radixTimes; radixIndex ++) {
				arrValueRightBits = radixIndex * LEN_BITS;
				for (arrIndex = 0; arrIndex != arr.length; arrIndex ++) {
					int saveI = ( arr[arrIndex] >>> arrValueRightBits ) & 0xf;
					save[saveI][count[saveI] ++] = arr[arrIndex];
				}
				arrIndex = 0;
				for (int countIndex = 0; countIndex != LEN; countIndex ++) {
					for (int countValue = 0; countValue != count[countIndex]; countValue ++) {
						arr[arrIndex ++] = save[countIndex][countValue];
					}
					count[countIndex] = 0;
				}
			}
		}
		//外部保证arr非null非空
		private int getRadixTimes(int[] arr) {
			int radixTimes = 0;
			for (int val : arr) {
				radixTimes = Math.max(radixTimes, getRadixTimes(val));
			}
			return radixTimes;
		}
		private int getRadixTimes(int val) {
			int radixTimes = 0;
			while (val != 0) {
				val = val >>> LEN_BITS;
				radixTimes ++;
			}
			return radixTimes;
		}
	}
}
