class Solution {
public:
    struct Node {
        int prod;
        int cnt[5];
    };

    int n, k;
    vector<Node> tree;

    Node merge(Node &a, Node &b) {
        Node res;
        res.prod = (a.prod * b.prod) % k;

        for(int i = 0; i < k; i++)
            res.cnt[i] = a.cnt[i];

        for(int i = 0; i < k; i++)
            res.cnt[(a.prod * i) % k] += b.cnt[i];

        return res;
    }

    void build(int p, int l, int r, vector<int>& nums) {
        if(l == r) {
            tree[p].prod = nums[l] % k;

            for(int i = 0; i < k; i++)
                tree[p].cnt[i] = 0;

            tree[p].cnt[tree[p].prod] = 1;
            return;
        }

        int m = (l + r) >> 1;

        build(p << 1, l, m, nums);
        build(p << 1 | 1, m + 1, r, nums);

        tree[p] = merge(tree[p << 1], tree[p << 1 | 1]);
    }

    void update(int p, int l, int r, int idx, int val) {
        if(l == r) {
            tree[p].prod = val % k;

            for(int i = 0; i < k; i++)
                tree[p].cnt[i] = 0;

            tree[p].cnt[tree[p].prod] = 1;
            return;
        }

        int m = (l + r) >> 1;

        if(idx <= m)
            update(p << 1, l, m, idx, val);
        else
            update(p << 1 | 1, m + 1, r, idx, val);

        tree[p] = merge(tree[p << 1], tree[p << 1 | 1]);
    }

    Node query(int p, int l, int r, int ql, int qr) {
        if(ql <= l && r <= qr)
            return tree[p];

        int m = (l + r) >> 1;

        if(qr <= m)
            return query(p << 1, l, m, ql, qr);

        if(ql > m)
            return query(p << 1 | 1, m + 1, r, ql, qr);

        Node left = query(p << 1, l, m, ql, qr);
        Node right = query(p << 1 | 1, m + 1, r, ql, qr);

        return merge(left, right);
    }

    vector<int> resultArray(vector<int>& nums, int k, vector<vector<int>>& queries) {
        this->k = k;
        n = nums.size();

        tree.resize(4 * n + 5);

        build(1, 0, n - 1, nums);

        vector<int> ans;
        ans.reserve(queries.size());

        for(auto &q : queries) {
            update(1, 0, n - 1, q[0], q[1]);

            Node res = query(1, 0, n - 1, q[2], n - 1);

            ans.push_back(res.cnt[q[3]]);
        }

        return ans;
    }
};