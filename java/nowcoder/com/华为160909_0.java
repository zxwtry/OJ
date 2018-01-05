package nowcoder.com;

import java.util.*;

/*
 * 	输入一行字符，分别统计出包含英文字母、空格、数字和其它字符的个数。
 * 	AC
 */

public class 华为160909_0 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String line = scan.nextLine();
			int cou_en = 0, cou_number = 0, cou_blank = 0, cou_other = 0;
			for (int i = line.length() - 1; i > -1; i --) {
				char c = line.charAt(i);
				if ( (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )
					cou_en ++;
				else if (c >= '0' && c <= '9')
					cou_number ++;
				else if (c == ' ')
					cou_blank ++;
				else
					cou_other ++;
			}
			System.out.println(cou_en);
			System.out.println(cou_blank);
			System.out.println(cou_number);
			System.out.println(cou_other);
		}
		scan.close();
	}
}
