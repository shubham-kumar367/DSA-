class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] s = nums.clone();
        Arrays.sort(s);
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            boolean ok = true;

            for (int i = 0; i < n && ok; i += k) {
                int[] pi = new int[k];
                for (int j = 1, len = 0; j < k; j++) {
                    while (len > 0 && s[i + j] != s[i + len])
                        len = pi[len - 1];
                    if (s[i + j] == s[i + len]) len++;
                    pi[j] = len;
                }

                int len = 0;
                boolean found = false;

                for (int j = 0; j < 2 * k - 1; j++) {
                    int x = nums[i + j % k];
                    while (len > 0 && x != s[i + len])
                        len = pi[len - 1];
                    if (x == s[i + len]) len++;
                    if (len == k) {
                        found = true;
                        break;
                    }
                }

                if (!found) ok = false;
            }

            if (ok) ans += k;
        }

        return ans;
    }
}