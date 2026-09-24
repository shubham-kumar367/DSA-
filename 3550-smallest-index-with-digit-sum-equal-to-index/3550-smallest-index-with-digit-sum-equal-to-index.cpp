class Solution {
public:
    int sumOfDigit(int digit) {
        long long sum = 0;

        while(digit) {
            sum += digit % 10;
            digit /= 10;
        }

        return sum;
    }

    int smallestIndex(vector<int>& nums) {
        int n = nums.size();

        for(int i = 0; i < n; i++) {
            if((int)sumOfDigit(nums[i]) == i) return i;
        }

        return -1;
    }
};