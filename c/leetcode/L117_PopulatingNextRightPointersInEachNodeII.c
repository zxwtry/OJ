/*
    url: leetcode.com/problems/populating-next-right-pointers-in-each-node-ii
    AC 9ms 40.91%
*/


struct TreeLinkNode {
    int val;
    struct TreeLinkNode *left, *right, *next;
};


typedef struct TreeLinkNode * ptln;

void connect(ptln root) {
    ptln ll = root, p = NULL, pl = root;
    if (root == NULL) return;
    root->next = NULL;
    while (pl != NULL) {
        ll = pl;
        pl = NULL;
        p = NULL;
        while (ll != NULL) {
            if (ll->left != NULL) {
                if (p == NULL) {
                    pl = p = ll->left;
                } else {
                    p->next = ll->left;
                    p = p->next;
                }
            }
            if (ll->right != NULL) {
                if (p == NULL) {
                    pl = p = ll->right;
                } else {
                    p->next = ll->right;
                    p = p->next;
                }
            }
            ll = ll->next;
        }
    }
}