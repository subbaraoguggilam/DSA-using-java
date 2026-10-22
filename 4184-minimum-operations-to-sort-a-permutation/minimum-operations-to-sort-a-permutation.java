class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        boolean inc = true, dec = true;
        for (int i = 0; i < n; i++) {
            int next = nums[(i + 1) % n];
            if (next != (nums[i] + 1) % n) inc = false;
            if (next != (nums[i] - 1 + n) % n) dec = false;
        }

        int ans = Integer.MAX_VALUE;

        if (inc) {
            int k = nums[0];
            if (k == 0) {
                ans = 0;
            } else {
                ans = Math.min(n - k, k + 2);
            }
        }

        if (dec) {
            int a = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == n - 1) {
                    a = i;
                    break;
                }
            }
            int cost = (a == 0) ? 1 : Math.min(a + 1, n - a + 1);
            ans = Math.min(ans, cost);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}