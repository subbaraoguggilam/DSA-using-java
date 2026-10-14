class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) return "";
        
        char[] res = new char[num];
        int remaining = sum;
        
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, remaining);
            res[i] = (char) ('0' + d);
            remaining -= d;
        }
        
        return new String(res);
    }
}