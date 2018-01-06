#pragma warning(disable:4786)
#pragma warning(disable:4503)

/*
    url: leetcode.com/problems/word-ladder
    need to use advanced data structure
    choose cpp
    AC 323ms 29.09%
*/

#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <queue>
#include <set>

using namespace std;

int ladderLength(string s, string t, vector<string >& w) {
    int sn = s.size() , i;
    set<string > nv , hv;
    for (i = 0; i < w.size(); i ++) nv.insert(w[i]);
    map<string, vector<string > > m;
    queue<string > q;
    nv.insert(s);
    q.push(s);
    bool isFind = false;
    int ans = 2;
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
        ans ++;
        for (set<string >::iterator iter = hv.begin(); iter != hv.end(); iter ++)
            nv.erase(*iter);
        hv.clear();
    }
    return isFind ? ans : 0;;
}

int main() {

    string g[] = {"hot","dot","dog","lot","log","cog"};
    string s = "hit", t = "cog";
    vector<string > w;
    int i;
    for (i = 0; i < (sizeof(g)/sizeof(g[0])); i ++) w.push_back(g[i]);
    int ans = ladderLength(s, t, w);
    cout<<ans<<endl;
    return 0;

}

