package hiho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 	输入
	第一行一个整数N，表示测试数据组数。
	
	接下来的N*2行，每两行表示一个测试数据。在每一个测试数据中，
	第一行为模式串，由不超过10^4个大写字母组成，
	第二行为原串，由不超过10^6个大写字母组成。
	
	其中N<=20
	
	输出
	对于每一个测试数据，按照它们在输入中出现的顺序输出一行Ans，表示模式串在原串中出现的次数。
	
	样例输入
	5
	HA
	HAHAHA
	WQN
	WQN
	ADA
	ADADADA
	BABABB
	BABABABABABABABABB
	DAD
	ADDAADAADDAAADAAD
	样例输出
	3
	1
	3
	1
	0
 */
//551ms	40MB
public class N003_KMP算法 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("D:/file/data/hiho_n003.txt"));
		int testTimes = Integer.parseInt(scanner.nextLine().trim());
		while (testTimes -- > 0) {
			String pattern = scanner.nextLine().trim();
			String origin = scanner.nextLine().trim();
			System.out.println(getTimesPatternExitsInOrigin(pattern, origin));
		}
		scanner.close();
//		testNext();
	}
//	static void testNext() {
//		char[] patternChars = tools.StringUtils.A_生成随机回文串char1_char2('A', 'B', 7).toCharArray();
//		int[] next = getNext(patternChars);
////		tools.Utils.printArray(next, 100);
//		char[] originChars = tools.StringUtils.A_生成随机回文串char1_char2('A', 'B', 10000).toCharArray();
//		ArrayList<Integer> firstIndexList = new ArrayList<>();
//		int originBaseEndIndex = originChars.length - patternChars.length;
//		int patternIndex = 0;
//		int compareCount = 0;
//		for (int originBaseIndex = 0; originBaseIndex <= originBaseEndIndex; originBaseIndex ++) {
//			for (; patternIndex < patternChars.length; patternIndex ++) {
//				compareCount ++;
//				if (originChars[originBaseIndex + patternIndex] != patternChars[patternIndex]) {
//					break;
//				}
//			}
//			if (patternIndex == patternChars.length) {
//				firstIndexList.add(originBaseIndex);
//			}
//			patternIndex = next[patternIndex];
//		}
//		ArrayList<Integer> firstIndexListStandard = new ArrayList<>();
//		int compareCountStandard = testStandard(patternChars, originChars, firstIndexListStandard);
//		System.out.printf("找到的个数：kmp:%d  常规:%d\r\n", firstIndexList.size(), firstIndexListStandard.size());
//		System.out.printf("比较次数 kmp:%d  常规:%d\r\n", compareCount, compareCountStandard);
//	}
	//返回比较次数
//	static int testStandard(char[] patternChars, char[] originChars, ArrayList<Integer> firstIndexList) {
//		int compareCount = 0;
//		int originBaseEndIndex = originChars.length - patternChars.length;
//		for (int originBaseIndex = 0; originBaseIndex <= originBaseEndIndex; originBaseIndex ++) {
//			int patternIndex = 0;
//			for (; patternIndex < patternChars.length; patternIndex ++) {
//				compareCount ++;
//				if (originChars[originBaseIndex + patternIndex] != patternChars[patternIndex]) {
//					break;
//				}
//			}
//			if (patternIndex == patternChars.length) {
//				firstIndexList.add(originBaseIndex);
//			}
//		}
//		return compareCount;
//	}
	static int getTimesPatternExitsInOrigin(String pattern, String origin) {
		int lengthOfPattern = pattern.length();
		int lengthOfOrigin = origin.length();
		if (lengthOfPattern > lengthOfOrigin) {
			return 0;
		}
		char[] patternChars = pattern.toCharArray();
		char[] originChars = origin.toCharArray();
		int[] next = getNext(patternChars);
		int originBaseEndIndex = originChars.length - patternChars.length;
		int patternIndex = 0;
		int matchCount = 0;
		for (int originBaseIndex = 0; originBaseIndex <= originBaseEndIndex; originBaseIndex ++) {
			for (; patternIndex < patternChars.length; patternIndex ++) {
				if (originChars[originBaseIndex + patternIndex] != patternChars[patternIndex]) {
					break;
				}
			}
			if (patternIndex == patternChars.length) {
				matchCount ++;
			}
			patternIndex = next[patternIndex];
		}
		return matchCount;
	}
	static int[] getNext(char[] patternChars) {
		int[] next = new int[patternChars.length + 1];
		next[0] = -1;
		int behindIndex = -1, frontIndex = 0;
		while(frontIndex < patternChars.length) {
			if (behindIndex == -1 || patternChars[behindIndex] == patternChars[frontIndex]) {
				frontIndex ++;
				behindIndex ++;
				next[frontIndex] = behindIndex;
			} else {
				behindIndex = next[behindIndex];
			}
		}
		next[0] = 0;
		return next;
	}
//	public static int[] getNext(String b) {
//		int len=b.length();
//		int j=0;
//			
//		int next[]=new int[len+1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
//		next[0]=next[1]=0;
//			
//		for(int i=1;i<len;i++)//i表示字符串的下标，从0开始
//		{//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
//			while(j>0&&b.charAt(i)!=b.charAt(j))j=next[j];
//			if(b.charAt(i)==b.charAt(j))j++;
//			next[i+1]=j;
//		}
//			
//		return next;
//	}
//	static int[] getNextStandard(int[] patternChars) {
//		int[] next = new int[patternChars.length];
//		next[0] = -1;
//		for (int index = 1; index < patternChars.length; index ++) {
//			
//		}
//		return next;
//	}
}
