#include <bits/stdc++.h>

// AC 1357MS  73156K
using namespace std;

const int sn = 10000;
char s[sn];
const char END[] = "END";
const char START[] = "START";

int main() {
    if (getenv("ZXWPC")) {
        freopen("h1075.in", "r", stdin);
        freopen("h1075.out", "w", stdout);
    }
    map<string, string> m;
    char *replace, *find;
    while (true) {
        cin.getline(s, sn, '\n');
        if (strcmp(s, START) == 0) {
            continue;
        }
        if (strcmp(s, END) == 0) {
            break;
        }
        replace = strtok(s, " ");
        find = strtok(NULL, " ");
        string replaceString = replace;
        string findString = find;
        m[findString] = replaceString;
    }
    string word;
    string wordPrint;
    while (true) {
        cin.getline(s, sn, '\n');
        if (strcmp(s, START) == 0) {
            continue;
        }
        if (strcmp(s, END) == 0) {
            break;
        }
        int si = 0;
        char c;
        while (true) {
            c = s[si++];
            if (c == '\0') {
                break;
            }
            if (c >= 'a' && c <= 'z') {
                word += c;
            } else {
                if (m.count(word)) {
                    printf("%s", m[word].c_str());
                } else {
                    printf("%s", word.c_str());
                }
                word.clear();
                printf("%c", c);
            }
        }
        printf("\n");
    }
}
