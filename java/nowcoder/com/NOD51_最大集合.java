package nowcoder.com;

import java.util.Arrays;
import java.util.HashSet;


/**
    描述
    给定一个1-N的排列A[1], A[2], ... A[N]，定义集合S[K] = {A[K], A[A[K]], A[A[A[K]]] ... }。  
    
    显然对于任意的K=1..N，S[K]都是有限集合。  
    
    你能求出其中包含整数最多的S[K]的大小吗？
    
    输入
    第一行包含一个整数N。(1 <= N <= 100000)  
    
    第二行包含N个两两不同的整数，A[1], A[2], ... A[N]。(1 <= A[i] <= N)
    
    输出
    最大的S[K]的大小。
    
    样例输入
    7  
    6 5 1 4 2 7 3
    样例输出
    4
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        NOD51_最大集合.java
 * @type        NOD51_最大集合
 * @date        2017年5月7日 下午12:45:31
 * @details     solve: TLE solve2: AC
 */
public class NOD51_最大集合 {
    static void solve(int n, int[] a) {
        int[] t = new int[n+1];
        boolean[] m = new boolean[n+1];
        int ans = 0;
        for (int i = 1; i <= n; i ++) {
            t[0] = a[i];
            Arrays.fill(m, false);
            m[t[0]] = true;
            for (int j = 1; j <= n; j ++) {
                t[j] = a[t[j-1]];
                if (m[t[j]]) {
                    ans = Math.max(ans, j);
                    break;
                }
            }
        }
        System.out.println(ans);
    }
    static void solve2(int n, int[] a) {
        HashSet<Integer> nu = new HashSet<Integer>();
        for (int i = 1; i <= n; i ++) nu.add(i);
        int ans = 0;
        while (! nu.isEmpty()) {
            int t = 0;
            int now = nu.iterator().next();
            while (true) {
                nu.remove(now);
                t ++;
                now = a[now];
                if (! nu.contains(now)) break;                
            }
            ans = Math.max(t, ans);
        }
        System.out.println(ans);
    }
}
