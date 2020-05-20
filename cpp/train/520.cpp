#include <iostream>
using namespace std;

#define LOVE(X) ((X) += (X), (X) += (X), (X) += (X))
int main() {
    int I, meet, U;         // 当我遇见了你
    I = !(I ^ I);           // 我不再是原来的我
    U = !(U ^ U);           // 你也不再是原来的你
    if (U, LOVE (I)) {      // 如果你愿意爱我
        U = I;
        I, LOVE(U);
        I, LOVE(U);         // 我会双倍的爱你
    }
    cout << I + U << endl;  // 我们在一起，就是520

    return !(I ^ U);
}