package hiho;

/**

时间限制:10000ms
单点时限:1000ms
内存限制:256MB
描述
给定一个1-N的排列A1, A2, ... AN，每次操作小Hi可以选择一个数，把它放到数组的最左边。

请计算小Hi最少进行几次操作就能使得新数组是递增排列的。

输入
第一行包含一个整数N。

第二行包含N个两两不同整数A1, A2, ... AN。(1 <= Ai <= N)

对于60%的数据 1 <= N <= 20

对于100%的数据 1 <= N <= 100000

输出
一个整数代表答案

样例输入
5
2 3 1 4 5
样例输出
1

 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        训练17_题目1_F1Score.java
 * @type        训练17_题目1_F1Score
 * @date        2017年5月21日 下午12:00:03
 * @details     
 */

import java.util.Scanner;

public class 训练17_题目2_数组重排2 {
    
    static class TA extends Thread {
        boolean[] a;
        public TA(boolean[] b) {
            a = b;
        }
        @Override
        public void run() {
            System.out.println("fuck");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
            a[0] = true;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        
//        int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(20, 1, 20);
//        tools.Utils.printArray(arr, arr.length);
        
//        solve();
//        solve2();
        
//        int[] ar = {1};
//        int[] br = {};
//        System.arraycopy(ar, 1, br, 0, 0);
//        System.out.println("done");
        boolean[] a = {true};
        TA t = new TA(a);
        a[0] = false;
        t.start();
        t.join();
        Thread.sleep(2000);
        t.start();
        
        
//        Scanner sc = new Scanner(System.in);
//        
//        int n = sc.nextInt();
//        int[][] t = new int[2][n];
        
//        int[] a = t[0];
//        int max = 0;
//        int ans = 0;
        
//        for (int i = 0; i < n; i ++) 
//            a[i] = sc.nextInt();
        
//        int[] b = t[1];
//        
//        
//        while (true) {
//            int thisCount = 0;
//            int bi = 0;
//            max = 0;
//            for (int i = 0 ;i < n; i ++) {
//                
//                int v = a[i];
//                max = Math.max(max, v);
//                if (v < max) {
//                    thisCount ++;
//                } else {
//                    b[bi ++] = v;
//                }
//            }
//            n = bi;
//            
//            int[]tt = t[0];
//            t[0] = t[1];
//            t[1] = tt;
//            
//            a = t[0];
//            b = t[1]; 
//            
//            ans += thisCount;
//            if (thisCount == 0) break;
//        }
//        
//        
//        System.out.println(ans);
//            a[i] =sc.nextInt();
        
        
        
        
        
        
//        sc.close();
        
        
    }
    
    
    
    
    private static void solve2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] is = new int[n];
        for (int i = 0; i < n; i ++) {
            a[i] = sc.nextInt() - 1;
            is[a[i]] = i;
        }
        int ans = 0;
        int left = n-1;
        for (int i = n-1; i > -1; i --) {
            if (left >= is[i]  - 1) {
                ans += left - is[i];
                left = is[i] - 1;
                if (left < 0) break;
            }
        }
        System.out.println(ans);
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        
        
        int n = sc.nextInt();
        
        int[][] t = new int[2][n];
        
        int[] a = t[0];
        int maxIndex = 0;
        int max = 0;
        int ans = 0;
        int[] b = t[1];
        int bi = 0;
        for (int i = 0; i < n; i ++) {
            a[i] = sc.nextInt();
            if (a[i] < max) {
                ans ++;
            } else if (a[i] > max) {
                b[bi ++] = a[i];
            } else {
                //a[i] == max
                
            }
            max = Math.max(max, a[i]);
        }
        
        swap(t);
        
        while (true) {
            a = t[0];
            b = t[1];
            int i = 0;
            int an = bi;
            bi = 0;
            max = 0;
            int count = 0;
            for (i = 0; i < an; i ++) {
                if (a[i] < max) {
                    count ++;
                } else if (a[i] > max) {
                    b[bi ++] = a[i];
                } else {
                    
                }
            }
            
            ans += count;
            if (count == 0) break;
            
            
            swap(t);
        }
        
        
        
        
        System.out.println(ans);
        
        
        sc.close();
        
        
    }
    
    
    static void swap(int[][] t) {
        int[] tt = t[0];
        t[0] = t[1];
        t[1] = tt;
    }

/**

#include <stdio.h>
#include <stdlib.h>

long a[100001];

int main()
{
    long n,i,g=0,s,maxs;
    scanf("%ld",&n);
    s=0;
    maxs=0;
    for (i=1;i<=n;i++)
    {
        scanf("%ld",&s);
        if (s>maxs)
            maxs=s;
        else if (s>g)
            g=s;
    }
    printf("%ld\n",g);
    return 0;
}
/*
find 3 ,largest number that previous number biggert than it
2 4 3 1 5 6
3 2 4 1 5 6
2 3 4 1 5 6
1 2 3 4 5 6
*/



    
}
