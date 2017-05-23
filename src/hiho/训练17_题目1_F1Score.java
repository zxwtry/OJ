package hiho;



import java.io.BufferedReader;
import java.io.InputStreamReader;

/**

时间限制:10000ms
单点时限:1000ms
内存限制:256MB
描述
小Hi和他的小伙伴们一起写了很多代码。时间一久有些代码究竟是不是自己写的，小Hi也分辨不出来了。

于是他实现了一个分类算法，希望用机器学习实现自动分类。

为了评价这个分类算法的优劣，他选出了N份有标记的代码作测试集，并决定用F1 Score作为评价标准。

给出N份代码的实际作者是不是小Hi以及分类算法预测的结果，请你计算F1 Score。

输入
第一行包含一个整数N。(1 <= N <= 1000)    

以下N行每行包含两个字符(+或-)。第一个字符代表这份代码的实际作者是不是小Hi(+代表是，-代表不是)，
第二个代表预测的作者是不是小Hi(+代表是，-代表不是)。  

输出
一个百分数，X%，代表答案，X保留两位小数。

样例输入
4  
+ +    
+ -  
- +  
- -
样例输出
50.00%

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
public class 训练17_题目1_F1Score {
    public static void main(String[] args) {
        
        
        char c = '\0';
        System.out.println();
        
        
        int tmp = 0;
        if (tmp == 0) return;
        
        
      Scanner sc = new Scanner(System.in);
      
      int n = Integer.parseInt(sc.nextLine().trim());
      
      int tp = 0;
      int fp = 0;
      int fn = 0;
      
      char TTT = '+';
      char FFF = '-';
      
      for (int i = 0; i < n; i ++) {
          char a = 'a';
          char b = 'b';
          while (b == 'b') {
              String l = sc.nextLine();
              for (int j = 0; j < l.length(); j ++)
                  if (l.charAt(j) == '+' || l.charAt(j) == '-') {
                      if (a == 'a')
                          a = l.charAt(j);
                      else b = l.charAt(j);
                  }
          }
          if (a == TTT && b == TTT) {
              tp += 1;
          }
          if (a == FFF && b == TTT) {
              fp += 1;
          }
          if (a == TTT && b == FFF) {
              fn += 1;
          }
      }
      
      if (tp == 0) {
          System.out.println("0.00%");
      } else {
          double a = ((double)(2 * tp * tp)) / ((double)(2 * tp * tp
                  + tp * fn + tp * fp)) * 100;
          System.out.printf("%.2f%%\n", a);
      }
      
//      a = 0.00012;
//      
//      int v = (int)(a * 10000);
//      
//      
//      System.out.printf("%d.%02d%%\n", v / 100, v % 100);
      
      sc.close();
        
        
    }
    /**

#include <stdio.h>
#include <stdlib.h>

int main()
{
    long n,g=0,i,tp=0,fn=0,fp=0,tn=0;
    double p,r;
    char c,x[10],y[10],sym='%';
    scanf("%ld",&n);
    for (i=1;i<=n;i++)
    {
//        c=getchar();
//        x=getchar();
//        c=getchar();
//        y=getchar();
//        if (x=='+' && y=='+')
//            tp++;
//        if (x=='+' && y=='-')
//            fn++;
//        if (x=='-' && y=='+')
//            fp++;
//        if (x=='-' && y=='-')
//            tn++;
        scanf("%s%s",x,y);
        if (strcmp(x,"+")==0 && strcmp(y,"+")==0)
            tp++;
        if (strcmp(x,"+")==0 && strcmp(y,"-")==0)
            fn++;
        if (strcmp(x,"-")==0 && strcmp(y,"+")==0)
            fp++;
        if (strcmp(x,"-")==0 && strcmp(y,"-")==0)
            tn++;
    }
    p=1.0*tp/(tp+fp);
    r=1.0*tp/(tp+fn);
    if (p+r==0)
        printf("0.00%c\n",sym);
    else
        printf("%.2lf%c\n",100.0*2*p*r/(p+r),sym);
    return 0;
}
/*
4
+ +
+ -
- +
- -
*/


    
}
