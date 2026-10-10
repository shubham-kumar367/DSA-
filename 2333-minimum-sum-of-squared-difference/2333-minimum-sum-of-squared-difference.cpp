class Solution {
public:
    long long minSumSquareDiff(vector<int>& nums1, vector<int>& nums2, int k1, int k2) {
        long long ops = (long long)k1 + k2;

        vector<long long> freq(100001, 0);
        long long totalDiff = 0;
        int maxDiff = 0;

        for (int i = 0; i < nums1.size(); i++) {
            int d = abs(nums1[i] - nums2[i]);
            freq[d]++;
            totalDiff += d;
            maxDiff = max(maxDiff, d);
        }

        if (ops >= totalDiff) return 0;

        for (int d = maxDiff; d > 0 && ops > 0; d--) {
            if (freq[d] == 0) continue;

            long long take = min(freq[d], ops);

            freq[d] -= take;
            freq[d - 1] += take;
            ops -= take;
        }

        long long ans = 0;
        for (long long d = 1; d <= 100000; d++) {
            ans += freq[d] * d * d;
        }

        return ans;
    }
};