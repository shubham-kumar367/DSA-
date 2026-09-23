class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        int sum = 0;

        for(int num : nums)
            sum += num;

        int target = sum - x;

        if(target == 0)
            return n;

        int left = 0, curr = 0, maxLen = -1;

        for(int right = 0; right < n; right++) {
            curr += nums[right];

            while(left <= right && curr > target)
                curr -= nums[left++];

            if(curr == target)
                maxLen = max(maxLen, right - left + 1);
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
};