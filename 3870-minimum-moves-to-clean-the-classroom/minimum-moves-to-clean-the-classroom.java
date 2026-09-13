class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();
        
        int sr = -1, sc = -1;
        List<int[]> litterPositions = new ArrayList<>();
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) Arrays.fill(row, -1);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i][j];
                if (c == 'S') {
                    sr = i; sc = j;
                } else if (c == 'L') {
                    litterIndex[i][j] = litterPositions.size();
                    litterPositions.add(new int[]{i, j});
                }
            }
        }
        
        int numL = litterPositions.size();
        if (numL == 0) return 0;
        
        int fullMask = (1 << numL) - 1;
        int maskCount = 1 << numL;
        
        int cellCount = (energy + 1) * maskCount;
        long totalLong = (long) m * n * cellCount;
        int total = (int) totalLong;
        boolean[] visited = new boolean[total];
        
        int startState = (sr * n + sc) * cellCount + energy * maskCount + 0;
        visited[startState] = true;
        
        int[] currentFrontier = new int[]{startState};
        int moves = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (currentFrontier.length > 0) {
            int[] nextFrontier = new int[currentFrontier.length * 4 + 16];
            int nextSize = 0;
            
            for (int state : currentFrontier) {
                int rc = state / cellCount;
                int rem = state % cellCount;
                int e = rem / maskCount;
                int mask = rem % maskCount;
                int r = rc / n;
                int c = rc % n;
                
                if (e == 0) continue;
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = grid[nr][nc];
                    if (cell == 'X') continue;
                    
                    int newE = e - 1;
                    if (cell == 'R') newE = energy;
                    
                    int newMask = mask;
                    if (cell == 'L') {
                        int idx = litterIndex[nr][nc];
                        if (idx >= 0 && (mask & (1 << idx)) == 0) {
                            newMask = mask | (1 << idx);
                        }
                    }
                    
                    int newState = (nr * n + nc) * cellCount + newE * maskCount + newMask;
                    if (!visited[newState]) {
                        visited[newState] = true;
                        if (newMask == fullMask) {
                            return moves + 1;
                        }
                        if (nextSize == nextFrontier.length) {
                            nextFrontier = Arrays.copyOf(nextFrontier, nextFrontier.length * 2);
                        }
                        nextFrontier[nextSize++] = newState;
                    }
                }
            }
            
            currentFrontier = Arrays.copyOf(nextFrontier, nextSize);
            moves++;
        }
        
        return -1;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna