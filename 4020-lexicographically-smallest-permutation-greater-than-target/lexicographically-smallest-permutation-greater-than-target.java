class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int lastFeasibleIndex = -1;
        int[] savedCnt = null;
        int[] cur = cnt.clone();

        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';

            // Can we place something strictly greater than target[i] here?
            boolean feasible = false;
            for (int d = c + 1; d < 26; d++) {
                if (cur[d] > 0) { feasible = true; break; }
            }
            if (feasible) {
                lastFeasibleIndex = i;
                savedCnt = cur.clone();
            }

            // Try to match target[i] exactly to extend the prefix
            if (cur[c] > 0) {
                cur[c]--;
            } else {
                break; // can't extend prefix any further
            }
        }

        if (lastFeasibleIndex == -1) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, lastFeasibleIndex); // exact-match prefix

        int tc = target.charAt(lastFeasibleIndex) - 'a';
        int chosen = -1;
        for (int d = tc + 1; d < 26; d++) {
            if (savedCnt[d] > 0) { chosen = d; break; }
        }
        savedCnt[chosen]--;
        sb.append((char) ('a' + chosen));

        // append all remaining characters in ascending order
        for (int d = 0; d < 26; d++) {
            for (int k = 0; k < savedCnt[d]; k++) {
                sb.append((char) ('a' + d));
            }
        }

        return sb.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna