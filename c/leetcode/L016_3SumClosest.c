/*
    url: leetcode.com/problems/3sum-closest/
    19ms 20.59%
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <math.h>

void swap(int* a, int* b) {
    int t = *a;
    printf("a is %d\t b is %d\r\n", *a, *b);
    *a = *b;
    *b = t;
}

void bubble_sort(int *n, int s) {
    int i = 0, sign = 1;
    while (sign) {
        sign = 0;
        for (i = 1; i < s; i ++) {
            if (*(n + i - 1) > *(n + i)) {
                swap(n + i - 1, n + i);
                sign = 1;
            }
        }
    }
}

int threeSumClosest(int* nums, int numsSize, int target) {
    int answer = 0, sum = 0;
    int i = 0, l = 0, r = 0;
    if (numsSize < 1) return 0;
    bubble_sort(nums, numsSize);
    answer = *(nums + 0) + *(nums + 1) + *(nums + 2);
    for (i = 0; i < numsSize; i ++) {
        l = i + 1;
        r = numsSize - 1;
        while (l < r) {
            //printf("l is %d\tr is %d\r\n", l, r);
            sum = *(nums + i) + *(nums + l) + *(nums + r);
            if (sum == target) {
                return target;
            } else if (sum < target) {
                answer = abs(answer - target) > abs(sum - target) ? sum : answer;
                l ++;
            } else {
                answer = abs(answer - target) > abs(sum - target) ? sum : answer;
                r --;
            }
        }
    }
    return answer;
}

int main() {
    int nums[] = {1, 1, 1, 1};
    int numsSize = 4;
    int target = 0;
    printf("answer is %d\r\n", threeSumClosest(nums, numsSize, target));
}
