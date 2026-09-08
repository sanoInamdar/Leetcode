class Solution {
    public int countCommas(int n) {

         long total = 0;

        for (long start = 1000; start <= n; start *= 1000) {
            long end = Math.min(n, start * 1000 - 1);
            total += end - start + 1;
        }

        return (int)total;
        
    }
}