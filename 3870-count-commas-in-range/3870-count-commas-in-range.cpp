class Solution {
public:
    int countCommas(int n) {
        int noOfDigits = 0;
        int temp = n;

        while(temp) {
            temp /= 10;
            noOfDigits++;
        }

        if(noOfDigits < 4) return 0;

        int count = 0;

        for(int i = 1000; i <=n; i++)  count++;

        return count;
    }
};