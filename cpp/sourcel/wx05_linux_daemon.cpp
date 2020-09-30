#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <pwd.h>
#include <time.h>
#include <signal.h>
// daemon(nochdir, noclose)
// nochdir = 0时，工作目录修改成根目录
// noclose = 0时，标准输出/错误输出重定向到 /dev/null

void sig_handler(int);

int main(void)
{
    //防止僵死子进程
    // struct sigaction sa, oldsa;
    // sa.sa_handler = sig_handler;
    // sigemptyset(&sa.sa_mask);
    // sa.sa_flags = SA_RESTART;
    // if (sigaction(SIGCHLD, &sa, &oldsa) == -1)
    // {
    //     fprintf(stderr, "sigaction(SIGCHLD) error/n");
    //     return 1;k
    // }

    /*

7. 处理SIGCHLD信号
　　处理SIGCHLD信号并不是必须的。
    但对于某些进程，特别是服务器进程往往在请求到来时生成子进程处理请求。
    如果父进程不等待子进程结束，子进程将成为僵尸进程（zombie）从而占用系统资源。
    如果父进程等待子进程结束，将增加父进程的负担，影响服务器进程的并发性能。
    在Linux下可以简单地将 SIGCHLD信号的操作设为SIG_IGN。
signal(SIGCHLD,SIG_IGN);
　　这样，内核在子进程结束时不会产生僵尸进程。

    */

    signal(SIGCHLD, SIG_IGN);

    if (daemon(0, 0) == -1)
    {
        exit(EXIT_FAILURE);
    }
    while (1)
    {
        sleep(60);
    }
    return 0;
}

void sig_handler(int sig)
{
    if (sig == SIGCHLD)
    {
    }
}