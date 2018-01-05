package poj10x;

/**
 *  
 *  Language:
 *  Exponentiation
 *  Time Limit: 500MS       Memory Limit: 10000K
 *  Total Submissions: 165462       Accepted: 40173
 *  Description
 *  
 *  Problems involving the computation of exact values of very large magnitude and precision
 *  are common. For example, the computation of the national debt is a taxing experience for
 *  many computer systems. 
 *  
 *  This problem requires that you write a program to compute the exact value of Rn where R is
 *   a real number ( 0.0 < R < 99.999 ) and n is an integer such that 0 < n <= 25.
 *  Input
 *  
 *  The input will consist of a set of pairs of values for R and n. The R value will occupy 
 *  columns 1 through 6, and the n value will be in columns 8 and 9.
 *  Output
 *  
 *  The output will consist of one line for each line of input giving the exact value of R^n. 
 *  Leading zeros should be suppressed in the output. Insignificant trailing zeros must not be printed.
 *   Don't print the decimal point if the result is an integer.
 *  Sample Input
 *  
 *  95.123 12
 *  0.4321 20
 *  5.1234 15
 *  6.7592  9
 *  98.999 10
 *  1.0100 12
 *  Sample Output
 *  
 *  548815620517731830194541.899025343415715973535967221869852721
 *  .00000005148554641076956121994511276767154838481760200726351203835429763013462401
 *  43992025569.928573701266488041146654993318703707511666295476720493953024
 *  29448126.764121021618164430206909037173276672
 *  90429072743629540498.107596019456651774561044010001
 *  1.126825030131969720661201
 *  Hint
 *  
 *  If you don't know how to determine wheather encounted the end of input: 
 *  s is a string and n is an integer 
 */


import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     poj10x
 * @file        P1001.java
 * @type        P1001
 * @date        2017年2月19日 下午8:46:36
 * @details     solve1: AC 3364K 141MS
 */
public class P1001 {
    public static void main(String[] args) {
        solve1();
    }
    static void solve1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            BigDecimal bigDecimal = scanner.nextBigDecimal();
            bigDecimal = bigDecimal.pow(scanner.nextInt());
            String string = bigDecimal.toPlainString();
            
            if (string.length() > 2 && string.charAt(0) == '0' && string.charAt(1) == '.')
                string = string.substring(1);
            if (string.indexOf('.') != -1) {
                int lastZeroIndex = string.length() - 1;
                while (lastZeroIndex > -1) {
                    if (string.charAt(lastZeroIndex) == '0')
                        lastZeroIndex --;
                    else break;
                }
                string = string.substring(0, lastZeroIndex + 1);
            }
            if (string.length() > 0 && string.charAt(string.length() - 1) == '.')
                string = string.substring(0, string.length() - 1);
            if (string.length() == 0)
                string = "0";
            System.out.println(string);
        }
        scanner.close();
    }
}
