#pragma warning(disable:4786)
/*
    url: leetcode.com/problems/longest-consecutive-sequence
    Solution:  AC 113ms  0.61%
    Solution2: AC  16ms 10.02%
*/

#include <iostream>
#include <set>
#include <vector>
#include <map>

using namespace std;

class Solution {
public :
    int _max(int a, int b) {
        return a <  b ? b : a;
    }

    int _min(int a, int b) {
        return a < b ? a : b;
    }

    int longestConsecutive(vector<int >& n) {
        int nn = n.size(), i = 0;
        int max_val = INT_MIN, min_val = INT_MAX;
        set<int > s;
        for (i = 0; i < nn; i ++) {
            max_val = _max(max_val, n[i]);
            min_val = _min(min_val, n[i]);
            s.insert(n[i]);
        }
        if (max_val - min_val == nn-1 && s.size() == nn) return nn;
        int ans = 0, l = 0, r = 0;
        for (i = 0; i < nn; i ++) {
            l = n[i];
            r = l;
            while (s.count(-- l)) {};
            while (s.count(++ r)) {};
            ans = _max(ans, r - l - 1);
        }
        return ans;
    }
};

class Solution2 {
public :
    int _max(int a, int b) {
        return a <  b ? b : a;
    }

    int _min(int a, int b) {
        return a < b ? a : b;
    }

    int longestConsecutive(vector<int >& n) {
        int ans = 0, i = 0, nn = n.size();
        int v = 0, l = 0, r = 0, sum = 0;
        map<int, int > m ;
        for (i = 0;i < nn; i ++) {
            v = n[i];
            if (! m.count(v)) {
                l = m.count(v-1) ? m[v-1] : 0;
                r = m.count(v+1) ? m[v+1] : 0;
                sum = l + r + 1;
                m[v] = sum;
                ans = _max(ans, sum);
                m[v-l] = sum;
                m[v+r] = sum;
            }
        }
        return ans;
    }
};


int main() {
    int a[] = {9, 2, 3, 4, 5, 6};
    int an = 6;
    vector<int > n;
    int i = 0;
    for (i = 0; i < an; i ++)
        n.push_back(a[i]);
    cout<<Solution2().longestConsecutive(n)<<endl;
    return 0;
}


