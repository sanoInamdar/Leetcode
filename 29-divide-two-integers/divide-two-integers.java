class Solution {
    public int divide(int dividend, int divisor) {

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        boolean negative = (dividend < 0) ^ (divisor < 0);

        long ans = 0;

        while (a >= b) {

            long temp = b;
            long count = 1;

            while ((temp << 1) <= a) {
                temp = temp << 1;
                count = count << 1;
            }

            a = a - temp;
            ans = ans + count;
        }

        if (negative) {
            ans = -ans;
        }

        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return (int) ans;
    }
}