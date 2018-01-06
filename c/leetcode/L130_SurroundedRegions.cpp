/*
    url: leetcode.com/problems/surrounded-regions
    想了各种办法，还是runtime error，不知道原因
*/

#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <queue>
#include <set>

using namespace std;

int rn = 0;
int cn = 0;

vector<vector<char > >* t;


class Solution {
public:    
	void solve(vector<vector<char > >& b) {
        rn = b.size();
        if (rn < 3) return;
        cn = b[0].size();
        if (cn < 3) return;
        t = &b;
        int i = 0, j = 0;
        for (i = 0; i < rn; i ++) {
            search(i, 0);
            search(i, cn - 1);
        }
        for (j = 1; j < cn-1; j ++) {
            search(0, j);
            search(rn-1, j);
        }
        for (i = 0; i < rn; i ++) {
            for (j = 0; j < cn; j ++) {
                if (b[i][j] == 'O') {
                    b[i][j] = 'X';
                } else if (b[i][j] == '1') {
                    b[i][j] = 'O';
                }
            }
        }
    }
    void search(int i, int j) {
        if ((*t)[i][j] != 'O') return;
        int x, y;
        queue<int > qx;
        queue<int > qy;
        qx.push(i);
        qy.push(j);
        while (! qx.empty()) {
            x = qx.front();
            y = qy.front();
            (*t)[x][y] = '1';
            qx.pop();
            qy.pop();
            if (x > 0 && (*t)[x-1][y] == 'O') {
                qx.push(x-1);
                qy.push(y);
            }
            if (x+1 < rn && (*t)[x+1][y] == 'O') {
                qx.push(x+1);
                qy.push(y);
            }
            if (y > 0 && (*t)[x][y-1] == 'O') {
                qx.push(x);
                qy.push(y-1);                
            }
            if (y+1 < cn && (*t)[x][y+1] == 'O') {
                qx.push(x);
                qy.push(y+1);       
            }
        }
    }
};


int main() {
    char* b[4] = {"XXXX", "XOOX", "XXOX", "XOXX"};
    int rn = 4;
    int cn = 4;
    int i = 0, j = 0;
    char** c = (char**) malloc(sizeof(char*) * rn);
    for (i = 0; i < rn; i ++) {
        c[i] = (char*) malloc(sizeof(char) * (cn+1));
        c[i][cn] = '\0';
        for (j = 0; j < cn; j ++) {
            c[i][j] = b[i][j];
        }
    }
    for (i = 0; i < rn; i ++) {
        printf("%s\n", c[i]);
    }
    return 0;
}