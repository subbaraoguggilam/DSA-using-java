class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long count = 0;
        int even = 1; // empty prefix has sum 0 (even)
        int odd = 0;
        int parity = 0;

        for (int num : arr) {
            parity = (parity + num) & 1;
            if (parity == 1) {
                count += even; // need an earlier even prefix
                odd++;
            } else {
                count += odd;  // need an earlier odd prefix
                even++;
            }
        }
        return (int) (count % MOD);
    }
}