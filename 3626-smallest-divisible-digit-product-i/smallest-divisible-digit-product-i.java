class Solution {
    public int smallestNumber(int n, int t) {
       int curr=n;
       while(true){
        int temp=curr;
       int prod=1;
       while(temp>0){
        int digit=temp%10;
        prod*=digit;
        temp/=10;
       }
       if(prod%t==0){
        return curr;
       }
       curr++;
       }
    }
}