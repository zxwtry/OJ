package nowcoder.com;

import java.io.File;
import java.util.Scanner;

/**
 * 最大子阵和（百度2017秋招真题）
	 题目描述
										
	给出一个n行m列的二维矩阵A[m,n]，其每个元素的取值范围是[-1000,1000]，
	其中1＜=n＜=100,1＜=m＜=100。求出p,q,r,s，满足条件1＜=p＜=q＜=n,1＜=r＜=s＜=m
	且p＜=i＜=q,r＜=j＜=s的(i,j)对应的A[i,j]之和最大。
	
	若(p,q,r,s)有多个解，输出最大子阵和即可。
	
	输入
	第一行表示测试样例的组数Te（Te＜=10）。
	对于每组测试样例，其第一行包含两个数n和m，其中1＜=n＜=100,1＜=m＜=100，
	接下来n行，每行有m个数，表示A[i,j]中的元素，满足-1000＜=A[i,j]＜=1000。
	
	样例输入
	1
	1 5
	2 3 -19 1 1
	输出
	对每组测试数据，单独输出一行答案。
	样例输出
	5
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        百度17_最大子阵和.java
 * @type        百度17_最大子阵和
 * @date        2016年11月27日 下午10:03:48
 * @details     
 */
public class 百度17_最大子阵和 {
	public static void main(String[] args) {
		try {
			System.gc();
			Thread.sleep(100000);
			long t1 = System.currentTimeMillis();
			for (int times = 0; times < 10000; times ++)
				solve1();
			long t2 = System.currentTimeMillis();
			System.out.println(t2 - t1);
			System.gc();
			Thread.sleep(100000);
			t1 = System.currentTimeMillis();
			for (int times = 0; times < 10000; times ++)
				solve2();
			t2 = System.currentTimeMillis();
			System.out.println(t2 - t1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method      solve1
	 * @parameter   
	 * @return      void
	 * @details     AC
	 * @details     时间复杂度：O(N^2 * M^2)
	 */
	static void solve1() throws Exception {
		Scanner sc = new Scanner(new File("D:/file/data/百度17_最大子阵和.txt"));
		int times = sc.nextInt();
		for (int timesIndex = 1; timesIndex <= times; timesIndex ++) {
			int n = sc.nextInt(), m = sc.nextInt();
			int[][] arr = new int[n][m];
			for (int i = 0; i < n; i ++) {
				for (int j = 0; j < m; j ++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int[][] sum = new int[n + 1][m + 1];
			for (int i = 1; i <= n; i ++) {
				for (int j = 1; j <= m; j ++) {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i - 1][j - 1];
				}
			}
			int ans = Integer.MIN_VALUE;
			for (int i1 = 0; i1 <= n; i1 ++) {
				for (int j1 = 0; j1 <= m; j1 ++) {
					for (int i2 = i1 + 1; i2 <= n; i2 ++) {
						for (int j2 = j1 + 1; j2 <= m; j2 ++) {
							ans = Math.max(ans, sum[i1][j1] + sum[i2][j2] - sum[i1][j2] - sum[i2][j1]);
						}
					}
				}
			}
//			System.out.println(ans);
		}
		sc.close();
	}
	
    /**
     * @method      solve2
     * @parameter   
     * @return      void
     * @details     效率是solve1的很多倍就是了
     * @details     时间复杂度：O(N^2 * M)
     */
    public static void solve2() throws Exception{
        Scanner sc = new Scanner(new File("D:/file/data/百度17_最大子阵和.txt"));
        while(sc.hasNext()){
            int c = sc.nextInt();
            for(int x = 0; x<c; x++){
                int n = sc.nextInt();
                int m = sc.nextInt();
                int[][] mat = new int[n][m];
                for(int i= 0;i<n; i++){
                    for(int j = 0; j<m; j++){
                        mat[i][j]=sc.nextInt();
                    }
                }
                solve2_subMatrix(mat);
//                System.out.println(solve2_subMatrix(mat));
            }
        }
        sc.close();
    }
	public static int solve2_subMatrix(int[][] mat) {
        int maxSum = Integer.MIN_VALUE;
        int n = mat.length;
        int m = mat[0].length;
        int sum[] = new int[m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++)
                sum[j]=0;
            for(int t=0; t<n-i; t++){
                for(int j=0; j<m; j++)
                    sum[j]+=mat[i+t][j];
                maxSum = Math.max(maxSum, solve2_getMaxSum(sum));
            }
        }
        return maxSum;
    }
    public static int solve2_getMaxSum(int a[]){
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        int n = a.length;
        for(int i=0; i<n; i++){
            curSum += a[i];
            maxSum = Math.max(maxSum, curSum);
            if(curSum<0) curSum=0;
        }
        return maxSum;
    }
}
