class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k) return 0;
        
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    isPal[i][j] = true;
                } else if (j == i + 1) {
                    isPal[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    isPal[i][j] = s.charAt(i) == s.charAt(j) && isPal[i + 1][j - 1];
                }
            }
        }
        
        int[] dp = new int[n + 1];
        // dp[i] = max number of non-overlapping valid palindromic substrings in s[0..i-1]
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // don't take a palindrome ending at i-1
            
            // Only need to check the two minimal lengths (k and k+1).
            // If a longer palindrome ending here exists, using the shortest
            // valid one is never worse (leaves more room for future picks),
            // and every longer palindrome's inner substring of length k or k+1
            // (same parity considerations) would also be checked at its own end point.
            for (int len = k; len <= k + 1; len++) {
                int start = i - len;
                if (start >= 0 && isPal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }
        
        return dp[n];
    }
}