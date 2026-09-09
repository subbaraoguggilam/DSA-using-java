class Solution {
    public long countCommas(long n) {
        long total = 0;
        long low = 1;      // start of current digit-length range
        long pow = 10;     // 10^d
        int d = 1;
        
        while (low <= n) {
            long high = Math.min(n, pow - 1);
            long count = high - low + 1;
            long commasPerNumber = (d - 1) / 3;
            total += count * commasPerNumber;
            
            low = pow;
            // guard against overflow when pow would exceed Long range
            if (pow > Long.MAX_VALUE / 10) {
                pow = Long.MAX_VALUE; // low will now exceed n eventually
            } else {
                pow *= 10;
            }
            d++;
        }
        
        return total;
    }
}