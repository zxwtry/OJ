package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	public static void B_纪录String_append(String path, String str) {
		File file = new File(path);
		try {
			if (! file.exists()) {
				file.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			bw.write(str + "\r\n");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int[] C_读取int数组_LEETCODE(String path, int len) {
		File file = new File(path);
		BufferedReader br = null;
		try {
			if (! file.exists()) {
				return null;
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (len > 1) {
				br.readLine();
				len --;
			}
			String line = br.readLine().trim();
			return tools.Utils.LEETCODE_int_array_反序列化_(line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
	public static int[][] C_读取int二维数组_LEETCODE(String path, int len) {
		File file = new File(path);
		BufferedReader br = null;
		try {
			if (! file.exists()) {
				return null;
			}
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while (len > 1) {
				br.readLine();
				len --;
			}
			String line = br.readLine().trim();
			return tools.Utils.LEETCODE_int_二位数组_反序列化_(line);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
}