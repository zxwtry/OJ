/*
    url: leetcode.com/problems/maximum-subarray/
    AC 6ms 44.59%
*/

int maxSubArray(int* n, int nn) {
    int ans = 0, ni = 0, sum = 0;
    if (nn < 1) return 0;
    ans = n[0];
    for (ni = 0; ni < nn; ni ++) {
        if (sum < 0) sum = 0;
        sum += n[ni];
        ans = ans < sum ? sum : ans;
    }
    return ans;
}

int main() {
    int n[] = {-2,1,-3,4,-1,2,1,-5,4};
    int nn = 9;
    printf("answer is %d\r\n", maxSubArray(n, nn));
}