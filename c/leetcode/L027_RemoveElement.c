/*
    url: leetcode.com/problems/remove-element/
    6ms 2.27%
*/

#include <stdio.h>
#include <stdlib.h>

int removeElement(int* nums, int numsSize, int val) {
    int i = 0, j = 0, count = 0;
    for (j = 0; j < numsSize; j ++)
        if (*(nums + j) != val) {
            *(nums + (i ++)) = *(nums + j);
            count ++;
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
    int nums[] = {3,2,2,3};
    int numsSize = 4;
    int val = 3;
    print_array(nums, removeElement(nums, numsSize, val));
}