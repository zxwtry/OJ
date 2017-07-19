package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        其它_最小编辑代价.java
 * @date        2017年7月19日 下午10:02:31
 * @details     
 */
public class 其它_最小编辑代价 {
    public static void main(String[] args) {
        //"abcccbcb",8,"bbb",3,7,4,2
        String A = "abcccbcb";
        String B = "bbb";
        int n = 8;
        int m = 3;
        int c0 = 7;
        int c1 = 4;
        int c2 = 2;
        System.out.println(new MinCost().findMinCost(A, n, B, m, c0, c1, c2));
    }
    static public class MinCost {
        public int findMinCost(String A, int n, String B, int m, int c0, int c1, int c2) {
            int[][] map = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i ++)
                map[i][0] = map[i - 1][0] + c1;
            for (int j = 1; j <= m; j ++)
                map[0][j] = map[0][j - 1] + c0;
            for (int i = 1; i <= n; i ++) {
                char a = A.charAt(i - 1);
                for (int j = 1; j <= m; j ++) {
                    char b = B.charAt(j - 1);
                    if (a == b) {
                        map[i][j] = Math.min(Math.min(map[i-1][j] + c1, map[i][j-1] + c0) , map[i-1][j-1]);
                    } else {
                        map[i][j] = Math.min(Math.min(map[i-1][j] + c1, map[i][j-1] + c0) , map[i-1][j-1] + c2);
                    }
                }
            }
            tools.Utils.A_打印二维数组(map);
            return map[n][m];
        }
    }
}
