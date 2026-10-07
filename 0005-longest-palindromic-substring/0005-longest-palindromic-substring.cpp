// class Solution {
// public:
//     bool checkPalindrome(string temp) {
//         string result = temp;
//         reverse(temp.begin(), temp.end());
//         if(result == temp) return true;
//         else return false;
//     }
//     string longestPalindrome(string s) {
//         string ans = "";
//         int maxi = INT_MIN;
//         for(int i = 0; i < s.size(); i++) {
//             for(int j = i; j < s.size(); j++) {
//                 string temp = s.substr(i, j - i + 1);
//                 if(checkPalindrome(temp)) {
//                     maxi = max(maxi, (int)temp.size());
//                     if(temp.size() == maxi) ans = temp;
//                 }
//             }
//         }
//         return ans;
//     }
// }; //It gives TLE

class Solution {
public:
    string longestPalindrome(string str) {
        if (str.length() <= 1)
            return str;

        string LPS = "";

        for (int i = 1; i < str.length(); i++) {
            // For odd lenth palindrome
            int low = i;
            int high = i;

            while (str[low] == str[high]) {
                low--;
                high++;

                if (low == -1 || high == str.length())
                    break;
            }

            string palindrome = str.substr(low + 1, high - low - 1);
            

            if (palindrome.length() > LPS.length()) {
                LPS = palindrome;
            }
            // Even length Palindrome
            low = i - 1;
            high = i;

            while (str[low] == str[high]) {
                low--;
                high++;

                if (low == -1 || high == str.length())
                    break;
            }

            palindrome = str.substr(low + 1, high - low - 1);

            if (palindrome.length() > LPS.length()) {
                LPS = palindrome;
            }
        }

        return LPS;
    }
};