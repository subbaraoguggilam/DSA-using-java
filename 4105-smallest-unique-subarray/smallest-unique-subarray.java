import java.util.*;

class Solution {
    private static final long MOD = (1L << 61) - 1;

    private static long mul(long a, long b) {
        long hi = Math.multiplyHigh(a, b);
        long lo = a * b;
        long r = (lo & MOD) + ((lo >>> 61) | (hi << 3));
        if (r >= MOD) r -= MOD;
        if (r >= MOD) r -= MOD;
        return r;
    }

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long base = 1_000_003L + new Random().nextInt(1_000_000_000);

        long[] h = new long[n + 1];
        long[] pw = new long[n + 1];
        pw[0] = 1;
        for (int i = 0; i < n; i++) {
            h[i + 1] = mul(h[i], base) + nums[i];
            if (h[i + 1] >= MOD) h[i + 1] -= MOD;
            pw[i + 1] = mul(pw[i], base);
        }

        int lo = 1, hi = n; // length n is always unique
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (hasUnique(h, pw, n, mid)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private boolean hasUnique(long[] h, long[] pw, int n, int len) {
        int cnt = n - len + 1;
        long[] arr = new long[cnt];
        for (int i = 0; i < cnt; i++) {
            long v = h[i + len] - mul(h[i], pw[len]);
            if (v < 0) v += MOD;
            arr[i] = v;
        }
        Arrays.sort(arr);
        for (int i = 0; i < cnt; i++) {
            boolean samePrev = i > 0 && arr[i] == arr[i - 1];
            boolean sameNext = i + 1 < cnt && arr[i] == arr[i + 1];
            if (!samePrev && !sameNext) return true;
        }
        return false;
    }
}