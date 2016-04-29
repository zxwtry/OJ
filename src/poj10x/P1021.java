package poj10x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * 	status : Wrong Answer
 * 	
 */

/*
 * 	text file encoding : utf8
 * 	大意：
 * 		1，意思非常绕口
 * 		2，先说操作吧：
 * 			a，删除连续的一行或者一列
 * 			b，删除最后一块的胜利
 * 			c，左右两个板，要求是左边赢的操作，在右边同样赢
 * 		3，自己的理解（以下说辞都是对左右两个板）：
 * 			a，找到群。要求左右，群数相同，群中点数相同
 * 			b，群中点数相同的，一定有一对一相同的形状
 * 	输入：
 * 		2（测试数）
 * 		8 5 11（X，Y，点个数）
 * 		0 0 1 0 2 0 5 0 7 0 1 1 2 1 5 1 3 3 5 2 4 4
 * 		0 4 0 3 0 2 1 1 1 4 1 3 3 3 5 2 6 2 7 2 7 4
 * 		8 5 11
 * 		0 0 1 0 2 0 5 0 7 0 1 1 2 1 5 1 3 3 6 1 4 4
 * 		0 4 0 3 0 2 1 1 1 4 1 3 3 3 5 2 6 2 7 2 7 4
 * 	输出：
 * 		YES（左右相同）
 * 		NO（左右不相同）
 */

