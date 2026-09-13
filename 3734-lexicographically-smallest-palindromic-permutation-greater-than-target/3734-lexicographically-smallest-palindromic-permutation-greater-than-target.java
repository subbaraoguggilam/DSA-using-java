class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        int oddCount = 0, oddChar = -1;
        for (int c = 0; c < 26; c++) {
            if (cnt[c] % 2 == 1) { oddCount++; oddChar = c; }
        }
        if (n % 2 == 0) { if (oddCount != 0) return ""; }
        else            { if (oddCount != 1) return ""; }

        int m = n / 2;
        int[] half = new int[26];
        for (int c = 0; c < 26; c++) half[c] = cnt[c] / 2;
        char midChar = (n % 2 == 1) ? (char) ('a' + oddChar) : '\0';

        // prefixRem[i] = half-multiset remaining after using target[0..i-1] as the prefix of H
        int[][] prefixRem = new int[m + 1][];
        prefixRem[0] = half.clone();
        int p = 0; // longest prefix of target[0..m-1] achievable from the half-multiset
        for (int j = 0; j < m; j++) {
            int idx = target.charAt(j) - 'a';
            if (prefixRem[j][idx] > 0) {
                int[] next = prefixRem[j].clone();
                next[idx]--;
                prefixRem[j + 1] = next;
                p = j + 1;
            } else break;
        }

        // Candidate 1: H matches target[0:m] exactly (only possible if p == m)
        if (p == m) {
            String H = target.substring(0, m);
            String F = H + (n % 2 == 1 ? String.valueOf(midChar) : "")
                         + new StringBuilder(H).reverse();
            if (F.compareTo(target) > 0) return F;
        }

        // Candidate 2: diverge at position i (try latest i first -> smallest result)
        int upper = Math.min(p, m - 1);
        for (int i = upper; i >= 0; i--) {
            int[] rem = prefixRem[i];
            int targetChar = target.charAt(i) - 'a';
            int chosen = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] > 0) { chosen = c; break; }
            }
            if (chosen == -1) continue; // no bigger char available here, try earlier i

            char[] H = new char[m];
            for (int k = 0; k < i; k++) H[k] = target.charAt(k);
            H[i] = (char) ('a' + chosen);

            int[] rem2 = rem.clone();
            rem2[chosen]--;

            int pos = i + 1;
            for (int c = 0; c < 26; c++)
                for (int k = 0; k < rem2[c]; k++) H[pos++] = (char) ('a' + c);

            String Hs = new String(H);
            String F = Hs + (n % 2 == 1 ? String.valueOf(midChar) : "")
                          + new StringBuilder(Hs).reverse();
            return F;
        }

        return "";
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna