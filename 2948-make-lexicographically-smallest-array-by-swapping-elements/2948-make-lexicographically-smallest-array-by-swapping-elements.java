class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        
        // Sort indices by their value
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);
        
        int[] result = new int[n];
        
        int i = 0;
        while (i < n) {
            int j = i;
            // Extend the group while consecutive sorted values are within limit
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) {
                j++;
            }
            
            // Group is from i to j (inclusive) in sorted order
            // Collect the original indices in this group and sort them
            List<Integer> groupIndices = new ArrayList<>();
            for (int k = i; k <= j; k++) {
                groupIndices.add(idx[k]);
            }
            Collections.sort(groupIndices);
            
            // Assign sorted values to sorted positions
            for (int k = 0; k < groupIndices.size(); k++) {
                result[groupIndices.get(k)] = nums[idx[i + k]];
            }
            
            i = j + 1;
        }
        
        return result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna