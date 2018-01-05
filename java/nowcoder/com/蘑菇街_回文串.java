package nowcoder.com;

import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        蘑菇街_回文串.java
 * @date        2017年7月11日 下午10:27:15
 * @details     
 */
public class 蘑菇街_回文串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            sovle(sc);
        }
        sc.close();
    }

    static final String YES = "YES";
    static final String NO = "NO";
    
    private static void sovle(Scanner sc) {
        String s = sc.next();
        int i = 0, j = s.length() - 1;
        boolean isP = is(s, i, j);
        if (isP) {
            System.out.println(YES);
        } else {
            i = 0;
            j = s.length() - 1;
            while (true) {
                if (s.charAt(i) == s.charAt(j)) {
                    i ++;
                    j --;
                } else {
                    break;
                }
            }
            System.out.println(is(s, i + 1, j) || is(s, i, j - 1) ? YES : NO);
        }
    }
    static boolean is(String s, int i, int j) {
        boolean isP = true;
        while (i < j) {
            isP &= s.charAt(i) == s.charAt(j);
            i ++;
            j --;
        }
        return isP;
    }
}
