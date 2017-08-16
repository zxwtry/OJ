package template;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Math_判断一个数是否是素数.java
 * @date        2017年8月16日 上午9:12:32
 * @details     
 */
public class RECITE_Math_素数相关 {
    public static void main(String[] args) {
        int val = 1000100;
        ArrayList<Integer> ans1 = getPrimeList(val);
        ArrayList<Integer> ans2 = getPrimeListBasic(val);
        System.out.println("ans1.size(): " + ans1.size() + "   ans2.size(): "
                + ans2.size());
        boolean isSame = ans1.size() == ans2.size();
        if (isSame) {
            for (int i = 0; i < ans1.size(); i++) {
                if (ans1.get(i).compareTo(ans2.get(i)) != 0) {
                    isSame = false;
                }
            }
        }
        System.out.println(isSame);
    }

    static boolean isPrime(int val) {
        if (val < 4) {
            return val > 1;
        }
        BitSet prime = new BitSet(val == Integer.MAX_VALUE ? val : val + 1);
        int size = val + 1;
        int step = 2;
        prime.set(2);
        while (true) {
            int i = step + step;
            for (; i < size; i += step) {
                if (i == val) {
                    return false;
                }
                prime.set(i);
            }
            i = step + 1;
            step = -1;
            for (; i < size; i++) {
                if (!prime.get(i)) {
                    step = i;
                    break;
                }
            }
            if (step == -1) {
                break;
            }
        }
        return true;
    }

    static boolean isPrimeBasic(int val) {
        int start = 2, end = (int) Math
                .sqrt(val == Integer.MAX_VALUE ? Integer.MAX_VALUE : val + 1);
        for (int i = start; i <= end; i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }

    static ArrayList<Integer> getPrimeList(int val) {
        if (val < 2) {
            return new ArrayList<>(0);
        }
        BitSet prime = new BitSet(val == Integer.MAX_VALUE ? val : val + 1);
        int step = 2, end = (int) Math.sqrt(prime.size());
        int listSize = val - 2 + 1;
        while (true) {
            int i = step * step;
            for (; i <= val; i += step) {
                prime.set(i);
            }
            i = step + 1;
            step = -1;
            for (; i <= end; i++) {
                if (! prime.get(i)) {
                    step = i;
                    break;
                }
            }
            if (step == -1) {
                break;
            }
        }
        listSize -= prime.cardinality();
        ArrayList<Integer> ans = new ArrayList<>(listSize);
        for (int i = 2; i <= val; i++) {
            if (!prime.get(i)) {
                ans.add(i);
            }
        }
        System.out.println("listSize: " + listSize + "   ans.size(): "
                + ans.size());
        return ans;
    }

    static ArrayList<Integer> getPrimeListBasic(int val) {
        if (val < 2) {
            return new ArrayList<>(0);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= val; i++) {
            int start = 2, end = (int) Math
                    .sqrt(i == Integer.MAX_VALUE ? Integer.MAX_VALUE : i + 1);
            int j = start;
            for (; j <= end; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j == end + 1) {
                list.add(i);
            }
        }
        return list;
    }
}
