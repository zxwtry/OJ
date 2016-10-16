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
}
