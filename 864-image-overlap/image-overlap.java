class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> pts1 = new ArrayList<>();
        List<int[]> pts2 = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) pts1.add(new int[]{i, j});
                if (img2[i][j] == 1) pts2.add(new int[]{i, j});
            }
        }
        
        if (pts1.isEmpty() || pts2.isEmpty()) return 0;
        
        Map<Integer, Integer> count = new HashMap<>();
        int maxOverlap = 0;
        
        for (int[] p1 : pts1) {
            for (int[] p2 : pts2) {
                int dx = p1[0] - p2[0];
                int dy = p1[1] - p2[1];
                int key = dx * 200 + dy; // encode offset uniquely (n <= 100, so dx,dy in [-99,99])
                int c = count.getOrDefault(key, 0) + 1;
                count.put(key, c);
                maxOverlap = Math.max(maxOverlap, c);
            }
        }
        
        return maxOverlap;
    }
}