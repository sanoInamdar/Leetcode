class Solution {
    public int thirdMax(int[] nums) {
     long f=Long.MIN_VALUE;
     long s=Long.MIN_VALUE;
     long t=Long.MIN_VALUE;
     int cnt=0;

     for(int num:nums){
        if (num == f || num == s || num == t) {
                continue;
            }
            cnt++;
        if(f<num){
            t=s;
            s=f;
            f=num;
        }
        else if(s<num){
            t=s;
            s=num;
        }
        else if(num>t){
            t=num;
        }
     }
     if (cnt < 3) {
            return (int)f;
        }
     return (int)t;
        
    }
}