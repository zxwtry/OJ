/*
    url: leetcode.com/problems/remove-duplicates-from-sorted-array/
    15ms 35.07%
*/

#include <stdio.h>
#include <stdlib.h>

int removeDuplicates(int* nums, int numsSize) {
    int i = 0, j = 0, count = 1;
    if (numsSize < 2) return numsSize;
    for (j = 1, i = 1; j < numsSize; j ++) {
        if (*(nums + j - 1) != *(nums + j)) {
            count ++;
            *(nums + (i ++)) = *(nums + j);
        }
    }
    return count;
}

void print_array(int * nums, int numsSize) {
    int i = 0;
    for (i = 0; i < numsSize; i ++)
        printf("%d ", *(nums + i));
    printf("\r\n");
}

int main() {
    int nums [] = {1, 1, 2, 2};
    int numsSize = 4;
    print_array(nums, removeDuplicates(nums, numsSize));
}