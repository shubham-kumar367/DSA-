class Solution {
    int m,n;
    boolean dfs(int[][] grid,int i,int j)
    {
        if(i<0||j<0||i>=m||j>=n) return false;
        if(grid[i][j]==1) return true;
        grid[i][j]=1;
        boolean a=dfs(grid,i-1,j);
        boolean b=dfs(grid,i+1,j);
        boolean c=dfs(grid,i,j-1);
        boolean d=dfs(grid,i,j+1);
        return a&&b&&c&&d;
    }
    public int closedIsland(int[][] grid)
    {
        m=grid.length;
        n=grid[0].length;
        int ans=0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j]==0&&dfs(grid,i,j))
                    ans++;
            }
        }
        return ans;
    }
}