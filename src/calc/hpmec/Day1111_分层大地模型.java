package calc.hpmec;

import org.jtransforms.fft.FloatFFT_1D;

public class Day1111_分层大地模型 {
	public static void main(String[] args) {
		
	}
	static void step161111_1613测试FFT() {
		FloatFFT_1D fft = new FloatFFT_1D(16);
		float[] arr = new float[] {1, 2, 3, 4};
		fft.complexForward(arr);
		
	}
}
