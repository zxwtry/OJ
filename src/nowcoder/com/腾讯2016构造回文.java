package nowcoder.com;

public class 腾讯2016构造回文 {
	static String line = null;
	static int[] dps = new int[3000];
	static char[] chs = new char[3000];
	static final char SIGN = '#';
	public static void main(String[] args) {
		line = "abcda";
		line = "google";
		line = "";
		dp();
	}
	static void dp() {
		if (line == null || line.length() == 0)
			return;
		int end = (line.length() << 1) - 2;
		for (int index = end; index >= 0; index --) {
			if ((index & 0x1) == 1) {
				chs[index] = SIGN;
			} else {
				chs[index] = line.charAt(index >> 1);
			}
		}
		int max_count = -1;
		for (int index = 0; index <= end; index ++) {
			int st = index-1, en = index+1, new_count = 1;
			while (st >= 0 && en <= end && chs[st] == chs[en]) {
				new_count += 2;
				st --; en ++;
			}
			if (new_count > max_count)
				max_count = new_count;
		}
		System.out.printf("%d\n", max_count);
	}
}