class Solution {
    public int maxVowels(String s, int k) {
        char[] arr = s.toCharArray();

        int cnt = 0;
        int max = 0;

       
        for (int i = 0; i < k; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' ||
                arr[i] == 'o' || arr[i] == 'u') {
                cnt++;
            }
        }

        max = cnt;

        
        for (int i = k; i < arr.length; i++) {

            
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' ||
                arr[i] == 'o' || arr[i] == 'u') {
                cnt++;
            }

            if (arr[i - k] == 'a' || arr[i - k] == 'e' ||
                arr[i - k] == 'i' || arr[i - k] == 'o' ||
                arr[i - k] == 'u') {
                cnt--;
            }

            max = Math.max(max, cnt);
        }

        return max;
    }
}