package leetcode;

/**

Design and implement a data structure for a compressed string iterator. 
It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed 
by a positive integer representing the number of this letter existing 
in the original uncompressed string.

next() - if the original string still has uncompressed characters, 
return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, 
as static/class variables are persisted across multiple test cases. 
Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P604_DesignCompressedStringIterator.java
 * @date        2017年6月13日 下午7:35:50
 * @details     Solution: AC 201ms 5.43%
 */
public class P604_DesignCompressedStringIterator {
    
    public static void main(String[] args) {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
        
        char c = '\0';
        boolean b = false;
        
        c = iterator.next(); // return 'L'
        System.out.println(c);
        c = iterator.next(); // return 'e'
        System.out.println(c);
        c = iterator.next(); // return 'e'
        System.out.println(c);
        c = iterator.next(); // return 't'
        System.out.println(c);
        c = iterator.next(); // return 'C'
        System.out.println(c);
        c = iterator.next(); // return 'o'
        System.out.println(c);
        c = iterator.next(); // return 'd'
        System.out.println(c);
        b = iterator.hasNext(); // return true
        System.out.println(b);
        c = iterator.next(); // return 'e'
        System.out.println(c);
        b = iterator.hasNext(); // return false
        System.out.println(b);
        c = iterator.next(); // return ' '
        System.out.println(c);
    }
    
    static public class StringIterator {
        String s;
        int sl;
        int i;
        int cnt;
        char c;
        public StringIterator(String compressedString) {
            this.s = compressedString;
            this.sl = s == null ? 0 : s.length();
            this.i = 0;
            this.cnt = 0;
            this.c = ' ';
        }
        
        public char next() {
            if (cnt != 0) {
                cnt --;
                return c;
            }
            if (i >= sl) return ' ';
            c = s.charAt(i);
            cnt = 0;
            if (i + 1 >= sl) return ' ';
            while (i+1 < sl) {
                char tc = s.charAt(i+1);
                if (tc >= '0' && tc <= '9') {
                    cnt = cnt * 10 + (tc - '0');
                    i ++;
                } else {
                    i ++;
                    break;
                }
            }
            return next();
        }
        
        public boolean hasNext() {
            return ! ((i+ 1 == sl) && cnt == 0);
        }
    }
}
