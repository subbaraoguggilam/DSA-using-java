class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        // For each character's first occurrence, try to build the minimal valid interval
        // that contains all occurrences of every character it touches.
        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            if (first[ch] != i) continue; // only start at a character's first occurrence

            int start = i;
            int end = last[ch];
            int j = start;
            boolean valid = true;

            while (j <= end) {
                int c = s.charAt(j) - 'a';
                if (first[c] < start) {
                    // this char's earlier occurrence can't be included -> invalid interval
                    valid = false;
                    break;
                }
                end = Math.max(end, last[c]);
                j++;
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // At most 26 intervals (one per distinct starting character).
        // Sort by end index ascending to greedily pick maximum count with minimum total length.
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> result = new ArrayList<>();
        int lastEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }

        return result;
    }
}