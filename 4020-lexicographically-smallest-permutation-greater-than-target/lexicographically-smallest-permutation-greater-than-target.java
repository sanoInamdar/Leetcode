class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {

            int[] count = freq.clone();

            boolean possible = true;

            // Match target[0 ... i-1]
            for (int j = 0; j < i; j++) {

                int x = target.charAt(j) - 'a';

                if (count[x] == 0) {
                    possible = false;
                    break;
                }

                count[x]--;
            }

            if (!possible) {
                continue;
            }

            // Find smallest character > target[i]
            int x = target.charAt(i) - 'a';

            for (int c = x + 1; c < 26; c++) {

                if (count[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Same prefix
                    ans.append(target.substring(0, i));

                    // First character greater than target
                    ans.append((char) ('a' + c));
                    count[c]--;

                    // Put remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            ans.append((char) ('a' + k));
                            count[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}