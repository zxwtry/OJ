#pragma warning(disable:4786)
#pragma warning(disable:4503)
/*
    url: leetcode.com/problems/word-ladder-ii
    too many advanced data structure
    use cpp
    Solution: AC 259ms 51.41%
*/

#include <iostream>
#include <queue>
#include <set>
#include <map>
#include <vector>
#include <string>

using namespace std;

class Solution {
public:
    void find(string t, string s, vector<string >& v, map<string, vector<string > >& m, vector<vector<string > >& ans) {
        v.insert(v.begin(), t);
        if (! t.compare(s)) {
            ans.push_back(v);
        } else if (m.count(t)) {
            for (vector<string >::iterator iter = m[t].begin(); iter != m[t].end(); iter ++)
                find(*iter, s, v, m, ans);
        }
        v.erase(v.begin());
    }

    vector<vector<string > > findLadders(string s, string t, vector<string>& w) {
        vector<vector<string > > ans;
        int sn = s.size() , i;
        set<string > nv , hv;
        for (i = 0; i < w.size(); i ++) nv.insert(w[i]);
        map<string, vector<string > > m;
        queue<string > q;
        nv.insert(s);
        q.push(s);
        bool isFind = false;
        while (! q.empty()) {
            int size = q.size();
            while (size -- > 0) {
                string n = q.front();
                string v = n;
                q.pop();
                for (i = 0; i < sn; i ++) {
                    for (char c = 'a'; c <= 'z'; c ++) {
                        v[i] = c;
                        if (! nv.count(v)) continue;
                        if (! hv.count(v)) {
                            hv.insert(v);
                            q.push(v);
                        }
                        m[v].push_back(n);
                        if (! v.compare(t)) isFind = true;
                    }
                    v[i] = n[i];
                }
            }
            if (isFind) break;
            for (set<string >::iterator iter = hv.begin(); iter != hv.end(); iter ++)
                nv.erase(*iter);
            hv.clear();
        }
        vector<string > v;
        find(t, s, v, m, ans);
        return ans;
    }
};

int main() {
    string g[] = {"hot","dot","dog","lot","log","cog"};
    string s = "hit", t = "cog";
    vector<string > w;
    int i;
    for (i = 0; i < (sizeof(g)/sizeof(g[0])); i ++) w.push_back(g[i]);
    vector<vector<string > > ans = Solution().findLadders(s, t, w);
    for (i = 0; i < ans.size(); i ++) {
        cout<<"+++++++++"<<endl;
        for (int j = 0; j < ans[i].size(); j ++) {
            cout<<ans[i][j]<<" ";
        }
        cout<<endl;
    }
    return 0;
}