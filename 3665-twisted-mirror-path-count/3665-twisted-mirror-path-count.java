
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = 1000000007;

        int[][] dp = new int[n][2];

        dp[0][0] = 1;

        for (int j = 1; j < n; j++) {
            dp[j][1] = 1;
            if (grid[0][j] == 1) {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            int[][] next = new int[n][2];

            next[0][0] = grid[i - 1][0] == 1 ? 0 : dp[0][0];

            for (int j = 1; j < n; j++) {
                next[j][0] = (int) (
                    ((long)(grid[i - 1][j] == 1 ? 0 : dp[j][0]) + dp[j][1]) % mod
                );

                next[j][1] = (int) (
                    ((long)next[j - 1][0] +
                    (grid[i][j - 1] == 1 ? 0 : next[j - 1][1])) % mod
                );
            }

            dp = next;
        }

        return (int)(((long)dp[n - 1][0] + dp[n - 1][1]) % mod);
    }
}
