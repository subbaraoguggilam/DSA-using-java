import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> intervals.get(a).get(1) - intervals.get(b).get(1));

        int[] lArr = new int[n];
        int[] rArr = new int[n];
        long[] wArr = new long[n];
        int[] origIdx = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(order[i]);
            lArr[i] = iv.get(0);
            rArr[i] = iv.get(1);
            wArr[i] = iv.get(2);
            origIdx[i] = order[i];
        }

        final long NEG = Long.MIN_VALUE / 2;
        long[][] score = new long[n + 1][5];
        int[][][] idxArr = new int[n + 1][5][];

        for (long[] row : score) Arrays.fill(row, NEG);
        score[0][0] = 0;
        idxArr[0][0] = new int[0];

        for (int i = 1; i <= n; i++) {
            int cur = i - 1;
            score[i][0] = 0;
            idxArr[i][0] = new int[0];

            int p = lowerBound(rArr, 0, cur, lArr[cur]);

            for (int k = 1; k <= 4; k++) {
                long bestScore = score[i - 1][k];
                int[] bestIdx = idxArr[i - 1][k];

                if (score[p][k - 1] > NEG) {
                    long candScore = score[p][k - 1] + wArr[cur];
                    int[] prevIdx = idxArr[p][k - 1];
                    int[] candIdx = insertSorted(prevIdx, origIdx[cur]);

                    if (candScore > bestScore
                            || (candScore == bestScore && (bestIdx == null || lexSmaller(candIdx, bestIdx)))) {
                        bestScore = candScore;
                        bestIdx = candIdx;
                    }
                }

                score[i][k] = bestScore;
                idxArr[i][k] = bestIdx;
            }
        }

        long bestScore = score[n][0];
        int[] bestIdx = idxArr[n][0];
        for (int k = 1; k <= 4; k++) {
            if (score[n][k] > NEG) {
                if (score[n][k] > bestScore
                        || (score[n][k] == bestScore && lexSmaller(idxArr[n][k], bestIdx))) {
                    bestScore = score[n][k];
                    bestIdx = idxArr[n][k];
                }
            }
        }

        return bestIdx;
    }

    // first index in [start, end) with arr[idx] >= target; end if none
    private int lowerBound(int[] arr, int start, int end, int target) {
        int lo = start, hi = end;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int pos = 0;
        while (pos < arr.length && arr[pos] < val) {
            res[pos] = arr[pos];
            pos++;
        }
        res[pos] = val;
        for (int j = pos; j < arr.length; j++) {
            res[j + 1] = arr[j];
        }
        return res;
    }

    private boolean lexSmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return a.length < b.length;
    }
}