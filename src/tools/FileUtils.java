package tools;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class FileUtils {
	public static int[][] A_读取二维数组(String path, int column) {
		int count = 0;
		int[][] ans = null;
		Scanner scan =   null;
		try {
			scan = new Scanner(new File(path));
			while (scan.hasNext()) {
				scan.nextInt();
				count ++;
			}
			scan.close();
			if (count < column)
				return ans;
			scan = new Scanner(new File(path));
			ans = new int[count / column][column];
			for (int i = 0; i != ans.length; i ++) {
				for (int j = 0; j != ans[0].length; j ++) {
					ans[i][j] = scan.nextInt();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
		return ans;
	}
	public static String[] A_读取一维数组(String path) {
		LinkedList<String> list = new LinkedList<>();
		Scanner scan =   null;
		try {
			scan = new Scanner(new File(path));
			while (scan.hasNext()) {
				list.add(scan.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
		String[] ans = new String[list.size()];
		Iterator<String> it = list.iterator();
		int count = 0;
		while (it.hasNext()) {
			ans[count ++] = it.next();
		}
		return ans;
	}
	public static int[] A_读取一维int数组_空格分割(String path) {
		String[] strs = A_读取一维数组(path);
		int[] ans = new int[strs.length];
		for (int i = 0; i < strs.length; i ++) {
			ans[i] = Integer.parseInt(strs[i]);
		}
		return ans;
	}
}