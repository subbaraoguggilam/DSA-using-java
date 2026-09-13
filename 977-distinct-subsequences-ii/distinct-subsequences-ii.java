class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[26];
        long total = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            long newCount = (total + 1) % MOD;
            total = (total - dp[c] + newCount + MOD) % MOD;
            dp[c] = newCount;
        }
        
        return (int) total;
    }
}