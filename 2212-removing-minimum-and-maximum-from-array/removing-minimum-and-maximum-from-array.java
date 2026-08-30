class Solution {
    public int minimumDeletions(int[] nums) {
        
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find min and max indices
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make sure minIndex is the smaller index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // 1. Remove both from the front
        int front = right + 1;

        // 2. Remove both from the back
        int back = n - left;

        // 3. Remove left element from front and right element from back
        int both = (left + 1) + (n - right);

        return Math.min(front, Math.min(back, both));
    
    }
}