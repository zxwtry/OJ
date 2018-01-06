#pragma warning(disable:4786)

/*
    url: leetcode.com/problems/palindrome-partitioning
    AC 6ms 75.03%
*/

#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <queue>
#include <set>

using namespace std;

class Solution {
public:
    int isP(string& s, int i, int j) {
        while (i < j) {
            if (s[i] != s[j]) return 0;
            i ++;
            j --;
        }
        return 1;
    }
    void search(vector<vector<string > > &ans, vector<string >& rec, string& s, int i, int j) {
        if (isP(s, i, j)) {
            rec.push_back(s.substr(i, j-i+1));
            for (int nt = j+1; nt < s.size(); nt ++) {
                search(ans, rec, s, j+1, nt);
            }
            if (j == s.size()-1) {
                ans.push_back(rec);
            }
            rec.pop_back();
        }
        
    }
    vector<vector<string > > partition(string s) {
        vector<vector<string > > ans;
        vector<string > rec;
        for (int i = 0; i < s.size(); i ++)
            search(ans, rec, s, 0, i);
        return ans;
    }
};

int main() {
    string s = "aab";
    vector<vector<string > > ans = Solution().partition(s);
    for (int i = 0; i < ans.size(); i ++) {
        cout<<"++++++++++++++++"<<endl;
        for (int j = 0; j < ans[i].size(); j ++) {
            cout<<ans[i][j]<<" ";
        }
        cout<<endl<<"----------------"<<endl;
    }
    return 0;
}