package hiho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 	就是Manacher
 */
public class N001_最长回文子串 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("D:/file/data/hiho_n001.txt"));
		int numOfTest = Integer.parseInt(scanner.nextLine().trim());
		while (numOfTest -- > 0) {
			String line = scanner.next();
			System.out.println(maxPalindromeSubString(line));
		}
		scanner.close();
		
//		test();
	}
//	static void test() {
//		int count = 0;
//		for (int times = 0; times < 1000; times ++) {
//			int[] arr = tools.Random随机生成器.A_生成一个随机数据(600, 0, 2);
//			char[] cs = new char[arr.length];
//			for (int i = 0; i < arr.length; i ++) {
//				cs[i] = (char) ('A' + arr[i]);
//			}
//			String line = new String(cs);
//			if (testStandard(line) != maxPalindromeSubString(line)) {
//				tools.FileUtils.B_纪录String_append("D:/file/data/hiho_n001_log.txt", line);
//				System.out.println(line);
//				System.out.println(testStandard(line) + "..." + maxPalindromeSubString(line));
//				count ++;
//				tools.FileUtils.B_纪录String_append("D:/file/data/hiho_n001_log.txt", "==========");
//			}
//		}
//		System.out.println(count);
//	}
//	static int testStandard(String line) {
//		char[] cs = line.toCharArray();
//		int maxDiameterOfPalindrome = 0;
//		for (int index = 0;index < cs.length; index ++) {
//			int leftIndex = index;
//			int rightIndex = index;
//			while (leftIndex - 1 > -1 && rightIndex + 1 < cs.length && cs[leftIndex - 1] == cs[rightIndex + 1]) {
//				leftIndex --;
//				rightIndex ++;
//			}
//			maxDiameterOfPalindrome = Math.max(maxDiameterOfPalindrome, rightIndex - leftIndex + 1);
//			leftIndex = index + 1;
//			rightIndex = index;
//			if (leftIndex >= cs.length) {
//				continue;
//			}
//			while (leftIndex - 1 > -1 && rightIndex + 1 < cs.length && cs[leftIndex - 1] == cs[rightIndex + 1]) {
//				leftIndex --;
//				rightIndex ++;
//			}
//			maxDiameterOfPalindrome = Math.max(maxDiameterOfPalindrome, rightIndex - leftIndex + 1);
//		}
//		return maxDiameterOfPalindrome;
//	}
	static char[] manacher = null;
	private static int maxPalindromeSubString(String line) {
		generateManacher(line);
		int[] radius = new int[manacher.length];
		int maxIndexTouched = 0;		//向右能够接触到的最大index
		int lastIndexOfCircle = 0;		//上一个扩的圆的圆心
		radius[0] = 0;
		int radiusOfMaxCircle = 0;		//这个就是最大的回文串的长度
		for (int index = 0; index < manacher.length; index ++) {
			if (maxIndexTouched >= manacher.length - 1) {
				break;
			}
			if (index >= maxIndexTouched) {
				int leftIndex = index, rightIndex = index;
				while (leftIndex - 1 > -1 && rightIndex + 1 < manacher.length && manacher[leftIndex - 1] == manacher[rightIndex + 1]) {
					leftIndex --;
					rightIndex ++;
				}
				radius[index] = (rightIndex - leftIndex) / 2;
				maxIndexTouched = Math.max(maxIndexTouched, rightIndex);
				lastIndexOfCircle = index;
				radiusOfMaxCircle = Math.max(radiusOfMaxCircle, radius[index]);
			} else {
				int mirrorIndex = 2 * lastIndexOfCircle - index;
				int radiusOfMirrorIndex = radius[mirrorIndex];
				if (radiusOfMirrorIndex + index == maxIndexTouched) {
					int leftIndex = index - radiusOfMirrorIndex;
					int rightIndex = maxIndexTouched;
					while (leftIndex - 1 > -1 && rightIndex + 1 < manacher.length && manacher[leftIndex - 1] == manacher[rightIndex + 1]) {
						leftIndex --;
						rightIndex ++;
					}
					radius[index] = (rightIndex - leftIndex) / 2;
					maxIndexTouched = Math.max(maxIndexTouched, rightIndex);
					lastIndexOfCircle = index;
					radiusOfMaxCircle = Math.max(radiusOfMaxCircle, radius[index]);
				} else if (radiusOfMirrorIndex + index < maxIndexTouched) {
					radius[index] = radiusOfMirrorIndex;
				} else {
					radius[index] = maxIndexTouched - index;
				}
			}
		}
//		tools.Utils.printArray_先打印下标(manacher.length);
//		tools.Utils.printArray(manacher, 100);
//		tools.Utils.printArray(radius, 100);
//		System.out.println("diameterOfMaxCircle : " + radiusOfMaxCircle);
		return radiusOfMaxCircle;
	}
	private static void generateManacher(String line) {
		manacher = new char[line.length() * 2 + 1];
		for (int i = 0; i < manacher.length; i ++) {
			if (i % 2 == 0) {
				manacher[i] = '#';
			} else {
				manacher[i] = line.charAt(i / 2);
			}
		}
	}
}
