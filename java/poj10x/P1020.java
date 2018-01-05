package poj10x;

/*
 * 	status : Accept
 * 	Memory	Time
 * 	3116K	860MS
 */

/*
 * 	text file encoding : utf8
 * 	大意：
 * 		1，给一个正方形蛋糕，边长已知；
 * 		2，无损分成正方形小蛋糕；
 * 	输入：
 * 		2（测试用例数）
 * 		4 8 1 1 1 1 1 3 1 1（边长，小蛋糕数，小蛋糕边长）
 * 		5 6 3 3 2 1 1 1
 * 	输出：
 * 		能，KHOOOOB!
 * 		否，HUTUTU!
 */

import java.util.Arrays;
import java.util.Scanner;

public class P1020 {
	static int partMap[] = new int[11];	//小蛋糕Map，[下标]边长，[值]个数
	static int totalCou[] = new int[41];	//未填，[下标]纵坐标，[值]最小横坐标
	static int totlLen, numOfPart, sumOfPartArea;
	static boolean isSolved;
	public static void main(String args[]) {
		int numOfTest, index, partLen, countOfMaxHalf;
		Scanner scanner = new Scanner(System.in);
		numOfTest = scanner.nextInt();
	    while (numOfTest -- > 0) {
	        Arrays.fill(partMap,0);
	        for(index = 1; index <= 40; index ++) {
	        	totalCou[index] = 1;
	        }
	    	totlLen = scanner.nextInt();
	    	numOfPart = scanner.nextInt();
	        sumOfPartArea = 0;
	        isSolved = false;
	        countOfMaxHalf = 0;
	        for(index = 1; index <= numOfPart; index ++) {
	        	partLen = scanner.nextInt();
	            partMap[partLen] ++;
	            sumOfPartArea += partLen*partLen;
	            if (partLen > totlLen/2) {
	            	countOfMaxHalf ++;
	            }
	        }	//end of for(index = 1; index <= numOfPart; index ++)
	        if(countOfMaxHalf > 1 || sumOfPartArea != totlLen*totlLen) {
	        	System.out.println("HUTUTU!");
	        	continue;
	        } else {	//else of if(countOfMaxHalf > 1 || sumOfPartArea != totlLen*totlLen)
	        	isSolved = false;   // 表明这是一个存在就行的。
	        						// 在今后的使用中，尽量使用全局boolean来标记是否完成。
	        						// 少用方法返回boolean，自己在这上犯的错太多了。
	        	dfs(0);
		        if (isSolved) {
		        	System.out.println("KHOOOOB!");
		        } else {
		    		System.out.println("HUTUTU!");
		        }
	        }	//end of if(countOfMaxHalf > 1 || sumOfPartArea != totlLen*totlLen)
	    }	//end of while (numOfTest -- > 0)
	    scanner.close();
	}	//end of public static void main
	public static void dfs(int depthOfDFS) {
		int saveOfX = Integer.MAX_VALUE, saveOfY = 0;
	    if(depthOfDFS == numOfPart) {	//如果对所有的n个小正方形搜索完毕。
			isSolved = true;
			return;
		}
		//遍历纵坐标，保存最小横坐标，保存对应纵坐标。
		for(int indexOfY = 1; indexOfY <= totlLen; indexOfY ++) {
			if(totalCou[indexOfY] < saveOfX) {
				saveOfX = totalCou[indexOfY];
				saveOfY = indexOfY;
			}
		}	//end of for(int indexOfY = 1; indexOfY <= totlLen; indexOfY ++)
		//小蛋糕，检索大的。
		for(int indexOfPartMap = 10; indexOfPartMap >= 1; indexOfPartMap --) {
			if (partMap[indexOfPartMap] <= 0 || saveOfX+indexOfPartMap-1 > totlLen || saveOfY+indexOfPartMap-1 > totlLen) {
				continue;
			}
		    boolean isPlacedInFor = true;
			for(int indexOfSelectPart = saveOfY; indexOfSelectPart <= saveOfY+indexOfPartMap-1; indexOfSelectPart ++) {
				if(totalCou[indexOfSelectPart] > saveOfX) {
					isPlacedInFor = false;
					break;
				}
			}	//end of for(int indexOfSelectPart = saveOfY; indexOfSelectPart <= saveOfY+indexOfMap-1; indexOfSelectPart ++)
			if(! isPlacedInFor) {
				continue;
			}
			//开始填充
			for(int indexOfSelectPart = saveOfY;indexOfSelectPart <= saveOfY+indexOfPartMap-1; indexOfSelectPart ++) {
				totalCou[indexOfSelectPart] += indexOfPartMap;
			}
			partMap[indexOfPartMap] --;
			dfs(depthOfDFS + 1);
			partMap[indexOfPartMap] ++;
            for(int indexOfSelectPart = saveOfY;indexOfSelectPart <= saveOfY+indexOfPartMap-1; indexOfSelectPart ++) {
            	totalCou[indexOfSelectPart] -= indexOfPartMap;
            }
            //回溯结束
		}	//end of for(int indexOfMap = 10; indexOfMap >= 1; indexOfMap --)
	}	// end of public static void dfs(int depthOfDFS)
}