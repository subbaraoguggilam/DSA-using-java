class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // best[i] = length of the shortest subarray with sum == target 
        // that ends at some index <= i (or Integer.MAX_VALUE if none exists)
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int minLenSoFar = Integer.MAX_VALUE; // shortest qualifying subarray seen so far
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int curLen = right - left + 1;

                // Try to pair with the best subarray ending before "left"
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, best[left - 1] + curLen);
                }

                minLenSoFar = Math.min(minLenSoFar, curLen);
            }

            best[right] = minLenSoFar;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}