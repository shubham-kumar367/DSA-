class Solution {
    int m, n;
    int[][] grid;

    public int countIslands(int[][] grid, int k) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && dfs(i, j) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    long dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0)
            return 0;

        long sum = grid[i][j];
        grid[i][j] = 0;

        sum += dfs(i + 1, j);
        sum += dfs(i - 1, j);
        sum += dfs(i, j + 1);
        sum += dfs(i, j - 1);

        return sum;
    }
}