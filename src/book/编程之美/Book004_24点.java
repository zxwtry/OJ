package book.编程之美;

import java.io.File;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     book.编程之美
 * @file        Book004_24点.java
 * @type        Book004_24点
 * @date        2017年1月25日 下午6:32:15
 * @details     Solution1: 很复杂就是了
 */
public class Book004_24点 {
    public static void main(String[] args) throws Exception {
//        Solution1 solution1 = new Solution1();
        
//        int[] arr = new int[]{0, 0, 0, 0};
//        solution1.solve(arr);     //eozpwqcvxxlhieudzph
        //12951
        
//        char [] cs = new char[19];
//        for (int i = 0; i < cs.length; i ++) {
//            cs[i] = (char)('a' + (Math.random() * 26));
//            
//        }
//        System.out.println(new String(cs));
        
//        Scanner scanner = new Scanner(new File("F:/a.txt"));
//        while (scanner.hasNextInt()) {
//            int label = scanner.nextInt();
//            System.out.printf("=============%d=============", label);
//            Solution1 solution1 = new Solution1();
//            solution1.solve(new int[] {scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()});
//            System.out.println("==============================");
//        }
        
//        System.out.println("连城诀[www.5tps.com]001.mp3".indexOf('0'));
        String pathName = "E:/file/dsj/连城诀/";
        File path = new File(pathName);
        String[] names = path.list();
        for (String name : names) {
            String newName = "连城诀" + name.substring(17);
              File file = new File(pathName + name);
                
            file.renameTo(new File(pathName + newName));
        }
//        
    }
	static class Solution1 {
		public Solution1() {
			this(4, 24);
		}
		public Solution1(int cardNumber, double resultValue) {
			this.cardNumber = cardNumber;
			this.resultValue = resultValue;
			this.number = new double[cardNumber];
			this.result = new String[cardNumber];
		}
		final double threshold = 1e-6;
		final int cardNumber;
		final double resultValue;
		double[] number = null;
		String[] result = null;
		public void solve(int[] arr) {
			if (arr == null || arr.length < 1)
				return;
			for (int i = 0; i < arr.length; i ++) {
				number[i] = arr[i];
				result[i] = "" + arr[i];
			}
			if (pointGame(cardNumber))  {
				System.out.println("Success.");
			} else {
				System.out.println("Fail.");
			}
		}
		public boolean pointGame(int n) {
			if (n == 1) {
				if (Math.abs(number[0] - resultValue) < threshold) {
					System.out.println(result[0]);
					return true;
				} else {
					return false;
				}
			}
			for (int i = 0; i < n; i ++) {
				for (int j = i+1; j < n; j ++) {
					double a = number[i], b = number[j];
					number[j] = number[n - 1];
					String expa = result[i], expb = result[j];
					result[j] = result[n - 1];
					//加法
					result[i] = '(' + expa + '+' + expb + ')';
					number[i] = a + b;
					if (pointGame(n - 1))
						return true;
					//减法
					result[i] = '(' + expa + '-' + expb + ')';
					number[i] = a - b;
					if (pointGame(n - 1))
						return true;
					//减法
					result[i] = '(' + expb + '-' + expa + ')';
					number[i] = b - a;
					if (pointGame(n - 1))
						return true;
					//乘法
					result[i] = '(' + expa + '*' + expb + ')';
					number[i] = a * b;
					if (pointGame(n - 1))
						return true;
					//除法
					if (b != 0) {
						result[i] = '(' + expa + '/' + expb + ')';
						number[i] = a / b;
						if (pointGame(n - 1))
							return true;
					}
					//除法
					if (a != 0) {
						result[i] = '(' + expb + '/' + expa + ')';
						number[i] = b / a;
						if (pointGame(n - 1))
							return true;
					}
					number[i] = a;
					number[j] = b;
					result[i] = expa;
					result[j] = expb;
				}
			}
			return false;
		}
	}
	static class Solution2 {
        public Solution2() {
            this(4, 24);
        }
        public Solution2(int cardNumber, double resultValue) {
            this.cardNumber = cardNumber;
            this.resultValue = resultValue;
            this.number = new double[cardNumber];
            this.result = new String[cardNumber];
        }
        final double threshold = 1e-6;
        final int cardNumber;
        final double resultValue;
        double[] number = null;
        String[] result = null;
        String answerString = "";
        public String solve(int[] arr) {
            if (arr == null || arr.length < 1)
                return "";
            for (int i = 0; i < arr.length; i ++) {
                number[i] = arr[i];
                result[i] = "" + arr[i];
            }
            if (pointGame(cardNumber))  {
                return answerString;
            } else {
                return "";
            }
        }
        public boolean pointGame(int n) {
            if (n == 1) {
                if (Math.abs(number[0] - resultValue) < threshold) {
                    //System.out.println(result[0]);
                    answerString = result[0] + answerString;
                    return true;
                } else {
                    return false;
                }
            }
            for (int i = 0; i < n; i ++) {
                for (int j = i+1; j < n; j ++) {
                    double a = number[i], b = number[j];
                    number[j] = number[n - 1];
                    String expa = result[i], expb = result[j];
                    result[j] = result[n - 1];
                    //加法
                    result[i] = '(' + expa + '+' + expb + ')';
                    number[i] = a + b;
                    if (pointGame(n - 1))
                        return true;
                    //减法
                    result[i] = '(' + expa + '-' + expb + ')';
                    number[i] = a - b;
                    if (pointGame(n - 1))
                        return true;
                    //减法
                    result[i] = '(' + expb + '-' + expa + ')';
                    number[i] = b - a;
                    if (pointGame(n - 1))
                        return true;
                    //乘法
                    result[i] = '(' + expa + '*' + expb + ')';
                    number[i] = a * b;
                    if (pointGame(n - 1))
                        return true;
                    //除法
                    if (b != 0) {
                        result[i] = '(' + expa + '/' + expb + ')';
                        number[i] = a / b;
                        if (pointGame(n - 1))
                            return true;
                    }
                    //除法
                    if (a != 0) {
                        result[i] = '(' + expb + '/' + expa + ')';
                        number[i] = b / a;
                        if (pointGame(n - 1))
                            return true;
                    }
                    number[i] = a;
                    number[j] = b;
                    result[i] = expa;
                    result[j] = expb;
                }
            }
            return false;
        }
	}
}