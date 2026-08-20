class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum=0;
        // int curr=0;
        int cnt=0;
        for(int i=0;i<k;i++){
            sum+=arr[i];
        }
        if(sum/k>=threshold){
            cnt++;
        }

        for(int i=k;i<=arr.length-1;i++){
            sum=sum+arr[i]-arr[i-k];

           if(sum/k>=threshold){
            cnt++;

        }
        }
        
        return cnt;

        
    }
}