public class P1021 {
	static int bigX, bigY;
	static Point p[], p_other[], p_this[];
	static Cluster cluster_save;
	static Cluster[] arrayOfCluster;
	static ClusterMap[] clusterMap_this, clusterMap_other;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numOfTest = scanner.nextInt();
		while(numOfTest -- > 0) {
			bigX = scanner.nextInt();
			bigY = scanner.nextInt();
			p = new Point[scanner.nextInt()];
			p_other = new Point[p.length];
			p_this = new Point[p.length];
			for (int indexOfPoint = 0; indexOfPoint < p.length; indexOfPoint ++) {
				p[indexOfPoint] = new Point(scanner.nextInt(), scanner.nextInt());
				p_this[indexOfPoint] = p[indexOfPoint];
			}
			arrayOfCluster = findClusters();
			Arrays.sort(arrayOfCluster);
			clusterMap_this = getClusterMap();
			for (int indexOfPoint = 0; indexOfPoint < p.length; indexOfPoint ++) {
				p[indexOfPoint] = new Point(scanner.nextInt(), scanner.nextInt());
				p_other[indexOfPoint] = p[indexOfPoint];
			}
			arrayOfCluster = findClusters();
			Arrays.sort(arrayOfCluster);
			clusterMap_other = getClusterMap();
			
			if (clusterMap_this.length != clusterMap_other.length) {
				System.out.println("NO");
				continue;
			}
			boolean isAllFormRight = true;
			for (int indexOfMap = 0; indexOfMap < clusterMap_this.length; indexOfMap ++) {
				isAllFormRight &= clusterMap_this[indexOfMap].data.length == clusterMap_other[indexOfMap].data.length;
				isAllFormRight &= clusterMap_this[indexOfMap].data[0].length == clusterMap_other[indexOfMap].data[0].length;
				if (!isAllFormRight) {
					break;
				}
			}
			if (!isAllFormRight) {
				System.out.println("NO");
				continue;
			}
			boolean returnMatch = getReturn();
			if (returnMatch) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		scanner.close();
	}
	static boolean getReturn() {
		boolean returnMatch = true;
		for (int indexOfMap = 0; indexOfMap < clusterMap_this.length; indexOfMap ++) {
			int[][] data_this = clusterMap_this[indexOfMap].data;
			int[][] data_other = clusterMap_other[indexOfMap].data;
			int sizeOfCluster = data_this[0].length, lengthOfData = data_this.length;
			if(sizeOfCluster == 1 || sizeOfCluster == 2) {
				continue;
			}
			Point[][] point_this = new Point[lengthOfData][sizeOfCluster];
			Point[][] point_other = new Point[lengthOfData][sizeOfCluster];
			for (int indexOfLen = 0; indexOfLen < lengthOfData; indexOfLen ++) {
				for (int indexOfSize = 0; indexOfSize < sizeOfCluster; indexOfSize ++) {
					Point this_point = p_this[data_this[indexOfLen][indexOfSize]];
					Point other_point = p_other[data_other[indexOfLen][indexOfSize]];
					point_this[indexOfLen][indexOfSize] = new Point(this_point.x, this_point.y);
					point_other[indexOfLen][indexOfSize] = new Point(other_point.x, other_point.y);
				}
				moveTo00(point_other[indexOfLen]);
				moveTo00(point_this[indexOfLen]);
			}
			final int len = lengthOfData;
			boolean map[] = new boolean[len];
			Arrays.fill(map, false);
			boolean isTrue1 = true;
			for (int index1 = 0; index1 < len; index1 ++) {
				boolean isTrue2 = false;
				for (int index2 = 0; index2 < len; index2 ++) {
					if (map[index2]) {
						continue;
					}
					if (! exitOneMatch(point_this[index1], point_other[index2])) {
						continue;
					}
					map[index2] = true;
					isTrue2 |= true;
					break;
				}
				if (! isTrue2) {
					isTrue1 &= false;
				}
			}
			returnMatch &= isTrue1;
		}
		return returnMatch;
	}
	static boolean exitOneMatch(Point[] arr1, Point[] arr2) {
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		axialSymmetry(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		clockwist90(arr1);
		if (compareTwoPointArray(arr1, arr2)) {
			return true;
		}
		return false;
	}
	static void moveTo00 (Point[] arr) {
		int min_x_1 = Integer.MAX_VALUE, min_y_1 = Integer.MAX_VALUE;
		for (int arr1Index = 0; arr1Index < arr.length; arr1Index ++) {
			if (arr[arr1Index].x < min_x_1) {
				min_x_1 = arr[arr1Index].x;
			}
			if (arr[arr1Index].y < min_y_1) {
				min_y_1 = arr[arr1Index].y;
			}
		}
		for (int arr1Index = 0; arr1Index < arr.length; arr1Index ++) {
			arr[arr1Index].x -= min_x_1;
			arr[arr1Index].y -= min_y_1;
		}
	}
	static void clockwist90(Point[] arr) {
		int min_x_1 = Integer.MAX_VALUE, min_y_1 = Integer.MAX_VALUE;
		for (int arr1Index = 0; arr1Index < arr.length; arr1Index ++) {
			int x = arr[arr1Index].x;
			int y = arr[arr1Index].y;
			arr[arr1Index].x = y;
			arr[arr1Index].y = -x;
			if (arr[arr1Index].x < min_x_1) {
				min_x_1 = arr[arr1Index].x;
			}
			if (arr[arr1Index].y < min_y_1) {
				min_y_1 = arr[arr1Index].y;
			}
		}
		for (int arr1Index = 0; arr1Index < arr.length; arr1Index ++) {
			arr[arr1Index].x -= min_x_1;
			arr[arr1Index].y -= min_y_1;
		}
	}
	static void axialSymmetry(Point[] arr) {
		int max_x = Integer.MIN_VALUE;
		for (int index = 0; index < arr.length; index ++) {
			if (arr[index].x > max_x) {
				max_x = arr[index].x;
			}
		}
		for (int index = 0; index < arr.length; index ++) {
			int x = arr[index].x;
			arr[index].x = max_x - x;
		}
	}
	static boolean compareTwoPointArray(Point[] arr1, Point[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		final int len = arr1.length;
		boolean[] match = new boolean[len];
		Arrays.fill(match, false);
		boolean returnAllMatch = true;
		for (int index1 = 0; index1 < len; index1 ++) {
			boolean findOneIn2Match = false;
			for (int index2 = 0; index2 < len; index2 ++) {
				if (match[index2]) {
					continue;
				}
				if (!arr2[index2].equals(arr1[index1])) {
					continue;
				}
				match[index2] = true;
				findOneIn2Match |= true;
				break;
			}
			if (!findOneIn2Match) {
				returnAllMatch &= false;
				break;
			}
		}
		return returnAllMatch;
	}
	static ClusterMap[] getClusterMap() {
		int finalLengthOfClusterMap = 0;
		for (int indexOfArray = 0; indexOfArray < arrayOfCluster.length; indexOfArray ++) {
			int sizeOfCluster = arrayOfCluster[indexOfArray].pointIndex.size();
			int lengthOfData = 1;
			for (int indexOfIndex = indexOfArray + 1; indexOfIndex < arrayOfCluster.length; indexOfIndex ++) {
				if (arrayOfCluster[indexOfIndex].pointIndex.size() == sizeOfCluster) {
					lengthOfData ++;
				} else {
					break;
				}
			}
			finalLengthOfClusterMap ++;
			indexOfArray += lengthOfData - 1;
		}
		ClusterMap[] arrayOfClusterMap = new ClusterMap[finalLengthOfClusterMap];
		int lengthOfClustermap = 0;
		for (int indexOfArray = 0; indexOfArray < arrayOfCluster.length; indexOfArray ++) {
			int sizeOfCluster = arrayOfCluster[indexOfArray].pointIndex.size();
			int lengthOfData = 1;
			for (int indexOfIndex = indexOfArray + 1; indexOfIndex < arrayOfCluster.length; indexOfIndex ++) {
				if (arrayOfCluster[indexOfIndex].pointIndex.size() == sizeOfCluster) {
					lengthOfData ++;
				} else {
					break;
				}
			}
			ClusterMap clusterMap = new ClusterMap(sizeOfCluster, lengthOfData);
			arrayOfClusterMap[lengthOfClustermap] = clusterMap;
			lengthOfClustermap ++;
			int[][] data = clusterMap.data;
			for (int indexOfData = 0; indexOfData < lengthOfData; indexOfData ++) {
				int realIndex = indexOfArray + indexOfData;
				Set<Integer> set = arrayOfCluster[realIndex].pointIndex;
				int secondIndex = 0;
				for(int setDate : set) {
					data[indexOfData][secondIndex] = setDate;
					secondIndex ++;
				}
			}
			indexOfArray += lengthOfData - 1;
		}
		return arrayOfClusterMap;
	}
	static Cluster[] findClusters() {
		List<Cluster> listOfCluster = new ArrayList<Cluster>();
		for (int indexOfPoint = 0; indexOfPoint < p.length; indexOfPoint ++) {
			if ( isExitsInListOfCluster(listOfCluster, indexOfPoint) ){
				continue;
			}
			if ( isExitContiguityInListOfCluster(listOfCluster, indexOfPoint) ) {
				cluster_save.pointIndex.add(indexOfPoint);
				continue;
			}
			Cluster newCluster = new Cluster();
			newCluster.pointIndex.add(indexOfPoint);
			listOfCluster.add(newCluster);
		}
		Cluster[] arrayOfCluster = new Cluster[listOfCluster.size()];
		for (int indexOfList = 0; indexOfList < listOfCluster.size(); indexOfList ++) {
			arrayOfCluster[indexOfList] = listOfCluster.get(indexOfList);
		}
		return arrayOfCluster;
	}
	static boolean isExitContiguityInListOfCluster(List<Cluster> listOfCluster, int indexOfPoint) {
		boolean isExits = false;
		for (int indexOfList = 0; indexOfList < listOfCluster.size(); indexOfList ++) {
			boolean isExitsInList = listOfCluster.get(indexOfList).isExitContiguity(indexOfPoint);
			if(isExitsInList) {
				cluster_save = listOfCluster.get(indexOfList);
			}
			isExits |= isExitsInList;
		}
		return isExits;
	}
	static boolean isExitsInListOfCluster(List<Cluster> listOfCluster, int indexOfPoint) {
		boolean isExits = false;
		for (int indexOfList = 0; indexOfList < listOfCluster.size(); indexOfList ++) {
			isExits |= listOfCluster.get(indexOfList).isExitsInThis(indexOfPoint);
		}
		return isExits;
	}
	static class Point {
		int x;
		int y;
		public Point() {
		}
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Point o) {
			return this.x == o.x && this.y == o.y;
		}
		public String toString() {
			return x+" "+y;
		}
 	}
	static class Cluster implements Comparable<Cluster>{
		Set<Integer> pointIndex = new HashSet<Integer>();
		public boolean isExitsInThis(int index) {
			return pointIndex.contains(index);
		}
		public boolean isExitContiguity(int index) {
			boolean isExit = false;
			for(int setIndex : pointIndex) {
				isExit |= (Math.abs(p[setIndex].x - p[index].x) + Math.abs(p[setIndex].y - p[index].y)) == 1;
			}
			return isExit;
		}
		@Override
		public int compareTo(Cluster o) {
			return this.pointIndex.size() - o.pointIndex.size();
		}
	}
	static class ClusterMap {
		int sizeOfCluster;
		int data[][];
		public ClusterMap(int sizeOfCluster, int lengthOfData) {
			this.sizeOfCluster = sizeOfCluster;
			data = new int[lengthOfData][sizeOfCluster];
		}
	}
}
