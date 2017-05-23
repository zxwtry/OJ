package hiho;

import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;

/**

时间限制:10000ms
单点时限:1000ms
内存限制:256MB
描述
小Hi被坏女巫抓进一座由无限多个格子组成的矩阵迷宫。

小Hi一开始处于迷宫中央(0, 0)的位置。他发现每个格子都印有一个大写字母。
并且从(0, 0)开始，按照A-Z的顺序，沿着顺时针螺旋循环排列，如下图所示(向右是X正方向，向上是Y正方向):

          0
         ...
      CXYZABCDE
      BWZABCDEF
      AVYJKLMFG
      ZUXIBCNGH
0  ...YTWHADOHI...
      XSVGFEPIJ
      WRUTSRQJK
      VQPONMLKL
      UTSRQPONM
         ... 
小Hi发现每次他可以移动到上下左右相邻的格子中，但是代价是心智受到1点伤害。
此外他还可以移动到印有相同字母的格子中，无论两个格子相距多远，代价也是心智受到1点伤害。

给定迷宫出口的位置(X, Y)，小Hi想知道离开迷宫最少受到多少点伤害。  

输入
第一行包含一个整数N，表示测试数据的组数。(1 <= N <= 12)  

以下N行每行包含两个整数X和Y，代表出口位置。  

对于30%的数据，-100 <= X, Y <= 100    

对于100%的数据， -1000000 <= X, Y <= 1000000

输出
对于每组数据输出一个整数表示答案。 每组数据单独一行。

样例输入
3  
1 2
-4 2  
4 1
样例输出
3  
1  
2

 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        训练17_题目4_逃离迷宫3.java
 * @type        训练17_题目4_逃离迷宫3
 * @date        2017年5月21日 下午2:40:17
 * @details
 */
public class 训练17_题目4_逃离迷宫3 {}


