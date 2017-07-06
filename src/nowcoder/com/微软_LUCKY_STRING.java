package nowcoder.com;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        微软_LUCKY_STRING.java
 * @date        2017年7月6日 下午10:05:17
 * @details     
 */
public class 微软_LUCKY_STRING {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int sn = s.length();
        int[][] m = new int[sn][26];
        for (int i = 0; i < sn; i ++) {
            int j = s.charAt(i) - 'a';
            for (int k = 0; k < 26; k ++) {
                if (k == j) {
                    if (i == 0) {
                        m[i][k] = 1;
                    } else {
                        m[i][k] = 1 + m[i-1][k];
                    }
                } else {
                    if (i != 0) {
                        m[i][k] = m[i-1][k];
                    }
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        int pv = 0, nv = 1, xv = 0;
        set.add(0);
        set.add(1);
        while (nv <= 26) {
            xv = pv + nv;
            set.add(xv);
            pv = nv;
            nv = xv;
        }
        System.out.println(set);
        TreeSet<String> ans = new TreeSet<>();
        for (int i = 0; i < sn; i ++) {
            int[] im = m[i];
            int cnt = 0;
            
            for (int j = i + 1; j < sn; j ++) {
                
                int[] jm = m[j];
                cnt = 0;
                for (int t = 0; t < 26; t ++) {
                    cnt += (jm[t] == im[t] ? 0 : 1);
                }
                if (set.contains(cnt)) {
                    ans.add(s.substring(i + 1, j));
                }
            }
        }
        for (String a : ans) {
            System.out.println(a);
        }
        sc.close();
    }
}
