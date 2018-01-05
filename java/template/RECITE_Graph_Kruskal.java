package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  数据结构与算法分析 Java语言描述 (原书第3版) Mark Allen Weiss
 *  第9章  图论算法  Kruskal算法  P269
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Graph_Kruskal.java
 * @type        RECITE_Graph_Kruskal
 * @date        2017年4月24日 下午3:39:04
 * @details     
 */
public class RECITE_Graph_Kruskal {
    
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        int[][] arr = {
                {0, 1, 2},
                {0, 2, 4},
                {0, 3, 1},
                {1, 3, 3},
                {1, 4, 10},
                {2, 3, 2},
                {2, 5, 5},
                {3, 5, 8},
                {3, 4, 7},
                {3, 6, 4},
                {4, 6, 6},
                {5, 6, 1},
        };
        List<Edge> edges = new ArrayList<Edge>(arr.length);
        for (int[] a : arr) {
            edges.add(new Edge(a[0], a[1], a[2]));
        }
        int numVertices = 7;
        List<Edge> ans = kruskal(edges, numVertices);
        System.out.println(ans);
    }
    
    public static List<Edge> kruskal(List<Edge> edges, int numVertices) {
        DisjSets set = new DisjSets(numVertices);
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
        List<Edge> mst = new ArrayList<Edge>();
        while (mst.size() != numVertices - 1) {
            Edge e = pq.poll();
            int u = set.find(e.u);
            int v = set.find(e.v);
            if (u != v) {
                mst.add(e);
                set.union(u, v);
            }
        }
        return mst;
    }
    
    static class Edge implements Comparable<Edge> {
        public int u, v, dist;
        public Edge(int u, int v, int dist) {
            this.u = Math.min(u, v);
            this.v = Math.max(u, v);
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
        @Override
        public String toString() {
            return String.format("u:%d v:%d d:%d", u, v, dist);
        }
    }
    
    static class DisjSets {
        private int[] arr = null;
        public DisjSets(int numOfElements) {
            super();
            arr = new int[numOfElements];
            Arrays.fill(arr, -1);
        }
        public void union(int root1, int root2) {
            arr[root2] = root1;
        }
        public int find(int x) {
            while (arr[x] >= 0)
                x = arr[x];
            return x;
        }
    }
}
