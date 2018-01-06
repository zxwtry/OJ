#pragma warning(disable:4786)
/*
    url: leetcode.com/problems/palindrome-partitioning-ii
    Solution1: TLE
    Solution2: AC 6ms 76.37%
*/

#include <iostream>
#include <vector>
#include <map>
#include <string>
#include <stdio.h>

using namespace std;


class Solution1 {
public:
    int _min(int a, int b) {
        return a < b ? a : b;
    }
    int convert(int a, int b) {
        return a * 100000 + b;
    }
    int isP(string& s, int i, int j) {
        while (i < j) {
            if (s[i] != s[j]) return 0;
            i ++;
            j --;
        }
        return 1;
    }
    int minCut(string s) {
        int sn = s.size(), ans = INT_MAX;
        int ** dp = new int* [sn];
        int ** sa = new int* [sn];
        for (int dpi = 0; dpi < sn; dpi ++) {
            dp[dpi] = new int[sn];
            sa[dpi] = new int[sn];
        }
        for (int len = 1; len <= sn; len ++) {
            for (int i = 0; i <= sn - len; i ++) {
                if (s[i] == s[i+len-1] && (len < 3 || (dp[i+1][i+len-2]) == 1988)) {
                    dp[i][i+len-1] = 1988;
                    sa[i][i+len-1] = 1;
                } else {
                    int min_val = INT_MAX;
                    for (int klen = 1; klen < len; klen ++) {
                        min_val = _min(sa[i][i+klen-1] + sa[i+klen][i+len-1], min_val);
                    }
                    sa[i][i+len-1] = min_val;
                }
                
            }
        }
        return sa[0][sn-1]-1;
    }
};

class Solution2 {
public:
    char access(string s, int i) {
        return i%2 == 0 ? '#' : s[i/2];
    }
    int _max(int a, int b) {
        return a < b ? b : a;
    }
    int _min(int a, int b) {
        return a < b ? a : b;
    }
    int* manacher(string& s, int& sn) {
        int nn = 2 * sn + 1;
        int* m = new int[nn];
        int mi = 0, li = 0, ti = 0, left = 0, right = 0;
        for (int i = 0; i < nn; i ++) {
            mi = 2 * li - i;
            if (i >= ti || m[mi] + i >= ti) {
                left = i;
                right = i;
                while (left > 0 && right < nn-1 && access(s, left-1) == access(s, right+1)) {
                    left --;
                    right ++;
                }
                li = i;
                ti = right;
                m[i] = (right - left + 2) / 2;
            } else m[i] = _min(ti - i, m[mi]);
        }
        return m;
    }
    int isPalindrome(int* m, int minIndex, int maxIndex) {
        return m[(maxIndex + minIndex + 1)] > maxIndex - minIndex + 1;
    }
    int minCut(string s) {
        int sn = s.size();
        int ans = 0;
        int* m = manacher(s, sn);
        int* rec = new int[sn];
        for (int i = 0; i < sn; i ++) {
            if (isPalindrome(m, 0, i)) {
                rec[i] = 0;
                continue;
            }
            rec[i] = INT_MAX - 1;
            for (int j = 1; j <= i; j ++) {
                if (isPalindrome(m, j, i)) {
                    rec[i] = _min(rec[i], rec[j-1] + 1);
                }
            }
        }
        delete(m);
        ans = rec[sn-1];
        delete(rec);
        return ans;
    };
};

void swap(int* a, int *b ) {
    int t = *a;
    *a = *b;
    *b = t;

}

void swap2(int& a, int &b) {
    int t = a;
    a = b;
    b = t;
}

int main() {
    string s = "cadfdsdfefbvabadfafsbcbc";
    cout<<Solution1().minCut(s)<<endl;;
    cout<<Solution2().minCut(s)<<endl;;
    return 0;
}