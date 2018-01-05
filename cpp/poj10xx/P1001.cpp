/*
    author:  zxwtry
    email:   zxwtry@qq.com
    project: ojcpp
    gcc:     4.8.4
    date:    2018-01-05 20:54:00
*/


/*
    数字的幂次方
*/


#include<iostream>
#include<string>

using namespace std;

int* int_arr(string & a) {
    int al = a.length();
    int rl = al;
    for (int i = 0; i < al; i ++) {
        if (a[i] == '.') {
            rl --;
        }
    }
    cout<<rl<<endl;
    return NULL;
}

int main() {
    string s;
    int t;
    cin>>s;
    cin>>t;
    int_arr(s);
    cout<<s<<endl;
    cout<<t<<endl;       
    return 0;
}
