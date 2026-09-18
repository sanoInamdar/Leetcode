class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        // first[c] = first occurrence of character c
        // last[c]  = last occurrence of character c
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Store all valid intervals
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1)
                continue;

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            // Expand interval to include all occurrences
            // of every character inside it
            for (int i = left; i <= right; i++) {

                int current = s.charAt(i) - 'a';

                // This character occurs before 'left'
                // so the substring cannot contain all occurrences
                if (first[current] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> {
            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);

            return Integer.compare(a[0], b[0]);
        });

        List<String> result = new ArrayList<>();

        int prevEnd = -1;

        // Greedily choose the interval that ends earliest
        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                result.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return result;
    
        
    }
}