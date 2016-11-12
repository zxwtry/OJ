package calc.hpmec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jtransforms.fft.DoubleFFT_1D;
import org.jtransforms.fft.FloatFFT_1D;

import tools.Complex辅助;
import tools.Complex辅助.Complex;

/*
 * 	这里使用的FFT包是：JTransforms-3.1-with-dependencies.jar
 */

public class Day1111_分层大地模型 {
	public static void main(String[] args) {
		step161111_1700测试FFT_使用均匀大地GIC();
	}
	static void step161111_1613测试FFT() {
		/*
		 * 	a[2*k] = Re[k]
		 * 	a[2*k+1] = Im[k]
		 */
		int n = 4;
		FloatFFT_1D fft = new FloatFFT_1D(n);
		float[] arr = new float[n * 2];
		for (int i = 0; i < arr.length; i ++) {
			if (i % 2 == 0) {
				arr[i] = 1;
			}
		}
		float[] calc = new float[n * 2];
		System.arraycopy(arr, 0, calc, 0, arr.length);
		fft.complexForward(calc);
		tools.Utils.printArray(calc, 100);
		for (int i = 0; i < calc.length; i ++) {
			calc[i] = 4 * calc[i];
		}
		fft.complexInverse(calc, true);
		tools.Utils.printArray(calc, 100);
	}
	static void step161111_1613测试FFT_sin() {
		//这里得到的结果和Matlab计算的结果一致
		int N = 4;
		DoubleFFT_1D fft = new DoubleFFT_1D(N);
		double[] arr = new double[8];
		arr[0] = 1;
//		arr[1] = 2;
		arr[2] = 2;
//		arr[3] = 3;
		arr[4] = 3;
//		arr[5] = 4;
//		arr[6] = 5;
//		arr[7] = 6;
		fft.complexForward(arr);
		tools.Utils.printArray(arr, 100);
	}
	static void step161111_1700测试FFT_使用均匀大地GIC() {
		/*
		 * 	频域标准公式：Ey(w)=-sqrt((jw)/(miu*siga))Bx(w)
		 * 	时域标准公式：Ey(t)=(-1/(sqrt(pi*miu*siga))) * [-无穷~t](gx(u) / sqrt(t-u))du
		 */
		/*
		 * 	忽略miu*siga
		 * 	频域标准公式：Ey(w)=-sqrt((jw))Bx(w)
		 * 	时域标准公式：Ey(t)=(-1/(sqrt(pi))) * [-无穷~t](gx(u) / sqrt(t-u))du
		 */
		double[] arr = null;
		try {
			Scanner sc = new Scanner(new File("C:/data/武汉地磁台06121413_B_x.csv"));
			String line = sc.nextLine();
			String[] parts = line.split(",");
			System.out.println(parts.length);
			int len = 1;
			while (len < parts.length) {
				len = len << 1;
			}
			arr = new double[len];
			for (int i = 0; i < parts.length; i ++) {
				arr[i] = Double.parseDouble(parts[i]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DoubleFFT_1D fft = new DoubleFFT_1D(arr.length);
		double[] fft_input = new double[arr.length * 2];
		for (int i = 0; i < arr.length; i ++) {
			fft_input[i * 2] = arr[i];
		}
		fft.complexForward(fft_input);
		Complex[] b_f = tools.Complex辅助.A_从FFT输出数据到Complex数组(fft_input);
		Complex k1 = new Complex(-Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
		Complex[] e_f = new Complex[b_f.length];
		for (int i = 0; i < e_f.length; i ++) {
			Complex k2 = new Complex(Math.sqrt(Math.PI * i / e_f.length), 0);
			e_f[i] = Complex辅助.multiply(k1, k2);
			e_f[i] = Complex辅助.multiply(e_f[i], b_f[i]);
		}
		double[] e_t = Complex辅助.A_从Complex数组到FFT输入数据(e_f);
		fft.complexInverse(e_t, true);
		tools.Utils.printArray(e_t, 10);
		double[] arr_d = new double[arr.length];
		for (int i = 1; i < arr_d.length; i ++) {
			arr_d[i] = arr[i] - arr[i - 1];
		}
		double[] e_t_t = new double[arr.length];
		for (int i = 0; i < arr.length; i ++) {
			double ett_i = 0;
			int sti = i < 100 ? 0 : i - 100;
			for (int j = sti; j < i; j ++) {
				ett_i += - (1/Math.sqrt(Math.PI)) * arr_d[j] / Math.sqrt(i - j);
			}
			e_t_t[i] = ett_i;
		}
		System.out.println("==============");
		tools.Utils.printArray(e_t_t, 5);
	}
}
