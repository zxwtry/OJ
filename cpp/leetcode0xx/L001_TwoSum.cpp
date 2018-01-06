/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:44:00
    status:  sort: 25.74 %
    status:  sort: 45.26 %
*/

#include<iostream>
#include<stdio.h>
#include<string>
#include<algorithm>
#include<map>
#include<vector>

using namespace std;


void print_arr(int* a, int ai, int aj) {
    printf("starting...\n");
    for (int ak = ai; ak < aj; ak ++) {
        printf("%-4d   %d\n", ak, a[ak]);
    }
    printf("ending...\n");
}

void swap(int* a, int ai, int aj) {
    int t = a[ai];
    a[ai] = a[aj];
    a[aj] = t;
}

int qsort_part(vector<int> & n, int* ids, int ni, int nj) {
    nj --;
    int idv = ids[ni];
    while ( ni < nj ) {
        while ( ni < nj && n[idv] <= n[ids[nj]]) {
            nj --;
        }
        ids[ni] = ids[nj];
        while ( ni < nj &&  n[idv] >= n[ids[ni]]) {
            ni ++;
        }
        ids[nj] = ids[ni];
    }
    ids[ni] = idv;
    return ni;
}

void qsort_main(vector<int> & n, int* ids, int ni ,int nj) {
    if (ni < nj) {
        int p = qsort_part(n, ids, ni, nj);
        qsort_main(n, ids, ni, p);
        qsort_main(n, ids, p + 1, nj);
    }
}


class Solution_sort {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> r;
        int nn = nums.size();
        int *ids = new int[nn];
        for (int idi = 0; idi < nn; idi ++) {
            ids[idi] = idi;
        }
        qsort_main(nums, ids, 0, nn);
        int i = 0, j = nums.size() - 1;
        while (i < j) {
            if (nums[ids[i]] + nums[ids[j]] > target) {
                j --;
            } else if (nums[ids[i]] + nums[ids[j]] < target) {
                i ++;
            } else {
                r.push_back(min(ids[i], ids[j]));
                r.push_back(max(ids[i], ids[j]));
                return r;
            }
        }
        return r;
    }
};


class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int nn = nums.size();
        map<int, int> m;
        vector<int> r;
        for (int idi = 0; idi < nn; idi ++) {
            if (m.count(target - nums[idi]) > 0) {
                r.push_back(m[target - nums[idi]]);
                r.push_back(idi);
                return r;
            }
            m[nums[idi]] = idi;
        }
        return r;
    }
};

int main() {
    int a[] = {2, 7, 11, 15};
    //int a[] = {11, 15, 7, 2};
    int target = 9;
    vector<int> nums(&a[0], &a[4]);
    vector<int> r = Solution().twoSum(nums, target);
    for (int i = 0, len = r.size(); i < len; i ++) {
        printf("%04d %d\n", i, r[i]);
    }
    return 0;
}
