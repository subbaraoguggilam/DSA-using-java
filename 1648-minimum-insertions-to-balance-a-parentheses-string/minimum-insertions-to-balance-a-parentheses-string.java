class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;   // unmatched '(' currently waiting for '))'
        int i = 0;
        int n = s.length();

        while (i < n) {
            if (s.charAt(i) == '(') {
                open++;
                i++;
            } else {
                // We have a ')'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    i += 2;               // consecutive "))"
                } else {
                    insertions++;         // add a missing ')' to form "))"
                    i++;
                }

                if (open > 0) {
                    open--;               // matched with a pending '('
                } else {
                    insertions++;         // no '(' available, insert one
                }
            }
        }

        // Each leftover '(' needs two ')' inserted
        insertions += open * 2;
        return insertions;
    }
}