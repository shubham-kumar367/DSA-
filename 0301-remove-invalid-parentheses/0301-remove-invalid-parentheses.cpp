// Brute force sol--->
// class Solution {
// public:
//     vector<string> ans;
//     int mn = INT_MAX;

//     bool valid(string s)
//     {
//         int cnt = 0;

//         for(char c : s)
//         {
//             if(c == '(')
//                 cnt++;
//             else if(c == ')')
//             {
//                 cnt--;

//                 if(cnt < 0)
//                     return false;
//             }
//         }

//         return cnt == 0;
//     }

//     void solve(string &s, int i, string curr, int removed)
//     {
//         if(i == s.size())
//         {
//             if(valid(curr))
//             {
//                 if(removed < mn)
//                 {
//                     mn = removed;
//                     ans.clear();
//                     ans.push_back(curr);
//                 }
//                 else if(removed == mn)
//                 {
//                     ans.push_back(curr);
//                 }
//             }
//             return;
//         }

//         if(s[i] != '(' && s[i] != ')')
//         {
//             solve(s, i + 1, curr + s[i], removed);
//         }
//         else
//         {
//             solve(s, i + 1, curr + s[i], removed);
//             solve(s, i + 1, curr, removed + 1);
//         }
//     }

//     vector<string> removeInvalidParentheses(string s) {
//         solve(s, 0, "", 0);

//         sort(ans.begin(), ans.end());
//         ans.erase(unique(ans.begin(), ans.end()), ans.end());

//         return ans;
//     }
// }; It gives MLE

// Optimal sol---> Using Backtraking

class Solution {
public:
    vector<string> removeInvalidParentheses(string s) {
        int leftRemove = 0, rightRemove = 0;

        for (char c : s) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) leftRemove--;
                else rightRemove++;
            }
        }

        unordered_set<string> ans;
        string path;

        function<void(int, int, int, int)> backtrack =
            [&](int i, int open, int leftRem, int rightRem) {
                if (i == s.size()) {
                    if (open == 0 && leftRem == 0 && rightRem == 0) {
                        ans.insert(path);
                    }
                    return;
                }

                char c = s[i];

                if (c == '(') {
                    if (leftRem > 0) {
                        backtrack(i + 1, open, leftRem - 1, rightRem);
                    }

                    path.push_back(c);
                    backtrack(i + 1, open + 1, leftRem, rightRem);
                    path.pop_back();

                } else if (c == ')') {
                    if (rightRem > 0) {
                        backtrack(i + 1, open, leftRem, rightRem - 1);
                    }

                    if (open > 0) {
                        path.push_back(c);
                        backtrack(i + 1, open - 1, leftRem, rightRem);
                        path.pop_back();
                    }

                } else {
                    path.push_back(c);
                    backtrack(i + 1, open, leftRem, rightRem);
                    path.pop_back();
                }
            };

        backtrack(0, 0, leftRemove, rightRemove);

        return vector<string>(ans.begin(), ans.end());
    }
};