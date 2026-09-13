class Solution {

    public void sortColors(int[] nums) {

        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {          

            if (nums[mid] == 0) {

                int t = nums[low]; nums[low] = nums[mid]; nums[mid] = t;

                low++; mid++;

            } else if (nums[mid] == 1) {

                mid++;

            } else {

                int t = nums[mid]; nums[mid] = nums[high]; nums[high] = t;

                high--;

            

            }

        }

    }

}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna