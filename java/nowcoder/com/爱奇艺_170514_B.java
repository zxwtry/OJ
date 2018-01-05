package nowcoder.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        爱奇艺_170514_B.java
 * @type        爱奇艺_170514_B
 * @date        2017年5月14日 下午10:30:02
 * @details     AC
 */
public class 爱奇艺_170514_B {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        ArrayList<Integer> l = new ArrayList<>();
        int sum = 0;
        int sum2 = 0;
        while (in.hasNextInt()) {//注意while处理多个case
            int v = in.nextInt();
            if (v > 1) {
            l.add(v);
            sum2 += v;
            }
            sum += v;
        }

        in.close();
        if ( k < 1 || sum < 2 * k)  {
            System.out.println(sum / k);
            return;
        }
        Collections.sort(l, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
            
        });
        
        int maxLen = sum2 / k;
        int minLen = 2;
        
        //在提交过程中，不知道哪个测试用例maxLen不能通过
        //添加如下就好了
        if (j(l, k, maxLen)) {
            System.out.println(maxLen);
            return;
        }
        
        while (maxLen > minLen) {
            int m = (maxLen + minLen + 1) / 2;
            if (j(l, k, m)) {
                minLen = m;
            } else {
                maxLen = m - 1;
            }
        }
        System.out.println(minLen);
    }
    
    static boolean  j(ArrayList<Integer> l, int k, int len) {
        int count = 0;
        for (int v : l) {
            if (v < len) break;
            count += v / len;
            if (count >= k) return true;
        }
        return false;
    }
    
}
