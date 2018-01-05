package nowcoder.com;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        华为_扑克牌大小.java
 * @date        2017年7月7日 下午8:14:43
 * @details     
 */
public class 华为_扑克牌大小 {
    //3 4 5 6 7 8 9 10 J Q K A 2
    static HashMap<Character, Integer> m = new HashMap<>();
    
    static char[] cs = {'3','4','5','6','7','8','9','1','J','Q','K','A','2'};
    
    static {
        for (int i = 0; i < cs.length; i ++) {
            m.put(cs[i], i);
        }
    }
    
    private static final int DAN = -3;
    private static final int DUI = -4;
    private static final int SAN = -5;
    private static final int SUN = -6;
    private static final int ERR = -7;
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int dashIndex = line.indexOf('-');
        String left = line.substring(0, dashIndex);
        String right = line.substring(dashIndex + 1);
        int lb = isBoom(left);
        int rb = isBoom(right);
        if (lb >= 0 || rb >= 0) {
            System.out.println( lb > rb ? left : right );
        } else if (lb != rb) {
            System.out.println("ERROR");
        } else {
            System.out.println( m.get(left.charAt(0)) > m.get(right.charAt(0)) ?
                    left : right);
        }
        sc.close();
    }
    static int isBoom(String s) {
        int sn = s.length();
        if (sn == 11 && (s.charAt(0) == 'j' || s.charAt(0) == 'J')) {
            return 20;
        }
        if (sn == 7 || sn == 11) {
            char c = s.charAt(0);
            return m.get(c);
        }
        if (sn == 9 || sn == 10) {
            return SUN;
        }
        if (sn == 1 || sn == 2) {
            return DAN;
        }
        if (sn == 5) {
            return s.charAt(2) == ' ' ? DUI : SAN;
        }
        if (sn == 3) {
            return DUI;
        }
        if (sn == 8) {
            return SAN;
        }
        return ERR;
    }
}
