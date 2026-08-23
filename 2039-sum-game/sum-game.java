class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int left = 0;
        int right = 0;

        int qLeft = 0;
        int qRight = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                qLeft++;
            } else {
                left += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                qRight++;
            } else {
                right += num.charAt(i) - '0';
            }
        }

        int diff = 2 * (left - right) + 9 * (qLeft - qRight);

        return diff != 0;
    }
}