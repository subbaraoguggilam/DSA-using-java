class Solution {
    public boolean uniformArray(int[] nums1) {
        // It's always possible:
        // - If no odd numbers exist, keep everything as-is (all even).
        // - If at least one odd number exists, every even element can subtract
        //   that odd element to flip to odd, while odd elements stay as-is,
        //   making everything odd.
        // Hence a valid nums2 always exists.
        return true;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna