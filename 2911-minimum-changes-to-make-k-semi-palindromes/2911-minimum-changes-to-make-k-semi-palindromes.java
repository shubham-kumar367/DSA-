class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                cost[i][j] = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    for (int r = 0; r < d; r++) {
                        int l = i + r;
                        int end = i + r + ((len - 1 - r) / d) * d;

                        while (l < end) {
                            if (s.charAt(l) != s.charAt(end)) {
                                changes++;
                            }

                            l += d;
                            end -= d;
                        }
                    }

                    cost[i][j] = Math.min(cost[i][j], changes);
                }
            }
        }

        int INF = 1000000;
        int[][] dp = new int[n + 1][k + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[n][0] = 0;

        for (int parts = 1; parts <= k; parts++) {
            for (int i = n - 2 * parts; i >= 0; i--) {
                for (int j = i + 2; j <= n - 2 * (parts - 1); j++) {
                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j - 1] + dp[j][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
