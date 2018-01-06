/*
    url: leetcode.com/problems/search-for-a-range/
    6ms 26.67%
*/

#include <stdio.h>
#include <stdlib.h>

//[i, j)
int binary_search_first_equal_or_larger(int* n, int i, int j, int t) {
    int m = 0;
    j --;
    while (i < j) {
        m = i + (j - i) / 2;
        if (n[m] >= t) {
            j = m;
        } else {
            i = m + 1;
        }
    }
    return i;
}

int* searchRange(int* nums, int numsSize, int target, int* returnSize) {
    int* a = (int*) malloc(sizeof(int) * 2);
    a[0] = -1;
    a[1] = -1;
    * returnSize = 2;
    if (nums[0] == target) a[0] = 0;
    else if (nums[0] > target) return a;
    if (nums[numsSize - 1] == target) a[1]  = numsSize - 1;
    else if (nums[numsSize - 1] < target) return a;
    if (a[0] == -1)
        a[0] = binary_search_first_equal_or_larger(nums, 0, numsSize, target);
    if (a[1] == -1)
        a[1] = binary_search_first_equal_or_larger(nums, 0, numsSize, target + 1) - 1;
    //target not exists
    if (a[0] > a[1]) {
        a[0] = -1;
        a[1] = -1;
    }
    return a;
}

int main() {
    int nums[] = {6, 6, 6, 8, 8, 10};
    int numsSize = 6;
    int target = 7;
    int returnSize = 0;
    int * a = searchRange(nums, numsSize, target, &returnSize);
    printf("answer is %d %d \r\n", a[0], a[1]);
    free(a);
    return 0;
}