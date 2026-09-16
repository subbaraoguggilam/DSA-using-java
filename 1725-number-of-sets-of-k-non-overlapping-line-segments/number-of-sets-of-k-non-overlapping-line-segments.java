class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        int maxN = n + k; // enough size
        long[] fact = new long[maxN + 1];
        long[] invFact = new long[maxN + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[maxN] = modPow(fact[maxN], MOD - 2, MOD);
        for (int i = maxN; i > 0; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }
        
        int top = n + k - 1;
        int bottom = 2 * k;
        if (bottom > top) return 0;
        
        long result = fact[top] * invFact[bottom] % MOD * invFact[top - bottom] % MOD;
        return (int) result;
    }
    
    private long modPow(long base, long exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }
}