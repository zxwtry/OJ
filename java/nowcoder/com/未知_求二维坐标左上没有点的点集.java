package nowcoder.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        未知_求二维坐标左上没有点的点集.java
 * @date        2017年8月22日 下午11:20:13
 * @details     输入是一个点集
 * @details     需要求的是这样的点：
 * @details         没有任何一个点能够满足：x > x0 && y > y0
 */
public class 未知_求二维坐标左上没有点的点集 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                if (! solve(br)) {
                    break;
                }
            } catch (Throwable e) {
                break;
            }
        }
        br.close();
    }

    final static int N = 600500;
    final static int[][] ps = new int[N][2];
    final static StringBuilder st = new StringBuilder();
    
    private static boolean solve(BufferedReader br) throws Exception {
        String l = br.readLine().trim();
        if (l == null) {
            return false;
        }
        int n = Integer.parseInt(l);
        for (int i = 0; i < n; i ++) {
            String line = br.readLine();
            if (line == null) {
                return false;
            }
            int a = 0;
            int b = 0;
            int k = 0;
            for (int len = line.length(); k < len; k ++) {
                char c = line.charAt(k);
                if (c == ' ') {
                    break;
                }
                a = a * 10 + c - '0';
            }
            k ++;
            for (int len = line.length(); k < len; k ++) {
                char c = line.charAt(k);
                if (c == ' ') {
                    break;
                }
                b = b * 10 + c - '0';
            }
            
            ps[i][0] = a;
            ps[i][1] = b;
        }
        Arrays.sort(ps, 0, n, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int cmp = Integer.compare(o1[0], o2[0]);
                if (cmp != 0) {
                    return - cmp;        //远的在前
                }
                cmp = Integer.compare(o1[1], o2[1]);
                return - cmp;
            }
        });
        int ansIndex = 1;
        int maxY = ps[0][1];
        for (int i = 1; i < n; i ++) {
            if (ps[i][0] == ps[i - 1][0]) {
                continue;
            } else {
                if (maxY <= ps[i][1]) {
                    ps[ansIndex][1] = ps[i][1];
                    ps[ansIndex][0] = ps[i][0];
                    ansIndex ++;
                    maxY = ps[i][1];
                }
            }
        }
        for (int i = ansIndex - 1; i > -1; i --) {
            st.append(ps[i][0]);
            st.append(" ");
            st.append(ps[i][1]);
            st.append("\n");
        }
        System.out.println(st.toString());
        return true;
    }
    
}
