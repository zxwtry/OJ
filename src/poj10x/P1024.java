package poj10x;

/*
 * 	status : Wrong Answer
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P1024 {
	static int bigX, bigY, numOfWalls;
	static int[] route;
	static int[] walls;
	static char[] rlud;
	static boolean[] mapOfWalls;
	static boolean[][] imageOfWalls;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		while(numOfTest -- > 0) {
			bigX = scanner.nextInt();
			bigY = scanner.nextInt();
			rlud = scanner.next().trim().toCharArray();
			route = getRoute(rlud);
			numOfWalls = scanner.nextInt();
			walls = new int[numOfWalls << 1];
			mapOfWalls = new boolean[numOfWalls];
			Arrays.fill(walls, 0);
			Arrays.fill(mapOfWalls, false);
			imageOfWalls = new boolean[bigX << 1][bigY << 1];
			for (int index = 0; index < imageOfWalls.length; index ++) {
				Arrays.fill(imageOfWalls[index], false);
			}
			for (int indexOfWalls = 0; indexOfWalls < numOfWalls; indexOfWalls ++) {
				int thisIndex = indexOfWalls << 1;
				walls[thisIndex] += scanner.nextInt();
				walls[thisIndex + 1] += scanner.nextInt();
				walls[thisIndex] += scanner.nextInt();
				walls[thisIndex + 1] += scanner.nextInt();
				int selX = walls[thisIndex], selY = walls[thisIndex+1];
				imageOfWalls[selX][selY] = true;
				if ( (selX & 0x1 ) == 0 ) {
					if (selX-1 >= 0)
						imageOfWalls[selX-1][selY] = true;
					if (selX+1 < bigX << 1)
						imageOfWalls[selX+1][selY] = true;
				} else {
					if (selY-1 >= 0)
						imageOfWalls[selX][selY-1] = true;
					if (selY+1 < bigY << 1)
						imageOfWalls[selX][selY+1] = true;
				}
			}
			if (! judgeUnique() ) {
				System.out.println("INCORRECT");
				continue;
			}
			ArrayList<ArrayList<Integer>> arrayList = findCulstersOfWalls();
			if (judgeUnnecessary(arrayList)) {
				System.out.println("CORRECT");
			} else {
				System.out.println("INCORRECT");
			}
		}
		scanner.close();
	}
	static boolean judgeUnnecessary(ArrayList<ArrayList<Integer>> arrayList) {
		boolean isAllUsed = true;
		for (int index0 = 0; index0  < arrayList.size(); index0 ++) {
			ArrayList<Integer> arrayList1 = arrayList.get(index0);
			boolean isOneUsed = false;
			for (int index1 = 0; index1 < arrayList1.size(); index1 ++) {
				isOneUsed |= mapOfWalls[arrayList1.get(index1)];
			}
			isAllUsed &= isOneUsed;
		}
		return isAllUsed;
	}
	static ArrayList<ArrayList<Integer>> findCulstersOfWalls() {
		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<ArrayList<Integer>>();
		for (int indexX = 0; indexX < (bigX << 1); indexX ++) {
			for (int indexY = 0; indexY < (bigY << 1); indexY ++) {
				if (! imageOfWalls[indexX][indexY]) {
					continue;
				}
				ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
				searchAllContiguity(indexX, indexY, arrayList2);
				arrayList.add(arrayList2);
			}
		}
		return arrayList;
	}
	private static void searchAllContiguity(int indexX, int indexY, ArrayList<Integer> arrayList2) {
		if (indexX < 0 || indexX >= bigX << 1 || indexY < 0 || indexY >= bigY << 1) {
			return;
		}
		if (imageOfWalls[indexX][indexY]) {
			int indexOfWalls = 0;
			for (; indexOfWalls < walls.length >> 1; indexOfWalls ++) {
				if (walls[indexOfWalls << 1] == indexX && walls[(indexOfWalls << 1) + 1] == indexY) {
					break;
				}
			}
			if (indexOfWalls != (walls.length >> 1))
				arrayList2.add(indexOfWalls);
			imageOfWalls[indexX][indexY] = false;
		} else {
			return;
		}
		searchAllContiguity(indexX-1, indexY, arrayList2);
		searchAllContiguity(indexX+1, indexY, arrayList2);
		searchAllContiguity(indexX, indexY-1, arrayList2);
		searchAllContiguity(indexX, indexY+1, arrayList2);
	}
	static boolean judgeUnique() {
		final int indexEnd = rlud.length;
		for (int index = 1; index < indexEnd; index ++) {
			 if(rlud[index] != rlud[index-1]) {
				 int baseIndex = (index << 1) + 2;
				 int point_0_X = route[baseIndex-4], point_0_Y = route[baseIndex-3];
				 int point_1_X = route[baseIndex-2], point_1_Y = route[baseIndex-1];
				 int point_2_X = route[baseIndex], point_2_Y = route[baseIndex+1];
				 int point_3_X = point_0_X+point_2_X-point_1_X, point_3_Y = point_0_Y+point_2_Y-point_1_Y;
				 int wall23X = point_3_X + point_2_X;
				 int wall23Y = point_3_Y + point_2_Y;
				 int wall03X = point_0_X + point_3_X;
				 int wall03Y = point_0_Y + point_3_Y;
				 boolean isFindWall = false;
				 int indexWallsEnd = walls.length >> 1;
				 for (int indexWalls = 0; indexWalls < indexWallsEnd; indexWalls ++) {
					 int baseWalls = indexWalls << 1;
					 if (walls[baseWalls] == wall03X && walls[baseWalls+1] == wall03Y) {
						 isFindWall = true;
						 mapOfWalls[indexWalls] = true;
						 break;
					 }
					 if (walls[baseWalls] == wall23X && walls[baseWalls+1] == wall23Y) {
						 isFindWall = true;
						 mapOfWalls[indexWalls] = true;
						 break;
					 }
				 }
				 if (! isFindWall) {
					 return false;
				 }
			 }
		}
		return true;
	}
	static int[] getRoute(char[] rlud) {
		int[] reRoute = new int[(rlud.length << 1) + 2];
		reRoute[0] = 0;	reRoute[1] = 0;
		for (int index = 0; index < rlud.length; index ++) {
			int thisX = (index << 1) + 2;
			if (index == 0) {
				reRoute[thisX] = 0;
				reRoute[thisX + 1] = 0;
			} else {
				reRoute[thisX] = reRoute[thisX - 2];
				reRoute[thisX + 1] = reRoute[thisX - 1];
			}
			switch(rlud[index]) {
			case 'R':
				reRoute[thisX] ++;
				break;
			case 'L':
				reRoute[thisX] --;
				break;
			case 'U':
				reRoute[thisX + 1] ++;
				break;
			case 'D':
				reRoute[thisX + 1] --;
				break;
			}
		}
		return reRoute;
	}
}