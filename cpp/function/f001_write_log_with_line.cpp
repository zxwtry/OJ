/*
 * Code平台-微码 全新支持 c++ 语言在线运行
 * 只需要在微码中增加如下 有main函数，即可在线运行。
 */

#include <stdarg.h>
#include <stdio.h>

#define KNRM "\x1B[0m"
#define KRED "\x1B[31m"
#define KGRN "\x1B[32m"
#define KYEL "\x1B[33m"
#define KBLU "\x1B[34m"
#define KMAG "\x1B[35m"
#define KCYN "\x1B[36m"
#define KWHT "\x1B[37m"

#define DEBUG 1
void myPrint(const char *argc, ...) {
#ifdef DEBUG
    if (NULL == argc)
        return;
    char buff[1024 << 4] = {0};
    // va_list args_ptr = NULL;
    va_list args_ptr;
    va_start(args_ptr, argc);
    vsprintf(buff, argc, args_ptr);
    va_end(args_ptr);
    fputs(buff, stdout);
#else
    // do nothing
#endif
    return;
}

#define Api_Debug_Log(format, ...)                                             \
    do {                                                                       \
        myPrint("\x1B[33m<debug>\x1B[0m%s::%s[%d]: " #format "\n", __FILE__,   \
                __FUNCTION__, __LINE__, ##__VA_ARGS__);                        \
    } while (0)

#define Api_Error_Log(format, ...)                                             \
    do {                                                                       \
        myPrint("\x1B[31m<error>%s::%s[%d]: " #format "\n\x1B[0m", __FILE__,   \
                __FUNCTION__, __LINE__, ##__VA_ARGS__);                        \
    } while (0)

int main(int argc, char *argv[]) {
    int a = 100;
    char *b = "hello world";
    Api_Debug_Log("debug log here! a=%d", a);
    Api_Error_Log("error log here! b=%s", b);
    return 0;
}