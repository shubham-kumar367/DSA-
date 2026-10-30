class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }

        long[][] dist = new long[n][k + 1];
        for (long[] d : dist) {
            Arrays.fill(d, Long.MAX_VALUE);
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[2], b[2])
        );

        dist[0][1] = 0;
        pq.offer(new long[]{0, 1, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int u = (int) cur[0];
            int count = (int) cur[1];
            long cost = cur[2];

            if (cost != dist[u][count]) {
                continue;
            }

            if (u == n - 1) {
                return (int) cost;
            }

            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];

                int nextCount;

                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                } else {
                    nextCount = 1;
                }

                if (nextCount > k) {
                    continue;
                }

                long newCost = cost + w;

                if (newCost < dist[v][nextCount]) {
                    dist[v][nextCount] = newCost;
                    pq.offer(new long[]{v, nextCount, newCost});
                }
            }
        }

        return -1;
    }
}
