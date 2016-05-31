package nowcoder.zuo;

import java.util.Arrays;

/*
 * 	给定一个数组，如果排序后，相邻两个数之间的差值是一定的。
 * 	找出这些差值中的最大值。
 * 	要求：
 * 		时间复杂度 O(N) 空间复杂度 O(N)
 */

public class C06数组排序后相邻数的最大差值 {
	public static void main(String[] args) {
		int[] arr = {9,4000,309,100,2,5,8201};
		System.out.println(findMaxCha(arr));
	}
	static int findMaxCha(int[] arr) {
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for (int index = 0; index < arr.length; index ++) {
			if (arr[index] > max)
				max = arr[index];
			if (arr[index] < min)
				min = arr[index];
		}
		int[] cou = new int[arr.length+1];
		int[] mim = new int[arr.length+1];
		int[] mam = new int[arr.length+1];
		Arrays.fill(cou, 0);
		Arrays.fill(mim, Integer.MAX_VALUE);
		Arrays.fill(mam, Integer.MIN_VALUE);
		int ran = (max-min)/(arr.length+1) + 1;
		for (int index = 0; index < arr.length; index ++) {
			int cenIndex = (arr[index] - min)/ran;
			cou[cenIndex] ++;
			if (arr[index] < mim[cenIndex])
				mim[cenIndex] = arr[index];
			if (arr[index] > mam[cenIndex])
				mam[cenIndex] = arr[index];
		}
		int laseCouNotOIndex = arr.length;
		for (; laseCouNotOIndex >= 0; laseCouNotOIndex --)
			if (cou[laseCouNotOIndex] != 0)
				break;
		int maxCha = Integer.MIN_VALUE;
		int minLastIndex = 0, minLast = 0;
		boolean isHas0 = false;
		while (minLastIndex <= laseCouNotOIndex) {
			if (cou[minLastIndex] == 0) {
				isHas0 = true;
			} else {
				if (isHas0) {
					if (mim[minLastIndex]-minLast > maxCha)
						maxCha = mim[minLastIndex] - minLast;
					minLast = mam[minLastIndex];
					isHas0 = false;
				} else {
					minLast = mam[minLastIndex];
				}
			}
			minLastIndex ++;
		}
		return maxCha;
	}
}
