class Solution {
public:
    using ll = long long;

    struct State {
        ll score = 0;
        vector<int> ids;
    };

    bool better(const State& a, const State& b) {
        if (a.score != b.score)
            return a.score > b.score;

        return a.ids < b.ids;
    }

    vector<int> maximumWeight(vector<vector<int>>& intervals) {
        int n = intervals.size();

        vector<array<ll, 4>> a(n);

        for (int i = 0; i < n; i++) {
            a[i] = {
                intervals[i][0],
                intervals[i][1],
                intervals[i][2],
                i
            };
        }

        sort(a.begin(), a.end(), [](const auto& x, const auto& y) {
            if (x[1] != y[1])
                return x[1] < y[1];

            return x[0] < y[0];
        });

        vector<ll> end(n);
        for (int i = 0; i < n; i++)
            end[i] = a[i][1];

        vector<int> prev(n);

        for (int i = 0; i < n; i++) {
            prev[i] =
                upper_bound(end.begin(), end.begin() + i, a[i][0] - 1)
                - end.begin();
        }

        vector<vector<State>> dp(5, vector<State>(n + 1));

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {

                dp[k][i] = dp[k][i - 1];
           
                int idx = i - 1;

                State take;
                take.score =
                    a[idx][2] + dp[k - 1][prev[idx]].score;

                take.ids = dp[k - 1][prev[idx]].ids;
                take.ids.push_back((int)a[idx][3]);
               
                sort(take.ids.begin(), take.ids.end());

                if (better(take, dp[k][i]))
                    dp[k][i] = take;
            }
        }

        return dp[4][n].ids;
    }
};