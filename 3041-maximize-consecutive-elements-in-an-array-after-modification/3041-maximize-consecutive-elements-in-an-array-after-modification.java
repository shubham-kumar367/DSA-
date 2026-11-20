class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int maxValue = nums[nums.length - 1];
        int[] dp = new int[maxValue + 2];
        int answer = 1;

        for (int x : nums) {
            // Use this element as x + 1; dp[x] only includes earlier elements.
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);

            // Use this element as x.
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);

            answer = Math.max(answer, Math.max(dp[x], dp[x + 1]));
        }

        return answer;
    }
}