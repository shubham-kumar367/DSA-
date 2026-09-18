class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.size();

        vector<int> first(26, n);
        vector<int> last(26, -1);

        for (int i = 0; i < n; ++i) {
            int c = s[i] - 'a';
            first[c] = min(first[c], i);
            last[c] = i;
        }

        vector<string> ans;
        int prevEnd = -1;

        for (int i = 0; i < n; ++i) {
            int ch = s[i] - 'a';

            // Minimal interval can only start at first occurrence.
            if (first[ch] != i)
                continue;

            int right = last[ch];
            bool valid = true;

            for (int j = i; j <= right; ++j) {
                int c = s[j] - 'a';

                if (first[c] < i) {
                    valid = false;
                    break;
                }

                right = max(right, last[c]);
            }

            if (!valid)
                continue;

            if (i > prevEnd) {
                ans.push_back(s.substr(i, right - i + 1));
            } else {
                ans.back() = s.substr(i, right - i + 1);
            }

            prevEnd = right;
        }

        return ans;
    }
};