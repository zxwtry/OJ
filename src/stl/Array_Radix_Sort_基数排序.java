package stl;

public class Array_Radix_Sort_基数排序 {
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
