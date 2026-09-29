class Solution
{
public:
    bool dfs(int i,int j,int bal,vector<vector<char>>& grid,vector<vector<vector<int>>>& dp)
    {
        int m=grid.size(),n=grid[0].size();

        if(bal<0)
            return false;

        if(i==m-1 && j==n-1)
            return bal==0;

        if(dp[i][j][bal]!=-1)
            return dp[i][j][bal];

        bool ans=false;

        if(i+1<m)
        {
            int nb=bal+(grid[i+1][j]=='('?1:-1);
            ans|=dfs(i+1,j,nb,grid,dp);
        }

        if(j+1<n)
        {
            int nb=bal+(grid[i][j+1]=='('?1:-1);
            ans|=dfs(i,j+1,nb,grid,dp);
        }

        return dp[i][j][bal]=ans;
    }

    bool hasValidPath(vector<vector<char>>& grid)
    {
        int m=grid.size(),n=grid[0].size();

        if(grid[0][0]==')' || grid[m-1][n-1]=='(')
            return false;

        if((m+n-1)%2)
            return false;

        vector<vector<vector<int>>> dp(
            m,
            vector<vector<int>>(n,vector<int>(m+n,-1))
        );

        return dfs(0,0,1,grid,dp);
    }
};