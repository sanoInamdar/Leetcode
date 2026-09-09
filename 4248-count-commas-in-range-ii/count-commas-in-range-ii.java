class Solution {
    public long countCommas(long n) {
        long total = 0;
        
        for (long power = 1000; power <= n; power *= 1000) {
            total += n - power + 1;
            
            if (power > n / 1000) {
                break;
            }
        }
        
        return total;
    }
}