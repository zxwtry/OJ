package nowcoder.com;




/**

阿里九游开放平台近日上架了一款新的益智类游戏——成三棋。成三棋是我国非常古老的一个双人棋类游戏，其棋盘如下图所示：



成三棋的棋盘上有很多条线段，只能在线段交叉点上放入棋子。我们可以用坐标系来描述棋盘：



如果一条线段上的三个交叉点都被同一玩家的棋子占据的话，则称这条线段被该玩家 成三。现在，小红和小明两人在游戏平台上下棋，其中小红的棋子是黑色的。请你帮小红计算他成三的线段数。

样例对应的棋盘如下：



输入格式

输入第一行两个整数 n,m(3 \le n, m \le 9)n,m(3≤n,m≤9)，nn 表示小红的棋子数，mm 表示小明的棋子数。

接下来 nn 行输入小红的棋子坐标。

接下来 mm 行输入小明的棋子坐标。

输入保证坐标合法，并且棋子之间不重合。

输出格式

输出小红成三的线段数。

样例输入

6 3
-1 0
-2 0
-3 0
-1 -1
-1 1
1 0
0 2
0 3
2 2
样例输出

2

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        计蒜客_初赛_1_1.java
 * @type        计蒜客_初赛_1_1
 * @date        2017年5月20日 下午6:59:12
 * @details     AC
 */


import java.util.HashSet;
import java.util.Scanner;

public class 计蒜客_初赛_1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
//        int[][] nn = new int[n][2];
//        int[][] mm = new int[m][2];
        HashSet<Integer> sn = new HashSet<>();
//        HashSet<Integer> sm = new HashSet<>();
//        int num = 10000;
        for (int i = 0; i < n; i ++) { 
//            nn[i][0] = sc.nextInt();
//            nn[i][1] = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            sn.add(calc(x, y));
        }
        for (int i = 0; i < m; i ++) {
//            mm[i][0] = sc.nextInt();
//            mm[i][1] = sc.nextInt();
//            sm.add(sc.nextInt() * num + sc.nextInt());
            sc.nextInt();
            sc.nextInt();
        }
        
        int ans = 0;
        for (int i = -3; i <= 3; i ++) {
            if (i == 0) continue;
            int v1 = calc(i, i);
            int v2 = calc(i, 0);
            int v3 = calc(i, -i);
            if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
                ans ++;
        }
        for (int i = -3; i <= 3; i ++) {
            if (i == 0) continue;
            int v1 = calc(i, i);
            int v2 = calc(0, i);
            int v3 = calc(-i, i);
            if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
                ans ++;
        }
        int v1 = 0, v2 = 0, v3 = 0;
        v1 = calc(0, 1);
        v2 = calc(0, 2);
        v3 = calc(0, 3);
        if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
            ans ++;
        v1 = calc(0, -1);
        v2 = calc(0, -2);
        v3 = calc(0, -3);
        if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
            ans ++;
        v1 = calc(1, 0);
        v2 = calc(2, 0);
        v3 = calc(3, 0);
        if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
            ans ++;
        v1 = calc(-1, 0);
        v2 = calc(-2, 0);
        v3 = calc(-3, 0);
        if (sn.contains(v1) && sn.contains(v2) && sn.contains(v3))
            ans ++;
        
        sc.close();
        
        System.out.println(ans);
        
    }
    
    static int calc(int x, int y) {
        return x * 10000 + y;
    }
}
