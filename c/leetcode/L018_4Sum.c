/*
    url: leetcode.com/problems/4sum/
    36ms 45.28%
*/

#include <stdio.h>
#include <stdlib.h>

//[l, r]
int partition(int* a, int l, int r) {
    int t = *(a + l);
    while (l < r) {
        while (l < r && *(a + r) >= t) r --;
        *(a + l) = *(a + r);
        while (l < r && *(a + l) <= t) l ++;
        *(a + r) = *(a + l);
    }
    *(a + l) = t;
    return l;
}

//[l, r)
void quick_sort(int* a, int l, int r) {
    int p = 0;
    if (l < r) {
        p = partition(a, l, r - 1);
        quick_sort(a, l, p);
        quick_sort(a, p + 1, r);
    }
}

struct list {
    int a[4];
    struct list *next;
};

void free_list(struct list * head) {
    struct list * next = head;
    while (next != NULL) {
        head = next->next;
        free(next);
        next = head;
    }
}

int** fourSum(int* nums, int numsSize, int target, int* returnSize) {
    int ** answer = NULL;
    struct list * head = NULL, * next = NULL, * temp = NULL;
    int i = 0, j = 0, l = 0, r = 0, sum = 0, count = 0;
    int * answer_temp = NULL;
    quick_sort(nums, 0, numsSize);
    for (i = 0; i < numsSize; i ++) {
        if (i != 0 && *(nums + i - 1) == *(nums + i))
            continue;
        for (j = i + 1; j < numsSize; j ++) {
            if (j != i + 1 && *(nums + j - 1) == *(nums + j))
                continue;
            l = j + 1;
            r = numsSize - 1;
            while (l < r) {
                sum = *(nums + i) + *(nums + j) + *(nums + l) + *(nums + r);
                if (sum == target) {
                    temp = (struct list *) malloc(sizeof(struct list));
                    temp->next = NULL;
                    temp->a[0] = *(nums + i);
                    temp->a[1] = *(nums + j);
                    temp->a[2] = *(nums + l);
                    temp->a[3] = *(nums + r);
                    if (NULL == head) {
                        head = temp;
                        next = temp;
                    } else {
                        next->next = temp;
                        next = temp;
                    }
                    count ++;
                    do {
                        l ++;
                    } while (l < r && nums[l - 1] == nums[l]);
                    do {
                        r --;
                    } while (l < r && nums[r + 1] == nums[r]);
                } else if (sum > target) {
                    do {
                        r --;
                    } while (l < r && nums[r + 1] == nums[r]);
                } else {
                    do {
                        l ++;
                    } while (l < r && nums[l - 1] == nums[l]);
                }
            }
        }
    }
    answer = (int **) malloc(sizeof(int *) * count);
    next = head;
    for (i = 0; i < count; i ++) {
        answer_temp = (int *) malloc(sizeof(int) * 4);
        answer_temp[0] = next->a[0];
        answer_temp[1] = next->a[1];
        answer_temp[2] = next->a[2];
        answer_temp[3] = next->a[3];
        *(answer + i) = answer_temp;
        next = next->next;
    }
    free_list(head);
    *(returnSize) = count;
    return answer;
}

int main() {
    int nums[] = {-1,0,1,2,-1,-4};
    //int nums[] = {1, 1, 1, 1, 1, 1};
    int numsSize = 6;
    int target = -1;
    int returnSize[] = {0};
    int ** a = fourSum(nums, numsSize, target, returnSize);
    int i = 0;
    printf("answer length is %d\r\n", *(returnSize));
    for (i = 0; i < *(returnSize); i ++)
        free(*(a + i));
    free(a);
    return 0;
}