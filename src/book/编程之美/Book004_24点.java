package book.编程之美;

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
}