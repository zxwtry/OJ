package test;

public class StringFormatAndConnect {
	public static void main(String[] args) {
//		testTwoAdd();
		testManyAdd();
	}

	static void testManyAdd() {
		String[] ss = new String[10];
		long timeFormat = 0;
		long timeConnect = 0;
		for (int i = 0; i < ss.length; i ++) {
			ss[i] = tools.StringUtils.A_生成随机数组A_Z(1000);
		}
		String temp = null;
		long start = 0;
		long end = 0;
		start = System.currentTimeMillis();
		temp = ss[0] + ss[1] + ss[2] + ss[3] + ss[4] + ss[5] + ss[6] + ss[7] + ss[8] + ss[9] ;
		end = System.currentTimeMillis();
		timeConnect = end - start;
		start = System.currentTimeMillis();
		temp = String.format("%s%s%s%s%s%s%s%s%s%s", ss[0] , ss[1] , ss[2] , ss[3] , ss[4] , ss[5] , ss[6] , ss[7] , ss[8] , ss[9]);
		end = System.currentTimeMillis();
		timeFormat = end - start;
		System.out.println(timeFormat + "..." + timeConnect);
		//34...0
		System.out.println(temp.length());
	}

	static void testTwoAdd() {
		long timeFormat = 0;
		long timeConnect = 0;
		String[] ss = new String[10]; 
		int len = 500;
		for (int i = 0; i < ss.length; i ++) {
			ss[i] = tools.StringUtils.A_生成随机数组A_Z(len);
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
		for (int i = 0; i < ss.length; i ++) {
			ss[i] = tools.StringUtils.A_生成随机数组A_Z(4);
		}
		start = System.currentTimeMillis();
		for (int i = 0; i < ss.length; i ++) {
			for (int j = 0; j < ss.length; j ++) {
				ss[j] = String.format("%s%s", ss[i], ss[j]);
			}
		}
		end = System.currentTimeMillis();
		timeFormat = end - start;
		System.out.println(timeFormat + "..." + timeConnect);
		//len: 3 --> 	50...6
		//len: 50 --> 	53...120
		//len: 500 --> 	48...1863
	}
	
}
