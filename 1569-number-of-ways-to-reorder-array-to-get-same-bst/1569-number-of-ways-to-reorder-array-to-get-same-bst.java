// import java.util.ArrayList;
// import java.util.List;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] combinations;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Pascal's triangle: combinations[i][j] = C(i, j)
        combinations = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            combinations[i][0] = 1;
            combinations[i][i] = 1;

            for (int j = 1; j < i; j++) {
                combinations[i][j] =
                    (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MOD;
            }
        }

        List<Integer> values = new ArrayList<>();
        for (int value : nums) {
            values.add(value);
        }

        long totalWays = countWays(values);
        return (int) ((totalWays - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> values) {
        if (values.size() <= 2) {
            return 1;
        }

        int root = values.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < values.size(); i++) {
            int value = values.get(i);
            if (value < root) {
                left.add(value);
            } else {
                right.add(value);
            }
        }

        long interleavings = combinations[left.size() + right.size()][left.size()];
        long leftWays = countWays(left);
        long rightWays = countWays(right);

        return (interleavings * leftWays % MOD) * rightWays % MOD;
    }
}