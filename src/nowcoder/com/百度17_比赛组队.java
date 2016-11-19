package nowcoder.com;

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
