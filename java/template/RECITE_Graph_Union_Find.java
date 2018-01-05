package template;

import java.util.Arrays;

/**
 *  数据结构与算法分析 Java语言描述 (原书第3版) Mark Allen Weiss
 *  第8章 不相交集类
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_Union_Find.java
 * @type        RECITE_Graph_Union_Find
 * @date        2017年4月23日 下午8:47:10
 * @details     
 */
public class RECITE_Graph_Union_Find {
    public static void main(String[] args) {
        DisjSets set = new DisjSets(8);
        set.union(4, 5);
        set.union(6, 7);
        set.union(4, 6);
        for (int i = 0; i < 8; i ++)
            System.out.println(i + "..." + set.find(i));
    }
    static class DisjSets {
        private int[] r = null;
        public DisjSets(int numOfElements) {
            r = new int[numOfElements];
            Arrays.fill(r, -1);
        }
        public void union(int root1, int root2) {
            r[root2] = root1;
        }
        public int find(int x) {
            while (r[x] >= 0)
                x = r[x];
            return x;
        }
    }
}
