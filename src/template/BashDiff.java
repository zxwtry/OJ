package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BashDiff {
    
    public static void main(String[] args) {
        List<String> ls = Arrays.asList(
                "aaaaaaaaaaaaaaaa", 
                "bbbbbbbbbbbbbbbb", 
                "dddddddddddddddd",
                "ffffffffffffffff"
                );
        List<String> rs = Arrays.asList(
                "aaaaaaaaaaaaaaaa", 
                "bbbbbbbbbbbbbbbb", 
                "cccccccccccccccc", 
                "dddddddddddddddd", 
                "eeeeeeeeeeeeeeee"
                );
        List<Integer> ans = diff(ls, rs);
        for (int i = 0; i < ans.size(); i += 2) {
            int v1 = ans.get(i);
            int v2 = ans.get(i+1);
            int line = v1 < 0 ? -(v1+1) : v1;
            String sgn = v1 < 0 ? "remove ls" : "insert rs";
            String str = v1 < 0 ? ls.get(-(v1+1)) : rs.get(v1);
            System.out.printf("pos:%03d %-8s[%03d]  %s\n", v2, sgn, line, str);
        }
    }
    
    public static List<Integer> diff(List<String> ls, List<String> rs) {
        int ln = ls == null ? 0 : ls.size();
        int rn = rs == null ? 0 : rs.size();
        int li = 0, ri = 0;
        int[][] dp = new int[ln+1][rn+1];
        for (li = ln-1; li > -1; li --)
            for (ri = rn-1; ri > -1; ri --)
                if (ls.get(li).equals(rs.get(ri)))
                    dp[li][ri] = dp[li+1][ri+1] + 1;
                else dp[li][ri] = Math.max(dp[li+1][ri], dp[li][ri+1]);
        li = 0;
        ri = 0;
        List<Integer> ans = new ArrayList<Integer>();
        int ai = 0;
        while (li < ln && ri < rn) {
            if (ls.get(li).equals(rs.get(ri))) {
                li ++;
                ri ++;
                ai ++;
            } else if (dp[li][ri+1] <= dp[li+1][ri]) {
                ans.add(-li-1);
                ans.add(ai);
                li ++;
            } else {
                ans.add(ri);
                ans.add(ai);
                ri ++;
                ai ++;
            }
        }
        if (ri == rn) {
            while (li < ln) {
                ans.add(-li-1);
                ans.add(ai);
                li ++;
            }
        } else {
            while (ri < rn) {
                ans.add(ri);
                ans.add(ai);
                ri ++;
                ai ++;
            }
        }
        return ans;
    }
}
