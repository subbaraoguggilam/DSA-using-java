class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        if (n <= 1) return true;

        int min = Integer.MAX_VALUE;
        int minOdd = Integer.MAX_VALUE;

        for (int v : nums1){
            if (v < min) min = v;
            if ((v & 1)==1 && v < minOdd) minOdd = v;
        }

        int p = min & 1;

        for (int v: nums1){
            int parity = v & 1;
            if (parity == p) continue;
            if (!(minOdd < v)) return false;
        }
        return true;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna