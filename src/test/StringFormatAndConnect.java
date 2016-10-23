package test;

public class StringFormatAndConnect {
	public static void main(String[] args) {
		testTwoAdd();
	}

	private static void testTwoAdd() {
		long timeFormat = 0;
		long timeConnect = 0;
		String[] ss = new String[8]; 
		for (int i = 0; i < ss.length; i ++) {
			ss[i] = tools.StringUtils.A_生成随机数组A_Z(4);
		}
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		for (int i = 0; i < ss.length; i ++) {
			for (int j = 0; j < ss.length; j ++) {
				ss[j] = ss[i] + ss[j];
			}
		}
		end = System.currentTimeMillis();
		timeConnect = end - start;
		start = System.currentTimeMillis();
		for (int i = 0; i < ss.length; i ++) {
			for (int j = 0; j < ss.length; j ++) {
				ss[j] = String.format("%s%s", ss[i], ss[j]);
			}
		}
		end = System.currentTimeMillis();
		timeFormat = end - start;
		System.out.println(timeFormat + "..." + timeConnect);
		//4808...1
	}
	
}
