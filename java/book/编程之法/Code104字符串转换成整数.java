package book.编程之法;

public class Code104字符串转换成整数 {
	public static void main(String[] args) {
		System.out.println(strToInt("123123".toCharArray()));
		System.out.println(strToInt("2147483647".toCharArray()));
		System.out.println(strToInt("00000".toCharArray()));
		System.out.println(strToInt("-2147483647".toCharArray()));
		System.out.println(strToInt("-2147483648".toCharArray()));
		System.out.println(strToInt("-2147483649".toCharArray()));
	}
	static int strToInt(char[] arr) {
		int n = 0, end = 0, i = 0, sta = 0;
		if (arr == null)
			throw new NullPointerException();
		if ((end=arr.length-1) < 0)
			throw new NumberFormatException();
		if (arr[0] == '-') {
			if (end == 0)
				throw new NumberFormatException();
			sta = 1;
			for (i = sta; i <= end; i ++) {
				char c = arr[i];
				if (c < '0' || c > '9')
					throw new NumberFormatException();
				n = n*10 - (c-'0');
				if (n > 0)
					throw new NumberFormatException();
			}
		} else {
			if (arr[0] == '+') {
				if (end == 0)
					throw new NumberFormatException();
				sta = 1;
			}
			for (i = sta; i <= end; i ++) {
				char c = arr[i];
				if (c < '0' || c > '9')
					throw new NumberFormatException();
				n = n*10 + (c-'0');
				if (n < 0)
					throw new NumberFormatException();
			}
		}
		return n;
	}
}
