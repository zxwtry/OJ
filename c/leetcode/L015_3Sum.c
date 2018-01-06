/*
    url: leetcode.com/problems/3sum/
    172ms 22.56%
*/

#include <stdio.h>
#include <stdlib.h>

void swap(int* a, int* b) {
    int t = *a;
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

int** threeSum(int* nums, int numsSize, int* returnSize) {
    int i = 0, l = 0, r = 0, t = 0, size = 0, sum = 0, si = 0;
    int ** answer = NULL;
    int * temp = NULL;
    bubble_sort(nums, numsSize);
    for (i = 0; i < numsSize; i ++) {
        if (i != 0 && *(nums + i - 1) == *(nums + i))
            continue;
        l = i + 1;
        r = numsSize - 1;
        t = - *(nums + i);
        while (l < r) {
            sum = *(nums + l) + *(nums + r);
            if (sum == t) {
                size ++;
                l ++;
                while (l < r && *(nums + l - 1) == *(nums + l))
                    l ++;
                r --;
                while (r > l && *(nums + r + 1) == *(nums + r))
                    r --;
            } else if (sum > t) {
                r --;
            } else {
                l ++;
            }
        }
    }
    printf("size is %d\r\n", size);
    answer = (int **) malloc(sizeof(int *) * size);
    for (i = 0; i < numsSize; i ++) {
        if (i != 0 && *(nums + i - 1) == *(nums + i))
            continue;
        l = i + 1;
        r = numsSize - 1;
        t = - *(nums + i);
        while (l < r) {
            sum = *(nums + l) + *(nums + r);
            if (sum == t) {
                temp = (int *) malloc(sizeof(int) * 3);
                *(temp + 0) = *(nums + i);
                *(temp + 1) = *(nums + l);
                *(temp + 2) = *(nums + r);
                *(answer + (si ++)) = temp;
                l ++;
                while (l < r && *(nums + l - 1) == *(nums + l))
                    l ++;
                r --;
                while (r > l && *(nums + r + 1) == *(nums + r))
                    r --;
            } else if (sum > t) {
                r --;
            } else {
                l ++;
            }
        }
    }
    *(returnSize + 0) = size;
    return answer;
}

int main() {
    int nums[] = {-2,0,1,1,2};
    int numsSize = 5;
    int returnSize[] = {3, 0};
    int i = 0, j = 0;
    //bubble_sort(nums, numsSize);
    int ** answer = threeSum(nums, numsSize, returnSize);
    for (i = 0; i < *returnSize; i ++) {
        for (j = 0; j < 3; j ++) {
            printf("%d\t", *(*(answer + i) + j));
        }
        printf("\r\n");
    }
    for (i = 0; i < *returnSize; i ++) {
        free(*(answer + i));
    }
    free(answer);
    return 0;
}
