package nowcoder.com;




/*
 * 	题目描述
									
	求一个字符串的最大回文前缀长度。回文是指正反方向读起来都一样的字符串，比如“abcdcba”就是一个回文。
	
									
	输入
	一个文本文件，至少包含一个字节。每个字节是一个字符。最大长度可能有几十万字节。
	样例输入
	sogou
	输出
	最大回文前缀的长度。
	样例输出
	1
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */


import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;

public class 搜狗17_一个字符串的最大回文前缀长度 {
	public static void main(String[] args) {
		solve3();
	}
	/*
	 * 	如何保证不TLE呢？
	 * 	Manacher是O(N)的时间复杂度，求的最长回文长度。
	 * 	现在需要进行的求以0开始的回文，可能的最大长度。
	 * 	wocao，真是不能多想，先实现再说。
	 * 	AC了
	 */
	static void solve3() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		for (int i = line.length() - 1; i > -1; i --) {
			int sti = 0, eni = i;
			boolean isPalindrome = true;
			while (sti < eni && isPalindrome) {
				if (line.charAt(sti) == line.charAt(eni)) {
					sti ++;
					eni --;
				} else {
					isPalindrome = false;
				}
			}
			if (isPalindrome) {
				System.out.println(i + 1);
				break;
			}
		}
		sc.close();
	}
	/*
	 * 	还是TLE，比solve1还慢
	 */
	static void solve2() {
		InputStreamReader isr = new InputStreamReader(System.in);
		char[] manacher = new char[2000000];
		int manacherLength = 0;
		try {
			manacherLength = (isr.read(manacher) - 2) * 2 + 1;
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int index = manacherLength - 1; index > -1; index --) {
			if (index % 2 == 0) {
				manacher[index] = '#';
			} else {
				manacher[index] = manacher[index / 2];
			}
		}
		//这里要找的是最大回文前缀
		int[] radius = new int[manacherLength];
		radius[0] = 0;
		int maxTouchedIndex = 0;
		int lastCircleIndex = 0;
		int ans = 0;
		for (int index = 0; index < manacherLength; index ++) {
			if (maxTouchedIndex >= manacherLength - 1) {
				break;
			}
			if (index >= maxTouchedIndex) {
				int leftIndex = index, rightIndex = index;
				while (leftIndex-1 > -1 && rightIndex+1 < manacherLength &&
						manacher[leftIndex-1] == manacher[rightIndex+1]) {
					leftIndex --;
					rightIndex ++;
				}
				radius[index] = (rightIndex - leftIndex) / 2;
				maxTouchedIndex = rightIndex;
				lastCircleIndex = index;
			} else {
				int mirrorIndex = lastCircleIndex*2 - index;
				int mirrorRadius = radius[mirrorIndex];
				if (mirrorRadius+index == maxTouchedIndex) {
					int leftIndex = index, rightIndex = index;
					while (leftIndex - 1 > -1 && rightIndex + 1 < manacherLength &&
							manacher[leftIndex - 1] == manacher[rightIndex + 1]) {
						leftIndex --;
						rightIndex ++;
					}
					radius[index] = (rightIndex - leftIndex) / 2;
					lastCircleIndex = index;
					maxTouchedIndex = rightIndex;
				} else if (mirrorRadius + index < maxTouchedIndex) {
					radius[index] = mirrorRadius;
				} else {
					radius[index] = maxTouchedIndex - index;
				}
			}
			if (radius[index] == index) {
				ans = Math.max(ans, index);
			}
		}
		System.out.println(ans);
	}
	/*
	 * 	TLE
	 */
	static void solve1() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		char[] manacher = new char[line.length() * 2 + 1];
		for (int index = 0; index < manacher.length; index ++) {
			if (index % 2 == 0) {
				manacher[index] = '#';
			} else {
				manacher[index] = line.charAt(index / 2);
			}
		}
		//这里要找的是最大回文前缀
		int[] radius = new int[manacher.length];
		radius[0] = 0;
		int maxTouchedIndex = 0;
		int lastCircleIndex = 0;
		int ans = 0;
		for (int index = 0; index < manacher.length; index ++) {
			if (maxTouchedIndex >= manacher.length - 1) {
				break;
			}
			if (index >= maxTouchedIndex) {
				int leftIndex = index, rightIndex = index;
				while (leftIndex-1 > -1 && rightIndex+1 < manacher.length &&
						manacher[leftIndex-1] == manacher[rightIndex+1]) {
					leftIndex --;
					rightIndex ++;
				}
				radius[index] = (rightIndex - leftIndex) / 2;
				maxTouchedIndex = rightIndex;
				lastCircleIndex = index;
			} else {
				int mirrorIndex = lastCircleIndex*2 - index;
				int mirrorRadius = radius[mirrorIndex];
				if (mirrorRadius+index == maxTouchedIndex) {
					int leftIndex = index, rightIndex = index;
					while (leftIndex - 1 > -1 && rightIndex + 1 < manacher.length &&
							manacher[leftIndex - 1] == manacher[rightIndex + 1]) {
						leftIndex --;
						rightIndex ++;
					}
					radius[index] = (rightIndex - leftIndex) / 2;
					lastCircleIndex = index;
					maxTouchedIndex = rightIndex;
				} else if (mirrorRadius + index < maxTouchedIndex) {
					radius[index] = mirrorRadius;
				} else {
					radius[index] = maxTouchedIndex - index;
				}
			}
			if (radius[index] == index) {
				ans = Math.max(ans, index);
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
