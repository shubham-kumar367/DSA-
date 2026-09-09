class Solution {
public:
    long long countCommas(long long n) {
        long long count = 0;
        
        long long power = 1000;
        long long commas = 1;

        while (power <= n) {
            long long end = power * 1000 - 1;
            
            if (end > n)
                end = n;

            count += (end - power + 1) * commas;

            if (power > n / 1000)
                break;

            power *= 1000;
            commas++;
        }

        return count;
    }
};