package stl;

/*
 * 	POJ3069
 */

import java.util.Arrays;
import java.util.Scanner;

public class Book1_2_2_贪心_2_2_3_添加点 {
    static int N,R,c,X[] = new int[1010];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            R = sc.nextInt();
            N = sc.nextInt();
            if (N == -1 && R == -1) break;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int ni = 0; ni < N; ni ++) {
                X[ni] = sc.nextInt();
                if (X[ni] > max)
                        max = X[ni];
                if (X[ni] < min)
                        min = X[ni];
            }
            Arrays.sort(X,0,N);
            c = 1;
            int ni = 0, c = 0;
            while(ni < N) {
            	int s = X[ni++];
            	while (ni < N && X[ni] <= s+R)  ni++;
            	s = X[ni - 1];
            	while (ni < N && X[ni] <= s+R)  ni++;
            	c ++;
            }
            System.out.printf("%d\n", c);
        }
        sc.close();
    }
}