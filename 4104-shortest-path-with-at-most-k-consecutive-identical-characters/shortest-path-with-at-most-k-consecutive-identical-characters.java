import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (n == 1) return 0;

        // adjacency list using arrays
        int m = edges.length;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[m];
        int[] to = new int[m];
        int[] wt = new int[m];
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            wt[i] = edges[i][2];
            nxt[i] = head[u];
            head[u] = i;
        }

        char[] lab = labels.toCharArray();
        int total = n * k;
        long[] dist = new long[total];
        Arrays.fill(dist, Long.MAX_VALUE);

        // PQ entries: (dist << 22) | state   (state < 2^22 since n*k <= 2.5M)
        PriorityQueue<Long> pq = new PriorityQueue<>();
        dist[0] = 0; // node 0, run length 1 -> index 0*k + 0
        pq.add(0L);

        final int SHIFT = 22;
        final long MASK = (1L << SHIFT) - 1;

        while (!pq.isEmpty()) {
            long top = pq.poll();
            int state = (int) (top & MASK);
            long d = top >>> SHIFT;
            if (d > dist[state]) continue;

            int u = state / k;
            int run = state % k + 1;

            if (u == n - 1) return (int) d;

            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                int newRun;
                if (lab[v] == lab[u]) {
                    newRun = run + 1;
                    if (newRun > k) continue;
                } else {
                    newRun = 1;
                }
                int ns = v * k + (newRun - 1);
                long nd = d + wt[e];
                if (nd < dist[ns]) {
                    dist[ns] = nd;
                    pq.add((nd << SHIFT) | ns);
                }
            }
        }
        return -1;
    }
}