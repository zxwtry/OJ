#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/epoll.h>
#include <arpa/inet.h>

#define EVENTS 12

int main()
{
    int sock1, sock2;
    sockaddr_in addr1, addr2;
    int epfd;
    epoll_event ev, ev_ret[EVENTS];
    char buff[2048];
    int i;
    int nfds;
    int n;

    // 创建两个接受消息的socket
    sock1 = socket(AF_INET, SOCK_DGRAM, 0);
    sock2 = socket(AF_INET, SOCK_DGRAM, 0);

    addr1.sin_family = AF_INET; // 协议簇 AF_INET表示TCP/IP协议
    addr2.sin_family = AF_INET;

    inet_pton(AF_INET, "127.0.0.1", &addr1.sin_addr.s_addr); // 配置 绑定ip地址
    inet_pton(AF_INET, "127.0.0.1", &addr2.sin_addr.s_addr);

    addr1.sin_port = htons(11111); // 配置 绑定端口
    addr2.sin_port = htons(22222);

    bind(sock1, (sockaddr *)&addr1, sizeof(addr1));
    bind(sock2, (sockaddr *)&addr2, sizeof(addr2));

    epfd = epoll_create(1);
    if (epfd < 0)
    {
        perror("epoll_create");
        return 1;
    }

    memset(&ev, 0, sizeof(ev));
    ev.events = EPOLLIN; // 只读
    ev.data.fd = sock1;  // 将sock1加到epoll
    if (epoll_ctl(epfd, EPOLL_CTL_ADD, sock1, &ev) != 0)
    {
        perror("epoll_ctl");
        return 1;
    }

    memset(&ev, 0, sizeof(ev));
    ev.events = EPOLLIN; // 只读
    ev.data.fd = sock2;  // 将sock2加到epoll
    if (epoll_ctl(epfd, EPOLL_CTL_ADD, sock2, &ev) != 0)
    {
        perror("epoll_ctl");
        return 1;
    }

    while (1)
    {
        printf("before epoll_wait\n");
        nfds = epoll_wait(epfd, ev_ret, EVENTS, -1);
        if (nfds <= 0)
        {
            perror("epoll_wait");
            return 1;
        }

        printf("after epoll_wait\n");

        for (i = 0; i < nfds; ++i)
        {
            if (ev_ret[i].data.fd == sock1)
            {
                n = recv(sock1, buff, sizeof(buff), 0);
                write(fileno(stdout), buff, n);
            }
            else if (ev_ret[i].data.fd == sock2)
            {
                n = recv(sock2, buff, sizeof(buff), 0);
                write(fileno(stdout), buff, n);
            }
        }
    }

    close(sock1);
    close(sock2);
    return 0;
}
