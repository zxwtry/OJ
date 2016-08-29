package tools;

import java.io.File;
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
}