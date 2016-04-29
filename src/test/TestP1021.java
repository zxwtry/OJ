package test;

import java.util.Arrays;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import sun.reflect.generics.tree.VoidDescriptor;

/*
 * 	取中点，强行算
 * 
 */
public class TestP1021 {
	public static void main(String[] args) {
		Point[] arr1 = new Point[]{new Point(0, 0), new Point(1, 0), new Point(2, 0), new Point(3, 0), new Point(0, 1), new Point(3, 1), new Point(0, 2), new Point(0, 3)};
		Point[] arr2 = new Point[]{new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(0, 2), new Point(0, 3), new Point(1, 3), new Point(2, 3), new Point(3, 3)};
		axialSymmetry(arr1);
		clockwist90(arr1);
		clockwist90(arr1);
		clockwist90(arr1);
		System.out.println();
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
	static class Point {
		int x,y;
		public Point () {
		}
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
		public boolean equals(Point o) {
			return this.x == o.x && this.y == o.y;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
}	
