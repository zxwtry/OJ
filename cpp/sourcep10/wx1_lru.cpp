#include <algorithm>

using namespace std;

const int MOD = 10000000;
const int LIMIT = 100000000;

struct Node
{
    uint32_t iKey;
    uint32_t iElem;
};

const int MOD = 10000000;    // 桶取模
const int LIMIT = 100000000; // 限制

// 存储hash冲突
struct HashLinkedList
{
    Node *val;
    HashLinkedList *next;
    HashLinkedList *prev;
};

// 每个hash下面的桶
struct HashBucket
{
    HashLinkedList *head;
    HashLinkedList *tail;
};

// 存储lru的链表
struct LRULinkedList
{
    HashLinkedList *val;
    LRULinkedList *next;
    LRULinkedList *prev;
};

// LRU 结构
HashBucket *hashBkts[MOD] = {NULL};
LRULinkedList *lruhead = NULL;
LRULinkedList *lrutail = NULL;
int lrusize = 0;

Node *Get(uint32_t key)
{
    int m = key % MOD;
    if (hashBkts[m] == NULL)
    {
        return NULL;
    }
    HashLinkedList *h = hashBkts[m]->head;
    while (h != NULL)
    {
        if (h->val->iKey == key)
        {
            return h->val;
        }
        h = h->next;
    }
    // 新用节点，放置到头
    if (hashBkts[m]->prev != NULL)
    {
        hashBkts[m]->val->next = hashBkts[m]->val->next;
    }
    else
    {
        lruhead = hashBkts[m]->val;
        hashBkts[m]->val->prev = NULL;
    }
    return NULL;
}

void Insert(uint32_t key, uint32_t val)
{

    // 写入hash
    int m = key % MOD;
    if (hashBkts[m] == NULL)
    {
        hashBkts[m] = new (HashBucket);
        hashBkts[m]->head = NULL;
        hashBkts[m]->tail = NULL;
    }
    Node *node = new (Node);
    node->iKey = key;
    node->iElem = val;

    HashLinkedList *l = new (HashLinkedList);
    l->val = node;
    l->next = NULL;

    if (hashBkts[m]->tail == NULL)
    {
        hashBkts[m]->head = hashBkts[m]->tail = l;
    }
    else
    {
        hashBkts[m]->tail->next = l;
        hashBkts[m]->tail = l;
    }

    // 写入lru
    LRULinkedList *lruNode = new (LRULinkedList);
    lruNode->next = lruhead; // 新使用节点，放在头
    lruNode->val = node;     // 复用
    if (lruhead != NULL)
    {
        lruhead->prev = lruNode;
    }
    lruhead = lruNode;

    if (lrusize > LIMIT)
    {
        if (lrutail != NULL)
        {
            // 删除hash
            int tailm = lrutail->val->iKey % MOD;
            HashLinkedList *tailN = hashBkts[tailm]->head;
            HashLinkedList *tailPre = hashBkts[tailm]->head;
            while (tailN != NULL)
            {
                // 删除当前节点
                if (tailN->val->iKey == lrutail->val->iKey)
                {
                    if (tailN == hashBkts[tailm]->head)
                    {
                        hashBkts[tailm]->head = tailN->next;
                    }
                    else
                    {
                        tailPre->next = tailN->next;
                    }
                }
                tailPre = tailN;
                tailN = tailN->next;
            }

            // 删除lru
            LRULinkedList *prev = lrutail->prev;
            if (prev != NULL)
            {
                prev->next = NULL;
                lrutail = prev;
            }
            else
            {
                lruhead = lrutail = NULL;
            }
            delete (lrutail);
        }
    }
}
