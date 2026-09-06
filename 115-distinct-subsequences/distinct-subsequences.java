class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) return 0;
        
        // dp[j] = number of distinct subsequences of s (processed so far) equal to t[0..j-1]
        int[] dp = new int[n + 1];
        dp[0] = 1; // empty t can always be formed in exactly one way (by choosing nothing)
        
        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            // iterate j backwards so dp[j-1] used is from previous row (before updating this row)
            for (int j = n; j >= 1; j--) {
                char tc = t.charAt(j - 1);
                if (sc == tc) {
                    dp[j] = dp[j] + dp[j - 1];
                }
                // else dp[j] stays the same (not including s[i-1])
            }
        }
        
        return dp[n];
    }
}