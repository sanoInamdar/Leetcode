class Solution {
    public int missingMultiple(int[] nums, int k) {
       HashSet<Integer>set=new HashSet<>();
       for(int m:nums){
        set.add(m);
       }
        int res=k;
       while(set.contains(res)){
        res+=k;
       }
       return res;
     
        
    }
}