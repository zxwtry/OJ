package hiho;

import java.util.Scanner;

public class N99_骑士问题_三个骑士走到一起_Second {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = Integer.parseInt(scanner.nextLine().trim());
		while (num -- > 0) {
			StringBuilder line = new StringBuilder(scanner.nextLine());
			for (int i = line.length() - 1; i >= 0; i --) {
				char c = line.charAt(i);
				if (c == ' ')
					line.deleteCharAt(i);
				if (c >= 'A' && c <= 'H')
					line.setCharAt(i, (char)(c - 'A' + '0'));
			}
			
		}
		scanner.close();
	}
	
}
