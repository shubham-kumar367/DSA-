class Solution {
    int n, timer;
    int[] parent, depth, size, heavy, head, pos, arr, seg;
    ArrayList<Integer>[] adj;

    void dfs(int u, int p) {
        parent[u] = p;
        size[u] = 1;
        int max = 0;

        for (int v : adj[u]) {
            if (v == p) continue;

            depth[v] = depth[u] + 1;
            dfs(v, u);
            size[u] += size[v];

            if (size[v] > max) {
                max = size[v];
                heavy[u] = v;
            }
        }
    }

    void decompose(int u, int h, String s) {
        head[u] = h;
        pos[u] = timer++;
        arr[pos[u]] = 1 << (s.charAt(u) - 'a');

        if (heavy[u] != -1)
            decompose(heavy[u], h, s);

        for (int v : adj[u]) {
            if (v != parent[u] && v != heavy[u])
                decompose(v, v, s);
        }
    }

    void build(int node, int l, int r) {
        if (l == r) {
            seg[node] = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            seg[node] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid)
            update(node * 2, l, mid, idx, val);
        else
            update(node * 2 + 1, mid + 1, r, idx, val);

        seg[node] = seg[node * 2] ^ seg[node * 2 + 1];
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return seg[node];

        int mid = (l + r) / 2;

        return query(node * 2, l, mid, ql, qr)
             ^ query(node * 2 + 1, mid + 1, r, ql, qr);
    }

    int pathQuery(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            mask ^= query(1, 0, n - 1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }

        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        mask ^= query(1, 0, n - 1, pos[u], pos[v]);

        return mask;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        timer = 0;

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        arr = new int[n];

        Arrays.fill(heavy, -1);

        dfs(0, -1);
        decompose(0, 0, s);

        seg = new int[4 * n];
        build(1, 0, n - 1);

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                int val = 1 << (p[2].charAt(0) - 'a');
                update(1, 0, n - 1, pos[u], val);
            } else {
                int v = Integer.parseInt(p[2]);
                int mask = pathQuery(u, v);
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }
}
