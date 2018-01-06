/*
    url: leetcode.com/problems/valid-number
    AC 6ms 11.86%
*/

typedef int bool;

/*
    sign:
        still sign means still true
        1: num
        2: e
        3: dot
*/

bool isNumber(char* s) {
    char c = '\0';
    int sign = 0;
    int dot_cnt = 0;
    int e_cnt = 0;
    int has_val = 0;
    int si = 0, ei = -1, i = 0;
    while (s[ei+1] != '\0') ei ++;
    while (s[ei] == ' ') ei --;
    while (s[si] == ' ') si ++;
    for (i = si; i <= ei ; i++) {
        c = s[i];
        if (c == '\0') break;
        if (c == '-' || c == '+') {
            if (i != si && sign != 2)
                return 0;
            continue;
        }
        if (c == ' ') {
            return 0;
        }
        //check .(dot)
        if (c == '.') {
            //has .(dot)
            if (dot_cnt != 0) return 0;
            if (sign == 2) return 0;
            if (e_cnt > 0)  return 0;
            dot_cnt = 1;
            sign = 3;
            continue;
        }
        //check e
        if (c == 'e' || c == 'E') {
            //has e
            if (e_cnt != 0) return 0;
            if (has_val == 0) return 0;
            e_cnt = 1;
            sign = 2;
            continue;
        }
        if (c >= '0' && c <= '9') {
            has_val = 1;
            sign = 1;
            continue;
        }
        return 0;
    }
    return sign == 2 ? 0 : has_val;
}

int main() {
    char* s = "-13. ";
    printf("answer is %d\r\n", isNumber(s));
    return 0;
}