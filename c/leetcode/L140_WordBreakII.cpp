#pragma warning(disable:4786)

/*
    url: leetcode.com/problems/word-break-ii
*/

#include <iostream>
#include <vector>
#include <string>
#include <vector>
#include <map>
#include <set>

using namespace std;

class Solution {
public:
    vector<string > wordBreak(string s, vector<string >& wd) {
        vector<string > ans;
        map<string, vector<string > > m_;
        set<string > se;
        int i = wd.size() - 1;
        for (; i > -1; i --) se.insert(wd[i]);
        return ans;
    }
    vector<string > search(string s, set<string > & se, map<string, vector<string > > & m) {
        
    }
};

int main() {

    string s = "0123456";
    cout<<s.substr(4, 2)<<endl;
    return 0;
}
