package tools;

public class Complex辅助 {
	public static class Complex {
		public double real;
		public double imag;
		public Complex() {
			this.real = 0;
			this.imag = 0;
		}
		public Complex(double real, double imag) {
			this.real = real;
			this.imag = imag;
		}
		@Override
		public String toString() {
			if (this.imag < 0) {
				return this.real +""+this.imag+"i";
			} else {
				return this.real +"+"+this.imag+"i";
			}
		}
	}
	public static Complex add(Complex c1, Complex c2) {
		return new Complex(c1.real + c2.real, c1.imag + c2.imag);
	}
	public static Complex minus(Complex c1, Complex c2) {
		return new Complex(c1.real - c2.real, c1.imag - c2.imag);
	}
	public static Complex multiply(Complex c1, Complex c2) {
		double real = c1.real * c2.real - c1.imag * c2.imag;
		double imag = c1.real * c2.imag + c1.imag * c2.real;
		return new Complex(real, imag);
	}
	public static Complex divide(Complex c1, Complex c2) {
		return multiply(new Complex(1/(c2.real * c2.real + c2.imag * c2.imag), 0), 
				multiply(c1, new Complex(c2.real, - c2.imag)));
	}
	public static double[] A_从Complex数组到FFT输入数据(Complex[] cs) {
		if (cs == null || cs.length == 0) {
			return new double[0];
		}
		double[] ans = new double[cs.length * 2];
		for (int i = 0; i < cs.length; i ++) {
			ans[2 * i] = cs[i].real;
			ans[2 * i + 1] = cs[i].imag;
		}
		return ans;
	}
}
