/*
    url: leetcode.com/problems/length-of-last-word/
    AC 3ms 7.44%
*/

int lengthOfLastWord(char* s) {
    int ans = 0, i = 0;
    char c = '\0';
    while (1) {
        
        if (s[i] == '\0') break;
        if (s[i] != ' ') {
            if (c == ' ') ans = 1;
            else ans ++;
        }
        c = s[i++];
    }
    return ans;
}

int main() {
    char* s = "w World ";
    printf("answer is %d\r\n", lengthOfLastWord(s));
}