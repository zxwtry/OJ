#include <iostream>
#include <strings.h>

using namespace std;

int main() {

    char ch[10];
    // sprintf(ch, "%d", 100);
    snprintf(ch, 9, "%d", 100);
    snprintf(ch, 9, "%d", 9);
    string eventResult(ch, ch + strlen(ch));

    std::cout << eventResult << std::endl;

    return 0;
}