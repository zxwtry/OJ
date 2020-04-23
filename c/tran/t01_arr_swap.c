// Given an array of ints = [6, 4, -3, 5, -2, -1, 0, 1, -9],
// implement a function to move all positive numbers to the left,
//  and move all negative numbers to the right.
//  Try your best to make its time complexity to O(n),
//  and space complexity to O(1).

#include <math.h>
#include <stdio.h>
#include <stdlib.h>

void swap(int arr[], int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

void solve(int arr[], int n) {
    int fn = 0;
    int lp = n - 1;

    while (fn < lp) {
        while (fn < n && arr[fn] > 0) {
            fn++;
        }
        while (lp > -1 && arr[lp] < 0) {
            lp--;
        }
        if (fn < lp) {
            swap(arr, fn, lp);
        }
    }

    for (int i = 0; i < n; i++) {
        printf("i:%d val:%d\n", i, arr[i]);
    }
}

int main() {
    int arr[9] = {6, 4, -3, 5, -2, -1, 0, 1, -9};
    // int arr[3] = {6, -3, 0};
    solve(arr, 9);
    return 0;
}
