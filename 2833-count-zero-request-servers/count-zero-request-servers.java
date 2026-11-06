import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));

        int[] cnt = new int[n + 1];
        int[] res = new int[m];
        int active = 0;
        int left = 0, right = 0;
        int L = logs.length;

        for (int idx : order) {
            int q = queries[idx];

            // add logs with time <= q
            while (right < L && logs[right][1] <= q) {
                if (cnt[logs[right][0]]++ == 0) active++;
                right++;
            }

            // remove logs with time < q - x
            while (left < right && logs[left][1] < q - x) {
                if (--cnt[logs[left][0]] == 0) active--;
                left++;
            }

            res[idx] = n - active;
        }

        return res;
    }
}