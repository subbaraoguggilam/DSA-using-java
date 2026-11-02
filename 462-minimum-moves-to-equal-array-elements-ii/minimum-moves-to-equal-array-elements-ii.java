import java.util.Random;

class Solution {
    public int minMoves2(int[] nums) {
        int median = select(nums, nums.length / 2);
        int moves = 0;
        for (int x : nums) {
            moves += Math.abs(x - median);
        }
        return moves;
    }

    private int select(int[] a, int k) {
        Random rnd = new Random();
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int pivot = a[lo + rnd.nextInt(hi - lo + 1)];
            int lt = lo, gt = hi, i = lo;
            // [lo..lt-1] < pivot, [lt..gt] == pivot, [gt+1..hi] > pivot
            while (i <= gt) {
                if (a[i] < pivot) swap(a, lt++, i++);
                else if (a[i] > pivot) swap(a, i, gt--);
                else i++;
            }
            if (k < lt) hi = lt - 1;
            else if (k > gt) lo = gt + 1;
            else return a[k];
        }
        return a[lo];
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}