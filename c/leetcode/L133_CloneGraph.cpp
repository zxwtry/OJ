#pragma warning(disable:4786)
/*
    url: leetcode.com/problems/clone-graph
    AC 36ms 23.81%
*/

#include <iostream>
#include <set>
#include <vector>
#include <map>

using namespace std;

struct UndirectedGraphNode {
    int label;
    vector<UndirectedGraphNode *> neighbors;
    UndirectedGraphNode(int x) : label(x) {};
};

map<UndirectedGraphNode* , UndirectedGraphNode* > m;

class Solution {
public:
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        if (node == 0) return node;
        if (m.count(node)) return m[node];
        UndirectedGraphNode *new_node = new UndirectedGraphNode(node->label);
        m[node] = new_node;
        for (int i = 0; i < node->neighbors.size(); i ++) {
           (new_node->neighbors).push_back(cloneGraph(node->neighbors[i]));
        }
        return new_node;
    }
};