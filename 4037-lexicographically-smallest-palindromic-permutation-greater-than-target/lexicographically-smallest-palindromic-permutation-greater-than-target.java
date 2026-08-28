class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int halfLen = n / 2;

        int[] freq = new int[26];

        // Count characters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check palindrome possibility
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Frequency of characters available in first half
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String targetHalf = target.substring(0, halfLen);

        /*
         * First check whether targetHalf itself can be formed.
         */
        int[] temp = halfFreq.clone();
        boolean canMakeExact = true;

        for (int i = 0; i < halfLen; i++) {
            int c = targetHalf.charAt(i) - 'a';

            if (temp[c] == 0) {
                canMakeExact = false;
                break;
            }

            temp[c]--;
        }

        /*
         * If we can make exactly targetHalf,
         * build the palindrome and check it directly.
         */
        if (canMakeExact) {

            char[] half = targetHalf.toCharArray();

            String candidate = buildPalindrome(half, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Now find the smallest half greater than targetHalf.
         *
         * We try changing a position from right to left.
         */
        for (int pos = halfLen - 1; pos >= 0; pos--) {

            int[] remaining = halfFreq.clone();

            boolean possible = true;

            /*
             * Keep everything before pos equal to target.
             */
            for (int i = 0; i < pos; i++) {

                int c = targetHalf.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            /*
             * At pos, choose the smallest character
             * greater than target[pos].
             */
            int targetChar = targetHalf.charAt(pos) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] == 0) {
                    continue;
                }

                char[] half = new char[halfLen];

                // Copy prefix
                for (int i = 0; i < pos; i++) {
                    half[i] = targetHalf.charAt(i);
                }

                // Make the first difference
                half[pos] = (char) ('a' + c);

                remaining[c]--;

                // Fill remaining positions with smallest characters
                int index = pos + 1;

                for (int x = 0; x < 26; x++) {

                    while (remaining[x] > 0) {
                        half[index++] = (char) ('a' + x);
                        remaining[x]--;
                    }
                }

                String candidate = buildPalindrome(half, middle, n);

                if (candidate.compareTo(target) > 0) {
                    return candidate;
                }
            }
        }

        return "";
    }

    private String buildPalindrome(
            char[] half,
            char middle,
            int n) {

        StringBuilder sb = new StringBuilder();

        // First half
        for (char c : half) {
            sb.append(c);
        }

        // Middle character
        if (n % 2 == 1) {
            sb.append(middle);
        }

        // Reverse first half
        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
}