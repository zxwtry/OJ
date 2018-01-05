package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        去哪儿_乘坐公交.java
 * @date        2017年7月14日 下午9:40:44
 * @details     
 */
public class 去哪儿_乘坐公交 {
    //[13,15,26,7,27,3,30],[1,2,1,2,2,2,1],[5,1,4,3,2,1,4],7,10
    public static void main(String[] args) {
        int[] stops = {13,15,26,7,27,3,30};
        int[] period = {1,2,1,2,2,2,1};
        int[] interval = {5,1,4,3,2,1,4};
        int n = 7;
        int s = 10;
        System.out.println(new TakeBuses().chooseLine(stops, period, interval, n, s));
    }
    static public class TakeBuses {
        public int chooseLine(int[] stops, int[] period, 
                int[] interval, int n, int s) {
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < n; i ++) {
                int costTime = stops[i] * period[i] + 5 * (stops[i] + 1);
                int waitTime = 0;
                if (s % interval[i] == 0) {
                    waitTime = s;
                } else {
                    waitTime = ( s / interval[i] + 1) * interval[i];
                }
                System.out.println(costTime + waitTime);
                ans = Math.min(ans, costTime + waitTime);
            }
            return ans;
        }
    }
}
