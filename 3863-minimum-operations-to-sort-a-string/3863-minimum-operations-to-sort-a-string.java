class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char mn = 'z', mx = 'a';

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            mn = (char) Math.min(mn, c);
            mx = (char) Math.max(mx, c);
        }

        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;
        if (n == 2) return -1;
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;

        boolean minInProperPrefix = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == mn) {
                minInProperPrefix = true;
                break;
            }
        }

        boolean maxInProperSuffix = false;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == mx) {
                maxInProperSuffix = true;
                break;
            }
        }

        return (minInProperPrefix || maxInProperSuffix) ? 2 : 3;
    }
}