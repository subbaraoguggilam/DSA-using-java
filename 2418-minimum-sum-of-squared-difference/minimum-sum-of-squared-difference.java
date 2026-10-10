class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int max = 0;
        long total = 0;
        int[] freq = new int[100002];
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            freq[d]++;
            total += d;
            max = Math.max(max, d);
        }

        // Everything can be reduced to zero
        if (total <= k) return 0;

        long cnt = 0;      // number of elements currently at level >= d (flattened to d)
        int level = max;   // current top level after flattening

        for (int d = max; d >= 1; d--) {
            cnt += freq[d];
            if (cnt == 0) continue;

            if (k >= cnt) {
                // lower all cnt elements from d to d-1
                k -= cnt;
                level = d - 1;
            } else {
                // can only lower k of them to d-1; the rest stay at d
                level = d;
                break;
            }
        }

        // Rebuild answer
        long result = 0;
        if (level == 0 && cnt > 0 && k >= 0) {
            // all flattened elements reached level 0 (handled by total <= k, but kept for safety)
        }

        // Elements originally below the flatten level keep their own values
        for (int d = 1; d < level; d++) {
            result += (long) freq[d] * d * d;
        }

        // Elements at flattened level: cnt of them are at 'level' (or level after partial)
        // Recompute cnt = number of elements with original diff >= level
        long atLevel = 0;
        for (int d = level; d <= max; d++) atLevel += freq[d];

        // k leftover operations lower 'k' of these elements by 1 more (only if level >= 1)
        long lowered = Math.min(k, atLevel);
        if (level >= 1) {
            result += lowered * (long) (level - 1) * (level - 1);
            result += (atLevel - lowered) * (long) level * level;
        }

        return result;
    }
}