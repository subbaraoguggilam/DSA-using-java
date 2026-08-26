class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        // collect positions of all '1's
        java.util.List<Integer> ones = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') ones.add(i);
        }
        
        int total = ones.size();
        if (total < k) return "";
        
        String best = null;
        int bestLen = Integer.MAX_VALUE;
        
        for (int i = 0; i + k - 1 < total; i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            int len = end - start + 1;
            String candidate = s.substring(start, end + 1);
            
            if (len < bestLen) {
                bestLen = len;
                best = candidate;
            } else if (len == bestLen && candidate.compareTo(best) < 0) {
                best = candidate;
            }
        }
        
        return best == null ? "" : best;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna