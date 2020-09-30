/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-06 13:24:00
    status:  48.10 %
*/

#include <iostream>
#include <stdio.h>
#include <string>
#include <algorithm>
#include <map>
#include <vector>

using namespace std;

void print_arr(int *a, int ai, int aj)
{
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak++)
    {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}

class Solution
{
public:
    int lengthOfLongestSubstring(string s)
    {
        int sn = s.length();
        int an = 256;
        int *a = new int[an];
        for (int ai = 0; ai < an; ai++)
        {
            a[ai] = -1;
        }
        int r = sn == 0 ? 0 : 1;
        int l = 0;
        for (int si = 0; si < sn; si++)
        {
            int v = s[si];
            if (a[v] != -1)
            {
                l = max(l, a[v] + 1);
            }
            r = max(r, si - l + 1);
            a[v] = si;
        }
        return r;
    }
};

int main()
{
    string s = "pwwkew";
    int r = Solution().lengthOfLongestSubstring(s);
    printf("r is : %d\n", r);
    return 0;
}
