class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = Integer.MAX_VALUE / 2;
        char[] c = s.toCharArray();

        // Precompute divisors for each length
        int[][] cost = new int[n][n];
        for (int[] row : cost) java.util.Arrays.fill(row, INF);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int best = INF;
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;
                    int groupSize = len / d;
                    int changes = 0;
                    for (int r = 0; r < d; r++) {
                        // group indices: i + r + t*d, t = 0..groupSize-1
                        int lo = 0, hi = groupSize - 1;
                        while (lo < hi) {
                            if (c[i + r + lo * d] != c[i + r + hi * d]) changes++;
                            lo++;
                            hi--;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int j = 2 * p; j <= n; j++) {
                // part is s[i..j-1], needs length >= 2
                for (int i = 2 * (p - 1); i <= j - 2; i++) {
                    if (dp[p - 1][i] >= INF || cost[i][j - 1] >= INF) continue;
                    dp[p][j] = Math.min(dp[p][j], dp[p - 1][i] + cost[i][j - 1]);
                }
            }
        }
        return dp[k][n];
    }
}