class Solution {
    public String[] createGrid(int m, int n) {
        String[] res = new String[m];

        char[] first = new char[n];
        java.util.Arrays.fill(first, '.');
        res[0] = new String(first);

        for (int i = 1; i < m; i++) {
            char[] row = new char[n];
            java.util.Arrays.fill(row, '#');
            row[n - 1] = '.';
            res[i] = new String(row);
        }
        return res;
    }
}