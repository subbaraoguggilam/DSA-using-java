import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[1_000_002 + 1];
        int ans = 1;
        
        for (int x : nums) {
            // Update x+1 first so this element isn't reused at both x and x+1
            dp[x + 1] = dp[x] + 1;
            dp[x] = dp[x - 1] + 1;
            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }
        
        return ans;
    }
}