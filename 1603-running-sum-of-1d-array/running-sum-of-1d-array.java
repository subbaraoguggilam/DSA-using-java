class Solution {
    public int[] runningSum(int[] nums) {
        int running = 0;
        int[] res = new int[nums.length];
        for (int i=0; i < nums.length; i++){
            running += nums[i];
            res[i]=running;
        }
        return res;
    }
}