class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;

        int odd = 0;
        int even = 0;

        for (int num : nums1) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        
        if (odd == 0 || even == 0) {
            return true;
        }

        return odd >= 1;
        
    }
}