package hiho;

import java.io.UnsupportedEncodingException;

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
 * @details     AC 90%
 */

import java.util.Scanner;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class 训练17_题目3_逆序对 {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        StringBuilder st = new StringBuilder();
//        for (int index = 0; index <= Character.MAX_VALUE; index ++) {
//            st.append((char)index);
//        }
//        System.out.println(st.toString().getBytes("UTF-8").length);
        
//        System.out.println(1 << 14);
//        
//        System.out.println(3 << 14 < 1 << 16);
        
//        byte b = -1;
//        int v = b & 0xff;
//        System.out.println(v);
        
//        int v = 0xd811;
//        char c = (char) v;
//        System.out.println(Integer.toHexString((int)v));
        
//        System.out.println(0xffff);
        
//        int val = 0xdcba;
//        System.out.println((val >> 0) & 0xf);
//        System.out.println((val >> 4) & 0xf);
//        System.out.println((val >> 8) & 0xf);
//        System.out.println((val >> 12) & 0xf);
        
//        int v = 127;
//        v = v << 7;
//        v += 127;
//        v = v << 7;
//        v += 127;
//        System.out.println(Integer.toHexString(v));
//        System.out.println(v);
        
//        int v = 255;
//        byte b = (byte) (v & 0xff);
//        System.out.println(b);
        
        
//        int v = 0x12345657;
//        System.out.println(Integer.toHexString((v ) & 0xff));
//        System.out.println(Integer.toHexString((v >> 8) & 0xff));
//        System.out.println(Integer.toHexString((v >> 16) & 0xff));
        
//        int v = 1;
//        System.out.println(v << 1);
        
        int v = 0;
        v = (v << 8) + 255;
        v = (v << 8) + 255;
        v = (v << 8) + 255;
        v = (v << 8) + 255;
        System.out.println(v);
        
//        int v = 0xffffff;
//        System.out.println(v);
        
        
        int ssssssss = 1;
        if (ssssssss == 1) return;
        
        
        
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int[] a = new int[n];
        int max = 0;
        long ans = 0;
        
        
        for (int i = 0; i < n; i ++) 
            a[i] = sc.nextInt();
        
        System.out.println(InversePairs(a));
//            a[i] =sc.nextInt();
        
        sc.close();
        
        
    }
    
    public static long InversePairs(int [] array) {
        if(array == null || array.length == 0) return 0;
        int[] hash = new int[200000];
        int i = array.length-1;
        long ans = 0;
        for(; i>= 0; i--){
            hash[array[i]] ++;
            ans += countInversePairs(hash, array[i]);
        }
        return ans;
    }
    
    private static long countInversePairs(int[] hash, int target){
        long count = 0;
        for(int i = 0; i < target; i++){
            count += hash[i];
        }
        return count;
    }

/**


#include <stdio.h>
#include <stdlib.h>

long a[100001],t[100001];
long long ans=0;

void mergesort(long l,long r)
{
    if (l==r)
        return ;
    long mid,x,y,z,i;
    mid=(l+r)/2;
    mergesort(l,mid);
    mergesort(mid+1,r);
    for (i=l;i<=r;i++)
        t[i]=a[i];
    x=l;
    y=mid+1;
    z=l;
    while (x<=mid && y<=r)
    {
        //1~n not the same
        if (t[x]<t[y])
        {
            a[z]=t[x];
            x++;
            //a[x] > a[mid+1]~a[y-1]
            ans+=(y-mid-1);
        }
        else
        {

            a[z]=t[y];
            y++;
        }
        z++;
    }
    if (x<=mid)
    {
        //a[x] > a[mid+1]~a[r]
        ans+=(mid-x+1)*(r-mid);
        while (z<=r)
        {
            a[z]=t[x];
            x++;
            z++;
        }
    }
    else
    {
        while (z<=r)
        {
            a[z]=t[y];
            y++;
            z++;
        }
    }
}

int main()
{
    long n,i;
    scanf("%ld",&n);
    for (i=1;i<=n;i++)
        scanf("%ld",&a[i]);
    mergesort(1,n);
    printf("%lld\n",ans);
    return 0;
}


 */
    
}
