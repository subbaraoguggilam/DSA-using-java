class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long c1 = cost1, c2 = cost2, cb = costBoth;
        long n1 = need1, n2 = need2;

        long m = Math.min(n1, n2);
        long M = Math.max(n1, n2);

        long best = Long.MAX_VALUE;
        long[] ks = {0, m, M};
        for (long k : ks) {
            long total = k * cb
                    + Math.max(0L, n1 - k) * c1
                    + Math.max(0L, n2 - k) * c2;
            best = Math.min(best, total);
        }
        return best;
    }
}