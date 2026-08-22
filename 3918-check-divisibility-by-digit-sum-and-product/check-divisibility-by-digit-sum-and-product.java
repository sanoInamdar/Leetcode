class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int prod=1;
        int num=n;
        while(n>0){
            int dig=n%10;
            sum+=dig;
            prod*=dig;
            n/=10;
        }
        int total=sum+prod;
        if(num%total==0){
            return true;
        }
        return false;

        
    }
}