class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {
        vector<long long> ans(k), dp(k);
        for(int num : nums) {
            vector<long long> ndp(k);
            int x = num % k;
            ndp[x]++;
            for(int r = 0; r < k; r++) {
                int nr = (r * x) % k;
                ndp[nr] += dp[r];
            }
            dp = ndp;
            for(int r = 0; r < k; r++) {
                ans[r] += dp[r];
            }
        }
        return ans;
    }
};