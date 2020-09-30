#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/epoll.h>
#include <arpa/inet.h>

//  cd "/pub/xinweizhu/github/sample/cpp/sourcel/" && g++ wx04_socket_dgram_send.cpp -o wx04_socket_dgram_send && "/pub/xinweizhu/github/sample/cpp/sourcel/"wx04_socket_dgram_send

int main()
{
    int sock;
    sockaddr_in dst1, dst2;
    char buf[1024];

    sock = socket(AF_INET, SOCK_DGRAM, 0);

    dst1.sin_family = AF_INET;
    dst2.sin_family = AF_INET;

    inet_pton(AF_INET, "127.0.0.1", &dst1.sin_addr.s_addr);
    inet_pton(AF_INET, "127.0.0.1", &dst2.sin_addr.s_addr);

    dst1.sin_port = htons(11111);
    dst2.sin_port = htons(22222);

    strcpy(buf, "data to port 11111\n");
    sendto(sock, buf, strlen(buf), 0, (sockaddr *)&dst1, sizeof(dst1));

    strcpy(buf, "data to port 22222\n");
    sendto(sock, buf, strlen(buf), 0, (sockaddr *)&dst2, sizeof(dst2));
    close(sock);

    return 0;
}