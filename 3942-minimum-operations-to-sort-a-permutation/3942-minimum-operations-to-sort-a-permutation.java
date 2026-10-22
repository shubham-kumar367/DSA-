
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] a = new int[2 * n];
        int[] dist = new int[2 * n];
        java.util.Arrays.fill(dist, -1);

        int[] rev = new int[n];
        for (int i = 0; i < n; i++)
            rev[i] = nums[n - 1 - i];

        int target = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) target = i;
        }

        boolean inc = true, dec = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) inc = false;
            if (nums[i] != n - 1 - i) dec = false;
        }

        if (inc) return 0;

        java.util.ArrayDeque<Integer> q = new java.util.ArrayDeque<>();
        q.offer(0);
        dist[0] = 0;

        while (!q.isEmpty()) {
            int s = q.poll();
            int type = s / n;
            int shift = s % n;

            int[] cur = type == 0 ? nums : rev;
            boolean sorted = true;

            for (int i = 0; i < n; i++) {
                if (cur[(i + shift) % n] != i) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) return dist[s];

            int next = type * n + (shift + 1) % n;
            if (dist[next] == -1) {
                dist[next] = dist[s] + 1;
                q.offer(next);
            }

            next = (1 - type) * n + (n - shift) % n;
            if (dist[next] == -1) {
                dist[next] = dist[s] + 1;
                q.offer(next);
            }
        }

        return -1;
    }
}
