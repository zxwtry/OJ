package hiho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     hiho
 * @file        N017_最近公共祖先三.java
 * @date        2017年7月27日 下午9:39:36
 * @details     
 */
public class N017_最近公共祖先三 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            solve(sc);
        }
        sc.close();
    }

    private static void solve(Scanner sc) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> nms = new ArrayList<>();
        int n = sc.nextInt();
        int[] g = new int[2 * n];
        int k = 0;
        String s1 = null, s2 = null;
        for (int i = 0; i < n; i ++) {
            s1 = sc.next();
            s2 = sc.next();
            if (! map.containsKey(s1)) {
                map.put(s1, k ++);
                nms.add(s1);
            }
            if (! map.containsKey(s2)) {
                map.put(s2, k ++);
                nms.add(s2);
            }
            g[map.get(s2)] = map.get(s1);
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i ++) {
            s1 = sc.next();
            s2 = sc.next();
            Stack<Integer> stk1 = new Stack<>();
            Stack<Integer> stk2 = new Stack<>();
            for (int j = map.get(s1); j != 0; j = g[j]) {
                stk1.add(j);
            }
            for (int j = map.get(s2); j != 0; j = g[j]) {
                stk2.add(j);
            }
            int res = 0;
            while (! stk1.isEmpty() && ! stk2.isEmpty() && 
                    stk1.peek().intValue() == stk2.peek().intValue()) {
                res = stk1.peek();
                stk1.pop();
                stk2.pop();
            }
            System.out.println(nms.get(res));
        }
    }
}
