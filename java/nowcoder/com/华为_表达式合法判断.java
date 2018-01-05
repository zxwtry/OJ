package nowcoder.com;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_表达式合法判断.java
 * @date        2017年7月14日 下午9:13:50
 * @details     
 */
public class 华为_表达式合法判断 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (solve(sc)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
        sc.close();
    }

    static final char[] ls = {'(', '[', '{'};
    static final char[] rs = {')', ']', '}'};
    private static boolean solve(Scanner sc) {
        //[a+b*(5-4)]*{x+b+b*(({1+2)}}
        String l = sc.next();
        Stack<Integer> q = new Stack<>();
        int ln = l.length();
        for (int i = 0; i < ln; i ++) {
            char c = l.charAt(i);
            int j = 0;
            for (; j < ls.length; j ++) {
                if (ls[j] == c) {
                    break;
                }
            }
            if (j != ls.length) {
                //处理左边
                q.push(j);
            } else {
                j = 0;
                for (; j < rs.length; j ++) {
                    if (rs[j] == c) {
                        break;
                    }
                }
                if (j != rs.length) {
                    if (q.size() == 0) {
                        return false;
                    }
                    int k = q.pop();
                    if (k != j) {
                        return false;
                    }
                }
            }
        }
        return q.size() == 0;
    }
}
