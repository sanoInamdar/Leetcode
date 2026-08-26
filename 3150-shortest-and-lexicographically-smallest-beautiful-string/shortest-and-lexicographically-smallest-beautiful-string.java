class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        
        int[] pos = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                pos[count++] = i;
            }
        }

       
        if (count < k) {
            return "";
        }

        int minLen = Integer.MAX_VALUE;
        String answer = "";

        
        for (int i = 0; i + k - 1 < count; i++) {

            int first = pos[i];
            int last = pos[i + k - 1];

            int start = first;
            int end = last;
            String current = s.substring(start, end + 1);

            int len = current.length();

            if (len < minLen) {
                minLen = len;
                answer = current;
            } else if (len == minLen && current.compareTo(answer) < 0) {
                answer = current;
            }
        }

        return answer;
    }
}