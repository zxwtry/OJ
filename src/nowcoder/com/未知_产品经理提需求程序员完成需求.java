package nowcoder.com;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        未知_产品经理提需求程序员完成需求.java
 * @date        2017年8月22日 下午11:44:49
 * @details     n是程序员数目
 * @details     m是产品经理数目
 * @details     p是总需求数
 * @details     接下来p行需求，每行 产品经理ID，开始时间，优先级，需要时间
 * @details     输出p行，每行是该需求完成时间。
 * @details     提交之后，TLE，通过20%
 */
public class 未知_产品经理提需求程序员完成需求 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                solve(sc);
            } catch (Throwable e) {
            }
        }
        sc.close();
    }

    static final int MAX = 4100;
    @SuppressWarnings("unchecked")
    static PriorityQueue<int[]>[] notyet = new PriorityQueue[MAX];
    @SuppressWarnings("unchecked")
    static PriorityQueue<int[]>[] ready = new PriorityQueue[MAX];
    static int[] times = new int[MAX];
    static int[][] vv = new int[MAX][4];
   
    static class MC implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            int cmp = Integer.compare(o1[1], o2[1]);
            if (cmp != 0) {
                return cmp;
            }
            cmp = Integer.compare(o1[2], o2[2]);
            if (cmp != 0) {
                return cmp;
            }
            cmp = Integer.compare(o1[0], o2[0]);
            if (cmp != 0) {
                return cmp;
            }
            return 0;
        }
        
    }
    
    static class MC2 implements Comparator<int[]> {

        @Override
        public int compare(int[] o1, int[] o2) {
            int cmp = Integer.compare(o1[0], o2[0]);
            if (cmp != 0) {
                return cmp;
            }
            return 0;
        }
        
    }
    
    private static void solve(Scanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();
        int p = sc.nextInt();
        
        for (int i = 0; i <= m; i ++) {
            if (notyet[i] == null) {
                notyet[i] = new PriorityQueue<>(MAX, new MC2());
            } else {
                notyet[i].clear();
            }
            if (ready[i] == null) {
                ready[i] = new PriorityQueue<>(MAX, new MC());
            } else {
                ready[i].clear();
            }
        }
        
        for (int i = 0; i < p; i ++) {
            int pindex = sc.nextInt();
            PriorityQueue<int[]> pn = notyet[pindex];
            int nvv[] = vv[i];
            nvv[0] = sc.nextInt();
            nvv[1] = sc.nextInt();
            nvv[2] = sc.nextInt();
            nvv[3] = i;
            pn.add(nvv);
        }
        
        PriorityQueue<Integer> cc = new PriorityQueue<>();
        for (int i = 0; i < n; i ++) {
            cc.add(0);
        }
        
        int pc = 0;
        while (pc < p) {
            int time = cc.poll();
            for (int i = 1; i <= m; i ++) {
                PriorityQueue<int[]> pn = notyet[i];
                PriorityQueue<int[]> dn = ready[i];
                while (! pn.isEmpty()) {
                    if (pn.peek()[0] <= time) {
                        dn.add(pn.poll());
                    } else {
                        break;
                    }
                }
            }
            int selectIndex = 0;
            int needTime = Integer.MAX_VALUE;
            for (int i = 1; i <= m; i ++) {
                PriorityQueue<int[]> dn = ready[i];
                if (dn.isEmpty()) {
                    continue;
                }
                if (dn.peek()[2] < needTime) {
                    selectIndex = i;
                    needTime = dn.peek()[2];
                }
            }
            if (selectIndex == 0) {
                time ++;
            } else {
                PriorityQueue<int[]> dn = ready[selectIndex];
                int[] val = dn.poll();
                time += val[2];
                times[val[3]] = time;
                pc ++;
            }
            cc.add(time);
        }
        for (int i = 0; i < p; i ++) {
            System.out.println(times[i]);
        }
        
    }
}
