
class Solution {
    static final long MOD = 1000000007L;

    int n;
    long[] sum, sq, lazy;

    void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            sq[node] = (sq[node] + 2L * val * sum[node] + (long) (r - l + 1) * val * val) % MOD;
            sum[node] = (sum[node] + (long) (r - l + 1) * val) % MOD;
            lazy[node] += val;
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void apply(int node, int l, int r, long val) {
        sq[node] = (sq[node] + 2L * val * sum[node]
                + (long) (r - l + 1) * val * val) % MOD;
        sum[node] = (sum[node] + (long) (r - l + 1) * val) % MOD;
        lazy[node] += val;
    }

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            update(1, 0, n - 1, prev, i, 1);
            last[nums[i]] = i + 1;
            ans = (ans + sq[1]) % MOD;
        }

        return (int) ans;
    }
}
