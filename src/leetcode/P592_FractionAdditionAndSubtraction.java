package leetcode;

import java.util.ArrayList;

/**

Given a string representing an expression of fraction addition and subtraction, 
you need to return the calculation result in string format. The final result 
should be irreducible fraction. If your final result is an integer, say 2, 
you need to change it to the format of fraction that has denominator 1. 
So in this case, 2 should be converted to 2/1.

Example 1:
Input:"-1/2+1/2"
Output: "0/1"
Example 2:
Input:"-1/2+1/2+1/3"
Output: "1/3"
Example 3:
Input:"1/3-1/2"
Output: "-1/6"
Example 4:
Input:"5/3+1/3"
Output: "2/1"
Note:
The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
Each fraction (input and output) has format ±numerator/denominator. If the first input 
fraction or the output is positive, then '+' will be omitted.

The input only contains valid irreducible fractions, where the numerator and denominator 
of each fraction will always be in the range [1,10]. If the denominator is 1, it means
 this fraction is actually an integer in a fraction format defined above.
 
The number of given fractions will be in the range [1,10].
The numerator and denominator of the final result are guaranteed to be valid and in the 
range of 32-bit int.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P592_FractionAdditionAndSubtraction.java
 * @type        P592_FractionAdditionAndSubtraction
 * @date        2017年5月21日 上午10:31:37
 * @details     Solution: AC
 */
public class P592_FractionAdditionAndSubtraction {
    public static void main(String[] args) {
        String e = "-1/2-1/2-1/3";
        e = "-6/1";
        System.out.println(new Solution().fractionAddition(e));
//        System.out.println(new Solution().minMultiple(-6, 8));
    }
    static public class Solution {
        public String fractionAddition(String e) {
            return plus(e);
        }
        
        public String plus(String e) {
            if (e.length() == 0) {
                return "0/1";
            }
            ArrayList<String> es = new ArrayList<>();
            ArrayList<Boolean> b = new ArrayList<>();
            int p = (e.charAt(0) == '-' || e.charAt(0) == '+') ? 1 : 0;
            if (e.charAt(0) == '-' || e.charAt(0) == '+') {
                es.add("");
                b.add(e.charAt(0) == '+');
            }
            for (int i = (e.charAt(0) == '-' || e.charAt(0) == '+') ? 1 : 0; i <= e.length(); i ++) {
                char c = i == e.length() ? '+' : e.charAt(i);
                if (c == '-' || c == '+') {
                    es.add(e.substring(p, i));
                    p = i + 1;
                    b.add(c == '+');
                }
            }
            
//            System.out.println(es);
//            System.out.println(b);
            int[][] m = new int[es.size()][];
            for (int i = 0; i < es.size(); i ++) {
                String[] ps = es.get(i).split("/");
                if (ps.length != 2) {
                    m[i] = new int[] {0, 1};
                } else {
                    m[i] = new int[] {Integer.parseInt(ps[0]), Integer.parseInt(ps[1])};
                }
            }
            long minM = 1;
            for (int i = 0; i < es.size(); i ++) {
                minM = minMultiple(minM, m[i][1]);
            }
            long calc = 0;
            for (int i = 0; i < es.size(); i ++) {
                if (i == 0 || b.get(i-1)) {
                    calc += (minM* m[i][0]) / m[i][1];
                } else {
                    calc -= (minM* m[i][0]) / m[i][1];
                }
//                System.out.println(i + "..." + calc);
            }
            long gys = gcd(Math.abs(minM), Math.abs(calc));
            return String.valueOf((int)(calc / gys)) + "/"+ String.valueOf( (int)(minM / gys));
        }
        
        long minMultiple(long a, long b) {
            a = Math.abs(a);
            b = Math.abs(b);
            if (a < b) return minMultiple(b, a);
            
            long r = a, s = a, t = b;
            while (r != 0) {
                r = a % b;
                a = b;
                b = r;
            }
            return s * t / a;
        }
        public long gcd(long a, long b) {
            a = Math.abs(a);
            b = Math.abs(b);
            if (a > b) return gcd(b, a);
            while(b != 0) {
                 long temp = a%b;
                 a = b;
                 b = temp;
            }
            return a;
        }
    }
}
