package tools;

public class StringUtils {
	public static String A_生成随机数组A_Z(int len) {
		char[] cs = new char[len];
		for (int i = 0; i < cs.length; i ++) {
			cs[i] = (char) ('A' + (int)(Math.random() * 26));
		}
		return new String(cs);
	}
	public static String A_生成随机回文串A_Z(int len) {
		if (len % 2 == 0) {
			String s = A_生成随机数组A_Z(len / 2);
			StringBuilder st = new StringBuilder(s);
			st = st.reverse();
			st.append(s);
			return st.toString();
		} else {
			String s = A_生成随机数组A_Z( (len + 1) / 2);
			StringBuilder st = new StringBuilder(s);
			st = st.reverse();
			st.deleteCharAt(st.length() - 1);
			st.append(s);
			return st.toString();
		}
	}
	public static String A_生成随机字符串A_Z_a_z(int len) {
		char[] cs = new char[len];
		for (int csIndex = 0; csIndex < len; csIndex ++) {
			int randomInt = (int)(Math.random() * 52);
			cs[csIndex] = randomInt < 26 ? (char)('a' + randomInt) : (char)('A' + randomInt - 26);
		}
		return new String(cs);
	}
	public static String A_生成随机回文串char1_char2(char char1, char char2, int len) {
		if (char1 > char2) {
			return A_生成随机回文串char1_char2(char2, char1, len);
		}
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, 0, char2 - char1 + 1);
		char[] cs = new char[len];
		for (int index = 0; index < len; index ++) {
			cs[index] = (char) (char1 + arr[index]);
		}
		return new String(cs);
	}
}
