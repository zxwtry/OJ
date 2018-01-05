package nowcoder.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
 * 	洗牌在生活中十分常见，现在需要写一个程序模拟洗牌的过程。
 *  现在需要洗2n张牌，从上到下依次是第1张，第2张，第3张一直到第2n张。
 *  首先，我们把这2n张牌分成两堆，左手拿着第1张到第n张（上半堆），
 *  右手拿着第n+1张到第2n张（下半堆）。接着就开始洗牌的过程，先放下右手的最后一张牌，
 *  再放下左手的最后一张牌，接着放下右手的倒数第二张牌，再放下左手的倒数第二张牌，
 *  直到最后放下左手的第一张牌。接着把牌合并起来就可以了。
 *   例如有6张牌，最开始牌的序列是1,2,3,4,5,6。首先分成两组，
 *   左手拿着1,2,3；右手拿着4,5,6。在洗牌过程中按顺序放下了6,3,5,2,4,1。
 *   把这六张牌再次合成一组牌之后，我们按照从上往下的顺序看这组牌，就变成了序列1,4,2,5,3,6。
 *    现在给出一个原始牌组，请输出这副牌洗牌k次之后从上往下的序列。 
	输入描述:
	第一行一个数T(T ≤ 100)，表示数据组数。对于每组数据，第一行两个数n,k(1 ≤ n,k ≤ 100)，
	接下来一行有2n个数a1,a2,...,a2n(1 ≤ ai ≤ 1000000000)。表示原始牌组从上到下的序列。
	
	
	输出描述:
	对于每组数据，输出一行，最终的序列。数字之间用空格隔开，不要在行末输出多余的空格。
	
	输入例子:
	3
	3 1
	1 2 3 4 5 6
	3 2
	1 2 3 4 5 6
	2 2
	1 1 1 1
	
	输出例子:
	1 4 2 5 3 6
	1 5 4 3 2 6
	1 1 1 1
 */


public class 网易2016洗牌 {
	public static void main(String[] args) throws FileNotFoundException {
		Solution2.main(null);
	}
	/*
	 * 	毫无疑问TLE
	 */
	static class Solution1 {
		static int n;
		static int[] arr = null, temp = null;
		public static void main(String[] args) throws FileNotFoundException {
			Scanner scan = new Scanner(new File("C:/data/wy.txt"));
			int times = scan.nextInt();
			while (times-- > 0) {
				n = scan.nextInt();
				int k = scan.nextInt();
				arr = new int[n << 1];
				temp = new int[arr.length];
				for (int i = 0; i != arr.length; i ++)
					arr[i] = scan.nextInt();
				while(k -- > 0)
					xipai();
				for (int i = 0; i != arr.length; i ++)
					System.out.printf("%d ", arr[i]);
				System.out.println();
			}
			scan.close();
		}
		static void xipai() {
			System.arraycopy(arr, 0, temp, 0, arr.length);
			int i1 = n - 1, i2 = arr.length - 1, j = arr.length - 1;
			for (int i = 0; i != n; i ++) {
				arr[j --] = temp[i2 --];
				arr[j --] = temp[i1 --];
			}
		}
	}
	/*
	 * 	AC了，代码好巧，好短
	 */
	static class Solution2 {
		static int n;
		static int[] arr = null, temp = null;
		public static void main(String[] args) throws FileNotFoundException {
			Scanner scan = new Scanner(new File("C:/data/wy.txt"));
			int times = scan.nextInt();
			while (times-- > 0) {
				n = scan.nextInt();
				int k = scan.nextInt();
				arr = new int[n << 1];
				temp = new int[arr.length];
				for(int i=0; i < 2*n; i ++){
	                int tmp = i + 1;
	                for(int j = 0; j < k; j ++){
	                    if (tmp <= n) tmp = 2*tmp - 1;
	                    else tmp = 2 * (tmp - n);
	                }
	                arr[tmp - 1]=scan.nextInt();
	            }
				for (int i = 0; i < arr.length - 1; i ++)
					System.out.printf("%d ", arr[i]);
				System.out.println(arr[arr.length - 1]);
			}
			scan.close();
		}
	}
}
