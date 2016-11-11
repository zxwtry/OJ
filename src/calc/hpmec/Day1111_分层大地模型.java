package calc.hpmec;

import org.jtransforms.fft.FloatFFT_1D;

/*
 * 	这里使用的FFT包是：JTransforms-3.1-with-dependencies.jar
 */

public class Day1111_分层大地模型 {
	public static void main(String[] args) {
		step161111_1613测试FFT();
	}
	static void step161111_1613测试FFT() {
		/*
		 * 	a[2*k] = Re[k]
		 * 	a[2*k+1] = Im[k]
		 */
		FloatFFT_1D fft = new FloatFFT_1D(8);
		float[] arr = new float[] {1, 2, 3, 4};
		float[] calc = new float[16];
		System.arraycopy(arr, 0, calc, 0, arr.length);
		fft.complexForward(calc);
		tools.Utils.printArray(calc, 100);
		fft.complexInverse(calc, true);
		tools.Utils.printArray(calc, 100);
	}
	static void step161111_1613测试FFT_w() {
		
	}
	static void step161111_1700测试FFT_使用均匀大地GIC() {
		/*
		 * 	频域标准公式：Ey(w)=-sqrt((jw)/(miu*siga))Bx(w)
		 * 	时域标准公式：Ey(t)=(-1/(sqrt(pi*miu*siga))) * [-无穷~t](gx(u) / sqrt(t-u))du
		 */
		/*
		 * 	忽略miu*siga
		 * 	Ey(w)=-sqrt((jw))Bx(w)
		 */
		
	}
}
