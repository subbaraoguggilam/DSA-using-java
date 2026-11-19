class Solution {
    public int minimizeSum(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            // Track three smallest: min1 <= min2 <= min3
            if (x < min1) {
                min3 = min2;
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min3 = min2;
                min2 = x;
            } else if (x < min3) {
                min3 = x;
            }

            // Track three largest: max1 >= max2 >= max3
            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        // Option 1: change two largest -> range = max3 - min1
        // Option 2: change two smallest -> range = max1 - min3
        // Option 3: change one smallest and one largest -> range = max2 - min2
        return Math.min(max3 - min1, Math.min(max1 - min3, max2 - min2));
    }
}