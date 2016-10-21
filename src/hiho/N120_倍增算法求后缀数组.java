package hiho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class N120_倍增算法求后缀数组 {
	public static void main(String[] args) {
		String s = "aabaaaab";
//		System.out.println(standardSolution(s));
//		String s = "aabaaaab";
//		String t = "aab";
//		System.out.println(compareString(s, t));
//		System.out.println(1 << 0);
		test();
//		debug();
	}
	static void debug() {
		String s = "VWTZIBJ";
		ArrayList<String> beizengAnswer = beizeng(s); 
		ArrayList<String> standardAnswer = standardSolution(s); 
		boolean isAllSame = beizengAnswer.size() == standardAnswer.size();
		tools.Utils.B_打印List_String(beizengAnswer);
		tools.Utils.B_打印List_String(standardAnswer);
//		for (int i = 0; isAllSame && i < beizengAnswer.size(); i ++) {
//			isAllSame &= beizengAnswer.get(i).equals(standardAnswer.get(i));
//			System.out.println(beizengAnswer.get(i));
//			System.out.println(standardAnswer.get(i));
//		}
//		if (! isAllSame) {
//			System.out.println("翻车了");
//			tools.FileUtils.B_纪录String_append("D:/file/data/1021.txt", s);
//		}
	}
	static void test() {
		long timeBeizeng = 0;
		long timeStandard = 0;
		for (int testTimes = 0; testTimes < 1; testTimes ++) {
			int len = (int)(Math.random() * 50);
			String s = tools.StringUtils.A_生成随机数组A_Z(len);
			ArrayList<String> beizengAnswer = beizeng(s); 
			ArrayList<String> standardAnswer = standardSolution(s); 
			boolean isAllSame = beizengAnswer.size() == standardAnswer.size();
			tools.Utils.B_打印List_String_OneLine(beizengAnswer);
			tools.Utils.B_打印List_String_OneLine(standardAnswer);
			for (int i = 0; isAllSame && i < beizengAnswer.size(); i ++) {
				isAllSame &= beizengAnswer.get(i).equals(standardAnswer.get(i));
			}
			if (! isAllSame) {
				System.out.println("翻车了");
				tools.FileUtils.B_纪录String_append("D:/file/data/1021.txt", s);
			}
		}
	}
	//听说能够实现O(N*logN)，真的么?
	//根据图，非常好理解，但是在代码上怎么写呢？
	static ArrayList<String> beizeng(String s) {
		int len = s.length();
		int[] value = new int[len];
		int[] indics = new int[len];
		char[][] arr = new char[len][len + 1];
		for (int i = 0; i < len; i ++) {
			char c = s.charAt(i);
			arr[i][0] = c;
			value[i] = c;
			indics[i] = 0;
		}
		//计算每一次乘以多少
		int multiValue = 1;
		int lenCopy = len;
		while (lenCopy != 0) {
			lenCopy = lenCopy / 10;
			multiValue = multiValue * 10;
		}
		//完成第一次组合
		int sortTimes = 0;
		while (true) {
			reset(value);
			int behindIndex = 0;
			int frontIndex = (1 << sortTimes);
			if (frontIndex >= len) {
				break;
			}
			while (frontIndex <= len-1) {
				for (int i = 0; i <= indics[frontIndex]; i ++) {
					arr[behindIndex][++indics[behindIndex]] = arr[frontIndex][i];
				}
				value[behindIndex] = value[behindIndex] * multiValue + value[frontIndex];
				behindIndex ++;
				frontIndex ++;
			}
			while (behindIndex <= len - 1) {
				value[behindIndex] *= 10;
				behindIndex ++;
			}
			sortTimes ++;
		}
//		tools.Utils.printArray(value, 100);
		ArrayList<String> list = new ArrayList<>();
//		for (int i = 0; i <= len - 1; i ++) {
//			list.add(new String(arr[i], 0, indics[i] + 1));
//		}
		int[] help = new int[len];
		for (int i = 0; i <= len - 1; i ++) {
			help[value[i] - 1] = i;
		}
//		tools.Utils.printArray(help, 100);
		for (int i = 0; i <= len - 1; i ++) {
			list.add(new String(arr[help[i]], 0 , indics[help[i]] + 1));
		}
		return list;
	}
	static void reset(int[] value) {
		int[] valueClone = value.clone();
		Arrays.sort(valueClone);
		HashMap<Integer, Integer> map = new HashMap<>();
		int index = 1;
		for (int i = 0; i < valueClone.length; i ++) {
			if (! map.containsKey(valueClone[i])) {
				map.put(valueClone[i], index ++);
			}
		}
		for (int i = 0; i < value.length; i ++) {
			value[i] = map.get(value[i]);
		}
	}
	//这样实现的代码复杂度是
	//假定s的长度是N
	//比较N*logN次
	//每次比较是比较N次
	//那么时间复杂度是O(NNlogN)
	static ArrayList<String> standardSolution(String s) {
		String[] strs = new String[s.length()];
		for(int i = 0; i < s.length(); i ++) {
			strs[i] = s.substring(i);
		}
		for (int i = 0; i < strs.length; i ++) {
			for (int j = i + 1; j < strs.length; j ++) {
				if (strs[i].compareTo(strs[j]) > 0) {
					String temp = strs[i];
					strs[i] = strs[j];
					strs[j] = temp;
				}
			}
		}
		ArrayList<String> list = new ArrayList<>(strs.length);
		for (String st : strs) {
			list.add(st);
		}
		return list;
	}
}
