
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] freq = new int[1501];

        // Count frequency of each factor
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Count items divisible by each factor
        int[] free = new int[1501];

        for (int i = 1; i <= 1500; i++) {
            for (int j = i; j <= 1500; j += i) {
                free[i] += freq[j];
            }
        }

        // DP: maximum items using at most budget
        int[] dp = new int[budget + 1];

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            // Exclude the current item itself
            int bonus = free[factor] - 1;

            int[] next = dp.clone();

            // Process each possible budget remainder
            for (int r = 0; r < price && r <= budget; r++) {

                int best = Integer.MIN_VALUE;

                for (int b = r, q = 0; b <= budget;
                     b += price, q++) {

                    // Buy at least one copy of this item
                    if (best != Integer.MIN_VALUE) {
                        next[b] = Math.max(
                            next[b],
                            q + bonus + best
                        );
                    }

                    // Prepare for buying additional copies
                    best = Math.max(best, dp[b] - q);
                }
            }

            dp = next;
        }

        return dp[budget];
    }
}
