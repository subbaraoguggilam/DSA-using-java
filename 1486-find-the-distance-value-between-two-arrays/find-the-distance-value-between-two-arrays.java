import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;

        for (int x : arr1) {
            // Find the first index in arr2 with value >= x - d
            int lo = 0, hi = arr2.length - 1, idx = arr2.length;
            int target = x - d;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (arr2[mid] >= target) {
                    idx = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            // If that element is within x + d, it violates the condition
            if (idx == arr2.length || arr2[idx] > x + d) {
                count++;
            }
        }

        return count;
    }
}