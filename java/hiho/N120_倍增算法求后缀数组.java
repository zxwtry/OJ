package hiho;

import java.util.ArrayList;

public class N120_倍增算法求后缀数组 {
	public static void main(String[] args) {
//		testBeizengSolution();
//		test();
//		testSpeed();
	}
	static void testSpeed() {
		int len = 2000;
		String s = tools.StringUtils.A_生成随机数组A_Z(len);
		long timeBeizeng = 0;
		long timeStandard = 0;
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		new BeizengSolution().solve(s); 
		end = System.currentTimeMillis();
		timeBeizeng = end - start;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start = System.currentTimeMillis();
		new StandardSolution().solve(s); 
		end = System.currentTimeMillis();
		timeStandard = end - start;
		System.out.println(timeBeizeng + "..." + timeStandard);
		//len=5000	541...287
		//len=900	12...16
		//len=3000	137...117
		//len=2000	72...57
		//可以看出倍增还是有一定用处的，只是由于内存消耗太大，慢了。
		//看看有什么内存节约的方法。
		//或者转到自己笔记本上再运行试试。
		/*
		 * 	在自己的笔记本上运行，采用java -Xms4096m -Xmx8192m方式执行
		 * 	len=100		3...3
		 * 	len=300		7...12
		 * 	len=500		10...21
		 * 	len=1000	22...37
		 * 	len=2000	53...93
		 * 	len=4000	139...372
		 * 	len=6000	354...1048
		 * 	len=8000	453...1842
		 * 	len=10000	1625...2927 (肯定又碰到GC了)
		 * 	改用java -Xms8192m -Xmx10240m
		 * 	len=10000	1070...3297
		 * 	len=12000	1261...4363
		 */
		//可以看到，只要不GC，倍增的效率高于标准计算后缀方法
	}
	static void testBeizengSolution() {
		BeizengSolution bs = new BeizengSolution();
		String s = "aabaaaab";
		bs.solve(s);
	}
	static void test() {
		for (int testTimes = 0; testTimes < 1000000; testTimes ++) {
			int len = (int)(Math.random() * 50);
			String s = tools.StringUtils.A_生成随机数组A_Z(len);
			ArrayList<String> beizengAnswer = new BeizengSolution().solve(s); 
			ArrayList<String> standardAnswer = new StandardSolution().solve(s); 
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
	//这样实现的代码复杂度是
	//假定s的长度是N
	//比较N*logN次，
	//每次比较是比较N次
	//那么时间复杂度是O(NNlogN)
	static class StandardSolution {
		ArrayList<String> solve(String s) {
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
	//听说能够实现O(N*logN)，真的么?
	//根据图，非常好理解，但是在代码上怎么写呢？
	static class BeizengSolution {
		//究竟是[]还是[)，务必要理清
		int[][] save = null;			//基数排序的保存
		int[] saveCount = null;			//基数排序的记数
		char[] cs = null;				//s.toCharArray()
		char[][] ans = null;			//答案
		int[] ansCount = null;			//答案的记数
		int[] value = null;				//就是排名value
		int[] valueIndex = null;		//在进行基数排序时候，对value索引的保存
		int[] valueNew = null;			//新的排名value，基数排序的时候使用
		int len = 0;					//s的长度
		int valueEdge = 0;				//1,2...valueEdge
		ArrayList<String> solve(String s) {
			init(s);		//变量初始化
			firstSort();	//产生初始value和 ans
			for (int apart = 1; apart < len; apart <<= 1) {
				for (int iLen = 0; iLen < len; iLen ++) {
					int jLen = iLen + apart;
					if (jLen < len) {
						System.arraycopy(ans[jLen], 0, ans[iLen], ansCount[iLen], ansCount[jLen]);
						ansCount[iLen] += ansCount[jLen];
						value[iLen] = value[iLen] * (valueEdge + 1) + value[jLen];
					} else {
						value[iLen] = value[iLen] * (valueEdge + 1);
					}
				}
				radixSort();
			}
			for (int iLen = 0; iLen < len; iLen ++) {
				valueNew[value[iLen] - 1] = iLen;
			}
			ArrayList<String> list = new ArrayList<String>(len);
			for (int iLen = 0; iLen < len; iLen ++) {
				list.add(new String(ans[valueNew[iLen]], 0, ansCount[valueNew[iLen]]));
			}
			return list;
		}
		//对value进行排序
		private void radixSort() {
			int valueIndexIndex = 0;
			for (valueIndexIndex = 0; valueIndexIndex < len; valueIndexIndex ++) {
				valueIndex[valueIndexIndex] = valueIndexIndex;
			}
			int bits = get2mi(valueEdge);
			int max1 = (1 << bits) - 1;
			for (int radixIndex = 0; radixIndex != 2; radixIndex ++) {
				int arrValueRightBits = radixIndex * bits;
				for (valueIndexIndex = 0; valueIndexIndex < len; valueIndexIndex ++) {
					int saveI = ( (value[valueIndex[valueIndexIndex]] - 1) >>> arrValueRightBits ) & max1;
					save[saveI][saveCount[saveI] ++] = valueIndex[valueIndexIndex];
				}
				valueIndexIndex = 0;
				for (int countIndex = 0; countIndex < saveCount.length; countIndex ++) {
					for (int countValue = 0; countValue < saveCount[countIndex]; countValue ++) {
						valueIndex[valueIndexIndex ++] = save[countIndex][countValue];
					}
					saveCount[countIndex] = 0;
				}
			}
			int valueIndexValue = 0;
			for (valueIndexIndex = 0; valueIndexIndex < len; valueIndexIndex ++) {
				if (valueIndexIndex != 0 && value[valueIndex[valueIndexIndex]] == value[valueIndex[valueIndexIndex - 1]]) {
					valueNew[valueIndex[valueIndexIndex]] = valueNew[valueIndex[valueIndexIndex - 1]];
				} else {
					valueNew[valueIndex[valueIndexIndex]] = ++ valueIndexValue;
				}
			}
			valueEdge = valueIndexValue;
			System.arraycopy(valueNew, 0, value, 0, len);
		}
		//产生初始value和 ans, ansCount
		private void firstSort() {
			for (int iLen = 0; iLen != len; iLen ++) {
				ans[iLen][0] = cs[iLen];
				ansCount[iLen] = 1;
			}
			//这里假设char是c的char，即0~255
			int[] map = new int[256];
			for (char c : cs) {
				map[c] = 1;
			}
			int valueTravel = 1;
			for (int mapIndex = 0; mapIndex != 256; mapIndex ++) {
				if (map[mapIndex] == 1) {
					map[mapIndex] = valueTravel ++;
				}
			}
			for (int iLen = 0; iLen != len; iLen ++) {
				value[iLen] = map[cs[iLen]];
			}
			valueEdge = valueTravel - 1;
		}
		//变量初始化
		private void init(String s) {
			cs = s.toCharArray();
			len = cs.length;
			int lenOfSave = 1 << get2mi(len);
			save = new int[lenOfSave][lenOfSave];
			saveCount = new int[lenOfSave];
			ans = new char[len][len];
			ansCount = new int[len];
			value = new int[len];
			valueIndex = new int[len];
			valueNew = new int[len];
		}
		private int get2mi(int len) {
			int bitsOfSave = 1;
			int lenTemp = len;
			while (lenTemp > 1) {
				lenTemp >>= 1;
				bitsOfSave ++;
			}
			return bitsOfSave;
		}
	}
}