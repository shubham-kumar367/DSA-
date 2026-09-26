class Solution {
public:
    string evaluate(string s, vector<vector<string>>& knowledge) {
        string ans = "";
        unordered_map<string, string> mp;

        for(auto it : knowledge) mp[it[0]] = it[1];

        for(int i = 0; i < s.size(); i++) {
            if(s[i] != '(') ans += s[i];
            else {

                int j = i + 1;

                while(s[j] != ')') j++;

                string key = s.substr(i + 1, j - i -1);

                if(mp.count(key)) ans += mp[key];
                else ans += "?";

                i = j;
            }
        }
        
        return ans; 
    }
};