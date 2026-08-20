class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for (int right=0; right < nums.length; right++){
            sum += nums[right];

            while(sum >= target){
                minLen = Math.min(minLen, right-left+1);
                 sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0:minLen; 
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna