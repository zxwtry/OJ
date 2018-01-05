package poj10x;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 	status : Accept
 * 	Memory 	Time
 * 	5260K	235MS
 */

/*
 * 	大意：
 * 		1，不要去想四维的东西是什么！
 * 		2，每个“小块”，各个边长都是单位长度
 * 		3，操作过程就是：将各个小块粘起来
 * 	输入：
  		1（测试用例数）
  		9（小块数目）
  		1 2 3 4 5 6 7 8 9（第一个数字是ID，后面8个数字是粘的规则）
		2 0 1 0 0 0 0 0 0
		3 1 0 0 0 0 0 0 0
		4 0 0 0 1 0 0 0 0
		5 0 0 1 0 0 0 0 0
		6 0 0 0 0 0 1 0 0
		7 0 0 0 0 1 0 0 0
		8 0 0 0 0 0 0 0 1
		9 0 0 0 0 0 0 1 0
 * 	输出：
 * 		1，规则有矛盾		输出：Inconsistent
 * 		2，没有粘完		输出：Inconsistent
 * 		3，用一个容器装下粘结好的大块。输出容器体积。
 */

public class P1022 {
	static int numOfParts, dataOfParts[][];		// 记录输入信息
	static boolean mapOfParts[];				// 记录小块有没有被粘进来
	static int[] lenOfGrow = new int[8];		// 以0号为原点，向8个方向的最大延伸
	static int[] location = new int[4];		// 记录深搜过程中，当前点的位置
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		while (numOfTest -- > 0) {
			numOfParts = scanner.nextInt();
			dataOfParts = new int[numOfParts][9];
			for (int indexOfParts = 0; indexOfParts < numOfParts; indexOfParts ++) {
				for (int indexOfData = 0; indexOfData < 9; indexOfData ++) {
					dataOfParts[indexOfParts][indexOfData] = scanner.nextInt();
				}
			}
			// 判断有没有规则矛盾
			if (! judgeSymmetric()) {
				System.out.println("Inconsistent");
				continue;
			}
			mapOfParts = new boolean[numOfParts];
			Arrays.fill(mapOfParts, false);
			Arrays.fill(lenOfGrow, 0);
			Arrays.fill(location, 0);
			// 从0号开始深搜小块
			searchAllContiguousStarts0(0);
			// 判断有没有全部粘完
			if (judgeOneComonent()) {
				System.out.println(calculateVolume());
			} else {
				System.out.println("Inconsistent");
			}
		}
		scanner.close();
	}
	static int calculateVolume() {
		int volume = 1;
		for (int index = 0; index < 4; index ++) {
			volume *= (lenOfGrow[2*index + 0] + lenOfGrow[2*index + 1] + 1);
		}
		return volume;
	}
	static int getIndexInLocation(int index) {
		return (index-1) >> 1;
	}
	// 判断有没有粘完
	static boolean judgeOneComonent() {
		boolean isOne = true;
		for (int index = 0; index < mapOfParts.length; index ++) {
			isOne &= mapOfParts[index];
		}
		return isOne;
	}
	// 从0号开始进行深搜
	static void searchAllContiguousStarts0(int thisPart) {
		if (mapOfParts[thisPart]) {
			return;
		}
		mapOfParts[thisPart] = true;
		for (int indexOfLocation = 0; indexOfLocation < 4; indexOfLocation ++) {
			if (location[indexOfLocation] > 0) {
				if (lenOfGrow[2*indexOfLocation] < location[indexOfLocation]) {
					lenOfGrow[2*indexOfLocation] = location[indexOfLocation];
				}
			} else if (location[indexOfLocation] < 0) {
				if (lenOfGrow[2*indexOfLocation + 1] < -location[indexOfLocation]) {
					lenOfGrow[2*indexOfLocation + 1] = -location[indexOfLocation];
				}
			}
		}
		for (int indexOfData = 1; indexOfData < dataOfParts[0].length; indexOfData ++) {
			if (dataOfParts[thisPart][indexOfData] == 0) {
				continue;
			}
			int correspond = dataOfParts[thisPart][indexOfData];
			int indexOfParts2 = 0;
			for (; indexOfParts2 < dataOfParts.length; indexOfParts2 ++) {
				if (dataOfParts[indexOfParts2][0] == correspond) {
					break;
				}
			}
			if ((indexOfData & 0x1) == 1) {
				location[getIndexInLocation(indexOfData)] ++;
			} else {
				location[getIndexInLocation(indexOfData)] --;
			}
			searchAllContiguousStarts0(indexOfParts2);
			if ((indexOfData & 0x1) == 1) {
				location[getIndexInLocation(indexOfData)] --;
			} else {
				location[getIndexInLocation(indexOfData)] ++;
			}
		}
	}
	// 判断有没有规则矛盾
	static boolean judgeSymmetric() {
		if (dataOfParts == null || dataOfParts[0].length != 9) {
			return false;
		}
		for (int indexOfParts = 0; indexOfParts < dataOfParts.length; indexOfParts ++) {
			for (int indexOfData = 1; indexOfData < dataOfParts[0].length; indexOfData ++) {
				if (dataOfParts[indexOfParts][indexOfData] == 0) {
					continue;
				}
				int correspond = dataOfParts[indexOfParts][indexOfData];
				int indexOfParts2 = 0;
				boolean isFound = false;
				for (; indexOfParts2 < dataOfParts.length; indexOfParts2 ++) {
					if (dataOfParts[indexOfParts2][0] == correspond) {
						isFound = true;
						break;
					}
				}
				if (! isFound) {
					return false;
				}
				int indexOfData2 = indexOfData;
				if ((indexOfData2 & 0x1) == 1) {
					indexOfData2 ++;
				} else {
					indexOfData2 --;
				}
				if (dataOfParts[indexOfParts2][indexOfData2] != dataOfParts[indexOfParts][0]) {
					return false;
				}
			}
		}
		return true;
	}
}