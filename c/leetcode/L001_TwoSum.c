/*
    url: leetcode.com/problems/two-sum/
    twoSum_L001_1: 99ms 59.11%
    twoSum_L001_2:  3ms 94.29%
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int* twoSum_L001_1(int* nums, int numsSize, int target) {
    int i = 0, j = 0;
    int* ret = (int*)malloc(sizeof(int)*2);
    for (i = 0; i < numsSize; i ++) {
        for (j = i + 1; j < numsSize; j ++) {
            if ((*(nums + i)) + (*(nums + j)) == target) {
                *(ret) = i;
                *(ret + 1) = j;
                return ret;
            }
        }
    }
    return ret;
}

int* twoSum_L001_2(int* nums, int numsSize, int target) {
    int i = 0, j = 0, len = 0;
    int min = (1 << 31) - 1;
    int max = 1 << 31;
    int* map ;
    int* ret = (int*)malloc(sizeof(int) * 2);
    for (i = 0; i < numsSize; i ++) {
        min = min > *(nums + i) ? *(nums + i) : min;
        max = max < *(nums + i) ? *(nums + i) : max;
    }
    min = min < target - max ? target - max : min;
    max = max > target - min ? target - min : max;
    len = max - min + 1;
    map = (int*)malloc(sizeof(int) * len);
    for (i = 0; i < len; i ++)
        *(map + i) = -1;    
    for (i = 0; i < numsSize; i ++) {
        j = *(nums + i) - min;
        if (j > -1 && j < len)
            *(map + j) = i;
    }
    for (i = 0; i < numsSize; i ++) {
        j = target - *(nums + i) - min;
        if (j > -1 && j < len && *(map + j) != -1 && *(map + j) != i) {
            *(ret) = i;
            *(ret + 1) = *(map + j);
            break;
        }
    }
    free(map);
    return ret;
}

void test_twoSum_L001() {
    int num[] = {3,2,4};
    int numSize = 3;
    int target = 6;
    int *ans = twoSum_L001_2(&num, numSize, target); 
    printf("answer is %d %d\r\n", *(ans), *(ans + 1));
    free(ans);
}
