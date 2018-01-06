/*
    url: leetcode.com/problems/search-insert-position/
    3ms 23.90%
*/

int searchInsert(int* nums, int numsSize, int target) {
    //first equal or larger
    int i = 0, j = numsSize - 1, m = 0;
    if (numsSize == 0) return 0;
    if (nums[i] >= target) return i;
    if (nums[j] < target) return j + 1;
    while (i < j) {
        m = i + (j - i) / 2;
        if (nums[m] >= target) {
            j = m;
        } else {
            i = m + 1;
        }
    }
    return i;
}

int main() {
    int nums[] = {1,3,5,6};
    int v[] = {5, 2, 7, 0};
    int i = 0;
    int numsSize = 4;
    for (i = 0; i < numsSize; i ++) {
        printf("target is %d answer is %d \r\n", v[i], searchInsert(nums, numsSize, v[i]));
        
    }
}