import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] a = new long[nums.length];

        for (int i = 0; i < nums.length; i++)
            a[i] = 1L * nums[i] * nums[i];

        Arrays.sort(a);

        long sum = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            if (i >= n / 2)
                sum += a[i];
            else
                sum -= a[i];
        }

        return sum;
    }
}