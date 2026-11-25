class Solution {

    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;
    static final long BASE = 100003L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            p1[i + 1] = p1[i] * BASE % MOD1;
            p2[i + 1] = p2[i] * BASE % MOD2;

            h1[i + 1] = (h1[i] * BASE + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE + nums[i]) % MOD2;
        }

        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isUnique(mid, n, h1, h2, p1, p2)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean isUnique(
        int len, int n,
        long[] h1, long[] h2,
        long[] p1, long[] p2
    ) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {

            long a = (h1[i + len] -
                    h1[i] * p1[len] % MOD1 + MOD1) % MOD1;

            long b = (h2[i + len] -
                    h2[i] * p2[len] % MOD2 + MOD2) % MOD2;

            long key = a * MOD2 + b;

            freq.put(key, freq.getOrDefault(key, 0) + 1);

            if (freq.get(key) == 1) {
                // Continue checking other subarrays.
            }
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}