class Solution {
public:
    set<string> merge(set<string> a, set<string> b) {
        set<string> res;
        for(string x : a)
            for(string y : b)
                res.insert(x + y);
        return res;
    }
    set<string> solve(string &s, int &i) {
        set<string> res;
        set<string> cur;
        while(i < s.size() && s[i] != '}') {
            if(s[i] == '{') {
                i++;
                set<string> temp = solve(s, i);
                cur = merge(cur.empty() ? set<string>{""} : cur, temp);
                i++;
            }
            else if(s[i] == ',') {
                res.insert(cur.begin(), cur.end());
                cur.clear();
                i++;
            }
            else {
                set<string> temp = {string(1, s[i])};
                cur = merge(cur.empty() ? set<string>{""} : cur, temp);
                i++;
            }
        }
        
        res.insert(cur.begin(), cur.end());
        return res;
    }
    vector<string> braceExpansionII(string expression) {
        int i = 0;
        set<string> ans = solve(expression, i);
        return vector<string>(ans.begin(), ans.end());
    }
};