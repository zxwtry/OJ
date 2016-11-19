package nowcoder.com;

/*
 * 	为活跃公司文化，公司计划组织一场比赛，让员工一展才艺。现有n个员工，
 * 	欲选出不少于k人组成一支队伍，1＜=n＜ =12,1＜=k＜=n。
	每个员工有个value值，表示其对队伍水平的贡献，-1000＜=value＜=1000，
	给出一个矩阵对角线为0的对称矩阵A， A[i][j]表示i,j同在队伍中时对队伍水平的贡献，
	为取得最好成绩，公司领导希望知道水平最高的组队方式能够达到的水平和组队方案数。
	
									
	输入
	第一行为测试样例组数Te（Te＜=100）。每组测试样例的第一行是两个数 n,k，第二行为n个数，
	表示每个人对队伍水平的贡献值，接下来有n行，每行有n个数，表示构成矩阵A的元素。
	样例输入
	1
	2 1
	100 -5
	0 10
	10 0
	输出
	每组测试样例输出一行，分别为能够达到的最高水平值和组队方案数。
	样例输出
	105 1
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

import java.util.Scanner;

/**
 * @author		zxwtry
 * @email		zxwtry@qq.com
 * @project		OJ
 * @package		nowcoder.com
 * @file		百度17_比赛组队.java
 * @date		2016年11月19日 下午10:24:37
 * @details		
 */
public class 百度17_比赛组队 {
	public static void main(String[] args) {
		solve1();
	}

	/**
	 * @method		solve1 
	 * @parameter	
	 * @return 		void
	 * @details 	
	 */
	private static void solve1() {
		int maxScore = 0;
		int n = 0, k = 0;
		int[] val = null;
		int[][] a = null;
		int maxNum = 0;
		boolean[] choose = null;
		Scanner in = new Scanner(System.in);
        int ss = in.nextInt();
        for(int loop=0; loop<ss; loop++) {
            n = in.nextInt();
            k = in.nextInt();
            val = new int[n];
            for(int i=0; i<n; i++) {
                val[i] = in.nextInt();
            }
            a = new int[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    a[i][j] = in.nextInt();
                }
            }
            maxScore = Integer.MIN_VALUE;
            maxNum = 0;
            choose = new boolean[n];
            int score;
            //select num people
            int selectNum;

            for(int i=0; i < 1<<n; i++) {
                score = 0;
                selectNum = 0;
                for(int j=0; j<n; j++) {
                    if((i>>j)%2==1) {
                        choose[j] = true;
                        selectNum++;
                        score = score + val[j];
                        for(k=0; k<j; k++) {
                            if(choose[k]) score = score + a[j][k];
                        }
                    } else {
                        choose[j] = false;
                    }
                }
                //System.out.println(i+" "+score+" "+selectNum);
                if(selectNum<k) continue;
                if(score>maxScore) {
                    maxScore = score;
                    maxNum = 1;
                } else if(score==maxScore) {
                    maxNum++;
                }
            }

            System.out.println(maxScore + " " + maxNum);

        }
        in.close();
	}
}
