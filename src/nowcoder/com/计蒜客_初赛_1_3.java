package nowcoder.com;

import java.util.Scanner;


/**

阿里“天池”竞赛平台近日推出了一个新的挑战任务：对于给定的一串 DNA 碱基序列 tt，判断它在另一个根据规则生成的 DNA 碱基序列 ss 中出现了多少次。

首先，定义一个序列 ww：

\displaystyle w_{i} = \begin{cases}b, & i = 0\\(w_{i-1} + a) \mod n, & i > 0\end{cases}w
​i
​​ ={
​b,
​(w
​i−1
​​ +a)modn,
​​ 
​i=0
​i>0
​​ 

接下来，定义长度为 nn 的 DNA 碱基序列 ss（下标从 00 开始）：

\displaystyle s_{i} = \begin{cases}A , & (L \le w_{i} \le R) \land (w_{i}\ \mathrm{mod}\ 2 = 0)\\T , & (L \le w_{i} \le R) \land (w_{i}\ \mathrm{mod}\ 2 = 1)\\G , & ((w_{i} < L) \lor (w_{i} > R)) \land (w_{i}\ \mathrm{mod}\ 2 = 0)\\C , & ((w_{i} < L) \lor (w_{i} > R)) \land (w_{i}\ \mathrm{mod}\ 2 = 1)\end{cases} s
​i
​​ =
​⎩
​⎪
​⎪
​⎪
​⎨
​⎪
​⎪
​⎪
​⎧
​​ 
​A,
​T,
​G,
​C,
​​ 
​(L≤w
​i
​​ ≤R)∧(w
​i
​​  mod 2=0)
​(L≤w
​i
​​ ≤R)∧(w
​i
​​  mod 2=1)
​((w
​i
​​ <L)∨(w
​i
​​ >R))∧(w
​i
​​  mod 2=0)
​((w
​i
​​ <L)∨(w
​i
​​ >R))∧(w
​i
​​  mod 2=1)
​​ 

其中 \land∧ 表示“且”关系，\lor∨ 表示“或”关系，a\ \mathrm{mod}\ ba mod b 表示 aa 除以 bb 的余数。

现给定另一个 DNA 碱基序列 tt，以及生成 ss 的参数 n , a , b , L , Rn,a,b,L,R，求 tt 在 ss 中出现了多少次。

输入格式

数据第一行为 55 个整数，分别代表 n , a , b , L , Rn,a,b,L,R。第二行为一个仅包含A、T、G、C的一个序列 tt。

数据保证 0 < a < n,0<a<n, 0 \le b < n,0≤b<n, 0 \le L \le R < n,0≤L≤R<n, |t| \le 10^{6}∣t∣≤10
​6
​​ ，a,na,n 互质。

对于简单版本，1 \leq n \leq 10^{6}1≤n≤10
​6
​​ ；

对于中等版本，1 \leq n \leq 10^{9}, a = 11≤n≤10
​9
​​ ,a=1；

对于困难版本，1 \leq n \leq 10^{9}1≤n≤10
​9
​​ 。

输出格式

输出一个整数，为 tt 在 ss 中出现的次数。

样例说明

对于第一组样例，生成的 ss 为TTTCGGAAAGGCC。

样例输入1

13 2 5 4 9
AGG
样例输出1

1
样例输入2

103 51 0 40 60
ACTG
样例输出2

5

 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        计蒜客_初赛_1_2.java
 * @type        计蒜客_初赛_1_2
 * @date        2017年5月20日 下午6:59:42
 * @details     
 */



public class 计蒜客_初赛_1_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        char[] t = sc.next().toCharArray();
        
        int tn = t.length;
        
        int breakIndex = (b+1)  % n;
        
        if (breakIndex < L) {
            if (tn == 1) {
                char LC = (L - breakIndex) % 2 == 0 ? 'A' : 'T';
                char RC = (R - breakIndex) % 2 == 0 ? 'A' : 'T';
                int A0 = L == R ? (LC == 'A' ? 1 : 0) : (LC == 'A' ? 1 : 0) + (RC == 'A' ? 1 : 0);
                int R0 = L == R ? 1 - A0 : 2 - A0;
                
                if (A0 > R0) {
                    
                } else if (A0 < R0 ) {
                    
                } else {
                    
                }
                
                int AC = (R - L + 1) / 2;
                
                int TC = AC;
                
            }
            
            
        } else if (breakIndex <= R) {
            
        } else {
            
        }
        
        if (tn == 0) {
            System.out.println(0);
        } else if (tn == 1) {
            int ans = 0;
            
            
        } else {
            int ans = 0;
            String k = new String(t);
            if (tn == 2 && k.equals("AT")) {
                
            } else if (tn == 2 && k.equals("TA")) {
                
            } else if (tn == 2 && k.equals("GC")) {
                
            } else if (tn == 2 && k.equals("CG")) {
                
            } else {

                char[] akkk = new char[3];
            }
            
        }
        
        
        
        
        
//        char[] s = new char[n];
//        int pw = 0, w = 0;
//        for (int i = 0; i < n; i ++) {
//            if (i == 0) {
//                w = b;
//                pw = w;
//            } else {
//                w = (pw + a) % n;
//                pw = w;
//            }
//            boolean bl = w < L;
//            boolean br = w > R;
//            boolean bw = w % 2 == 0;
//            if ((! bl) && (! br) ) {
//                if (bw) {
//                    s[i] = 'A';
//                } else {
//                    s[i] = 'T';
//                }
//            } else {
//                if (bw) {
//                    s[i] = 'G';
//                } else {
//                    s[i] = 'C';
//                }
//            }
//        }
        
//        System.out.println(new String(s));
//        System.out.println(t);
//        System.out.println(t.length);
//        System.out.println(kmp(s, t, n, t.length));
//        char[] uu = "ABCABC".toCharArray();
//        char[] vv = "ABCABCABCABCABCABCABCABC".toCharArray();
//        System.out.println(uu.length);
//        System.out.println(vv.length);
//        
//        System.out.println(kmp(vv, uu, vv.length, uu.length));
        
        
        sc.close();
    }
    
    
    
    
    private static int kmp(char[] s, char[] p, int sn, int pn) {
        if (pn == 0) return 0;
        if (sn == 0) return -1;
        int[] next = getNext(p, pn);
//        tools.Utils.printArray(next, next.length);
        int pi = 0, si = 0;
        int ans = 0;
        while (si < sn)
            if (s[si] == p[pi]) {
                si ++;
                pi ++;
                if (pi == pn) {
                    ans ++;
                    pi = next[pn - 1];
                    si --;
                }
            } else if (next[pi] == -1) {
                si ++;
            } else pi = next[pi];
        return ans;
    }
    private static int[] getNext(char[] p, int pn) {
        //return short p
        if (pn < 2) return new int[] {-1};
        int[] next = new int[pn];
        next[0] = -1;
        next[1] = 0;
        int fi = 2, bi = 0;
        while (fi < pn)
            if (p[fi - 1] == p[bi]) {
                next[fi ++] = ++ bi;
            } else if (bi <= 0) {
                next[fi ++] = 0;
            } else bi = next[bi];;
        return next;
    }
}
